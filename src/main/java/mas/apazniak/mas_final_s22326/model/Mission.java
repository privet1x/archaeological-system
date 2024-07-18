package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import mas.apazniak.mas_final_s22326.enumm.MissionState;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Type is mandatory.")
    @Size(min = 5, max = 50)
    private String type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "start_location_id")
    private Location startLocation;

    @OneToMany(mappedBy = "mission")
    @OrderBy("visitOrder")
    @JsonIgnore
    private List<IntermediateLocation> intermediateLocations = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "end_location_id")
    private Location endLocation;

    @ManyToOne
    @JoinColumn(name = "field_team_id", updatable = false)
    @NotNull
    @JsonIgnore
    private FieldTeam fieldTeam;

    @ManyToOne
    @JoinColumn(name = "exploration_vehicle_id", updatable = false)
    @NotNull
    @JsonIgnore
    private ExplorationVehicle explorationVehicle;

    @Enumerated(EnumType.STRING)
    private MissionState state = MissionState.SCHEDULED;

    @NotNull(message = "Start date is mandatory.")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory.")
    private LocalDate endDate;


    public Mission(String type, Location startLocation, Location endLocation, MissionState state) {
        this.type = type;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public void updateStateBasedOnStartDate() {
        LocalDate today = LocalDate.now();
        if (this.startDate.isBefore(today))
            this.state = MissionState.IN_PROGRESS;
        else
            this.state = MissionState.SCHEDULED;
    }

    public String getType() {
        return type;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public FieldTeam getFieldTeam() {
        return fieldTeam;
    }

    public ExplorationVehicle getExplorationVehicle() {
        return explorationVehicle;
    }

    public MissionState getState() {
        return state;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<IntermediateLocation> getIntermediateLocations() {
        return intermediateLocations;
    }
}
