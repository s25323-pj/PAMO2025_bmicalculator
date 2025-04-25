package com.example.bmicalc_v2;

public class BMICalculator {

    public double calculateBMI(double weight, double heightCm) {
        if (weight <= 0 || heightCm <= 0) {
            throw new IllegalArgumentException("Waga i wzrost muszą być większe od zera!");
        }

        double height = heightCm / 100.0;
        return weight / (height * height);
    }

    public String getBMIStatus(double bmi) {
        if (bmi < 18.5) return "Niedowaga";
        else if (bmi < 25) return "Optimum";
        else if (bmi < 30) return "Nadwaga";
        else return "Otyłość";
    }
}