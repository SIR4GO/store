package com.platform.platform.services;


import java.util.ArrayList;
import java.util.List;

import com.platform.platform.Models.Product;
import com.platform.platform.Models.ShoppingCart;
import com.platform.platform.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class ShoppingCartService {

     private List<ShoppingCart> shoppingCartList = new ArrayList<>();
     private ShoppingCart shoppingCart = new ShoppingCart();
     @Autowired
     private ShoppingCartRepository shoppingCartRepository ;

    public List<ShoppingCart> FindAll()
    {
        this.shoppingCartList.clear(); // to deny duplication , each call . this add ALL table rows again on last all added rows from last call , makes duplications ==> magic store , apple store , magic Store , Apple store
        shoppingCartRepository.findAll().forEach(e ->  shoppingCartList.add(e)); // find all store owners

        return this.shoppingCartList ;
    }


    public List<ShoppingCart> FindCartItemsByIdUser(String discriminator)
    {
        this.shoppingCartList= FindAll();
        List<ShoppingCart> MatchesCartItems = new ArrayList<>();
        MatchesCartItems.clear();

        for (ShoppingCart shoppingCart : this.shoppingCartList)
            if( shoppingCart.getUser_Id().equals(discriminator) )
            {

                MatchesCartItems.add(shoppingCart);
            }

        return MatchesCartItems;  // noT found Store

    }

    public void CommitCartItem(String userId , String productName, Integer quantity , Double price)
    {


       shoppingCart.setUser_Id(userId);
       shoppingCart.setProductName(productName);
       shoppingCart.setQuantity(quantity);
       shoppingCart.setPrice(price);

        shoppingCartRepository.save(shoppingCart);
    }

}
