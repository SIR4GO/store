package com.platform.platform.Controllers;

import com.platform.platform.Models.Sale;
import com.platform.platform.Models.ShoppingCart;
import com.platform.platform.Repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Controller
public class SaleController {


    @Autowired
    SaleRepository saleRepository;


    @RequestMapping(value = "/check-out" , method = RequestMethod.GET)
    public String ShowFormOfSales(HttpSession session)
    {
        if(session.getAttribute("userId") != null)
                return "order-form";

        return "redirect:/cart";
    }

    @RequestMapping(value = "/check-out" , method = RequestMethod.POST)
    public String AddSale(HttpSession session , @RequestParam("name") String cusName , @RequestParam("address") String address , @RequestParam("mobile") String mobile , @RequestParam("payment") String paymentMethod , @RequestParam("date") String date  )
    {


        Enumeration<String> keysList = session.getAttributeNames();
        List<ShoppingCart> shoppingCartList = new ArrayList<>();

        List<Sale> sales =new ArrayList<>();
        sales.clear();
        double total = 0;





        for (String key : Collections.list(keysList))
        {
            if(key.contains("order")) // to get orders attribute only from session
            {
                Sale sale = new Sale();

                sale.setName(cusName);
                sale.setAddress(address);
                sale.setMobile(mobile);    // and add some info from form to order that come from session
                sale.setDate(date);
                sale.setPaymentMethod(paymentMethod);

                shoppingCartList.add((ShoppingCart) session.getAttribute(key));
                sale.setProductName( ((ShoppingCart) session.getAttribute(key)).getProductName());
                sale.setPrice(((ShoppingCart) session.getAttribute(key)).getTotalPriceOfProductUnits());
                sale.setOrderQuantity(((ShoppingCart) session.getAttribute(key)).getQuantity());
                sale.setUserId(((ShoppingCart) session.getAttribute(key)).getUser_Id());

                session.removeAttribute(key);
                session.removeAttribute("userId");
                sales.add(sale);
            }

        }


        saleRepository.saveAll(sales);

        return "redirect:/";
    }




}
