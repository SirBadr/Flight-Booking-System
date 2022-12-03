package com.example.demo.Role;

import jakarta.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @SequenceGenerator(
            name = "adminRole_sequence",
            sequenceName = "adminRole_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adminRole_sequence"
    )
    private Long id;
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
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
}
