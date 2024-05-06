package edu.rit.croatia.swen383.g1.dm.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Recipe extends Food {

    public Recipe(String name, Map<Food, Double> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.calculateNutritionalValues();
    }

    public Recipe() {
        this.ingredients = new HashMap<>();
    }

    /************ GETTERS AND SETTERS******************* */
    @Override
    public void setIngredients(Map<Food, Double> ingredients) {
        this.ingredients = ingredients;
        this.calculateNutritionalValues();
    }

    @Override
    Map<Food, Double> getIngredients() {
        return ingredients;
    }

    /************* METHODS*********** */

    @Override
    public String toCSVString() {
        StringBuilder sb = new StringBuilder("r,").append(name);
        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        df.setMaximumFractionDigits(2);

        for (Entry<Food, Double> entry : ingredients.entrySet()) {
            // Formatting the double value using DecimalFormat with Locale.US
            String formattedValue = df.format(entry.getValue());

            // Appending formatted key and value
            sb.append(",").append(entry.getKey().getName()).append(",").append(formattedValue);
        }
        return sb.toString();

    }

    @Override
    public void add(Food ingredient, double count) {
        this.ingredients.put(ingredient, count);
        calculateNutritionalValues();
    }

    @Override
    public void remove(Food ingredient) {
        this.ingredients.remove(ingredient);
        calculateNutritionalValues();
    }

    @Override
    void calculateNutritionalValues() {
        this.calories = 0;
        this.fat = 0;
        this.carbs = 0;
        this.protein = 0;

        for (Map.Entry<Food, Double> entry : ingredients.entrySet()) {
            Food ingredient = entry.getKey();
            double quantity = entry.getValue();

            this.calories += ingredient.getCalories() * quantity;
            this.fat += ingredient.getFat() * quantity;
            this.carbs += ingredient.getCarb() * quantity;
            this.protein += ingredient.getProtein() * quantity;

        }
    }

}
