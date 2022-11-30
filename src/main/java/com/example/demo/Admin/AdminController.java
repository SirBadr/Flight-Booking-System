package com.example.demo.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/Admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/signUp")
    public void adminSignUp(
            @RequestBody Admin newAdmin
    ) {
        adminService.adminSignUp(newAdmin);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Admin> adminSignIn(@RequestBody Admin adm) {
//        System.out.println(email);
        var admin = adminService.adminSignIn(adm.getEmail());
        System.out.println(admin);
        if(admin == null) {
            return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Admin>(admin, HttpStatus.OK);
        }
    }

}
