package com.platform.platform.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer store_ID;
    @Column(unique=true)
    @NotNull
    private String name;
    private  String type ; // onsite , online
    private  Integer storeOwner_Id; // foreign Key on StoreOwnerController (table)
    private  String statue;  // accepted , or no




    public void setStoreID(Integer store_ID) {
        this.store_ID = store_ID;
    }
    public Integer getStoreID() { return  this.store_ID; }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() { return  this.type; }

    public void setStoreOwnerID(Integer storeOwner_Id) {
        this.storeOwner_Id = storeOwner_Id;
    }
    public Integer getStoreOwnerID() { return  this.storeOwner_Id; }


    public void setStatue(String statue ) {
        this.statue = statue;
    }
    public String getStatue() { return  this.statue; }

}
