package com.platform.platform.services;

import com.platform.platform.Models.StoreOwner;
import com.platform.platform.Models.StoreProduct;
import com.platform.platform.Repositories.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreProductService {


    private List<StoreProduct> productsList = new ArrayList<>();
    private StoreProduct product = new StoreProduct();
    private StoreOwner storeOwner = new StoreOwner();

    @Autowired
    private StoreProductRepository storeProductRepository; // each transaction on crued should be in new object to avoid  nullable object

    public List<StoreProduct> FindAll()
    {
        this.productsList.clear();
        storeProductRepository.findAll().forEach(e ->  productsList.add(e)); // find all store owners

        return this.productsList ;
    }


    public void CommitProduct(String productName ,  String storeName, Double price , String description, String category, Integer quantity, String BrandName , String Photo ,String title , Double offer , HttpSession session)
    {
        Object CurrentUser = session.getAttribute("CurrentUser");

        if( CurrentUser instanceof StoreOwner )
            this.storeOwner = (StoreOwner) CurrentUser;


        product.setProduct_Name(productName);
        product.setProduct_price(price);
        product.setProduct_Description(description);
        product.setProduct_category(category);
        product.setProduct_Quantity(quantity);
        product.setProduct_brand(BrandName);
        product.setProduct_Photo(Photo);
        product.setProduct_title(title);
        product.setProduct_offer(offer);
        product.setStoreOwner_id(storeOwner.getId());  // when add product . admin id assign auto to record


        storeProductRepository.save(product);
    }


    public StoreProduct Product_Exist( String ProductName)
    {
        this.productsList = FindAll();

        for (StoreProduct product : this.productsList)
        {
            if( product.getProduct_Name().equals( ProductName))
                return product;
        }

        return null;  // noT found Product
    }


    public StoreProduct Product_Exist_By_StoreName( String StoreName)
    {
        this.productsList = FindAll();

        for (StoreProduct product : this.productsList)
        {
            if( product.getStore_Name().equals( StoreName))
                return product;
        }

        return null;  // noT found Product
    }



}


