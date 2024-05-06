package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.model.Exercise;
import edu.rit.croatia.swen383.g1.dm.model.ExerciseModel;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WindowExerciseAddExerciseHandler  implements EventHandler<ActionEvent> {

    
    ExerciseModel exerciseModel;
    DietManagerView view;

    public WindowExerciseAddExerciseHandler(ExerciseModel exerciseModel, DietManagerView view) {
        this.exerciseModel = exerciseModel;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        String name = view.getExerciseNameField().getText();
        String calPerHourS = view.getExerciseCaloriesField().getText();

        if (this.validate(name, calPerHourS)) {
            double calPerHour = Double.parseDouble(calPerHourS);

            this.exerciseModel.addExercise(name, calPerHour);
            ConfirmationMessageHandler.alert("Exercise: " + name + " has been added successfully");
        }
    }

    public boolean validate(String name, String calories) {

        if (name.length() > 50) {
            ErrorMessageHandler.alert("Name cannot be empty and must be less than 50 characters.");
            return false;
        }

        if (name.isEmpty() || calories.isEmpty()) {
            ErrorMessageHandler.alert("All fields must be filled in.");
            return false;
        }

        if (name.matches(".*\\d.*")) {
            ErrorMessageHandler.alert("Name field mustn't contain numbers.");
            return false;
        }

        try {
            double caloriesValue = Double.parseDouble(calories);
            if (caloriesValue < 0) {
                ErrorMessageHandler.alert("Calories value cannot be negative.");
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

        for (Exercise exercise : exerciseModel.getExerciseList()) {
            if (exercise.getName().equalsIgnoreCase(name)) {
                ErrorMessageHandler.alert("Exercise already exists: " + exercise.getName());
                return false;
            }
        }

        return true;
    }



    
}

