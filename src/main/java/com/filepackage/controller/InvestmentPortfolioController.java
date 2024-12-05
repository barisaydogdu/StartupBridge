package com.filepackage.controller;

import com.filepackage.dto.InvestmentPortfolioDto;
import com.filepackage.service.impl.InvestmentPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investment-portfolios")
public class InvestmentPortfolioController {

    private final InvestmentPortfolioService investmentPortfolioService;

    @Autowired
    public InvestmentPortfolioController(InvestmentPortfolioService investmentPortfolioService) {
        this.investmentPortfolioService = investmentPortfolioService;
    }

    @GetMapping
    public ResponseEntity<List<InvestmentPortfolioDto>> getAllPortfolios() {
        List<InvestmentPortfolioDto> portfolios = investmentPortfolioService.getAll();
        return ResponseEntity.ok(portfolios);
    }

    @PostMapping
    public ResponseEntity<InvestmentPortfolioDto> addPortfolio(@RequestBody InvestmentPortfolioDto portfolioDto) {
        InvestmentPortfolioDto savedPortfolio = investmentPortfolioService.createInvestmentPortfolio(portfolioDto);
        return new ResponseEntity<>(savedPortfolio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentPortfolioDto> getPortfolioById(@PathVariable("id") Long portfolioId) {
        InvestmentPortfolioDto portfolioDto = investmentPortfolioService.getById(portfolioId);
        return ResponseEntity.ok(portfolioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable("id") Long portfolioId) {
        investmentPortfolioService.delete(portfolioId);
        return ResponseEntity.ok("Investment portfolio deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentPortfolioDto> updatePortfolio(
            @PathVariable("id") Long portfolioId,
            @RequestBody InvestmentPortfolioDto updatedPortfolioDto) {
        InvestmentPortfolioDto updatedPortfolio = investmentPortfolioService.update(portfolioId, updatedPortfolioDto);
        return ResponseEntity.ok(updatedPortfolio);
    }
}
