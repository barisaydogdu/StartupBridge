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
public class InvestmentPortfolioService implements IInvestmentPortfolioService {

    private final IInvestmentPortfolioRepository investmentPortfolioRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public InvestmentPortfolioService(IInvestmentPortfolioRepository investmentPortfolioRepository, AutoMapper autoMapper) {
        this.investmentPortfolioRepository = investmentPortfolioRepository;
        this.autoMapper = autoMapper;
    }

    @Override
    public List<InvestmentPortfolioDto> getAll() {
        List<InvestmentPortfolio> portfolios = investmentPortfolioRepository.findAll();
        return portfolios.stream()
                .map(portfolio -> autoMapper.convertToDto(portfolio, InvestmentPortfolioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public InvestmentPortfolioDto createInvestmentPortfolio(InvestmentPortfolioDto investmentPortfolioDto) {
        InvestmentPortfolio portfolio = autoMapper.convertToEntity(investmentPortfolioDto, InvestmentPortfolio.class);
        InvestmentPortfolio savedPortfolio = investmentPortfolioRepository.save(portfolio);
        return autoMapper.convertToDto(savedPortfolio, InvestmentPortfolioDto.class);
    }

    @Override
    public InvestmentPortfolioDto getById(Long portfolioId) {
        InvestmentPortfolio portfolio = investmentPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Investment Portfolio not found with ID: " + portfolioId));
        return autoMapper.convertToDto(portfolio, InvestmentPortfolioDto.class);
    }

    @Override
    public void delete(Long portfolioId) {
        if (!investmentPortfolioRepository.existsById(portfolioId)) {
            throw new ResourceNotFoundException("Investment Portfolio not found with ID: " + portfolioId);
        }
        investmentPortfolioRepository.deleteById(portfolioId);
    }

    @Override
    public InvestmentPortfolioDto update(Long portfolioId, InvestmentPortfolioDto updatedPortfolioDto) {
        InvestmentPortfolio portfolio = investmentPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Investment Portfolio not found with ID: " + portfolioId));

        portfolio.setInvestorId(updatedPortfolioDto.getInvestorId());
        portfolio.setInvestedCompanyName(updatedPortfolioDto.getInvestedCompanyName());
        portfolio.setInvestmentDate(updatedPortfolioDto.getInvestmentDate());
        portfolio.setDescription(updatedPortfolioDto.getDescription());

        InvestmentPortfolio savedPortfolio = investmentPortfolioRepository.save(portfolio);
        return autoMapper.convertToDto(savedPortfolio, InvestmentPortfolioDto.class);
    }
}
