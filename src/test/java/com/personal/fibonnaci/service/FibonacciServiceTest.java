package com.personal.fibonnaci.service;

import com.personal.fibonnaci.model.StorageFibonacci;
import com.personal.fibonnaci.repository.StorageFibonacciRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author juandelgado
 * @project fibonnaci
 */
@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {

    private FibonacciService fibonacciService;

    @Mock
    private StorageFibonacciRepository storageFibonacciRepository;

    @BeforeEach
    public void setUp() {
        fibonacciService = new FibonacciServiceImpl(storageFibonacciRepository);
    }

    @Test
    public void calculateFibonacciTest() {
        when(storageFibonacciRepository.findByNumber(10)).thenReturn(null);
        String result = fibonacciService.calculateFibonacci(10);
        assertEquals(result, "0 1 1 2 3 5 8 13 21 ");
    }

    @Test
    public void calculateFibonacciExistsTest() {
        when(storageFibonacciRepository.findByNumber(10)).thenReturn(buildStorageFibonacci());
        String result = fibonacciService.calculateFibonacci(10);
        assertEquals(result, "0 1 1 2 3 5 8 13 21 ");
    }

    private StorageFibonacci buildStorageFibonacci() {
        StorageFibonacci storageFibonacci = new StorageFibonacci();
        storageFibonacci.setNumber(10);
        storageFibonacci.setAttempts(1);
        storageFibonacci.setRange("0 1 1 2 3 5 8 13 21 ");
        storageFibonacci.setId(1L);
        return storageFibonacci;
    }
}
