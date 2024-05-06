package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//Opens window for adding new food to the foods.csv
public class OpenWindowHandler implements EventHandler<ActionEvent> {

    private DietManagerView view;

    public OpenWindowHandler(DietManagerView dietManagerView) {
        this.view = dietManagerView;
    }

    @Override
    public void handle(ActionEvent event) {
        this.view.showAddFoodWindow();
    }

}
