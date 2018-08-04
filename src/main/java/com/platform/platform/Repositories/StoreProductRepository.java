package com.platform.platform.Repositories;

import com.platform.platform.Models.StoreProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreProductRepository extends CrudRepository<StoreProduct,Integer>{

}
