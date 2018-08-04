package com.platform.platform.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Brand {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer brand_ID;
    @Column(unique=true)
    @NotNull
    private  String name;
    private  String date;
    private  String category;
    private  Integer adminId; // Fk




    public void setId(Integer id) {
        this.brand_ID = id;
    }
    public Integer getId() {
        return this.brand_ID;
    }



    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String geDate() { return  this.date; }

    public void setCategory(String Category) {
        this.category = Category;
    }
    public String getCategory() { return  this.category; }

    public void setAdminId(Integer AdminId){ this.adminId = AdminId;}
    public Integer getAdminId(){return this.adminId ;}






}
