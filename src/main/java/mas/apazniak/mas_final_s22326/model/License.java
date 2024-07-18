package mas.apazniak.mas_final_s22326.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String LicenseNumber;
    @FutureOrPresent
    private LocalDate expirationDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "employee_id",nullable = true, updatable = false)
    private ArchaeologicalEmployee archaeologicalEmployee;


}
