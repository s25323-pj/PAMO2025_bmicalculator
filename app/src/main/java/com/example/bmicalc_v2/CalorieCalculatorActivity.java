package com.example.bmicalc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalorieCalculatorActivity extends AppCompatActivity {

    private EditText ageInput, weightInput, heightInput;
    private RadioGroup genderGroup;
    private Spinner activityLevelSpinner;
    private TextView calorieResultText;
    private double calculatedCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        genderGroup = findViewById(R.id.genderGroup);
        activityLevelSpinner = findViewById(R.id.activityLevelSpinner);
        calorieResultText = findViewById(R.id.calorieResultText);
        Button calculateButton = findViewById(R.id.calculateCaloriesButton);
        Button showRecipesButton = findViewById(R.id.showRecipesButton);
        Button backButton = findViewById(R.id.backButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevelSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalories();
            }
        });

        showRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalorieCalculatorActivity.this, RecipesActivity.class);
                intent.putExtra("calorieNeeds", calculatedCalories);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calculateCalories() {
        if (isEmpty(ageInput) || isEmpty(weightInput) || isEmpty(heightInput)) {
            calorieResultText.setText("Wypełnij wszystkie pola!");
            return;
        }

        int age = Integer.parseInt(ageInput.getText().toString());
        double weight = Double.parseDouble(weightInput.getText().toString());
        double height = Double.parseDouble(heightInput.getText().toString());

        if (age <= 0 || weight <= 0 || height <= 0) {
            calorieResultText.setText("Wartości muszą być większe od zera!");
            return;
        }

        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            calorieResultText.setText("Wybierz płeć!");
            return;
        }
        boolean isMale = selectedGenderId == R.id.maleRadio;

        double activityFactor = getActivityFactor(activityLevelSpinner.getSelectedItemPosition());

        double bmr;
        if (isMale) {
            bmr = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else {
            bmr = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }

        calculatedCalories = bmr * activityFactor;

        calorieResultText.setText(String.format("Twoje dzienne zapotrzebowanie: %.0f kcal", calculatedCalories));
    }

    private double getActivityFactor(int position) {
        switch (position) {
            case 0:
                return 1.2;
            case 1:
                return 1.375;
            case 2:
                return 1.55;
            case 3:
                return 1.725;
            case 4:
                return 1.9;
            default:
                return 1.2;
        }
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }
}