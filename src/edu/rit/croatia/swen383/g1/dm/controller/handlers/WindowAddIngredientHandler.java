package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.model.BasicFood;
import edu.rit.croatia.swen383.g1.dm.model.Food;
import edu.rit.croatia.swen383.g1.dm.model.FoodModel;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WindowAddIngredientHandler implements EventHandler<ActionEvent> {
    private DietManagerView view;
    private FoodModel foodModel;

    public WindowAddIngredientHandler(DietManagerView view, FoodModel foodModel) {
        this.view = view;
        this.foodModel = foodModel;
    }

    @Override
    public void handle(ActionEvent event) {
        String recipeName = view.getRecipeNameField().getText();
        Food selectedIngredient = view.getIngredientsListView().getSelectionModel().getSelectedItem();
        String amountText = view.getAmountField().getText();

        if (validate(recipeName, selectedIngredient, amountText)) {
            double amount = Double.parseDouble(amountText);
            this.foodModel.addIngredient(recipeName, selectedIngredient, amount);
            ConfirmationMessageHandler.alert(
                    "Ingredient: " + selectedIngredient.getName() + " has been added successfully to " + recipeName);
        }
    }

    public boolean validate(String name, Food ingredient, String amount) {
        if (ingredient == null) {
            ErrorMessageHandler.alert("Select an ingredient.");
            return false;
        }

        if (amount.isEmpty()) {
            ErrorMessageHandler.alert("Specify the ingredient amount.");
            return false;
        }

        try {
            double amount2 = Double.parseDouble(amount);
            if (amount2 <= 0) {
                ErrorMessageHandler.alert("Ingredient amount must be a number larger than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            ErrorMessageHandler.alert("Invalid amount format, should be only numerical.");
            return false;
        }

        if (!foodModel.getFoodList().contains(ingredient)) {
            ErrorMessageHandler.alert("Selected ingredient is not valid");
            return false;
        }

        if (!(ingredient instanceof BasicFood)) {
            ErrorMessageHandler.alert("Ingredient is not a basic food");
            return false;
        }

        return true;
    }

}