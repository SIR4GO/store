package com.platform.platform.services;

import com.platform.platform.Models.Admin;
import com.platform.platform.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminService {


    @Autowired
    private AdminRepository adminRepository ; // each transaction on crued should be in new object to avoid  nullable object

    public List<Admin> FindAll()
    {
       List<Admin> adminsList = new ArrayList<>();
        adminsList.clear();
        adminRepository.findAll().forEach(e ->  adminsList.add(e)); // find all store owners

        return adminsList ;
    }


    public void CommitAdmin(String name ,String username , String password , String email)
    {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setUserName(username);
        admin.setPassword(password);
        admin.setEmail(email);
        adminRepository.save(admin);
    }


    public Admin Exist(String username , String password)
    {
        List<Admin> adminsList = new ArrayList<>();
        adminsList.clear();

        adminsList = FindAll();

        for (Admin admin : adminsList)
        {
            if(admin.getUserName().equals(username ) && admin.getPassword().equals( password))
                return admin;
        }

        return null;
    }
}
