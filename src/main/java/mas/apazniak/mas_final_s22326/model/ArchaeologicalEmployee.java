package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.enumm.MissionState;
import mas.apazniak.mas_final_s22326.model.interfaces.IArchaeologist;
import mas.apazniak.mas_final_s22326.model.interfaces.IConservator;
import mas.apazniak.mas_final_s22326.model.interfaces.IGuard;
import mas.apazniak.mas_final_s22326.model.interfaces.ISurveyor;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ArchaeologicalEmployee extends Employee implements IArchaeologist, IConservator, IGuard, ISurveyor {

    @OneToMany(mappedBy = "archaeologicalEmployee",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<License> licenses;

    @OneToMany(mappedBy = "archaeologicalEmployee",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Permit> permits;

    @ManyToOne
    @JoinColumn(name = "field_team_id")
    @JsonIgnore
    private FieldTeam fieldTeam;

    @ManyToOne
    @JoinColumn(name = "report_id")
    @JsonIgnore
    private Report report;

    @ElementCollection(targetClass = ArchaeologicalEmployeeRole.class)
    @CollectionTable(name = "field_team_roles", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<ArchaeologicalEmployeeRole> fieldTeamRole = EnumSet.noneOf(ArchaeologicalEmployeeRole.class);

    @Column(nullable = true)
    @ElementCollection
    private Set<String> executiveNotes = null;

    @Column(nullable = true)
    @ElementCollection
    private Set<String> preservationTechniques = null;

    @Column(nullable = true)
    @ElementCollection
    private Set<String> surveyEquipment = null;

    @Column(nullable = true)
    @ElementCollection
    private Set<String> securityNotes = null;

    public ArchaeologicalEmployee(String firstName, String lastName, String nationality, LocalDate dob, Set<String> responsibilities, FieldTeam fieldTeam, EnumSet<ArchaeologicalEmployeeRole> fieldTeamRole) {
        super(firstName, lastName, nationality, dob, responsibilities);
        this.fieldTeam = fieldTeam;

        setFieldTeamRole(fieldTeamRole);

        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.GUARD)) {
            setSecurityNotes(securityNotes);
        }
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.SURVEYOR)) {
            setSurveyEquipment(surveyEquipment);
        }
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.CONSERVATOR)) {
            setPreservationTechniques(preservationTechniques);
        }
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.ARCHAEOLOGIST)) {
            setExecutiveNotes(executiveNotes);
        }
    }

    public Mission viewCurrentMission() {
        return getCurrentMission();
    }

    public void changeRoleTo(ArchaeologicalEmployeeRole role) {
        this.fieldTeamRole = EnumSet.of(role);
    }

    public void addGuardRole(Set<String> securityNotes) {
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.GUARD))
            throw new IllegalArgumentException("EMPLOYEE ALREADY HAS GUARD ROLE");

        fieldTeamRole.add(ArchaeologicalEmployeeRole.GUARD);
        setSecurityNotes(securityNotes);
    }

    public void removeGuardRole() {
        if (!fieldTeamRole.contains(ArchaeologicalEmployeeRole.GUARD))
            throw new NullPointerException("EMPLOYEE DOESN'T HAVE GUARD ROLE");

        setSecurityNotes(null);
        fieldTeamRole.remove(ArchaeologicalEmployeeRole.GUARD);
    }

    public void addSurveyorRole(Set<String> surveyEquipment) {
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.SURVEYOR))
            throw new IllegalArgumentException("EMPLOYEE ALREADY HAS SURVEYOR ROLE");

        fieldTeamRole.add(ArchaeologicalEmployeeRole.SURVEYOR);
        setSurveyEquipment(surveyEquipment);
    }

    public void removeSurveyorRole() {
        if (!fieldTeamRole.contains(ArchaeologicalEmployeeRole.SURVEYOR))
            throw new NullPointerException("EMPLOYEE DOESN'T HAVE SURVEYOR ROLE");

        setSurveyEquipment(null);
        fieldTeamRole.remove(ArchaeologicalEmployeeRole.SURVEYOR);
    }

    public void addConservatorRole(Set<String> preservationTechniques) {
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.CONSERVATOR))
            throw new IllegalArgumentException("EMPLOYEE ALREADY HAS CONSERVATOR ROLE");

        fieldTeamRole.add(ArchaeologicalEmployeeRole.CONSERVATOR);
        setPreservationTechniques(preservationTechniques);
    }

    public void removeConservatorRole() {
        if (!fieldTeamRole.contains(ArchaeologicalEmployeeRole.CONSERVATOR))
            throw new NullPointerException("EMPLOYEE DOESN'T HAVE CONSERVATOR ROLE");

        setPreservationTechniques(null);
        fieldTeamRole.remove(ArchaeologicalEmployeeRole.CONSERVATOR);
    }

    public void addArchaeologistRole(Set<String> executiveNotes) {
        if (fieldTeamRole.contains(ArchaeologicalEmployeeRole.ARCHAEOLOGIST))
            throw new IllegalArgumentException("EMPLOYEE ALREADY HAS ARCHAEOLOGIST ROLE");

        fieldTeamRole.add(ArchaeologicalEmployeeRole.ARCHAEOLOGIST);
        setExecutiveNotes(executiveNotes);
    }

    public void removeArchaeologistRole() {
        if (!fieldTeamRole.contains(ArchaeologicalEmployeeRole.ARCHAEOLOGIST))
            throw new NullPointerException("EMPLOYEE DOESN'T HAVE ARCHAEOLOGIST ROLE");

        setExecutiveNotes(null);
        fieldTeamRole.remove(ArchaeologicalEmployeeRole.ARCHAEOLOGIST);
    }
    // Methods for IArchaeologist
    @Override
    public void changeArchaeologicalEmployeeRole(ArchaeologicalEmployee emp, ArchaeologicalEmployeeRole role) {
        if (!emp.getFieldTeamRole().contains(role)) {
            emp.getFieldTeamRole().clear();
            emp.getFieldTeamRole().add(role);
            System.out.println("Changed role of employee " + emp.getFirstName() + " " + emp.getLastName() + " to: " + role);
        } else {
            System.out.println("Employee " + emp.getFirstName() + " " + emp.getLastName() + " already has the role: " + role);
        }
    }

    @Override
    public void finishCurrentMission() {
        Mission currentMission = getCurrentMission();
        if (currentMission != null) {
            currentMission.setState(MissionState.ARCHIVED);
            System.out.println("Mission " + currentMission.getId() + " is finished and set to ARCHIVED.");
        } else {
            System.out.println("No current mission to finish.");
        }
    }

    @Override
    public void analyzeFindings() {
        System.out.println("Analyzing findings.");
    }

    @Override
    public Set<String> getExecutiveOperations() {
        return executiveNotes;
    }

    @Override
    public void setExecutiveOperations(List<String> executiveNotes) {
        this.executiveNotes = new HashSet<>(executiveNotes);
        System.out.println("Setting executive operations.");
    }

    // Methods for IConservator
    @Override
    public void preserveArtifacts(String preservationTechnique) {
        if (preservationTechniques != null) {
            preservationTechniques.add(preservationTechnique);
            System.out.println("Preserving artifacts using: " + preservationTechnique);
        }
    }

    @Override
    public void updateMissionState(MissionState state) {
        System.out.println("Updating mission state to: " + state);
    }

    // Methods for IGuard
    @Override
    public void ensureSecurity(String securityNote) {
        if (securityNotes != null) {
            securityNotes.add(securityNote);
            System.out.println("Security note added: " + securityNote);
        }
    }

    @Override
    public void planSecurity() {
        System.out.println("Planning security for the mission.");
    }

    // Methods for ISurveyor
    @Override
    public void conductSurveys(String surveyEquipment) {
        if (this.surveyEquipment != null) {
            this.surveyEquipment.add(surveyEquipment);
            System.out.println("Conducting surveys using: " + surveyEquipment);
        }
    }

    @Override
    public void analyzeSurveys() {
        System.out.println("Analyzing survey data.");
    }





}
