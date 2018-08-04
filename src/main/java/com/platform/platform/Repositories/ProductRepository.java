package com.platform.platform.Repositories;

import com.platform.platform.Models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository <Product ,Integer> {

}
