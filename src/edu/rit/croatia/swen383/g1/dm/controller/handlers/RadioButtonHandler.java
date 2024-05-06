package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

//deletes selected entries from log list
public class RadioButtonHandler implements EventHandler<ActionEvent> {

    private DietManagerView view;

    public RadioButtonHandler(DietManagerView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof RadioButton) {
            RadioButton selectedRadioButton = (RadioButton) source;
            if (selectedRadioButton == this.view.getBasicFoodRadioButton()) {
                // Enable fields for basic food
                this.view.enableBasicFoodFields();
                // Disable fields for recipe
                this.view.disableRecipeFields();
            } else if (selectedRadioButton == this.view.getRecipeRadioButton()) {
                // Enable fields for recipe
                this.view.enableRecipeFields();
                // Disable fields for basic food
                this.view.disableBasicFoodFields();
            }
        }
    }

}
