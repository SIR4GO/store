package com.platform.platform.Repositories;

import com.platform.platform.Models.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository <Store, Integer> {

}
