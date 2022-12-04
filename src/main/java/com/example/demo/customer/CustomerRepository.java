package com.example.demo.customer;

import com.example.demo.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository //responsible for data access
public interface CustomerRepository
        extends JpaRepository<Customer, Long>
{
    Customer findByEmail(String email);
    @Query("SELECT roles FROM Customer a where a.id=?1")
    ArrayList<Role> findCustomerRoles(Long id);

    @Modifying
    @Query("UPDATE Customer c SET c.roles=?2 where c.id=?1")
    void updateCustomerRoles(Long id, Collection<Role> roles);

}
