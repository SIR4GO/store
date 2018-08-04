package com.platform.platform.Controllers;


import com.platform.platform.Models.Admin;
import com.platform.platform.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class StatisticsController {



   @Autowired
    StatisticsService statisticsService ;

    @RequestMapping(value = "admin-statistics" , method = RequestMethod.GET)
    public ModelAndView ShowAdminStatistics(HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        if(session.getAttribute("CurrentUser") instanceof Admin)
        {
            view.setViewName("AdminStatistics");
            view.addObject("total",statisticsService.CalculateAggregateStatistics());

            return view;
        }

        view.setViewName("redirect:/login");
        return view;
    }




}
