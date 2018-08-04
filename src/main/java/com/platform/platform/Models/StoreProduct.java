package com.platform.platform.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoreProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer counter;

    private String store_Name; // foreign key on store
    private String product_Name; // foreign key on product
    private String Product_Description;
    private Double Product_price;
    private Integer Product_Quantity;
    private String Product_category;
    private String Product_title;
    private String Product_brand;
    private String Product_Photo;
    private Double Product_offer;
    private Integer StoreOwner_id; // foreign key on store owner

    public StoreProduct() {
    }

    public String getStore_Name() {
        return store_Name;
    }

    public void setStore_Name(String store_Name) {
        this.store_Name = store_Name;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public Integer getProduct_Quantity() {
        return Product_Quantity;
    }

    public void setProduct_Quantity(Integer product_Quantity) {
        Product_Quantity = product_Quantity;
    }
    public String getProduct_Description() {
        return Product_Description;
    }

    public void setProduct_Description(String product_Description) {
        Product_Description = product_Description;
    }

    public Double getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(Double product_price) {
        Product_price = product_price;
    }

    public String getProduct_category() {
        return Product_category;
    }

    public void setProduct_category(String product_category) {
        Product_category = product_category;
    }

    public String getProduct_title() {
        return Product_title;
    }

    public void setProduct_title(String product_title) {
        Product_title = product_title;
    }

    public String getProduct_brand() {
        return Product_brand;
    }

    public void setProduct_brand(String product_brand) {
        Product_brand = product_brand;
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
