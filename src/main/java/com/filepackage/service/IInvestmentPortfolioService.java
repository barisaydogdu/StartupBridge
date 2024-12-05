package com.filepackage.service;

import com.filepackage.dto.InvestmentPortfolioDto;

import java.util.List;

public interface IInvestmentPortfolioService {
    List<InvestmentPortfolioDto> getAll();

    InvestmentPortfolioDto createInvestmentPortfolio(InvestmentPortfolioDto investmentPortfolioDto);

    InvestmentPortfolioDto getById(Long portfolioId);

    void delete(Long portfolioId);

    InvestmentPortfolioDto update(Long portfolioId, InvestmentPortfolioDto updatedPortfolioDto);
}
