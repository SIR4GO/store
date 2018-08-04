package com.platform.platform.Controllers;


import com.platform.platform.Models.Admin;
import com.platform.platform.Models.Brand;
import com.platform.platform.Repositories.BrandRepository;
import com.platform.platform.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BrandController {

    @Autowired
    BrandService brandService;
    Brand brand = new Brand();

    @RequestMapping(value = "/add-brand" , method = RequestMethod.GET)
    public  String AddBrandView(HttpSession session)
    {
        if(session.getAttribute("CurrentUser") instanceof Admin) // he had  login
             return "AddBrand";

        return "redirect:/login";  // don't has authority to access this is page
    }


    @RequestMapping(value = "/add-brand" , method = RequestMethod.POST)
    public  String AddBrand(@RequestParam("name") String name , @RequestParam("category") String category , @RequestParam("date") String date , HttpSession session)
    {



        this.brand = brandService.Exist(name);
        if(brand == null ) // brand not exist in my platform
        {
            brandService.CommitBrand(name , category , date , session );
            return "AddDone";
        }
        return  "AddFail";
    }

}

