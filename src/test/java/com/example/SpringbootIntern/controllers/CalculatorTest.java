package com.example.SpringbootIntern.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void testAddTest() {
        assertEquals(10, calc.add(4, 6));
    }

    @Test
    public void testSubTest() {
        assertEquals(2, calc.sub(5, 3));
    }

    @Test
    public void testMulTest() {
        assertEquals(15, calc.mul(3, 5));
    }

    @Test
    public void testDivTest() {
        assertEquals(5, calc.div(10, 2));
    }

    @Test
    public void testDivByZeroTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.div(10, 0));
        assertEquals("Division by zero", exception.getMessage());
    }
}
