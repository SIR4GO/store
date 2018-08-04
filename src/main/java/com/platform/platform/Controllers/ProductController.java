package com.platform.platform.Controllers;


import com.platform.platform.Models.*;
import com.platform.platform.services.BrandService;
import com.platform.platform.services.ProductService;
import com.platform.platform.services.StoreService;
import com.platform.platform.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    StoreService storeService;

    @Autowired
    TransactionService transactionService;


    Product product = new Product();
    Brand brand = new Brand();
    Store store = new Store();

    @RequestMapping(value = "/add-product" , method = RequestMethod.GET)
    public  String AddProductView(HttpSession session , Model model)
    {

        if(session.getAttribute("CurrentUser") instanceof Admin) // he had  login
        {
            List<Brand> brands = new ArrayList<>();
            brands = brandService.FindAll();
            model.addAttribute("brands" , brands);
            return "AddProduct";

        }

        return "redirect:/login"; // don't has authority to access this is page

    }

     @RequestMapping(value = "/add-product" , method = RequestMethod.POST)
    public  String AddProduct(@RequestParam("name") String name , @RequestParam("version") String version, @RequestParam("price") Double price , @RequestParam("category") String category , @RequestParam("quantity") Integer quantity, @RequestParam("brandName") String brandName, HttpSession session)
     {
        this.product = productService.Exist(name + " " + version);
        this.brand = brandService.Exist(brandName);
        if(this.product == null && this.brand !=null )  // Product not Exist in platform , and brand exist
        {
            productService.CommitProduct(name + " " + version , price , category , quantity , brandName , session );
            return "ADDDone";
        }

        return "AddFail";
    }



    @RequestMapping(value = "add-product-to-store" , method = RequestMethod.GET)
    public ModelAndView ViewAddProductToStore (HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        StoreOwner currentUser = (StoreOwner) session.getAttribute("CurrentUser");
        if(currentUser instanceof StoreOwner) // he had  login
        {
            List<Store> stores = new ArrayList<>();
            List<Product> products = new ArrayList<>();

            stores = storeService.FindStoresByIdOfStoreOwner(currentUser.getId());  // get his stores
            products = productService.FindAll();


            for(Store store : stores) // filter stores
            {
                if(store.getStatue().equals("Rejected")) // filter his stores
                    stores.remove(store);
            }


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


    @RequestMapping(value = "add-product-to-store" , method = RequestMethod.POST)
    public String  AddProductToStore (@RequestParam("productName") String productName , @RequestParam("store") String storeName, @RequestParam("price") Double price , @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("quantity") Integer quantity , @RequestParam("photoUrl") String Photo , @RequestParam("title") String title ,  @RequestParam("offer") Double offer , HttpSession session)
    {
        // update quantity in service

        this.product = productService.Exist(productName);  // products that coming from system  check quantity after add required product quantity to store
        this.store = storeService.Exist(storeName);
        String StoreStatue = store.getStatue();

        Integer AvailableQuantity = this.product.getQuantity();

        if(this.product != null  && AvailableQuantity >= quantity && StoreStatue.equals("Accepted") )  // product exist , store accepted ,there is sufficient quantity in system
        {

            productService.CommitProductTOStore(productName, storeName , price , description , category ,quantity , Photo , title , offer ,session);

            transactionService.CommitTransaction(productName , quantity , price * quantity , "owner" , storeName);


            return "AddDone";
        }


        return "AddFail";

    }


    @RequestMapping(value = "add-product-to-store-coll" , method = RequestMethod.GET)
    public ModelAndView ViewAddProductToStoreByColl (HttpSession session)
    {
        ModelAndView view = new ModelAndView();
       Collaborator currentUser = (Collaborator) session.getAttribute("CurrentUser");
        if(currentUser instanceof Collaborator) // he had  login
        {
            List<Store> stores = new ArrayList<>();
            List<Product> products = new ArrayList<>();

            stores = storeService.FindStoresByIdOfStoreOwner(currentUser.getId());  // get his stores
            products = productService.FindAll();


            for(Store store : stores) // filter stores
            {
                if(store.getStatue().equals("Rejected")) // filter his stores
                    stores.remove(store);
            }


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


    @RequestMapping(value = "add-product-to-store-coll" , method = RequestMethod.POST)
    public String  AddProductToStoreByColl (@RequestParam("productName") String productName , @RequestParam("store") String storeName, @RequestParam("price") Double price , @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("quantity") Integer quantity , @RequestParam("photoUrl") String Photo , @RequestParam("title") String title ,  @RequestParam("offer") Double offer , HttpSession session)
    {
        // update quantity in service

        this.product = productService.Exist(productName);  // products that coming from system  check quantity after add required product quantity to store
        this.store = storeService.Exist(storeName);
        String StoreStatue = store.getStatue();

        Integer AvailableQuantity = this.product.getQuantity();

        if(this.product != null  && AvailableQuantity >= quantity && StoreStatue.equals("Accepted") )  // product exist , store accepted ,there is sufficient quantity in system
        {

            productService.CommitProductTOStore(productName, storeName , price , description , category ,quantity , Photo , title , offer ,session);

            transactionService.CommitTransaction(productName , quantity , price * quantity , "collaborator" , storeName);


            return "AddDone";
        }


        return "AddFail";

    }





    // retrive product

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String showProducts(@RequestParam(value = "category" , defaultValue = "All") String productCategory , Model model)
    {
        List<Product> ProductList = new ArrayList<>();
        List<Product> StoreProductsList = new ArrayList<>();

        if(productCategory.equals("All"))
        {
             ProductList= productService.FindAll();

             for(Product product : ProductList)
             {
                 if(product.getStore_Name() != null && product.getProduct_Quantity_In_store() > 0)
                      StoreProductsList.add(product);

             }
             model.addAttribute("products" , StoreProductsList);
             return "Home";
        }

        ProductList = productService.FindAllByCategory(productCategory);
        model.addAttribute("products" ,ProductList); // here products for store handle by FindAllByCategory
        return "Home";


    }


    @RequestMapping(value = "/product" , method = RequestMethod.GET  )
    public String showProduct(@RequestParam("name") String productName , HttpSession session,Model model)
    {
        this.product = productService.Exist(productName);
        model.addAttribute("product",this.product);
        return "Product-Page";
    }





}
