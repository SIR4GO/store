package com.platform.platform.Repositories;

import com.platform.platform.Models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository <Admin , Integer> {



}
