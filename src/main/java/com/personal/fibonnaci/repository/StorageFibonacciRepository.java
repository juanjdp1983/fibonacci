package com.personal.fibonnaci.repository;

import com.personal.fibonnaci.model.StorageFibonacci;
import org.springframework.data.repository.CrudRepository;

/**
 * @author juandelgado
 * @project fibonnaci
 */
public interface StorageFibonacciRepository extends CrudRepository<StorageFibonacci, Long> {

    StorageFibonacci findByNumber(int number);
}
