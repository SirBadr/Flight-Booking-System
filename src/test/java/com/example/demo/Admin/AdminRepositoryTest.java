package com.example.demo.Admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AdminRepositoryTest {
    @Autowired
    private AdminRepository underTest;

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
}