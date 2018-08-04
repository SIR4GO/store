package com.platform.platform.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // surrogate key
    private Integer product_ID;
    @Column(unique=true)
    @NotNull
    private String name;
    private Double price;
    private String category;
    private Integer quantity;
    private String BrandName; // foreign Key on brand (table) // to valid brand
    private Integer adminId ;   // foreign Key on admin (table)


    // for  Add product in Store
    private String store_Name; // foreign key on store
    private String Product_Description;
    private Double Product_price_In_store;
    private Integer Product_Quantity_In_store;
    private String Product_category_In_store;
    private String Product_title;
    private String Product_Photo;
    private Double Product_offer;
    private Integer StoreOwner_id; // foreign key on store owner

    public void setId(Integer id) {
        this.product_ID = id;
    }
    public Integer getId() {
        return this.product_ID;
    }



    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice() { return  this.price; }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() { return  this.category; }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantity() { return  this.quantity; }


    public void setBrandName(String brandName) {
        this.BrandName = brandName;
    }
    public String getBrandName() { return  this.BrandName; }

    public void setAdminId(Integer AdminId){ this.adminId = AdminId;}
    public Integer getAdminId(){return this.adminId ;}


    public String getStore_Name() {
        return store_Name;
    }

    public void setStore_Name(String store_Name) {
        this.store_Name = store_Name;
    }

    public String getProduct_Description() {
        return Product_Description;
    }

    public void setProduct_Description(String product_Description) {
        Product_Description = product_Description;
    }

    public Double getProduct_price_In_store() {
        return Product_price_In_store;
    }

    public void setProduct_price_In_store(Double product_price_In_store) {
        Product_price_In_store = product_price_In_store;
    }

    public Integer getProduct_Quantity_In_store() {
        return Product_Quantity_In_store;
    }

    public void setProduct_Quantity_In_store(Integer product_Quantity_In_store) {
        Product_Quantity_In_store = product_Quantity_In_store;
    }

    public String getProduct_category_In_store() {
        return Product_category_In_store;
    }

    public void setProduct_category_In_store(String product_category_In_store) {
        Product_category_In_store = product_category_In_store;
    }

    public String getProduct_title() {
        return Product_title;
    }

    public void setProduct_title(String product_title) {
        Product_title = product_title;
    }

    public String getProduct_Photo() {
        return Product_Photo;
    }

    public void setProduct_Photo(String product_Photo) {
        Product_Photo = product_Photo;
    }

    public Double getProduct_offer() {
        return Product_offer;
    }

    public void setProduct_offer(Double product_offer) {
        Product_offer = product_offer;
    }

    public Integer getStoreOwner_id() {
        return StoreOwner_id;
    }

    public void setStoreOwner_id(Integer storeOwner_id) {
        StoreOwner_id = storeOwner_id;
    }
}
