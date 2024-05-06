package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.rit.croatia.swen383.g1.dm.model.*;
import edu.rit.croatia.swen383.g1.dm.view.*;

public class AddExerciseToDailyListHandler implements EventHandler<ActionEvent> {
  
  public DietManagerView view;
  public LogModel log;
  private LocalDate date;

  public AddExerciseToDailyListHandler(DietManagerView view, LogModel log) {
    this.view = view;
    this.log = log;
  }

  @Override
  public void handle(ActionEvent event) {
    String timeS = this.view.getMinutesTextField().getText();
    Exercise exercise = this.view.getExerciseListView().getSelectionModel().getSelectedItem();
    double time;
    try {
        time = Double.parseDouble(timeS);
    } catch (NumberFormatException e) {
        time = 0;
    }
    date = this.view.getDatePicker().getValue();
    if (this.validate(date, time, exercise)) {
      this.log.addExerciseEntry(date, exercise, time);
    }

  }

  public boolean validate(LocalDate date, double time, Exercise exercise) {

    if (exercise == null) {
      ErrorMessageHandler.alert("You must choose a food");
      return false;
    }

    if (date == null) {
      ErrorMessageHandler.alert("You must choose a date.");
      return false;
    }

    if (time <= 0) {
      ErrorMessageHandler.alert("Number of servings must be a number larger than 0");
      return false;
    }

    return true;
  }

}
