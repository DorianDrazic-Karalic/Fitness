package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import java.util.List;

import edu.rit.croatia.swen383.g1.dm.model.ExerciseEntry;
import edu.rit.croatia.swen383.g1.dm.model.FoodEntry;
import edu.rit.croatia.swen383.g1.dm.model.LogEntry;
import edu.rit.croatia.swen383.g1.dm.model.LogModel;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//deletes selected entries from log list
public class DeleteButtonHandler implements EventHandler<ActionEvent> {
    private LogModel logModel;
    private DietManagerView dietManagerView;

    public DeleteButtonHandler(LogModel logModel, DietManagerView dietManagerView) {
        this.logModel = logModel;
        this.dietManagerView = dietManagerView;
    }

    @Override
    public void handle(ActionEvent event) {
        LogEntry selectedEntry = dietManagerView.getDailyFoodLogList().getSelectionModel().getSelectedItem();
        if (validate(selectedEntry)) {
            if (selectedEntry instanceof FoodEntry) {
                logModel.removeEntry((FoodEntry) selectedEntry);
            }
            else if(selectedEntry instanceof ExerciseEntry){
                logModel.removeEntry((ExerciseEntry) selectedEntry);
            }
            else {
                ErrorMessageHandler.alert("You must select a food, not an exercise entry.");
            }
        }
    }

    public boolean validate(LogEntry selectedEntry) {

        if (logModel == null) {
            ErrorMessageHandler.alert("Log model is not initialized");
            return false;
        }

        List<FoodEntry> entries = logModel.getFoodEntries();
        List<ExerciseEntry> eEntries=logModel.getExerciseEntries();
        if (entries.isEmpty() && eEntries.isEmpty()) {
            ErrorMessageHandler.alert("Log is empty. Nothing available to delete.");
            return false;
        }

        if (selectedEntry == null) {
            ErrorMessageHandler.alert("Select an entry to delete it.");
            return false;
        }
        return true;
    }

}