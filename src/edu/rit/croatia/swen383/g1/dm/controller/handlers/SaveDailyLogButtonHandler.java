package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import java.time.LocalDate;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.rit.croatia.swen383.g1.dm.model.*;
import edu.rit.croatia.swen383.g1.dm.view.*;

//responsible for saving weight and desired calorie intake entries
public class SaveDailyLogButtonHandler implements EventHandler<ActionEvent> {
    public DietManagerView view;
    public WeightEntry weight;
    public CalorieEntry calorie;
    public LogModel log;

    public SaveDailyLogButtonHandler(DietManagerView view, LogModel log) {
        this.view = view;
        this.log = log;
    }

    @Override
    public void handle(ActionEvent event) {
        LocalDate date = this.view.getSelectedDate();
        String weightValue = this.view.getWeightTextField().getText();

        Double weightNum;
        try {
            weightNum = Double.parseDouble(weightValue);
        } catch (NumberFormatException e) {
            weightNum = 0.0;
        }
        String calorieValue = this.view.getDesiredCalorieIntakeTextField().getText();
        Double calorieNum;
        try {
            calorieNum = Double.parseDouble(calorieValue);
        } catch (NumberFormatException e) {
            calorieNum = 0.0;
        }
        if (this.validate(date, weightNum, weightValue, calorieNum, calorieValue)) {
            this.log.addWeightEntry(date, weightNum);
            this.log.addCalorieEntry(date, calorieNum);
            ConfirmationMessageHandler.alert("Daily log on " + String.valueOf(date) + " has saved successfully");
        }
    }

    public boolean validate(LocalDate date, Double weightNum, String weightValue, Double calorieNum,
            String calorieValue) {

        if (date == null) {
            ErrorMessageHandler.alert("Date cannot be empty.");
            return false;
        }

        ObservableList<LogEntry> items = this.view.getDailyFoodLogList().getItems();
        if (items.isEmpty()) {
            ErrorMessageHandler.alert("You have to choose at least one food");
            return false;
        }

        if (calorieNum <= 0) {
            ErrorMessageHandler.alert("Desired calorie intake must be a number larger than 0 (#.##).");
            return false;
        }

        if(weightNum <= 0){
            ErrorMessageHandler.alert("Weight must be a number larger than 0 (#.##).");
            return false;
        }

        if (calorieValue == null) {
            ErrorMessageHandler.alert("Desired calorie intake cannot be empty.");
            return false;
        }

        if(weightValue == null){
            ErrorMessageHandler.alert("Weight cannot be empty.");
            return false;
        }

        Iterator<WeightEntry> wIterator = this.log.getWeightEntries().iterator();
        while (wIterator.hasNext()) {
            WeightEntry weightEntry = wIterator.next();
            if (weightEntry.getDate().equals(date)) {
                wIterator.remove(); // Remove the current element using the iterator
            }
        }

        Iterator<CalorieEntry> cIterator = this.log.getCalorieEntries().iterator();
        while (cIterator.hasNext()) {
            CalorieEntry calorieEntry = cIterator.next();
            if (calorieEntry.getDate().equals(date)) {
                cIterator.remove(); // Remove the current element using the iterator
            }
        }

        return true;
    }

}
