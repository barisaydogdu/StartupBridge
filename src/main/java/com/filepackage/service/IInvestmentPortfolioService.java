package com.filepackage.service;

import com.filepackage.dto.InvestmentPortfolioDto;

public interface IInvestmentPortfolioService<InvestmentPortfolioDto, Long> extends IBaseService<InvestmentPortfolioDto, Long> {
    InvestmentPortfolioDto createInvestmentPortfolio(InvestmentPortfolioDto investmentPortfolioDto);
}
