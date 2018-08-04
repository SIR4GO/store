package com.platform.platform.Controllers;

import com.platform.platform.Models.Product;
import com.platform.platform.Models.Sale;
import com.platform.platform.Models.StoreOwner;
import com.platform.platform.Models.Transaction;
import com.platform.platform.services.ProductService;
import com.platform.platform.services.SaleService;
import com.platform.platform.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    ProductService productService;
    @Autowired
    SaleService saleService;

    @RequestMapping(value = "/show-transaction" , method = RequestMethod.GET)
    public String ShowTransaction(Model model, HttpSession session)
    {
        Object currentUser =  session.getAttribute("CurrentUser");
        if(currentUser instanceof StoreOwner) // he had  login
        {
            //StoreOwner user = (StoreOwner)  session.getAttribute("CurrentUser");

            List<Transaction> transactionList = transactionService.FindAll();
            model.addAttribute("transactions" , transactionList);
            return "ShowTransaction";
        }

            return "redirect:/login";
    }

    @RequestMapping(value = "/cancel-transaction" , method = RequestMethod.GET)
     public String  CancelTransaction(@RequestParam("id") Integer id)
    {
        Transaction transaction = transactionService.Exist(id);

        String productName = transaction.getProductName();   // get info from transaction
        System.out.println(productName);


        Product product = productService.Exist(productName);

       List<Sale> saleList = saleService.FindSalesByProductName(productName);
       Integer SoldQuantitiesAfterDoingTransaction = 0;
       if(! saleList.isEmpty())
          for (Sale sale : saleList)
            SoldQuantitiesAfterDoingTransaction += sale.getOrderQuantity(); // PRODUCT Quantities HAD SOLD



        Integer addingQuantityInStore = 0;

        if(transaction.getQuantity() >= product.getProduct_Quantity_In_store() ) // may product sold from store and not have sufficient product to return
              addingQuantityInStore = transaction.getQuantity() - SoldQuantitiesAfterDoingTransaction;  // quantity which add to store recently without products had sold
        else
              addingQuantityInStore = transaction.getQuantity();






        // undo
        Integer CurrentQuantityInSystem = product.getQuantity();

        Integer CurrentQuantityInStore = product.getProduct_Quantity_In_store();

        Integer QuantityInStoreAfterUndo = CurrentQuantityInStore - addingQuantityInStore;  // quantity in store after return some product to system

        Integer QuantityInSystemAfterUndo = CurrentQuantityInSystem + addingQuantityInStore;  // quantity in system after some products sold and return some product from store

        product.setProduct_Quantity_In_store(QuantityInStoreAfterUndo); // update quantity in store

        // return quantity of product to system
        product.setQuantity(QuantityInSystemAfterUndo);

        // undo Done




       transactionService.RemoveTransaction(transaction);


        return "redirect:/";

    }
}
