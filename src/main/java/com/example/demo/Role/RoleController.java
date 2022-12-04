package com.example.demo.Role;

import com.example.demo.APIResponses.APIResponses;
import com.example.demo.Admin.AdminService;
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
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        try{
            roleService.saveRole(role);
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Role Created"), HttpStatus.OK);
//            return ResponseEntity.ok().body(roleService.saveRole(role));
        }catch(Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addRoleToCustomer/{id}")
    public ResponseEntity<?> addRoleToCustomer(@RequestBody Role role, @PathVariable Long id) {
        try{
            if(roleService.getRoleByName(role.getName()) == null) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "ROLE DOES NOT EXIST"), HttpStatus.OK);
            }
            roleService.addRoleToCustomer(id, role.getName());
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Role added to customer") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addRoleToAdmin/{id}")
    public ResponseEntity<?> addRoleToAdmin(@RequestBody Role role, @PathVariable Long id) {
        try{
            if(roleService.getRoleByName(role.getName()) == null) {
                return new ResponseEntity<APIResponses>(new APIResponses(false, "ROLE DOES NOT EXIST"), HttpStatus.OK);
            }
            roleService.addRoleToAdmin(id, role.getName());
            return new ResponseEntity<APIResponses>(new APIResponses(true, "Role added to admin") ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<APIResponses>(new APIResponses(false, e.getMessage()) ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customerRoles/{id}")
    public ResponseEntity<?> getCustomerRoles (@PathVariable Long id) {
        Collection<Role> roles = roleService.getCustomerRoles(id);
        System.out.println(roles);
        if(roles.size() == 0) {
            return new ResponseEntity<APIResponses>(new APIResponses(true, "No Roles") ,HttpStatus.OK);
        }
        return new ResponseEntity<Collection<Role>>(roles ,HttpStatus.OK);
//        return roleService.getCustomerRoles(id);
    }

}
