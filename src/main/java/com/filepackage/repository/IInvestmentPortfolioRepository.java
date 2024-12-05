package com.filepackage.repository;

import com.filepackage.entity.InvestmentPortfolio;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvestmentPortfolioRepository extends IBaseRepository<InvestmentPortfolio> {
    // Özel sorgular gerekiyorsa burada tanımlanabilir
}
