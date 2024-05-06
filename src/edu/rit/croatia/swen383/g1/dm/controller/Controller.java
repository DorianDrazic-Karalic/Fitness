package edu.rit.croatia.swen383.g1.dm.controller;

import java.time.LocalDate;

import edu.rit.croatia.swen383.g1.dm.controller.handlers.*;
import edu.rit.croatia.swen383.g1.dm.model.*;
import edu.rit.croatia.swen383.g1.dm.view.*;
import javafx.scene.control.DatePicker;

public class Controller {
    public LogModel logModel;
    public Recipe Recipe;
    public DietManagerView view;
    public FoodCSVManager foodManager;
    public LogCSVManager logManager;

    public Controller() {

    }

    public Controller(DietManagerView view, LogModel logModel) {
        this.logModel = logModel;
        this.view = view;

        this.view.addAddFoodToDailyListHandler(new AddFoodToDailyListHandler(this.view, this.logModel));
        this.view.addDeleteButtonHandler(new DeleteButtonHandler(this.logModel, this.view));
        this.view.addOpenWindowHandler(new OpenWindowHandler(this.view));
        this.view.addSaveDailyLogButtonHandler(new SaveDailyLogButtonHandler(this.view, this.logModel));
        this.view.addDateHandler(new DateHandler(this.view));
        this.view.addWindowExerciseHandler(new OpenWindowExerciseButton(view));
        /*Exercise related handlers */
        this.view.addAddExerciseToDailyListHandler(new AddExerciseToDailyListHandler(view, logModel));
    }

    public void initilizeView() {
        DatePicker datePicker = this.view.getDatePicker();
        // Set the specific date
        LocalDate specificDate = LocalDate.now();
        datePicker.setValue(specificDate);

        this.view.update();
    }

}