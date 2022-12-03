package com.example.demo.Role;

import com.example.demo.Admin.Admin;
import com.example.demo.Admin.AdminRepository;
import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RoleService(
            RoleRepository roleRepository,
            AdminRepository adminRepository,
            CustomerRepository customerRepository
    ){
        this.roleRepository = roleRepository;
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void addRoleToAdmin(Long id, String name) {
        Role role = roleRepository.findByName(name);
        Admin admin = adminRepository.findById(id).get();
        admin.getRoles().add(role);
    }

    @Transactional
    public void addRoleToCustomer(Long id, String name) {
        Role role = roleRepository.findByName(name);
        Customer customer = customerRepository.findById(id).get();
        customer.getRoles().add(role);
    }

    public Collection<Role> getCustomerRoles(Long id) {
        Collection<Role> roles = customerRepository.findCustomerRoles(id);
        return roles;
    }

    public Collection<Role> getAdminRoles(Long id) {
        Collection<Role> roles = adminRepository.findAdminRoles(id);
        return roles;
    }
}
