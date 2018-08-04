package com.platform.platform.services;


import com.platform.platform.Models.*;
import com.platform.platform.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductService {

    private StoreOwner storeOwner = new StoreOwner();
    private Admin  admin = new Admin();

    @Autowired
    private ProductRepository productRepository ; // each transaction on crued should be in new object to avoid  nullable object

    public List<Product> FindAll()
    {
        List<Product> productsList = new ArrayList<>();
        productsList.clear();

        productRepository.findAll().forEach(e ->  productsList.add(e)); // find all store owners

        return productsList ;
    }

    public List<Product> FindAllByCategory(String category)
    {
        List<Product> productsList = new ArrayList<>();
        productsList.clear();

        productsList = FindAll();

        List<Product> AllProducts = new ArrayList<>();
        AllProducts.clear(); //to avoid repeat in request

        for (Product product : productsList)
        {
            if( product.getProduct_category_In_store() != null && product.getProduct_category_In_store().equals(category) && product.getProduct_Quantity_In_store() > 0 )
                   AllProducts.add(product);

        }

        return AllProducts;  // noT found Product
    }


    public void CommitProduct(String name , Double price , String category, Integer quantity, String BrandName , HttpSession session)
    {
        Object CurrentUser = session.getAttribute("CurrentUser");

         if( CurrentUser instanceof Admin )
             this.admin = (Admin) CurrentUser;

       Product product = new Product(); // create object each call to avoid overwrite

        product.setName(name.toLowerCase());
        product.setPrice(price);
        product.setCategory(category);
        product.setQuantity(quantity);
        product.setBrandName(BrandName);
        product.setAdminId(admin.getId());  // when add product . admin id assign auto to record


        productRepository.save(product);
    }


    public Product Exist( String ProductName)
    {
        List<Product> productsList = new ArrayList<>();
        productsList.clear();

        productsList = FindAll();

        for (Product product : productsList)
        {
            if( product.getName().equals( ProductName.toLowerCase()))
                return product;
        }

        return null;  // noT found Product
    }


    public void updateProduct (Product product)
    {
        productRepository.save(product);
    }

    /////// for Store ////////


    public void CommitProductTOStore (String productName ,  String storeName, Double price , String description, String category, Integer quantity, String Photo ,String title , Double offer , HttpSession session)
    {
        Object CurrentUser = session.getAttribute("CurrentUser");

        if( CurrentUser instanceof StoreOwner)
            this.storeOwner = (StoreOwner) CurrentUser;


        Product product = new Product(); // create object each call to avoid overwrite


        product = Exist(productName); // get product to update it to add it to store after check some issue in controller
        product.setQuantity(product.getQuantity() - quantity); // update quantity of original product in system

        Integer CurrentQuantityInStore = product.getProduct_Quantity_In_store(); // if we want add more from same product

        if(CurrentQuantityInStore == null ) // to avoid error nullable
            CurrentQuantityInStore = 0;

                   // add store info to product
        product.setStore_Name(storeName);
        product.setProduct_price_In_store(price);
        product.setProduct_Description(description);
        product.setProduct_category_In_store(category);
        product.setProduct_Quantity_In_store( CurrentQuantityInStore + quantity); // if we want add more from same product
        product.setProduct_Photo(Photo);
        product.setProduct_title(title);
        product.setProduct_offer(offer);
        product.setStoreOwner_id(storeOwner.getId());  // when add product to store . StoreOwner id assign  to record  // if null then coll who add product to store

       productRepository.save(product);
    }

    public Product Product_Exist_By_StoreName(String StoreName)
    {

        List<Product> productsList = new ArrayList<>();

        productsList = FindAll();

        for (Product product : productsList)
        {
            if( product.getStore_Name().equals( StoreName))
                return product;
        }

        return null;  // noT found Product
    }



}
