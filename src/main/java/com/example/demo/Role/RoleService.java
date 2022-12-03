package com.example.demo.Role;

import com.example.demo.Admin.Admin;
import com.example.demo.Admin.AdminRepository;
import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addRoleToAdmin(Long id, String roleName) {
        Role role = roleRepository.findByName(roleName);

        List<Role> adminRoles = adminRepository.findAdminRoles(id);
        adminRoles.add(role);
        adminRepository.updateAdminRoles(id, adminRoles);
    }

    @Transactional
    public void addRoleToCustomer(Long id, String roleName) {
        Role role = roleRepository.findByName(roleName);

        List<Role> customerRoles = customerRepository.findCustomerRoles(id);
        customerRoles.add(role);
        customerRepository.updateCustomerRoles(id, customerRoles);
    }
}
