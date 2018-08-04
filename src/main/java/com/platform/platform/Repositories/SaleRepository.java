package com.platform.platform.Repositories;


import com.platform.platform.Models.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository< Sale , Long> {
}
