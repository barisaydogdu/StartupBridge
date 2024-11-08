package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.InvestmentPortfolioDto;
import com.filepackage.entity.InvestmentPortfolio;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IInvestmentPortfolioRepository;
import com.filepackage.service.IInvestmentPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentPortfolioService implements IInvestmentPortfolioService<InvestmentPortfolioDto, Long> {

    @Autowired
    private AutoMapper autoMapper;

    private final IInvestmentPortfolioRepository investmentPortfolioRepository;

    @Autowired
    public InvestmentPortfolioService(IInvestmentPortfolioRepository investmentPortfolioRepository) {
        this.investmentPortfolioRepository = investmentPortfolioRepository;
    }

    @Override
    public InvestmentPortfolioDto getById(Long portfolioId) {
        InvestmentPortfolio portfolio = investmentPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio does not exist with given id: " + portfolioId));
        return autoMapper.convertToDto(portfolio, InvestmentPortfolioDto.class);
    }

    @Override
    public List<InvestmentPortfolioDto> getAll() {
        List<InvestmentPortfolio> portfolios = investmentPortfolioRepository.findAll();
        return portfolios.stream()
                .map(portfolio -> autoMapper.convertToDto(portfolio, InvestmentPortfolioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long portfolioId) {
        InvestmentPortfolio portfolio = investmentPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio does not exist with given id: " + portfolioId));
        investmentPortfolioRepository.deleteById(portfolioId);
    }

    @Override
    public InvestmentPortfolioDto update(Long portfolioId, InvestmentPortfolioDto updatedPortfolio) {
        InvestmentPortfolio portfolio = investmentPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio does not exist with given id: " + portfolioId));

        portfolio.setInvestor_id(updatedPortfolio.getInvestor_id());
        portfolio.setInvested_company_name(updatedPortfolio.getInvested_company_name());
        portfolio.setInvestment_date(updatedPortfolio.getInvestment_date());
        portfolio.setDescription(updatedPortfolio.getDescription());

        InvestmentPortfolio updatedEntity = investmentPortfolioRepository.save(portfolio);
        return autoMapper.convertToDto(updatedEntity, InvestmentPortfolioDto.class);
    }

    @Override
    public InvestmentPortfolioDto createPortfolio(InvestmentPortfolioDto portfolioDto) {
        InvestmentPortfolio portfolio = autoMapper.convertToEntity(portfolioDto, InvestmentPortfolio.class);
        InvestmentPortfolio savedPortfolio = investmentPortfolioRepository.save(portfolio);
        return autoMapper.convertToDto(savedPortfolio, InvestmentPortfolioDto.class);
    }
}
