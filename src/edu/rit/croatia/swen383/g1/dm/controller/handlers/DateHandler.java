package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import java.time.LocalDate;

import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//triggers loading of the entries for the selected date
public class DateHandler implements EventHandler<ActionEvent> {

    private DietManagerView view;

    public DateHandler(DietManagerView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent event) {
        LocalDate selectedDate = this.view.getDatePicker().getValue();
        if (selectedDate != null) {
            this.view.update();
        } else {
            ErrorMessageHandler.alert("Date picked is invalid");
        }
    }

}
