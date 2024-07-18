package mas.apazniak.mas_final_s22326;



import jakarta.transaction.Transactional;
import mas.apazniak.mas_final_s22326.enumm.ArchaeologicalEmployeeRole;
import mas.apazniak.mas_final_s22326.enumm.MissionState;
import mas.apazniak.mas_final_s22326.model.*;
import mas.apazniak.mas_final_s22326.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class Main {

    private final EmployeeRepository employeeRepository;

    private final ResearchCompanyRepository researchCompanyRepository;
    private final FieldTeamRepository fieldTeamRepository;
    private final ExplorationVehicleRepository explorationVehicleRepository;
    private final ArchaeologicalEmployeeRepository archaeologicalEmployeeRepository;

    private final MissionRepository missionRepository;

    private final EmploymentRepository employmentRepository;
    private final LocationRepository locationRepository;
    private final CompartmentRepository compartmentRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public Main(EmployeeRepository employeeRepository, ResearchCompanyRepository researchCompanyRepository, FieldTeamRepository fieldTeamRepository, ArchaeologicalEmployeeRepository archaeologicalEmployeeRepository, ExplorationVehicleRepository explorationVehicleRepository, MissionRepository missionRepository, EmploymentRepository employmentRepository, LocationRepository locationRepository, CompartmentRepository compartmentRepository, OwnerRepository ownerRepository) {
        this.employeeRepository = employeeRepository;
        this.researchCompanyRepository = researchCompanyRepository;
        this.fieldTeamRepository = fieldTeamRepository;
        this.archaeologicalEmployeeRepository = archaeologicalEmployeeRepository;
        this.explorationVehicleRepository = explorationVehicleRepository;
        this.missionRepository = missionRepository;
        this.employmentRepository = employmentRepository;
        this.locationRepository = locationRepository;
        this.compartmentRepository = compartmentRepository;
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public void createSampleData() {

        FieldTeam fieldTeam1 = FieldTeam.builder()
                .name("Quantum Logistics Field Team")
                .build();

        FieldTeam fieldTeam2 = FieldTeam.builder()
                .name("Deep Blue Field Team")
                .build();

        FieldTeam fieldTeam3 = FieldTeam.builder()
                .name("Nebula Squad Field Team")
                .build();

        FieldTeam fieldTeam4 = FieldTeam.builder()
                .name("Starlight Field Team")
                .build();

        FieldTeam fieldTeam5 = FieldTeam.builder()
                .name("Galactic Field Team")
                .build();

        FieldTeam fieldTeam6 = FieldTeam.builder()
                .name("Celestial Field Team")
                .build();


        //Creating addresses
        Address ad1 = new Address("New York", "USA", "Amsterdam Avenue MC", 5523);
        Address ad2 = new Address("Ashfield", "USA", "Conwoy Street", 498);
        Address ad3 = new Address("Lodz", "Poland", "Bonifacego Street", 45);
        Address ad4 = new Address("Wroclaw", "Poland", "Tronczyka Street", 7);
        Address ad5 = new Address("Malaga", "Spain", "Champs-Minuasto", 7);
        Address ad  = new Address("Barcelona", "Spain", "Indawo Street", 77);

        Address ad6 = new Address("Washington", "USA", "Crua avenue", 22);
        Address ad7 = new Address("Warsaw", "Poland", "Nowogrodzka st", 55);
        Address ad8 = new Address("New York", "USA", "Franklin st", 33);
        Address ad9 = new Address("Madrid", "Spain", "Fiance st", 44);



        Location location1 = Location.builder()
                .address(ad2)
                .square(1200)
                .build();

        Location location2 = Location.builder()
                .address(ad3)
                .square(2000)
                .build();

        Location location3 = Location.builder()
                .address(ad4)
                .square(4000)
                .build();

        Location location4 = Location.builder()
                .address(ad5)
                .square(1000)
                .build();

        Location location5 = Location.builder()
                .address(ad1)
                .square(1000)
                .build();

        Location location6 = Location.builder()
                .address(ad)
                .square(1000)
                .build();

        List<Location> locations = Arrays.asList(location1, location2, location3, location4, location5, location6);


        Owner owner1 = Owner.builder()
                .firstName("Alice")
                .lastName("Smith")
                .nationality("American")
                .dob(LocalDate.of(1970, 1, 1))
                .build();

        Owner owner2 = Owner.builder()
                .firstName("Bob")
                .lastName("Johnson")
                .nationality("British")
                .dob(LocalDate.of(1980, 2, 2))
                .build();

        Owner owner3 = Owner.builder()
                .firstName("Charlie")
                .lastName("Brown")
                .nationality("Canadian")
                .dob(LocalDate.of(1990, 3, 3))
                .build();

        Owner owner4 = Owner.builder()
                .firstName("Diana")
                .lastName("Jones")
                .nationality("Australian")
                .dob(LocalDate.of(2000, 4, 4))
                .build();

        List<Owner> owners = Arrays.asList(owner1, owner2, owner3, owner4);

        //Creating company
        ResearchCompany old = ResearchCompany.builder()
                .name("Old Researchers Company")
                .specializationArea("Rehistoric Archaeology")
                .address(ad6)
                .contactInfo("+48555333222")
                .empSize(222)
                .owner(owner1)
                .build();

        ResearchCompany weare = ResearchCompany.builder()
                .name("We Are Researchers Company")
                .specializationArea("Historical Archaeology")
                .address(ad7)
                .contactInfo("+48909080801")
                .empSize(123)
                .owner(owner2)
                .build();

        ResearchCompany now = ResearchCompany.builder()
                .name("Now or Never Researchers Company")
                .specializationArea("All types Archaeology")
                .address(ad8)
                .contactInfo("+48123478133")
                .empSize(321)
                .owner(owner3)
                .build();

        ResearchCompany olives = ResearchCompany.builder()
                .name("Olives Researchers Company")
                .specializationArea("Environmental Archaeology")
                .address(ad9)
                .contactInfo("+4874930234")
                .empSize(200)
                .owner(owner4)
                .build();

        Compartment compartment1 = Compartment.builder()
                .supplierCompany("Supplier Super")
                .capacity(1000)
                .suitableFor(Set.of("Normal Items"))
                .build();

        Compartment compartment2 = Compartment.builder()
                .supplierCompany("Supplier Warsaw Part")
                .capacity(1500)
                .suitableFor(Set.of("Big Items", "Huge Items"))
                .build();

        Compartment compartment3 = Compartment.builder()
                .supplierCompany("Supplier Berlin Technologies")
                .capacity(1020)
                .suitableFor(Set.of("Normal Items", "Small Items"))
                .build();

        Compartment compartment4 = Compartment.builder()
                .supplierCompany("Supplier OMG")
                .capacity(1030)
                .suitableFor(Set.of("Small Items"))
                .build();

        Compartment compartment5 = Compartment.builder()
                .supplierCompany("Supplier E")
                .capacity(1400)
                .suitableFor(Set.of("Huge Items", "Big Items"))
                .build();

        Compartment compartment6 = Compartment.builder()
                .supplierCompany("Supplier We Are Suppliers")
                .capacity(1100)
                .suitableFor(Set.of("Small Items", "Normal Items"))
                .build();


        ExplorationVehicle explorationVehicle1 = ExplorationVehicle.builder()
                .name("Crazy Explorer")
                .type("Archaeological")
                .productionYear(LocalDate.of(2016, 9,30))
                .maxSpeed(52)
                .researchCompany(old)
                .build();

        ExplorationVehicle explorationVehicle2 = ExplorationVehicle.builder()
                .name("Aqua Explorer")
                .type("Marine")
                .productionYear(LocalDate.of(2018, 3, 15))
                .maxSpeed(75)
                .researchCompany(now)
                .build();

        ExplorationVehicle explorationVehicle3 = ExplorationVehicle.builder()
                .name("Sky Watcher")
                .type("Aerial")
                .productionYear(LocalDate.of(2019, 5, 10))
                .maxSpeed(45)
                .researchCompany(weare)
                .build();

        ExplorationVehicle explorationVehicle4 = ExplorationVehicle.builder()
                .name("Jungle Crawler")
                .type("Amphibious")
                .productionYear(LocalDate.of(2017, 8, 25))
                .maxSpeed(70)
                .researchCompany(olives)
                .build();

        ExplorationVehicle explorationVehicle5 = ExplorationVehicle.builder()
                .name("Mountain Mover")
                .type("Land")
                .productionYear(LocalDate.of(2016, 12, 5))
                .maxSpeed(100)
                .researchCompany(old)
                .build();

        ExplorationVehicle explorationVehicle6 = ExplorationVehicle.builder()
                .name("Cave Diver")
                .type("Subterranean")
                .productionYear(LocalDate.of(2020, 11, 30))
                .maxSpeed(50)
                .researchCompany(weare)
                .build();

        //Creating employees
        Employee employee1 = Employee.builder()
                .firstName("Doe")
                .lastName("John")
                .dob(LocalDate.of(1999,02,02))
                .responsibilities(Set.of("make a coffee"))
                .nationality("Polish")
                .build();

        ArchaeologicalEmployee arch_emp = ArchaeologicalEmployee.builder()
                .firstName("Doe")
                .lastName("John")
                .responsibilities(Set.of("Manage team", "Make significant decision"))
                .nationality("Belarusian")
                .dob(LocalDate.of(1999, 2, 2))
                .fieldTeam(fieldTeam1)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.ARCHAEOLOGIST, ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        ArchaeologicalEmployee arch_emp2 = ArchaeologicalEmployee.builder()
                .firstName("Anna")
                .lastName("Smith")
                .responsibilities(Set.of("Make a survey", "Analyze Survey"))
                .nationality("American")
                .dob(LocalDate.of(1985, 6, 15))
                .fieldTeam(fieldTeam1)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        ArchaeologicalEmployee arch_emp3 = ArchaeologicalEmployee.builder()
                .firstName("Robert")
                .lastName("Jones")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("British")
                .dob(LocalDate.of(1978, 4, 20))
                .fieldTeam(fieldTeam1)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.CONSERVATOR))
                .build();

        ArchaeologicalEmployee arch_emp4 = ArchaeologicalEmployee.builder()
                .firstName("Emily")
                .lastName("Williams")
                .responsibilities(Set.of("Make sure place is safe", "Check electricity"))
                .nationality("Canadian")
                .dob(LocalDate.of(1988, 12, 5))
                .fieldTeam(fieldTeam1)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.GUARD))
                .build();

        ArchaeologicalEmployee arch_emp5 = ArchaeologicalEmployee.builder()
                .firstName("Michael")
                .lastName("Brown")
                .responsibilities(Set.of("Manage team", "Make significant decision"))
                .nationality("Australian")
                .dob(LocalDate.of(1992, 7, 30))
                .fieldTeam(fieldTeam2)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.ARCHAEOLOGIST))
                .build();

        ArchaeologicalEmployee arch_emp6 = ArchaeologicalEmployee.builder()
                .firstName("Jessica")
                .lastName("Davis")
                .responsibilities(Set.of("Make a survey", "Analyze Survey"))
                .nationality("German")
                .dob(LocalDate.of(1990, 10, 10))
                .fieldTeam(fieldTeam2)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        ArchaeologicalEmployee arch_emp7 = ArchaeologicalEmployee.builder()
                .firstName("Thomas")
                .lastName("Miller")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("French")
                .dob(LocalDate.of(1983, 3, 25))
                .fieldTeam(fieldTeam2)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.CONSERVATOR, ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        ArchaeologicalEmployee arch_emp8 = ArchaeologicalEmployee.builder()
                .firstName("Sarah")
                .lastName("Wilson")
                .responsibilities(Set.of("Make sure place is safe", "Check electricity"))
                .nationality("Italian")
                .dob(LocalDate.of(1995, 8, 8))
                .fieldTeam(fieldTeam2)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.GUARD))
                .build();

        ArchaeologicalEmployee arch_emp9 = ArchaeologicalEmployee.builder()
                .firstName("Charles")
                .lastName("Moore")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("Spanish")
                .dob(LocalDate.of(1981, 1, 18))
                .fieldTeam(fieldTeam3)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.GUARD))
                .build();

        ArchaeologicalEmployee arch_emp10 = ArchaeologicalEmployee.builder()
                .firstName("Laura")
                .lastName("Taylor")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("Japanese")
                .dob(LocalDate.of(1989, 9, 12))
                .fieldTeam(fieldTeam3)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.CONSERVATOR))
                .build();

        ArchaeologicalEmployee arch_emp11 = ArchaeologicalEmployee.builder()
                .firstName("James")
                .lastName("Anderson")
                .responsibilities(Set.of("Make a survey", "Analyze Survey"))
                .nationality("Russian")
                .dob(LocalDate.of(1996, 11, 21))
                .fieldTeam(fieldTeam3)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        ArchaeologicalEmployee arch_emp12 = ArchaeologicalEmployee.builder()
                .firstName("Patricia")
                .lastName("Thomas")
                .responsibilities(Set.of("Manage team", "Make significant decision"))
                .nationality("Indian")
                .dob(LocalDate.of(1987, 5, 3))
                .fieldTeam(fieldTeam3)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.ARCHAEOLOGIST))
                .build();

        ArchaeologicalEmployee arch_emp13 = ArchaeologicalEmployee.builder()
                .firstName("Mark")
                .lastName("Jackson")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("Brazilian")
                .dob(LocalDate.of(1982, 6, 14))
                .fieldTeam(fieldTeam4)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.GUARD))
                .build();

        ArchaeologicalEmployee arch_emp14 = ArchaeologicalEmployee.builder()
                .firstName("Linda")
                .lastName("White")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("Chinese")
                .dob(LocalDate.of(1993, 2, 9))
                .fieldTeam(fieldTeam4)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.CONSERVATOR))
                .build();

        ArchaeologicalEmployee arch_emp15 = ArchaeologicalEmployee.builder()
                .firstName("David")
                .lastName("Harris")
                .responsibilities(Set.of("Make a survey", "Analyze Survey"))
                .nationality("South African")
                .dob(LocalDate.of(1979, 7, 23))
                .fieldTeam(fieldTeam4)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.SURVEYOR, ArchaeologicalEmployeeRole.CONSERVATOR))
                .build();

        ArchaeologicalEmployee arch_emp16 = ArchaeologicalEmployee.builder()
                .firstName("Karen")
                .lastName("Clark")
                .responsibilities(Set.of("Manage team", "Make significant decision"))
                .nationality("Mexican")
                .dob(LocalDate.of(1984, 4, 4))
                .fieldTeam(fieldTeam4)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.ARCHAEOLOGIST))
                .build();

        ArchaeologicalEmployee arch_emp17 = ArchaeologicalEmployee.builder()
                .firstName("George")
                .lastName("Lewis")
                .responsibilities(Set.of("Manage team", "Make significant decision"))
                .nationality("Turkish")
                .dob(LocalDate.of(1986, 12, 20))
                .fieldTeam(fieldTeam5)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.ARCHAEOLOGIST))
                .build();

        ArchaeologicalEmployee arch_emp18 = ArchaeologicalEmployee.builder()
                .firstName("Nancy")
                .lastName("Martinez")
                .responsibilities(Set.of("Make a survey", "Analyze Survey"))
                .nationality("Korean")
                .dob(LocalDate.of(1991, 8, 1))
                .fieldTeam(fieldTeam5)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        ArchaeologicalEmployee arch_emp19 = ArchaeologicalEmployee.builder()
                .firstName("Daniel")
                .lastName("Robinson")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("Egyptian")
                .dob(LocalDate.of(1980, 3, 29))
                .fieldTeam(fieldTeam5)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.CONSERVATOR))
                .build();

        ArchaeologicalEmployee arch_emp20 = ArchaeologicalEmployee.builder()
                .firstName("Betty")
                .lastName("Walker")
                .responsibilities(Set.of("Make a conservation", "Analyze finding"))
                .nationality("Argentinian")
                .dob(LocalDate.of(1985, 11, 11))
                .fieldTeam(fieldTeam5)
                .fieldTeamRole(EnumSet.of(ArchaeologicalEmployeeRole.GUARD, ArchaeologicalEmployeeRole.SURVEYOR))
                .build();

        Employment employment1 = Employment.builder()
                .employee(arch_emp)
                .researchCompany(olives)
                .monthlySalary(1500)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment2 = Employment.builder()
                .employee(arch_emp2)
                .researchCompany(olives)
                .monthlySalary(2000)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment3 = Employment.builder()
                .employee(arch_emp3)
                .researchCompany(olives)
                .monthlySalary(3000)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment4 = Employment.builder()
                .employee(arch_emp4)
                .researchCompany(old)
                .monthlySalary(4000)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment5 = Employment.builder()
                .employee(arch_emp5)
                .researchCompany(old)
                .monthlySalary(5300)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment6 = Employment.builder()
                .employee(arch_emp6)
                .researchCompany(old)
                .monthlySalary(3249)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment7 = Employment.builder()
                .employee(arch_emp7)
                .researchCompany(weare)
                .monthlySalary(4390)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment8 = Employment.builder()
                .employee(arch_emp8)
                .researchCompany(weare)
                .monthlySalary(2322)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();


        Employment employment9 = Employment.builder()
                .employee(arch_emp9)
                .researchCompany(weare)
                .monthlySalary(23442)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment10 = Employment.builder()
                .employee(arch_emp10)
                .researchCompany(olives)
                .monthlySalary(2522)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment11 = Employment.builder()
                .employee(arch_emp11)
                .researchCompany(olives)
                .monthlySalary(2522)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        Employment employment12 = Employment.builder()
                .employee(arch_emp12)
                .researchCompany(olives)
                .monthlySalary(2522)
                .startDate(LocalDate.of(2018, 12, 10))
                .endDate(LocalDate.of(2025, 11, 11))
                .build();

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);

        List<ArchaeologicalEmployee> archaeologicalEmployees = new ArrayList<>();
        archaeologicalEmployees.add(arch_emp);
        archaeologicalEmployees.add(arch_emp2);
        archaeologicalEmployees.add(arch_emp3);
        archaeologicalEmployees.add(arch_emp4);
        archaeologicalEmployees.add(arch_emp5);
        archaeologicalEmployees.add(arch_emp6);
        archaeologicalEmployees.add(arch_emp7);
        archaeologicalEmployees.add(arch_emp8);
        archaeologicalEmployees.add(arch_emp9);
        archaeologicalEmployees.add(arch_emp10);
        archaeologicalEmployees.add(arch_emp11);
        archaeologicalEmployees.add(arch_emp12);
        archaeologicalEmployees.add(arch_emp13);
        archaeologicalEmployees.add(arch_emp14);
        archaeologicalEmployees.add(arch_emp15);
        archaeologicalEmployees.add(arch_emp16);
        archaeologicalEmployees.add(arch_emp17);
        archaeologicalEmployees.add(arch_emp18);
        archaeologicalEmployees.add(arch_emp19);

        List<Employment> employments = new ArrayList<>();
        employments.add(employment1);
        employments.add(employment2);
        employments.add(employment3);
        employments.add(employment4);
        employments.add(employment5);
        employments.add(employment6);
        employments.add(employment7);
        employments.add(employment8);
        employments.add(employment9);
        employments.add(employment10);
        employments.add(employment11);
        employments.add(employment12);

        List<FieldTeam> fieldTeams = new ArrayList<>();
        fieldTeams.add(fieldTeam1);
        fieldTeams.add(fieldTeam2);
        fieldTeams.add(fieldTeam3);
        fieldTeams.add(fieldTeam4);
        fieldTeams.add(fieldTeam5);
        fieldTeams.add(fieldTeam6);

        Set<ResearchCompany> companies = new HashSet<>();
        companies.add(old);
        companies.add(weare);
        companies.add(olives);
        companies.add(now);

        Set<Compartment> compartments = new HashSet<>();
        compartments.add(compartment1);
        compartments.add(compartment2);
        compartments.add(compartment3);
        compartments.add(compartment4);
        compartments.add(compartment5);
        compartments.add(compartment6);


        Set<ExplorationVehicle> explorationVehicles = new HashSet<>();
        explorationVehicles.add(explorationVehicle1);
        explorationVehicles.add(explorationVehicle2);
        explorationVehicles.add(explorationVehicle3);
        explorationVehicles.add(explorationVehicle4);
        explorationVehicles.add(explorationVehicle5);
        explorationVehicles.add(explorationVehicle6);

        //Saving

        fieldTeamRepository.saveAll(fieldTeams);
        employeeRepository.saveAll(employees);
        archaeologicalEmployeeRepository.saveAll(archaeologicalEmployees);
        locationRepository.saveAll(locations);
        researchCompanyRepository.saveAll(companies);
        compartmentRepository.saveAll(compartments);
        explorationVehicleRepository.saveAll(explorationVehicles);
        employmentRepository.saveAll(employments);
        ownerRepository.saveAll(owners);

        Mission mission1 = Mission.builder()
                .fieldTeam(fieldTeam1)
                .explorationVehicle(explorationVehicle1)
                .startDate(LocalDate.of(2024, 9, 20))
                .endDate(LocalDate.of(2025, 3, 20))
                .type("Pyramids of the Sun")
                .startLocation(location1)
                .endLocation(location2)
                .state(MissionState.SCHEDULED)
                .build();

        Mission mission2 = Mission.builder()
                .fieldTeam(fieldTeam2)
                .explorationVehicle(explorationVehicle2)
                .startDate(LocalDate.of(2024, 4, 20))
                .endDate(LocalDate.of(2025, 10, 2))
                .type("Forbidden City secrets")
                .startLocation(location1)
                .endLocation(location3)
                .state(MissionState.IN_PROGRESS)
                .build();

        Mission mission3 = Mission.builder()
                .fieldTeam(fieldTeam3)
                .explorationVehicle(explorationVehicle3)
                .startDate(LocalDate.of(2023, 4, 2))
                .endDate(LocalDate.of(2024, 2, 22))
                .type("Vikings Expedition")
                .startLocation(location1)
                .endLocation(location4)
                .state(MissionState.ARCHIVED)
                .build();

        Set<Mission> missions = new HashSet<>();
        missions.add(mission1);
        missions.add(mission2);
        missions.add(mission3);
        missionRepository.saveAll(missions);
    }
}