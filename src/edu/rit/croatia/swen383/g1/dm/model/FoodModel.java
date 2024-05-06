package edu.rit.croatia.swen383.g1.dm.model;

import java.util.ArrayList;
import java.util.Iterator;

import edu.rit.croatia.swen383.g1.dm.observer.Subject;

public class FoodModel extends Subject {

    private ArrayList<Food> foodList;
    private CSVManager foodCSVManager;
    String filename;

    public FoodModel() {
        foodList = new ArrayList<>();
        this.filename = "foods.csv";
        this.foodCSVManager = new FoodCSVManager(this);
        this.foodCSVManager.loadEntries(filename);
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public void addFood(String name, double calories, double fat, double carbs, double protein) {
        Food food = new BasicFood(name, calories, fat, carbs, protein);
        foodList.add(food);
        this.foodCSVManager.saveEntries(this.filename);
        notifyObserver();
    }

    public void addIngredient(String name, Food food, double count) {
        for (Food food2 : foodList) {
            if (food2 instanceof Recipe && food2.getName().equals(name)) {
                food2.add(food, count);
            }
        }
        this.foodCSVManager.saveEntries(this.filename);
        notifyObserver();

    }

    public void addFood(String name) {
        Food recipe = new Recipe();
        recipe.setName(name);
        foodList.add(recipe);
        this.foodCSVManager.saveEntries(this.filename);
        notifyObserver();
    }

    public void removeFood(String foodName) {
        Iterator<Food> iterator = foodList.iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (food.getName().equals(foodName)) {
                iterator.remove(); // Remove the current food item
            }
        }
        this.foodCSVManager.saveEntries(this.filename);
        notifyObserver();
    }

}
