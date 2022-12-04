package com.example.demo.Role;

import com.example.demo.Flight.FlightRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindRole() {
        // given
        Role role = new Role(
                "ROLE_CUSTOMER"
        );
        underTest.save(role);

        // where
        Role _role = underTest.findByName("ROLE_CUSTOMER");

        // then
        assertThat(_role.getName()).isEqualTo(role.getName());
    }

}