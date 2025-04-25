package com.example.bmicalc_v2;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class BMICalculatorTest {

    private BMICalculator calculator;

    @Before
    public void setUp() {
        calculator = new BMICalculator();
    }

    @Test
    public void calculateBMI_WithNormalValues_ReturnsCorrectBMI() {
        double weight = 70.0;
        double heightCm = 175.0;
        double expectedBMI = 22.86;
        double delta = 0.01;

        double result = calculator.calculateBMI(weight, heightCm);

        assertEquals(expectedBMI, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateBMI_WithZeroWeight_ThrowsException() {
        calculator.calculateBMI(0, 175.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateBMI_WithZeroHeight_ThrowsException() {
        calculator.calculateBMI(70.0, 0);
    }

    @Test
    public void getBMIStatus_Underweight_ReturnsNiedowaga() {
        double bmi = 18.0;
        String expected = "Niedowaga";

        String result = calculator.getBMIStatus(bmi);

        assertEquals(expected, result);
    }

    @Test
    public void getBMIStatus_NormalWeight_ReturnsOptimum() {
        double bmi = 22.0;
        String expected = "Optimum";

        String result = calculator.getBMIStatus(bmi);

        assertEquals(expected, result);
    }

    @Test
    public void getBMIStatus_Overweight_ReturnsNadwaga() {
        double bmi = 27.0;
        String expected = "Nadwaga";

        String result = calculator.getBMIStatus(bmi);

        assertEquals(expected, result);
    }

    @Test
    public void getBMIStatus_Obese_ReturnsOtylosc() {
        double bmi = 32.0;
        String expected = "Otyłość";

        String result = calculator.getBMIStatus(bmi);

        assertEquals(expected, result);
    }
}