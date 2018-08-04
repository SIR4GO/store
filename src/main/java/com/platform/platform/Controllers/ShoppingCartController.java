package com.platform.platform.Controllers;


import com.platform.platform.Models.Product;
import com.platform.platform.Models.ShoppingCart;
import com.platform.platform.lib.GenerateRandomNum;
import com.platform.platform.services.ProductService;
import com.platform.platform.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.*;

@Controller
public class ShoppingCartController {

    private GenerateRandomNum generateRandomNum = new GenerateRandomNum();

    @Autowired
    private ShoppingCartService shoppingCartService;




    @Autowired
    ProductService productService;

    @RequestMapping(value = "/cart" , method = RequestMethod.GET)
    public String ShowCart (Model model , HttpSession session)
    {
        Enumeration<String> keysList = session.getAttributeNames();
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        double total = 0;
        for (String key : Collections.list(keysList))
        {
            if(key.contains("order")) // to get orders attribute only from session
            {
                shoppingCartList.add((ShoppingCart) session.getAttribute(key));
                 total +=   ((ShoppingCart) session.getAttribute(key)).getTotalPriceOfProductUnits();
            }

        }


        model.addAttribute("carts" , shoppingCartList);
        model.addAttribute("total", total);
        int counter = 0;
        counter = shoppingCartList.size();
        session.setAttribute("counter" , counter);



        return "cart";
    }

    @RequestMapping(value = "/cart" , method = RequestMethod.POST)
    public String AddToCart ( HttpSession session , @RequestParam("productName") String productName , @RequestParam("quantity") Integer quantity , @RequestParam("price") Double price)
    {
//            // prepare parameters

        String userId = session.getId();
        Product product = new Product();

        session.setAttribute("userId" , userId);

        if(quantity.equals(0)) // edit quantity if it's 0
               quantity=1;


        ShoppingCart shoppingCart = new ShoppingCart();  // if we do it private object cause problem duplicate same object each request
        shoppingCart.setUser_Id(userId);
        shoppingCart.setProductName(productName);
        shoppingCart.setQuantity(quantity);
        shoppingCart.setPrice(price);
        shoppingCart.setTotalPriceOfProductUnits(price * quantity);

        int index = generateRandomNum.GenerateNumber(); // here not check


        String key = "order" + index ; // generate special key without duplicates

        session.setAttribute(key , shoppingCart);


        // update product quantity in store

        product = productService.Exist(productName);

        product.setProduct_Quantity_In_store(product.getProduct_Quantity_In_store() - quantity);

        productService.updateProduct(product);


            return "redirect:/cart";

    }



}

