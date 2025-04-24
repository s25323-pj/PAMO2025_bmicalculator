package com.example.bmicalc_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private TextView recipeTitle, recipeIngredients, recipeInstructions, recipeCalories;
    private RadioGroup dietTypeGroup;
    private double calorieNeeds = 2000;
    private List<Recipe> standardRecipes = new ArrayList<>();
    private List<Recipe> vegetarianRecipes = new ArrayList<>();
    private List<Recipe> veganRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        if (getIntent().hasExtra("calorieNeeds")) {
            calorieNeeds = getIntent().getDoubleExtra("calorieNeeds", 2000);
        }

        recipeTitle = findViewById(R.id.recipeTitle);
        recipeIngredients = findViewById(R.id.recipeIngredients);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        recipeCalories = findViewById(R.id.recipeCalories);
        dietTypeGroup = findViewById(R.id.dietTypeGroup);
        Button backButton = findViewById(R.id.backToMainButton);

        initializeRecipes();

        dietTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateRecipeDisplay();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateRecipeDisplay();
    }

    private void initializeRecipes() {
        standardRecipes.add(new Recipe(
                "Kurczak z warzywami",
                "- 150g piersi z kurczaka\n- 100g brokułów\n- 100g marchewki\n- 1 łyżka oliwy z oliwek\n- przyprawy (sól, pieprz, czosnek)",
                "1. Pokrój kurczaka na kawałki.\n2. Umyj i pokrój warzywa.\n3. Rozgrzej patelnię z oliwą.\n4. Dodaj kurczaka i smaż 5 minut.\n5. Dodaj warzywa i przyprawy.\n6. Smaż jeszcze 10 minut mieszając.",
                450
        ));
        standardRecipes.add(new Recipe(
                "Makaron z sosem bolognese",
                "- 100g makaronu pełnoziarnistego\n- 100g mielonej wołowiny\n- 1 cebula\n- 1 marchewka\n- 2 łyżki przecieru pomidorowego\n- przyprawy (sól, pieprz, oregano)",
                "1. Ugotuj makaron zgodnie z instrukcją.\n2. Na patelni podsmaż cebulę.\n3. Dodaj mięso i smaż do zrumienienia.\n4. Dodaj startą marchewkę i przecier.\n5. Dopraw i gotuj 10 minut.\n6. Połącz z makaronem.",
                600
        ));

        vegetarianRecipes.add(new Recipe(
                "Sałatka z fetą i quinoa",
                "- 100g quinoa\n- 50g sera feta\n- 1 pomidor\n- 1 ogórek\n- garść rukoli\n- oliwa z oliwek\n- sok z cytryny",
                "1. Ugotuj quinoa zgodnie z instrukcją.\n2. Pokrój warzywa w kostkę.\n3. Pokrusz fetę.\n4. Połącz wszystkie składniki.\n5. Skrop oliwą i sokiem z cytryny.",
                400
        ));
        vegetarianRecipes.add(new Recipe(
                "Risotto z grzybami",
                "- 100g ryżu arborio\n- 150g pieczarek\n- 1 cebula\n- 50ml białego wina\n- 300ml bulionu warzywnego\n- 2 łyżki parmezanu\n- masło",
                "1. Podsmaż posiekaną cebulę na maśle.\n2. Dodaj ryż i smaż 2 minuty.\n3. Wlej wino i poczekaj aż odparuje.\n4. Stopniowo dodawaj bulion, mieszając.\n5. Pod koniec dodaj grzyby i parmezan.",
                550
        ));

        veganRecipes.add(new Recipe(
                "Curry z ciecierzycą",
                "- 200g ciecierzycy z puszki\n- 1 cebula\n- 1 papryka\n- 100ml mleka kokosowego\n- 2 łyżki pasty curry\n- ryż basmati",
                "1. Podsmaż cebulę i paprykę.\n2. Dodaj pastę curry i ciecierzycę.\n3. Wlej mleko kokosowe.\n4. Gotuj 10 minut.\n5. Podawaj z ryżem.",
                500
        ));
        veganRecipes.add(new Recipe(
                "Makaron z sosem z awokado",
                "- 100g makaronu pełnoziarnistego\n- 1 dojrzałe awokado\n- sok z 1/2 cytryny\n- 2 łyżki oliwy\n- garść świeżej bazylii\n- czosnek",
                "1. Ugotuj makaron.\n2. Zblenduj awokado z pozostałymi składnikami.\n3. Wymieszaj makaron z sosem.\n4. Posyp świeżymi ziołami.",
                450
        ));
    }

    private void updateRecipeDisplay() {
        List<Recipe> recipeList;
        int selectedDietId = dietTypeGroup.getCheckedRadioButtonId();

        if (selectedDietId == R.id.veganRadio) {
            recipeList = veganRecipes;
        } else if (selectedDietId == R.id.vegetarianRadio) {
            recipeList = vegetarianRecipes;
        } else {
            recipeList = standardRecipes;
        }

        Recipe bestRecipe = findBestRecipe(recipeList, calorieNeeds);

        recipeTitle.setText(bestRecipe.getTitle());
        recipeIngredients.setText(bestRecipe.getIngredients());
        recipeInstructions.setText(bestRecipe.getInstructions());
        recipeCalories.setText(String.format("Kalorie: %d kcal", bestRecipe.getCalories()));
    }

    private Recipe findBestRecipe(List<Recipe> recipes, double targetCalories) {
        Recipe bestRecipe = recipes.get(0);
        double minDifference = Math.abs(recipes.get(0).getCalories() - targetCalories);

        for (Recipe recipe : recipes) {
            double difference = Math.abs(recipe.getCalories() - targetCalories);
            if (difference < minDifference) {
                minDifference = difference;
                bestRecipe = recipe;
            }
        }

        return bestRecipe;
    }

    private static class Recipe {
        private String title;
        private String ingredients;
        private String instructions;
        private int calories;

        public Recipe(String title, String ingredients, String instructions, int calories) {
            this.title = title;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.calories = calories;
        }

        public String getTitle() { return title; }
        public String getIngredients() { return ingredients; }
        public String getInstructions() { return instructions; }
        public int getCalories() { return calories; }
    }
}
