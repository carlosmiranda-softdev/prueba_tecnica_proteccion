package com.proteccion.pruebatecnica;

import com.proteccion.pruebatecnica.model.Fibonacci;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFibonacci {
    @Test
    public void testGettersAndSettersFibonacci() {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.setSize(4);
        int n = fibonacci.getSize();

        assertEquals(4, n);

        ArrayList<Number> listExample = new ArrayList<Number>();
        listExample.add(2);
        listExample.add(3);

        fibonacci.setSerie(listExample);

        ArrayList<Number> expectedList = new ArrayList<Number>();
        expectedList.add(2);
        expectedList.add(3);

        assertEquals(expectedList, fibonacci.getSerie());
    }

    @Test
    public void testGetFibonacci() {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.execute(2, 3, 4);

        ArrayList<Number> realResult = fibonacci.getSerie();

        ArrayList<Number> expectedResult = new ArrayList<Number>();
        expectedResult.add(21);
        expectedResult.add(13);
        expectedResult.add(8);
        expectedResult.add(5);
        expectedResult.add(3);
        expectedResult.add(2);

        assertEquals(expectedResult, realResult);

    }

    @Test
    public void testGetFibonacciSize() {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.execute(2, 3, 4);

        Number realResult = fibonacci.getSerie().size();

        ArrayList<Number> expectedResult = new ArrayList<Number>();
        expectedResult.add(2);
        expectedResult.add(3);
        expectedResult.add(5);
        expectedResult.add(8);
        expectedResult.add(13);
        expectedResult.add(21);

        assertEquals(expectedResult.size(), realResult);

    }
}
