package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OpenWindowExerciseButton implements EventHandler<ActionEvent>{

    private DietManagerView view;

    public OpenWindowExerciseButton(DietManagerView dietManagerView) {
        this.view = dietManagerView;
    }

    @Override
    public void handle(ActionEvent event) {
        this.view.showAddExerciseWindow();
    }
}
