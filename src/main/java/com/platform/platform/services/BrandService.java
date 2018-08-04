package com.platform.platform.services;


import com.platform.platform.Models.Admin;
import com.platform.platform.Models.Brand;
import com.platform.platform.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BrandService {



    @Autowired
    private BrandRepository brandRepository ; // each transaction on crued should be in new object to avoid  nullable object

    public List<Brand> FindAll()
    {
        List<Brand> brandsList = new ArrayList<>();
        brandsList.clear();

        brandRepository.findAll().forEach(e ->  brandsList.add(e)); // find all store owners

        return brandsList ;
    }


    public void CommitBrand(String name , String category , String date ,  HttpSession session)
    {
        Admin admin = new Admin();

        Object CurrentUser = session.getAttribute("CurrentUser");
        if(CurrentUser instanceof Admin)
             admin = (Admin) CurrentUser;

        Brand brand = new Brand();

        brand.setName(name.toLowerCase());
        brand.setCategory(category);
        brand.setDate(date);
        brand.setAdminId(admin.getId());

        brandRepository.save(brand); // save at db
    }


    public Brand Exist( String BrandName)
    {
        List<Brand> brandsList = new ArrayList<>();

        brandsList = FindAll();  // get all brand from database to check exist

        for (Brand brand : brandsList)
        {
            if( brand.getName().equals( BrandName.toLowerCase()))
                return brand;
        }

        return null;  // noT found bARAND
    }


}
