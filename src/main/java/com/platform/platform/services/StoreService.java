package com.platform.platform.services;


import com.platform.platform.Models.Store;
import com.platform.platform.Models.StoreOwner;
import com.platform.platform.Repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreService {

    private StoreOwner StoreOwner = new StoreOwner();

    @Autowired
    private StoreRepository storeRepository ; // each transaction on crued should be in new object to avoid  nullable object

    public List<Store> FindAll()
    {
        List<Store> storesList = new ArrayList<>();

        storesList.clear(); // to deny duplication , each call . this add ALL table rows again on last all added rows from last call , makes duplications ==> magic store , apple store , magic Store , Apple store
        storeRepository.findAll().forEach(e ->  storesList.add(e)); // find all store owners

        return storesList ;
    }

    public List<Store> FindStoresByIdOfStoreOwner(Integer StoreOwnerId)
    {
        List<Store> storesList = new ArrayList<>();
        storesList.clear();

        storesList = FindAll();
        List<Store> MatchesStores = new ArrayList<>();
        MatchesStores.clear();

        for (Store store : storesList)
            if( store.getStoreOwnerID().equals(StoreOwnerId ) )
            {

                MatchesStores.add(store);
            }

        return MatchesStores;  // noT found Store

    }


    public void CommitStore(String name , String type , HttpSession session)
    {
        Object CurrentUser = session.getAttribute("CurrentUser");

        if( CurrentUser instanceof StoreOwner)
            this.StoreOwner = (StoreOwner) CurrentUser;

         Store store = new Store();

        store.setName(name.toLowerCase());
        store.setType(type);
        store.setStoreOwnerID(this.StoreOwner.getId());  // when add product . admin id assign auto to record
        store.setStatue("Pending");

        storeRepository.save(store);
    }


    public Store Exist( String StoreName)
    {
        List<Store> storesList = new ArrayList<>();
        storesList.clear();

        storesList = FindAll();

        for (Store store : storesList)
        {
            if( store.getName().equals(StoreName.toLowerCase()))
                return store;
        }

        return null;  // noT found Store
    }

    public void UpdateStoreStatue(String StoreName , String statue)
    {
        Store store = new Store();

        store = Exist(StoreName);
        store.setStatue(statue);
        storeRepository.save(store);
    }


}
