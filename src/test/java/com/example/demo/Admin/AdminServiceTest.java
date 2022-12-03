package com.example.demo.Admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;
    private AutoCloseable autoCloseable;
    private AdminService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AdminService(adminRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void CanAdminSignUp() {
        // when
        Admin admin = new Admin(
                "admin",
                "admin@gmail.com"
        );
        underTest.adminSignUp(
                admin
        );

        // then
        verify(adminRepository).save(admin);
    }

    @Test
    void canAdminSignIn() {
        // when
        String email = "email@gmail.com";
        underTest.adminSignIn(email);

        // then
        verify(adminRepository).findByEmail(email);
    }
}