package com.example.demo.Role;

import com.example.demo.Admin.AdminService;
import com.example.demo.Flight.FlightService;
import com.example.demo.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/Roles")
public class RoleController {
    private final AdminService adminService;
    private final CustomerService customerService;
    private final RoleService roleService;

    @Autowired
    public RoleController(
            AdminService adminService,
            CustomerService customerService,
            RoleService roleService
    ) {
        this.customerService = customerService;
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @PostMapping("/saveRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.saveRole(role));
    }

    @PostMapping("/addRoleToCustomer/{id}")
    public ResponseEntity addRoleToCustomer(@RequestBody Role role, @PathVariable Long id) {
        roleService.addRoleToCustomer(id, role.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addRoleToAdmin/{id}")
    public ResponseEntity addRoleToAdmin(@RequestBody Role role, @PathVariable Long id) {
        roleService.addRoleToAdmin(id, role.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customerRoles/{id}")
    public Collection<Role> getCustomerRoles (@PathVariable Long id) {
        return roleService.getCustomerRoles(id);
    }

}
