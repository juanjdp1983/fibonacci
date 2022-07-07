package com.personal.fibonnaci.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.fibonnaci.model.StorageFibonacci;
import com.personal.fibonnaci.service.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author juandelgado
 * @project fibonnaci
 */
@ExtendWith(MockitoExtension.class)
public class FibonacciControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private FibonacciController fibonacciController;

    @Mock
    FibonacciService fibonacciService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        fibonacciController = new FibonacciController(fibonacciService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fibonacciController).build();
    }

    @Test
    public void fibonacciTest() throws Exception {
        when(fibonacciService.calculateFibonacci(10)).thenReturn("0 1 1 2 3 5 8 13 21 ");

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fibonacci/10"))
                .andExpect(status().isOk()).andExpect(content().string("0 1 1 2 3 5 8 13 21 "));

    }

    @Test
    public void fibonacciStatisticsTest() throws Exception {
        when(fibonacciService.getStadistic()).thenReturn(buildStorageFibonacciList());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/statistics"))
                .andExpect(status().isOk()).andExpect(content().string(MAPPER.writeValueAsString(buildStorageFibonacciList())));

    }

    private List<StorageFibonacci> buildStorageFibonacciList() {
        StorageFibonacci storageFibonacciL1 = new StorageFibonacci();
        storageFibonacciL1.setNumber(10);
        storageFibonacciL1.setAttempts(1);
        storageFibonacciL1.setRange("0 1 1 2 3 5 8 13 21 ");
        storageFibonacciL1.setId(1L);

        StorageFibonacci storageFibonacciL2 = new StorageFibonacci();
        storageFibonacciL2.setNumber(25);
        storageFibonacciL2.setAttempts(2);
        storageFibonacciL2.setRange("0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 ");
        storageFibonacciL2.setId(2L);

        List<StorageFibonacci> storageFibonacciList = new ArrayList<>();
        storageFibonacciList.add(storageFibonacciL1);
        storageFibonacciList.add(storageFibonacciL2);

        return storageFibonacciList;
    }


}
