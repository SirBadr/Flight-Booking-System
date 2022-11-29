package com.example.demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //responsible for data access
public interface CustomerRepository
        extends JpaRepository<Customer, Long>
{

}
