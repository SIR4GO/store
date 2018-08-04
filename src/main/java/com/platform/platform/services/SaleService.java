package com.platform.platform.services;

import com.platform.platform.Models.Sale;
import com.platform.platform.Repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SaleService {


    @Autowired
    private SaleRepository saleRepository;


    public List<Sale> FindSalesByProductName(String productName)
    {
        List<Sale> sales = new ArrayList<>();
        sales.clear();
        List<Sale> saleList = new ArrayList<>();
        saleList.clear();

        saleRepository.findAll().forEach(e ->  saleList.add(e)); // find all at first


        for(Sale sale : saleList)  // here filter
        {
            if( sale.getProductName().equals(productName))
                   sales.add(sale);
        }


        return sales ;
    }



}
