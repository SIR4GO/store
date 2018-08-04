package com.platform.platform.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticsService {

    @Autowired
    BrandService brandService;
    @Autowired
    ProductService productService;
    @Autowired
    StoreService storeService;
    @Autowired
    StoreOwnerService storeOwnerService;


    public List<Integer> CalculateAggregateStatistics()
    {
        List <Integer> TotalStatistics  = new ArrayList<>();
        TotalStatistics.clear();

        TotalStatistics.add(this.brandService.FindAll().size()); // sum of Brands
        TotalStatistics.add(this.productService.FindAll().size());// sum of products
        TotalStatistics.add(this.storeService.FindAll().size()); // sum of stores
        TotalStatistics.add(this.storeOwnerService.FindAll().size()); // sum of store owners

        return TotalStatistics;

    }






}
