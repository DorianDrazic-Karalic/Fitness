package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import java.util.List;

import edu.rit.croatia.swen383.g1.dm.model.ExerciseEntry;
import edu.rit.croatia.swen383.g1.dm.model.FoodEntry;
import edu.rit.croatia.swen383.g1.dm.model.LogEntry;
import edu.rit.croatia.swen383.g1.dm.model.LogModel;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RemoveExerciseHandler implements EventHandler<ActionEvent> {
    private LogModel logModel;
    private DietManagerView view;

    public RemoveExerciseHandler(LogModel logModel, DietManagerView view) {
        this.logModel = logModel;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        LogEntry selectedEntry = view.getDailyFoodLogList().getSelectionModel().getSelectedItem();
        if (validate(selectedEntry)) {
            if (selectedEntry instanceof ExerciseEntry) {
                logModel.removeEntry((ExerciseEntry) selectedEntry);
            } else {
                ErrorMessageHandler.alert("You must select an exercise, not a food entry.");
            }
        }

    }

    public boolean validate(LogEntry selectedEntry) {
        if (logModel == null) {
            ErrorMessageHandler.alert("Log model not initialized.");
            return false;
        }

        List<FoodEntry> eEntries = logModel.getFoodEntries();
        List<ExerciseEntry> entries = logModel.getExerciseEntries();
        if (entries.isEmpty() && eEntries.isEmpty()) {
            ErrorMessageHandler.alert("Empty log, there is nothing to remove.");
            return false;
        }

        if (selectedEntry == null) {
            ErrorMessageHandler.alert("Select an entry that is to be removed.");
            return false;
        }
        return true;
    }
}
