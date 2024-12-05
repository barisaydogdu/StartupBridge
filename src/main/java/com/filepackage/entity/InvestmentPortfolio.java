package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "investment_portfolio", schema = "public")
public class InvestmentPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @Column(name = "investor_id", nullable = false)
    private Integer investorId;

    @Column(name = "invested_company_name", length = 100)
    private String investedCompanyName;

    @Column(name = "investment_date")
    private LocalDate investmentDate;

    @Column(name = "description")
    private String description;
}
