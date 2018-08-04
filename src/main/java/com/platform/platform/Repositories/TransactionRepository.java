package com.platform.platform.Repositories;

import com.platform.platform.Models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
