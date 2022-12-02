package com.example.demo.Admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AdminRepositoryTest {
    @Autowired
    private AdminRepository underTest;

    @AfterEach
    void tearDown() {
       underTest.deleteAll();
    }

    @Test
    void itShouldFindByEmail() {
        // given
        Admin admin = new Admin(
                "admin1",
                "admin@gmail.com"
        );
        underTest.save(admin);

        // when
        Admin _admin = underTest.findByEmail("admin@gmail.com");

        // then
        assertThat(_admin.getEmail()).isEqualTo(admin.getEmail());
        assertThat(_admin.getName()).isEqualTo(admin.getName());
    }

    @Test
    void itShouldNotFindByEmail() {
        // given
        String email = "client@gmail.com";

        // when
        Admin _admin = underTest.findByEmail(email);
        System.out.println(_admin);
        // then
        assertThat(_admin).isNull();
    }
}