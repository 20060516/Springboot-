package com.example.SpringbootIntern.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator cal = new Calculator();

    @Test
    public void testAddTest() {
        assertEquals(10, cal.add(4, 6));
    }

    @Test
    public void testSubTest() {
        assertEquals(2, cal.sub(5, 3));
    }

    @Test
    public void testMulTest() {
        assertEquals(15, cal.mul(3, 5));
    }

    @Test
    public void testDivTest() {
        assertEquals(5, cal.div(10, 2));
    }

    @Test
    public void testDivByZeroTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cal.div(10, 0));
        assertEquals("Division by zero", exception.getMessage());
    }
}
