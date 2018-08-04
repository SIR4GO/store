package com.platform.platform.Controllers;

import com.platform.platform.Models.StartModel;
import com.platform.platform.Models.Store;
import com.platform.platform.Repositories.UserRepository;
import com.platform.platform.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Start {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    StoreService storeService;


   // @RequestMapping(value = "/register")
    public String form( )
    {
        return "register";
    }

    //@RequestMapping("/")
    public ModelAndView input(@RequestParam("name") String name ,@RequestParam("email") String  email )
    {
        StartModel n = new StartModel();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);

        ModelAndView view = new ModelAndView();
        view.setViewName("hello");
        view.addObject("name" ,  name);
        view.addObject("email" ,  email);
        return view;

    }

    //@RequestMapping(value = "/" , method = RequestMethod.GET)
    public ModelAndView test()
    {
//        List<Store> Stores = new ArrayList<>();
//
//        Stores = storeService.FindAll();
//
        ModelAndView view1 = new ModelAndView();

        view1.setViewName("test");
        //view1.addObject("stores" , Stores);

//        int [] arr = {1 ,2 ,3 ,4 ,6};
//
//        ModelAndView view1 = new ModelAndView();
//        view1.setViewName("test");
//        view1.addObject("arr" , arr);

        return view1;
    }







}
