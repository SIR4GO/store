package com.platform.platform.Controllers;



import com.platform.platform.Models.Admin;
import com.platform.platform.Models.Collaborator;
import com.platform.platform.Models.StoreOwner;
import com.platform.platform.Repositories.StoreOwnerRepository;
import com.platform.platform.lib.validate;
import com.platform.platform.services.AdminService;
import com.platform.platform.services.CollaboratorService;
import com.platform.platform.services.StoreOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class Access
{


    private validate check = new validate();
    private StoreOwner storeOwner = new StoreOwner();
    private Collaborator collaborator = new Collaborator();
    private Admin admin = new Admin();
    private List<StoreOwner> storeOwnersList = new ArrayList<>();


    @Autowired
    private StoreOwnerService service ;  //  StoreOwnerService service = new StoreOwnerService (); ==> Wrong  that return null
//The error is instantiating a PersonService manually in your controller, like StoreOwnerService service = new StoreOwnerService ().
//For Spring to be able to autowire anything, you need to use the beans managed by it, so instead of creating a new PersonService on your controller, autowire it:
    @Autowired
    private AdminService adminService;

    @Autowired
    private CollaboratorService collaboratorService;


    public StoreOwner Exist(String username , String password)
    {
        this.storeOwnersList = service.FindAll();

        for (StoreOwner owner : storeOwnersList)
        {
            if(owner.getUserName().equals(username ) && owner.getPassword().equals( password))
                return owner;
        }

        return null;
    }



    @RequestMapping(value = "/register" , method = RequestMethod.GET )
    public String register_view()
    {
        return "register";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String Register(@RequestParam("name") String name  , @RequestParam("username") String username , @RequestParam("password") String password , @RequestParam("email") String email   )
    {

        this.storeOwner = Exist(username ,password);
        if(this.storeOwner == null) // not found in database
        {
            // validate Data
            if( ! check.IsValid(name , username , password , email))
                return "redirect:/register";

            //  assign Data After validate
            service.CommitOwner(name , username ,password , email);

            return "redirect:/login";
        }
        else // found it in Db
            return "RegistFail";

    }



    @RequestMapping(value = "/login" , method = RequestMethod.GET )
    public String login_view(HttpSession session)
    {
        session.removeAttribute("CurrentUser"); // delete session if it make re login or sign out

        return "login";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST )
    public String login(@RequestParam("username") String username , @RequestParam("password") String password , @RequestParam(value = "userType" , defaultValue = "owner") String userType ,HttpSession session)
    {

        if(userType.equals("admin") )
        {
            this.admin = adminService.Exist(username , password);
            if(this.admin != null) // log in successful
            {
                session.setAttribute("CurrentUser" , admin); // set session  admin
                return "redirect:/admin-dashboard";
            }
            else
                return "LoginFail"; // template  Control panel for Admin

        }

            this.storeOwner = Exist(username ,password);  // if store Owner
            if(this.storeOwner != null)
            {
                session.setAttribute("CurrentUser" , storeOwner);
                return "redirect:/owner-dashboard"; // template  Control panel for Store owner
            }
            else
            {
                this.collaborator = collaboratorService.Exist(username , password);
                if(this.collaborator != null)
                {
                    session.setAttribute("CurrentUser" , collaborator);
                    return "redirect:/collaborator-dashboard"; // template  Control panel for Store owner
                }
                else
                    return "LoginFail";


            }










    }
    
    
    
    
    
}
