package com.personal.fibonnaci.service;

import com.personal.fibonnaci.model.StorageFibonacci;
import com.personal.fibonnaci.repository.StorageFibonacciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author juandelgado
 * @project fibonnaci
 */
@Service
public class FibonacciServiceImpl implements FibonacciService {

    private StorageFibonacciRepository storageFibonacciRepository;

    @Autowired
    public FibonacciServiceImpl(StorageFibonacciRepository storageFibonacciRepository) {
        this.storageFibonacciRepository = storageFibonacciRepository;
    }

    @Override
    public String calculateFibonacci(int number) {
        if (number < 2) {
            return String.valueOf(number);
        }

        StorageFibonacci storageFibonacci = this.getData(number);
        if (storageFibonacci != null) {
            //Se modifica el intento en + 1 y se devuelve el resultado que ya se tiene registrado
            this.modifyData(storageFibonacci);
            return storageFibonacci.getRange();
        }

        String result = this.getFibonacci(number)
                .orElseThrow(() -> new IllegalArgumentException("Can't calculate fibonacci for the number ".concat(String.valueOf(number))));

        //Se guarda en la BD
        this.saveData(result, number);

        return result;
    }

    @Override
    public List<StorageFibonacci> getStadistic() {
        List<StorageFibonacci> storageFibonacciList = (List<StorageFibonacci>) storageFibonacciRepository.findAll();
        storageFibonacciList.sort(Comparator.comparing(StorageFibonacci::getAttempts).reversed());
        return storageFibonacciList;
    }

    private Optional<String> getFibonacci(int number) {
        StringBuilder fibonacciResult = new StringBuilder();
        Long n0 = 0L;
        Long n1 = 1L;
        Long fib = 0L;
        int max = number;
        int[] arrNumbers = IntStream.rangeClosed(1, (max - 1)).toArray();

        for (int arrElement : arrNumbers) {
            fibonacciResult.append(n0);
            fibonacciResult.append(" ");
            fib = n0 + n1;
            n0 = n1;
            n1 = fib;
        }

        if (fibonacciResult.length() > 0) {
            return Optional.of(fibonacciResult.toString());
        } else {
            return Optional.empty();
        }
    }

    private void saveData(String result, int number) {
        StorageFibonacci storageFibonacci = new StorageFibonacci();
        storageFibonacci.setEnable(true);
        storageFibonacci.setRange(result);
        storageFibonacci.setNumber(number);
        storageFibonacci.setAttempts(1);
        storageFibonacciRepository.save(storageFibonacci);
    }

    private void modifyData(StorageFibonacci storageFibonacci) {
        storageFibonacci.setAttempts(storageFibonacci.getAttempts() + 1);
        storageFibonacciRepository.save(storageFibonacci);
    }

    private StorageFibonacci getData(int number) {
        return storageFibonacciRepository.findByNumber(number);
    }


}
