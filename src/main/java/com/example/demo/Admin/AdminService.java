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
        //System.out.println(newCustomer);
        adminRepository.save(
                newAdmin
        );
    }

    public Admin adminSignIn(String email) {
        System.out.println(email);
        var result = adminRepository.findByEmail(email);
        System.out.println("RESULT");
        System.out.println(result);
        return result;
    }


}
