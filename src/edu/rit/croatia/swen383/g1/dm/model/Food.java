package edu.rit.croatia.swen383.g1.dm.model;

import java.util.Map;

public abstract class Food {

    protected String name;
    protected double calories;
    protected double fat;
    protected double carbs;
    protected double protein;
    protected Map<Food, Double> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarb() {
        return carbs;
    }

    public void setCarb(double carb) {
        this.carbs = carb;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public abstract void setIngredients(Map<Food, Double> ingredients);

    abstract Map<Food, Double> getIngredients();

    abstract String toCSVString();

    abstract void add(Food ingredient, double count);

    abstract void remove(Food ingredient);

    abstract void calculateNutritionalValues();
}
