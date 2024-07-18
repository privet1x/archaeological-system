package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class IntermediateLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @OneToOne
    @JoinColumn(name = "location_id", unique = true)
    private Location location;

    @NotNull
    @Min(0)
    @Max(20)
    @Column(name = "visit_order")
    private int visitOrder;
}

