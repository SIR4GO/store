package com.platform.platform.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // surrogate key
    private Integer Id;

    @Column(unique=true)
    @NotNull
    private String user_Id;

    private String productName;
    private Integer quantity;
    private Double price;
    private Double TotalPriceOfProductUnits;



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPriceOfProductUnits() {
        return TotalPriceOfProductUnits;
    }

    public void setTotalPriceOfProductUnits(Double totalPriceOfProductUnits) {
        TotalPriceOfProductUnits = totalPriceOfProductUnits;
    }
}
