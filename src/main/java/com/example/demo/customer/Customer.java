package com.example.demo.customer;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDate;
@Entity
@Table //to create a table in database
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Gender gender;

    public Customer() {
    }

    public Customer(Long id, String name, String email, LocalDate birthDate, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Customer(String name, String email, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                '}';
    }
}
