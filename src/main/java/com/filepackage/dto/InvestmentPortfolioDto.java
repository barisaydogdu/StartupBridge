package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentPortfolioDto {

    private Long portfolioId;
    private Integer investorId;
    private String investedCompanyName;
    private LocalDate investmentDate;
    private String description;
}
