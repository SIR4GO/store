package com.platform.platform.Controllers;

import com.platform.platform.Models.Product;
import com.platform.platform.Models.Store;
import com.platform.platform.Models.StoreOwner;
import com.platform.platform.Repositories.StoreProductRepository;
import com.platform.platform.services.ProductService;
import com.platform.platform.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreProductController {


    @Autowired
    StoreService storeService;
    @Autowired
    ProductService productService;


    //@RequestMapping(value = "add-product-to-store" , method = RequestMethod.GET
    public ModelAndView AddProductToStore (HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        StoreOwner currentUser = (StoreOwner) session.getAttribute("CurrentUser");
        if(currentUser instanceof StoreOwner) // he had  login
        {
            List<Store> stores = new ArrayList<>();
            List<Product> products = new ArrayList<>();

            stores = storeService.FindStoresByIdOfStoreOwner(currentUser.getId());
            products = productService.FindAll();

            view.setViewName("AddProductToStore");
            view.addObject("stores" , stores);
            view.addObject("products" , products);

            //System.out.println(Stores);
            return view;
        }
        else
        {
            view.setViewName("redirect:/login");
            return view;  // don't has authority to access this is page
        }



    }



}
