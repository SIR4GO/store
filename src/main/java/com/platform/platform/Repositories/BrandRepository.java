package com.platform.platform.Repositories;


import com.platform.platform.Models.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand,Integer> {


}
