package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.model.Food;
import edu.rit.croatia.swen383.g1.dm.model.FoodModel;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//adds new basic food to foodModel list and foods.csv
public class WindowAddBasicFoodButtonHandler implements EventHandler<ActionEvent> {

    FoodModel foodModel;
    DietManagerView view;

    public WindowAddBasicFoodButtonHandler(FoodModel foodModel, DietManagerView view) {
        this.foodModel = foodModel;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        String name = view.getNameField().getText();
        String caloriesValue = view.getCaloriesField().getText();
        String fatValue = view.getFatField().getText();
        String carbsValue = view.getCarbsField().getText();
        String proteinValue = view.getProteinField().getText();

        if (this.validate(name, caloriesValue, fatValue, carbsValue, proteinValue)) {
            double calories = Double.parseDouble(caloriesValue);
            double fat = Double.parseDouble(fatValue);
            double carbs = Double.parseDouble(carbsValue);
            double protein = Double.parseDouble(proteinValue);

            this.foodModel.addFood(name, calories, fat, carbs, protein);
            ConfirmationMessageHandler.alert("Basic food: " + name + " has been added successfully");
        }
    }

    public boolean validate(String name, String calories, String fat, String carbs, String protein) {

        if (name.length() > 50) {
            ErrorMessageHandler.alert("Name cannot be empty and must be less than 50 characters.");
            return false;
        }

        if (name.isEmpty() || calories.isEmpty() || fat.isEmpty() || carbs.isEmpty() || protein.isEmpty()) {
            ErrorMessageHandler.alert("All fields must be filled in.");
            return false;
        }

        if (name.matches(".*\\d.*")) {
            ErrorMessageHandler.alert("Name field mustn't contain numbers.");
            return false;
        }

        try {
            double caloriesValue = Double.parseDouble(calories);
            double fatValue = Double.parseDouble(fat);
            double carbsValue = Double.parseDouble(carbs);
            double proteinValue = Double.parseDouble(protein);
            if (caloriesValue < 0 || fatValue < 0 || carbsValue < 0 || proteinValue < 0) {
                ErrorMessageHandler.alert("Value cannot be negative.");
                return false;
            }
        } catch (NumberFormatException e) {
            ErrorMessageHandler.alert("Values must be valid numbers.");
            return false;
        }

        if (!name.matches("[a-zA-Z0-9\\s]+")) {
            ErrorMessageHandler.alert("Name must not have special characters");
            return false;
        }

        for (Food food : foodModel.getFoodList()) {
            if (food.getName().equalsIgnoreCase(name)) {
                ErrorMessageHandler.alert("Food item already exists: " + food.getName());
                return false;
            }
        }

        return true;
    }

}
