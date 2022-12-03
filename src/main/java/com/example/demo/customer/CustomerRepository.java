package com.example.demo.customer;

import com.example.demo.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //responsible for data access
public interface CustomerRepository
        extends JpaRepository<Customer, Long>
{
    Customer findByEmail(String email);
    @Query("SELECT roles FROM Customer a where a.id=?1")
    List<Role> findCustomerRoles(Long id);

    @Modifying
    @Query("UPDATE Customer a SET a.roles=?2 where a.id=?1")
    void updateCustomerRoles(Long id, List<Role> roles);

}
