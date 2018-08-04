package com.platform.platform.Models;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer admin_ID;
  private String user_name;
  private  String name;
  private String email;
  private String password;




  public void setId(Integer id) {
    this.admin_ID = id;
  }
  public Integer getId() {
    return this.admin_ID;
  }



  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return this.name;
  }

  public void setUserName(String username) {
    this.user_name = username;
  }
  public String getUserName() {
    return  this.user_name;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  public String getPassword() {
    return  this.password;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public String getEmail() {
    return email;
  }



}
