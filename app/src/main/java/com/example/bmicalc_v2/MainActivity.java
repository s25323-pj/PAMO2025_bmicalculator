package com.example.bmicalc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public EditText weightInput, heightInput;
    public TextView resultText;
    private BMICalculator bmiCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmiCalculator = new BMICalculator();

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        resultText = findViewById(R.id.resultText);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button caloriesButton = findViewById(R.id.caloriesButton);
        Button recipesButton = findViewById(R.id.recipesButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        caloriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalorieCalculatorActivity.class);
                startActivity(intent);
            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            resultText.setText("Podaj poprawne wartości!");
            return;
        }

        try {
            double weight = Double.parseDouble(weightStr);
            double heightCm = Double.parseDouble(heightStr);

            double bmi = bmiCalculator.calculateBMI(weight, heightCm);
            String status = bmiCalculator.getBMIStatus(bmi);

            resultText.setText(String.format("Twoje BMI: %.2f (%s)", bmi, status));
        } catch (NumberFormatException e) {
            resultText.setText("Nieprawidłowy format liczby!");
        } catch (IllegalArgumentException e) {
            resultText.setText(e.getMessage());
        }
    }
}