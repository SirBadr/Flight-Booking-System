package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

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
                Gender.MALE
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
                Gender.MALE
        );
        underTest.save(customer);

        // when
        Customer _customer = underTest.findByEmail("admin@gmail.com");

        // then
        assertThat(_customer).isNull();
    }
}