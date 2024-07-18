package mas.apazniak.mas_final_s22326.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Owner extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<ResearchCompany> companies;

    public Owner(String firstName, String lastName, String nationality, LocalDate dob) {
        super(firstName, lastName, nationality, dob);
    }

    public ResearchCompany createCompany(String name, Address address, String contactInfo, String specializationArea, int empSize) {
        ResearchCompany company = new ResearchCompany();
        company.setName(name);
        company.setAddress(address);
        company.setContactInfo(contactInfo);
        company.setSpecializationArea(specializationArea);
        company.setEmpSize(empSize);
        company.setOwner(this);
        companies.add(company);
        return company;
    }

    public ExplorationVehicle createExplorationVehicle(String name, String type, LocalDate productionYear, int maxSpeed, ResearchCompany company) {
        ExplorationVehicle vehicle = new ExplorationVehicle();
        vehicle.setName(name);
        vehicle.setType(type);
        vehicle.setProductionYear(productionYear);
        vehicle.setMaxSpeed(maxSpeed);
        vehicle.setResearchCompany(company);
        return vehicle;
    }

    public Employment createEmployment(Employee employee, ResearchCompany company, LocalDate startDate, LocalDate endDate) {
        Employment employment = new Employment();
        employment.setEmployee(employee);
        employment.setResearchCompany(company);
        employment.setStartDate(startDate);
        employment.setEndDate(endDate);
        return employment;
    }
}

