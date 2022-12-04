package com.example.demo.Admin;

import com.example.demo.Role.Role;
import com.example.demo.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    @Query("SELECT roles FROM Admin a where a.id=?1")
    List<Role> findAdminRoles(Long id);

    @Modifying
    @Query("UPDATE Admin a SET a.roles=?2 where a.id=?1")
    void updateAdminRoles(Long id, List<Role> roles);
}
