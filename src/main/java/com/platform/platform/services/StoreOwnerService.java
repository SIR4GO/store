package com.platform.platform.services;


import com.platform.platform.Models.StoreOwner;
import com.platform.platform.Repositories.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreOwnerService {


    @Autowired
    private StoreOwnerRepository storeOwnerRepository ; // each transaction on crued should be in new object to avoid  nullable object

    public List<StoreOwner> FindAll()
    {
        List<StoreOwner> storeOwnersList = new ArrayList<>();
        storeOwnersList.clear();

        storeOwnerRepository.findAll().forEach(e -> storeOwnersList.add(e)); // find all store owners

        return storeOwnersList ;
    }


    public void CommitOwner(String name ,String username , String password , String email)
    {
        StoreOwner storeOwner = new StoreOwner();

        storeOwner.setName(name);
        storeOwner.setUserName(username);
        storeOwner.setPassword(password);
        storeOwner.setEmail(email);
        storeOwnerRepository.save(storeOwner);

    }
}

