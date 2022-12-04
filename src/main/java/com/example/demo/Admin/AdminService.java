package com.example.demo.Admin;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void adminSignUp(
            Admin newAdmin
    ) {
        // save admin
        adminRepository.save(
                newAdmin
        );
    }

    public Admin adminSignIn(String email) {
        // return record from DB for that unique email
        System.out.println(email);
        var result = adminRepository.findByEmail(email);
        System.out.println("RESULT");
        System.out.println(result);
        return result;
    }

    public boolean adminExists(String email) {
        // checks if an admin exists or not
        var admin = adminRepository.findByEmail(email);
        if(admin == null) {
            return false;
        }
        return true;
    }

}
