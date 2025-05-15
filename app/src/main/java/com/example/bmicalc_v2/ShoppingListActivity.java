package com.example.bmicalc_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final List<ShoppingItem> shoppingItems = new ArrayList<>();
    private List<RecipesActivity.Recipe> standardRecipes;
    private List<RecipesActivity.Recipe> vegetarianRecipes;
    private List<RecipesActivity.Recipe> veganRecipes;
    private TextView recipeNameText;

    private String selectedRecipeType = "standard";
    private int selectedRecipeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        if (getIntent().hasExtra("recipeType")) {
            selectedRecipeType = getIntent().getStringExtra("recipeType");
        }
        if (getIntent().hasExtra("recipeIndex")) {
            selectedRecipeIndex = getIntent().getIntExtra("recipeIndex", 0);
        }

        initializeRecipesFromRecipesActivity();

        recyclerView = findViewById(R.id.shoppingRecyclerView);
        RadioGroup recipeSelector = findViewById(R.id.recipeSelector);
        recipeNameText = findViewById(R.id.recipeNameText); // Upewnij się, że masz to w layoucie
        Button backButton = findViewById(R.id.backButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setDefaultSelection(recipeSelector);

        recipeSelector.setOnCheckedChangeListener((group, checkedId) -> {
            updateShoppingList(checkedId);
        });

        backButton.setOnClickListener(v -> finish());

        updateShoppingList(recipeSelector.getCheckedRadioButtonId());
    }

    private void setDefaultSelection(RadioGroup recipeSelector) {
        RadioButton radioButton;
        if (selectedRecipeType.equals("vegan")) {
            radioButton = findViewById(R.id.veganRecipeRadio);
        } else if (selectedRecipeType.equals("vegetarian")) {
            radioButton = findViewById(R.id.vegetarianRecipeRadio);
        } else {
            radioButton = findViewById(R.id.standardRecipeRadio);
        }
        radioButton.setChecked(true);
    }

    private void initializeRecipesFromRecipesActivity() {
        standardRecipes = RecipesActivity.getStandardRecipes();
        vegetarianRecipes = RecipesActivity.getVegetarianRecipes();
        veganRecipes = RecipesActivity.getVeganRecipes();
    }

    private void updateShoppingList(int checkedId) {
        shoppingItems.clear();

        RecipesActivity.Recipe selectedRecipe;

        if (checkedId == R.id.veganRecipeRadio) {
            selectedRecipe = veganRecipes.get(selectedRecipeIndex);
        } else if (checkedId == R.id.vegetarianRecipeRadio) {
            selectedRecipe = vegetarianRecipes.get(selectedRecipeIndex);
        } else {
            selectedRecipe = standardRecipes.get(selectedRecipeIndex);
        }

        if (recipeNameText != null) {
            recipeNameText.setText(selectedRecipe.getTitle());
        }

        String ingredients = selectedRecipe.getIngredients();
        String[] ingredientLines = ingredients.split("\n");

        for (String ingredient : ingredientLines) {
            String cleanedIngredient = ingredient.replace("- ", "").trim();
            if (!cleanedIngredient.isEmpty()) {
                shoppingItems.add(new ShoppingItem(cleanedIngredient, false));
            }
        }

        ShoppingListAdapter adapter = new ShoppingListAdapter(this, shoppingItems);
        recyclerView.setAdapter(adapter);
    }
}