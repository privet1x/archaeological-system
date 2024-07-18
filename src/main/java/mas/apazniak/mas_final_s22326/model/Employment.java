package mas.apazniak.mas_final_s22326.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Min(500)
    private double monthlySalary;

    @Column(nullable = true)
    private Double penalty;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private ResearchCompany researchCompany;

    private LocalDate startDate;
    private LocalDate endDate;

    @Transient
    public double getSalary() {
        if (penalty != null) {
            return monthlySalary - penalty;
        } else {
            return monthlySalary;
        }
    }
}
