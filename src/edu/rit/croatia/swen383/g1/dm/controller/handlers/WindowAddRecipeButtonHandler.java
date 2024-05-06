package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.model.Food;
import edu.rit.croatia.swen383.g1.dm.model.FoodModel;
import edu.rit.croatia.swen383.g1.dm.model.Recipe;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//adds new Recipe to foodModel list and foods.csv
public class WindowAddRecipeButtonHandler implements EventHandler<ActionEvent> {
    DietManagerView view;
    FoodModel foodModel;

    public WindowAddRecipeButtonHandler(DietManagerView view, FoodModel foodModel) {
        this.view = view;
        this.foodModel = foodModel;
    }

    @Override
    public void handle(ActionEvent event) {
        String name = view.getRecipeNameField().getText();
        if (this.validate(name)) {
            this.foodModel.addFood(name);
            ConfirmationMessageHandler.alert("Recipe: " + name + " has been added successfully");
        }

    }

    public boolean validate(String name) {

        if (name == null || name.isEmpty() || name.length() > 50) {
            ErrorMessageHandler.alert("Recipe name cannot be empty and must be under 50 characters");
            return false;
        }

        for (Food food : foodModel.getFoodList()) {
            if (food instanceof Recipe && food.getName().equals(name)) {
                ErrorMessageHandler.alert("Recipe already exists.");
                return false;
            }
        }

        if (!name.matches("[a-zA-Z0-9\\\\s]+")) {
            ErrorMessageHandler.alert("Recipe name must not contain any special characters.");
            return false;
        }

        return true;
    }

}
