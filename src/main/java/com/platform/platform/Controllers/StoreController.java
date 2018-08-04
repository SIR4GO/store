package com.platform.platform.Controllers;


import com.platform.platform.Models.Admin;
import com.platform.platform.Models.Store;
import com.platform.platform.Models.StoreOwner;
import com.platform.platform.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreController {



    @Autowired
    StoreService storeService;
    Store store = new Store();

    @RequestMapping(value = "/add-store" , method = RequestMethod.GET)
    public  String AddStoreView(HttpSession session)
    {
        if(session.getAttribute("CurrentUser") instanceof StoreOwner) // he had  login
            return "AddStore";

        return "redirect:/login";  // don't has authority to access this is page
    }


    @RequestMapping(value = "/add-store" , method = RequestMethod.POST)
    public  String AddStore(@RequestParam("name") String name , @RequestParam("type") String type , HttpSession session)
    {

        this.store = storeService.Exist(name);
        if(this.store == null ) // Store not exist in my platform
        {
            storeService.CommitStore(name , type , session );
            return "AddDone";
        }
        return  "AddFail";
    }


    @RequestMapping(value = "/check-stores" , method = RequestMethod.GET)
    public ModelAndView AcceptAndRejectStore(HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        if(session.getAttribute("CurrentUser") instanceof Admin) // he had  login
        {
            List <Store> Stores = new ArrayList<>();
            Stores.clear(); // clear before add again
            view.clear();
            Stores = storeService.FindAll();
            view.setViewName("CheckStoreStatue");
            view.addObject("stores" , Stores);
            System.out.println(Stores);
            return view;
        }
        else
        {
            view.setViewName("redirect:/login");
            return view;  // don't has authority to access this is page
        }


    }



    @RequestMapping(value = "/check-stores" , method = RequestMethod.POST)
    public String ChangeStoreStatue( @RequestParam("store") String StoreName ,  @RequestParam("confirm") String statue )
    {
        if(statue.equals("Accepted") )
        {
            storeService.UpdateStoreStatue(StoreName , statue);
            return "StoreAccepted";
        }
        else if(statue.equals("Rejected"))
        {
            storeService.UpdateStoreStatue(StoreName , statue);
            return "StoreRejected";
        }

        return null;
    }






}
