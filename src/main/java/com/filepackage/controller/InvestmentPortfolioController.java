package com.filepackage.controller;

import com.filepackage.dto.InvestmentPortfolioDto;
import com.filepackage.service.impl.InvestmentPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investment-portfolio")
public class InvestmentPortfolioController {

    @Autowired
    private InvestmentPortfolioService investmentPortfolioService;

    @GetMapping
    public ResponseEntity<List<InvestmentPortfolioDto>> getAllInvestments() {
        List<InvestmentPortfolioDto> investments = investmentPortfolioService.getAll();
        return ResponseEntity.ok(investments);
    }

    @PostMapping
    public ResponseEntity<InvestmentPortfolioDto> addInvestment(@RequestBody InvestmentPortfolioDto investmentPortfolioDto) {
        InvestmentPortfolioDto savedInvestment = investmentPortfolioService.createInvestment(investmentPortfolioDto);
        return new ResponseEntity<>(savedInvestment, HttpStatus.CREATED);
    }

   
    @GetMapping("{id}")
    public ResponseEntity<InvestmentPortfolioDto> getInvestmentById(@PathVariable("id") Long portfolioId) {
        InvestmentPortfolioDto investmentPortfolioDto = investmentPortfolioService.getById(portfolioId);
        return ResponseEntity.ok(investmentPortfolioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestment(@PathVariable("id") Long portfolioId) {
        investmentPortfolioService.delete(portfolioId);
        return ResponseEntity.ok("Investment portfolio deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<InvestmentPortfolioDto> updateInvestment(@PathVariable("id") Long portfolioId, @RequestBody InvestmentPortfolioDto updatedInvestment) {
        InvestmentPortfolioDto investmentPortfolioDto = investmentPortfolioService.update(portfolioId, updatedInvestment);
        return ResponseEntity.ok(investmentPortfolioDto);
    }
}
