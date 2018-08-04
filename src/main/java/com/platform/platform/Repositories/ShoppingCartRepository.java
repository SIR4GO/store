package com.platform.platform.Repositories;

import com.platform.platform.Models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository <ShoppingCart, Integer> {
}
