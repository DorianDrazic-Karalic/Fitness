package edu.rit.croatia.swen383.g1.dm.model;

import java.util.Locale;
import java.util.Map;

/**
 * BasicFood
 */
public class BasicFood extends Food {

    public BasicFood(String name, double calorie, double fat, double carbs, double protein) {
        this.name = name;
        this.calories = calorie;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public BasicFood() {

    }

    @Override
    public String toCSVString() {
        return String.format(Locale.US, "b,%s,%.2f,%.2f,%.2f,%.2f", name, calories, fat, carbs, protein);
    }

    @Override
    public void setIngredients(Map<Food, Double> ingredients) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIngredients'");
    }

    @Override
    public Map<Food, Double> getIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredients'");
    }

    @Override
    public void add(Food ingredient, double count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(Food ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    void calculateNutritionalValues() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateNutritionalValues'");
    }
}