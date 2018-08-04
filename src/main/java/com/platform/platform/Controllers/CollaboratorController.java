package com.platform.platform.Controllers;


import com.platform.platform.Models.Collaborator;
import com.platform.platform.Models.StoreOwner;
import com.platform.platform.services.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;


    @RequestMapping(value = "/collaborator-dashboard" , method = RequestMethod.GET)
    public String controlPanel(HttpSession session)
    {
        if(session.getAttribute("CurrentUser") instanceof Collaborator) // he had  login
            return "templateCollaborator";

        return "redirect:/login";
    }


    @RequestMapping(value = "/add-collaborator" , method = RequestMethod.GET)
    public String AddCollaboratorView (HttpSession session)
    {
        if(session.getAttribute("CurrentUser") instanceof StoreOwner) // he had  login
            return "AddCollaborator";


        return "redirect:/login";

    }

    @RequestMapping(value = "/add-collaborator" , method = RequestMethod.POST)
    public String AddCollaborator ( HttpSession session, String name , String userName , String password)
    {
        StoreOwner owner = (StoreOwner) session.getAttribute("CurrentUser");


        collaboratorService.CommitCollaborator(owner.getId() , name , userName , password );
        return "AddDone";
    }





}
