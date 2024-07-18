package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @Min(5)
    @Max(5000)
    private int square;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private IntermediateLocation intermediateLocation;

    @OneToMany(mappedBy = "startLocation")
    @JsonIgnore
    private Set<Mission> startMissions;

    @OneToMany(mappedBy = "endLocation")
    @JsonIgnore
    private Set<Mission> endMissions;


}

