package com.example.demo.customer;

import com.example.demo.Role.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByEmail() {
        // given
        Customer customer = new Customer(
                "customerA",
                "customer@gmail.com",
                LocalDate.now(),
                Gender.MALE,
                new ArrayList<>()
        );
        underTest.save(customer);

        // when
        Customer _customer = underTest.findByEmail("customer@gmail.com");

        // then
        assertThat(_customer.getEmail()).isEqualTo("customer@gmail.com");
    }

    @Test
    void itShouldNotFindByEmail() {
        // given
        Customer customer = new Customer(
                "customerA",
                "customer@gmail.com",
                LocalDate.now(),
                Gender.MALE,
                new ArrayList<>()
        );
        underTest.save(customer);

        // when
        Customer _customer = underTest.findByEmail("admin@gmail.com");

        // then
        assertThat(_customer).isNull();
    }
}