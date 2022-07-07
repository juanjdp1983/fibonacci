package com.personal.fibonnaci.service;

import com.personal.fibonnaci.model.StorageFibonacci;

import java.util.List;

/**
 * @author juandelgado
 * @project fibonnaci
 */
public interface FibonacciService {
    String calculateFibonacci(int number);

    List<StorageFibonacci> getStadistic();

}
