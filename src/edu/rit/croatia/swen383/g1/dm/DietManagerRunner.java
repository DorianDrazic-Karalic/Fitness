package edu.rit.croatia.swen383.g1.dm;

import edu.rit.croatia.swen383.g1.dm.controller.Controller;
import edu.rit.croatia.swen383.g1.dm.model.ExerciseModel;
import edu.rit.croatia.swen383.g1.dm.model.FoodModel;
import edu.rit.croatia.swen383.g1.dm.model.LogModel;
import edu.rit.croatia.swen383.g1.dm.view.DietManagerView;
import javafx.application.Application;
import javafx.stage.Stage;

public class DietManagerRunner extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FoodModel foodModel = new FoodModel();
        ExerciseModel exerciseModel = new ExerciseModel();
        LogModel logModel = new LogModel(foodModel, exerciseModel);
       
        DietManagerView view = new DietManagerView(foodModel, logModel, exerciseModel);
        view.start(primaryStage);

        Controller con = new Controller(view, logModel);

        con.initilizeView();

    }
}
