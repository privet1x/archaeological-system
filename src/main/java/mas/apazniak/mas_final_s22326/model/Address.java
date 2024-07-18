package mas.apazniak.mas_final_s22326.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Embeddable
public class Address {
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String street;
    @Positive
    private Integer number;

    public Address() {}

    public Address(String city, String country, String street, Integer number) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

