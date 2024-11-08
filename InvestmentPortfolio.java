import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "investment_portfolio", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investor_id", referencedColumnName = "investor_id", nullable = false)
    private Investor investor;

    @Column(name = "invested_company_name")
    private String investedCompanyName;

    @Column(name = "investment_date")
    private LocalDate investmentDate;

    @Column(name = "description")
    private String description;

    @PrePersist
    protected void onCreate() {
        this.investmentDate = LocalDate.now();  
    }
}
