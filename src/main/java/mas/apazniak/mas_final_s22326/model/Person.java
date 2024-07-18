package mas.apazniak.mas_final_s22326.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
public abstract class Person {
    @NotBlank
    private String nationality;
    @PastOrPresent(message = "Date of birth cannot be in the future")
    private LocalDate dob;
    @NotBlank(message="First Name is mandatory.")
    @Size(min = 2, max = 25)
    private String firstName;
    @NotBlank(message="Last Name is mandatory.")
    @Size(min = 2, max = 15)
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Person(String firstName, String lastName, String nationality, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

