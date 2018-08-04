package com.platform.platform.Controllers;


import com.platform.platform.Models.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class AdminController {


  // show admin control panal after login
    @RequestMapping(value = "/admin-dashboard" , method = RequestMethod.GET)
    public String controlPanel(HttpSession session)
    {
        if(session.getAttribute("CurrentUser") instanceof Admin) // he had  login
                return "templateAdmin";

        return "redirect:/login";
    }



}
