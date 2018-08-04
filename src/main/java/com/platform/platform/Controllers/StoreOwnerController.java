package com.platform.platform.Controllers;


import com.platform.platform.Models.Admin;
import com.platform.platform.Models.StoreOwner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class StoreOwnerController {


    // show admin control panel after login
    @RequestMapping(value = "/owner-dashboard" , method = RequestMethod.GET)
    public String controlPanel(HttpSession session)
    {
        if(session.getAttribute("CurrentUser") instanceof StoreOwner) // he had  login
            return "templateOwner";

        return "redirect:/login";
    }

}
