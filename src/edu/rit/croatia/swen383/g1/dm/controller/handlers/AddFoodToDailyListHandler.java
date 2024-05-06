package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.rit.croatia.swen383.g1.dm.model.*;
import edu.rit.croatia.swen383.g1.dm.view.*;

public class AddFoodToDailyListHandler implements EventHandler<ActionEvent> {
  
  public DietManagerView view;
  public LogModel log;
  private LocalDate date;

  public AddFoodToDailyListHandler(DietManagerView view, LogModel log) {
    this.view = view;
    this.log = log;
  }

  @Override
  public void handle(ActionEvent event) {
    String numberOfServings = this.view.getNumOfServingsTextField().getText();
    Food foodConsumed = this.view.getBasicFoodsListView().getSelectionModel().getSelectedItem();
    double numOfServings;
    try {
      numOfServings = Double.parseDouble(numberOfServings);
    } catch (NumberFormatException e) {
      numOfServings = 0;
    }
    date = this.view.getDatePicker().getValue();
    if (this.validate(date, numOfServings, foodConsumed, numberOfServings)) {
      this.log.addFoodEntry(date, foodConsumed, numOfServings);
    }

  }

  public boolean validate(LocalDate date, double numOfServings, Food foodConsumed, String numberOfServings) {

    if (foodConsumed == null) {
      ErrorMessageHandler.alert("You must choose a food");
      return false;
    }

    if (numberOfServings == null) {
      ErrorMessageHandler.alert("number of servings cannot be empty.");
      return false;
    }

    if (date == null) {
      ErrorMessageHandler.alert("You must choose a date.");
      return false;
    }

    if (this.view.getBasicFoodsListView() == null) {
      ErrorMessageHandler.alert("BasicFoodsList cannot be null.");
      return false;
    }

    if (numOfServings <= 0) {
      ErrorMessageHandler.alert("Number of servings must be a number larger than 0");
      return false;
    }

    return true;
  }

}
