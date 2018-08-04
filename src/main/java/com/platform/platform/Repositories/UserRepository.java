package com.platform.platform.Repositories;

import com.platform.platform.Models.StartModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <StartModel,Integer> {


}
