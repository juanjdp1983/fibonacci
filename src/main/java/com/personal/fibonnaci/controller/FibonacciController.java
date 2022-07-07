package com.personal.fibonnaci.controller;

import com.personal.fibonnaci.model.StorageFibonacci;
import com.personal.fibonnaci.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author juandelgado
 * @project fibonnaci
 */
@RestController
@RequestMapping("/api/v1")
public class FibonacciController {

    private FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    /**
     * Calcula el fibonacci para un determinado numero que se le proporcione por parametro.
     ***/
    @RequestMapping(value = "/fibonacci/{number}", method = RequestMethod.GET)
    public ResponseEntity<String> getNesimoFib(@PathVariable("number") int number) {
        return ResponseEntity.ok(fibonacciService.calculateFibonacci(number));
    }

    /**
     * Obtiene la lista de los numero que se les ha calculado el fibonacci, ordenados por que tiene mayor numero de consultas.
     ***/
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResponseEntity<List<StorageFibonacci>> getStatistics() {
        return ResponseEntity.ok(fibonacciService.getStadistic());
    }
}
