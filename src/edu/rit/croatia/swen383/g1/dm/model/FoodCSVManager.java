package edu.rit.croatia.swen383.g1.dm.model;

import java.util.ArrayList;
import java.util.List;

/*This is a FoodCSV class that will hold all needed methods */

public class FoodCSVManager extends CSVManager {

    private FoodModel foodModel;

    public FoodCSVManager(FoodModel foodModel) {
        this.foodModel = foodModel;
    }

    // Method to load foods from foods.csv file
    @Override
    public void loadEntries(String fileName) {
        List<String[]> records = this.loadCSV(fileName); // Call CSVManager's loadCSV method

        for (String[] record : records) {
            if (!isValidDataStructure(record)) {
                continue;
            }
            // Determine if it's a basic food or a recipe
            String type = record[0].trim();
            switch (type) {
                case "b":
                    Food basicFood = parseBasicFood(record);
                    if (basicFood != null) {
                        this.foodModel.getFoodList().add(basicFood);
                    }
                    break;
                case "r":
                    Food recipe = parseRecipe(record);
                    if (recipe != null) {
                        this.foodModel.getFoodList().add(recipe);

                    }
                    break;
                default:
                    // Handle unrecognized types
                    System.err.println("Unknown type encountered: " + type);
                    break;
            }
        }
    }

    // Method to add a new food item (either a basic food or a recipe) and save it
    // to the CSV file
    @Override
    public void saveEntries(String fileName) {
        List<String> lines = new ArrayList<>();
        for (Food basicFood : foodModel.getFoodList()) {
            lines.add(basicFood.toCSVString());
        }
        this.saveCSV(fileName, lines);
    }

    @Override
    public boolean isValidDataStructure(String[] parts) {
        if (parts.length < 2) {
            return false;
        }
        if (parts[0].trim().equals("b") && parts.length != 6) {
            return false;
        }
        return true;
    }

    // Method to parse a basic food from CSV record
    private Food parseBasicFood(String[] record) {
        if (record.length == 6) {
            try {
                String name = record[1].trim();
                double calories = Double.parseDouble(record[2].trim());
                double fat = Double.parseDouble(record[3].trim());
                double carb = Double.parseDouble(record[4].trim());
                double protein = Double.parseDouble(record[5].trim());

                if (validateFood(name, calories, fat, carb, protein)) {
                    Food basicFood = new BasicFood();
                    basicFood.setName(name);
                    basicFood.setCalories(calories);
                    basicFood.setFat(fat);
                    basicFood.setCarb(carb);
                    basicFood.setProtein(protein);
                    return basicFood;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in basic food record.");
            }
        }
        return null;
    }

    // Method to parse a recipe from CSV record
    private Food parseRecipe(String[] record) {
        if (record.length > 2 && record.length % 2 == 0) {
            String name = record[1].trim();
            Food recipe = new Recipe();
            recipe.setName(name);

            for (int i = 2; i < record.length; i += 2) {
                String foodName = record[i].trim();
                double count = Double.parseDouble(record[i + 1].trim());

                // Find referenced food by name
                Food referencedFood = findItemByName(foodName, foodModel.getFoodList(), "f");
                if (referencedFood != null) {
                    recipe.add(referencedFood, count);
                } else {
                    System.err.println("Referenced food not found for recipe: " + name);
                    return null;
                }
            }

            return recipe;
        }
        return null;
    }

    /* Method that checks if the food name and values are valid */
    public static boolean validateFood(String name, double calories, double fat, double carb, double protein) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Food name must not be empty.");
            return false;
        }
        if (calories < 0 || fat < 0 || carb < 0 || protein < 0) {
            System.out.println("Values cannot be negative.");
            return false;
        }
        return true;
    }

}