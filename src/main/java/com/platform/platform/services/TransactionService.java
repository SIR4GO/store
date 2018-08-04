package com.platform.platform.services;

import com.platform.platform.Models.Transaction;
import com.platform.platform.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;





    public void CommitTransaction ( String productName , Integer quantity , Double price , String actor , String storeName)
    {
        Transaction transaction = new Transaction();


        transaction.setProductName(productName);
        transaction.setQuantity(quantity);
        transaction.setPrice(price);
        transaction.setActor(actor);
        transaction.setStoreName(storeName);


        transactionRepository.save(transaction);
    }


    public List<Transaction> FindAll()
    {
        List<Transaction> transactionList = new ArrayList<>();

        transactionList.clear();
        transactionRepository.findAll().forEach(e ->  transactionList.add(e)); // find all Coll

        return transactionList ;
    }



    public Transaction Exist(Integer id)
    {
        List<Transaction> transactionList = FindAll();


        for (Transaction transaction :  transactionList)
        {
            if(transaction.getId().equals( id) )
                return transaction;
        }

        return null;
    }


   public void RemoveTransaction(Transaction transaction)
   {
       transactionRepository.delete(transaction);
   }

}
