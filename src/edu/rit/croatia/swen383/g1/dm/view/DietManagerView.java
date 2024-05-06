package edu.rit.croatia.swen383.g1.dm.view;

import edu.rit.croatia.swen383.g1.dm.controller.handlers.RadioButtonHandler;
import edu.rit.croatia.swen383.g1.dm.controller.handlers.WindowAddBasicFoodButtonHandler;
import edu.rit.croatia.swen383.g1.dm.controller.handlers.WindowAddIngredientHandler;
import edu.rit.croatia.swen383.g1.dm.controller.handlers.WindowAddRecipeButtonHandler;
import edu.rit.croatia.swen383.g1.dm.controller.handlers.WindowExerciseAddExerciseHandler;
import edu.rit.croatia.swen383.g1.dm.model.*;
import edu.rit.croatia.swen383.g1.dm.observer.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DietManagerView extends Application implements Observer {
    // Attributes in DietManagerView.fxml:
    @FXML
    private ListView<Food> basicFoodsListView;
    @FXML
    private TextField numOfServingsTextField;
    @FXML
    private Button addFoodToDailyList;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<LogEntry> dailyFoodLogList;
    @FXML
    private Button saveDailyLogButton;
    @FXML
    private Button openWindowButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField desiredCalorieIntakeTextField;
    @FXML
    private TextField totalCalField;
    @FXML
    private TextField totalCalBurnedField;
    @FXML
    private TextField calDifferenceField;
    @FXML
    private PieChart pieChart;
    @FXML
    private ListView<Exercise> exerciseListView;
    @FXML
    private Button saveExerciseToDailyLogButton;
    @FXML
    private TextField minutesTextField;
    @FXML
    private Button addNewExerciseButton;

    // Attributes in AddFoodWindow.fxml
    @FXML
    private TextField nameField;
    @FXML
    private TextField caloriesField;
    @FXML
    private TextField fatField;
    @FXML
    private TextField carbsField;
    @FXML
    private TextField proteinField;
    @FXML
    private TextField recipeNameField;
    @FXML
    private TextField amountField;
    @FXML
    private Button addFoodWindowButton;
    @FXML
    private Button createRecipeButton;
    @FXML
    private Button addIngridientButton;
    @FXML
    private ListView<Food> ingredientsListView;
    @FXML
    private RadioButton basicFoodRadioButton;
    @FXML
    private RadioButton recipeRadioButton;

    private ToggleGroup toggleGroup;

    // Attributes in AddExerciseWindow.fxml
    @FXML
    private TextField exerciseNameField;
    @FXML
    private TextField exerciseCaloriesField;
    @FXML
    private Button addExerciseWindowButton;

    // DietManagerView.java attributes
    Stage stage;
    FoodModel foodModel;
    LogModel logModel;
    private ExerciseModel exerciseModel;

    /****** GETTERS AND SETTERS ********/

    /* FOR ADD FOOD WINDOW */
    public TextField getNameField() {
        return nameField;
    }

    public RadioButton getBasicFoodRadioButton() {
        return basicFoodRadioButton;
    }

    public void setBasicFoodRadioButton(RadioButton basicFoodRadioButton) {
        this.basicFoodRadioButton = basicFoodRadioButton;
    }

    public RadioButton getRecipeRadioButton() {
        return recipeRadioButton;
    }

    public void setRecipeRadioButton(RadioButton recipeRadioButton) {
        this.recipeRadioButton = recipeRadioButton;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getCaloriesField() {
        return caloriesField;
    }

    public void setCaloriesField(TextField caloriesField) {
        this.caloriesField = caloriesField;
    }

    public TextField getFatField() {
        return fatField;
    }

    public void setFatField(TextField fatField) {
        this.fatField = fatField;
    }

    public TextField getCarbsField() {
        return carbsField;
    }

    public void setCarbsField(TextField carbsField) {
        this.carbsField = carbsField;
    }

    public TextField getProteinField() {
        return proteinField;
    }

    public void setProteinField(TextField proteinField) {
        this.proteinField = proteinField;
    }

    public TextField getRecipeNameField() {
        return recipeNameField;
    }

    public void setRecipeNameField(TextField recipeNameField) {
        this.recipeNameField = recipeNameField;
    }

    public TextField getAmountField() {
        return amountField;
    }

    public void setAmountField(TextField amountField) {
        this.amountField = amountField;
    }

    public Button getAddFoodWindowButton() {
        return addFoodWindowButton;
    }

    public void setAddFoodWindowButton(Button addFoodWindowButton) {
        this.addFoodWindowButton = addFoodWindowButton;
    }

    public Button getCreateRecipeButton() {
        return createRecipeButton;
    }

    public void setCreateRecipeButton(Button createRecipeButton) {
        this.createRecipeButton = createRecipeButton;
    }

    public Button getAddIngridientButton() {
        return addIngridientButton;
    }

    public void setAddIngridientButton(Button addIngridientButton) {
        this.addIngridientButton = addIngridientButton;
    }

    public ListView<Food> getIngredientsListView() {
        return ingredientsListView;
    }

    public void setIngredientsListView(ArrayList<Food> foodList) {

        // Clear existing items before adding new ones
        this.ingredientsListView.getItems().clear();

        // Set custom cell factory
        this.ingredientsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Food food, boolean empty) {
                super.updateItem(food, empty);

                if (empty || food == null) {
                    setText(null);
                } else {
                    setText(food.getName() + " - Calories: " + food.getCalories());
                }
            }
        });

        // Add food items to the ListView
        this.ingredientsListView.getItems().addAll(foodList);
    }

    /* DietManagerView GETTERS AND SETTERS */

    public ListView<Food> getBasicFoodsListView() {
        return basicFoodsListView;
    }

    public void setBasicFoodsListView(ArrayList<Food> foodList) {

        // Clear existing items before adding new ones
        this.basicFoodsListView.getItems().clear();

        // Set custom cell factory
        this.basicFoodsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Food food, boolean empty) {
                super.updateItem(food, empty);

                if (empty || food == null) {
                    setText(null);
                } else {
                    setText(food.getName() + " - Calories: " + food.getCalories());
                }
            }
        });

        // Add food items to the ListView
        this.basicFoodsListView.getItems().addAll(foodList);
    }

    public TextField getNumOfServingsTextField() {
        return numOfServingsTextField;
    }

    public void setNumOfServingsTextField(TextField numOfServingsTextField) {
        this.numOfServingsTextField = numOfServingsTextField;
    }

    public Button getAddFoodToDailyList() {
        return addFoodToDailyList;
    }

    public void setAddFoodToDailyList(Button addFoodToDailyList) {
        this.addFoodToDailyList = addFoodToDailyList;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public ListView<LogEntry> getDailyFoodLogList() {
        return dailyFoodLogList;
    }

    public void setDailyFoodLogList(List<LogEntry> logEntries, LocalDate selectedDate) {
        // Clear existing items before adding new ones
        this.dailyFoodLogList.getItems().clear();

        // Set custom cell factory
        this.dailyFoodLogList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(LogEntry entry, boolean empty) {
                super.updateItem(entry, empty);

                if (empty || entry == null) {
                    setText(null);
                } else {
                    if (entry instanceof FoodEntry) {
                        FoodEntry foodEntry = (FoodEntry) entry;
                        setText(foodEntry.getFoodConsumed().getName() + "\t\tCal: "
                                + foodEntry.getFoodConsumed().getCalories() + "\t\tCount: " + foodEntry.getCount());
                    } else if (entry instanceof ExerciseEntry) {
                        ExerciseEntry exerciseEntry = (ExerciseEntry) entry;
                        setText(exerciseEntry.getExercise().getName() + "\t\tTime: "
                                + exerciseEntry.getTime());
                    }
                }
            }
        });

        // Add entries to the ListView
        for (LogEntry entry : logEntries) {
            if (entry instanceof FoodEntry) {
                FoodEntry foodEntry = (FoodEntry) entry;
                if (foodEntry.getDate().equals(selectedDate)) {
                    this.dailyFoodLogList.getItems().add(foodEntry);
                }
            } else if (entry instanceof ExerciseEntry) {
                ExerciseEntry exerciseEntry = (ExerciseEntry) entry;
                if (exerciseEntry.getDate().equals(selectedDate)) {
                    this.dailyFoodLogList.getItems().add(exerciseEntry);
                }
            }
        }
    }

    public Button getSaveDailyLogButton() {
        return saveDailyLogButton;
    }

    public void setSaveDailyLogButton(Button saveDailyLogButton) {
        this.saveDailyLogButton = saveDailyLogButton;
    }

    public Button getOpenWindowButton() {
        return openWindowButton;
    }

    public void setOpenWindowButton(Button openWindowButton) {
        this.openWindowButton = openWindowButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public TextField getWeightTextField() {
        return weightTextField;
    }

    public void setWeightTextField(double weight) {
        weightTextField.setText(Double.toString(weight));
    }

    public TextField getDesiredCalorieIntakeTextField() {
        return desiredCalorieIntakeTextField;
    }

    public void setDesiredCalorieIntakeTextField(List<CalorieEntry> calorieEntries, LocalDate targetDate) {
        // Default calorie limit if no specific entry found
        String calorieIntakeText = "2000.0";
        LocalDate earliestDate = LocalDate.MIN;

        // Search for an entry on the specified date
        for (CalorieEntry entry : calorieEntries) {
            if (entry.getDate().isEqual(targetDate)) {
                calorieIntakeText = Double.toString(entry.getDesiredCaloricIntake());
                break; // Exit loop once the entry for the target date is found
            } else if (entry.getDate().isBefore(targetDate) && entry.getDate().isAfter(earliestDate)) {
                earliestDate = entry.getDate();
                calorieIntakeText = Double.toString(entry.getDesiredCaloricIntake());
            }
        }

        desiredCalorieIntakeTextField.setText(calorieIntakeText);
    }

    public TextField getTotalCalField() {
        return totalCalField;
    }

    public void setTotalCalField(ArrayList<FoodEntry> foodEntries, LocalDate selectedDate) {
        double totalCalories = 0;

        // Iterate through the FoodEntry objects
        for (FoodEntry entry : foodEntries) {
            // Check if the date matches the selected date
            if (entry.getDate().equals(selectedDate)) {
                // Get the Food object and count from the FoodEntry
                Food food = entry.getFoodConsumed();
                double count = entry.getCount();

                // Calculate and add the calories to the total
                totalCalories += food.getCalories() * count;
            }
        }

        // Set the totalCalField TextField with the calculated result
        totalCalField.setText(String.format(Locale.US, "%.2f",totalCalories));
    }

    public TextField getTotalCalBurnedField() {
        return totalCalBurnedField;
    }

    public void setTotalCalBurnedField(double caloriesBurned) {
        this.totalCalBurnedField.setText(String.format(Locale.US, "%.2f", caloriesBurned));
    }

    public TextField getCalDifferenceField() {
        return calDifferenceField;
    }

    public void setCalDifferenceField() {
        double caloriesConsumed = Double.parseDouble(this.getTotalCalField().getText());
        double caloriesBurned = Double.parseDouble(this.getTotalCalBurnedField().getText());
        double caloriesDifference = caloriesConsumed - caloriesBurned;
        this.calDifferenceField.setText(String.format(Locale.US, "%.2f", caloriesDifference));

        // Get the current style of the TextField
        String currentStyle = calDifferenceField.getStyle();

        // Change text color based on condition, preserving the existing style
        if (caloriesDifference > Double.parseDouble(this.getDesiredCalorieIntakeTextField().getText()) || caloriesDifference<=0) {
            calDifferenceField.setStyle(currentStyle + "-fx-text-fill: red;");
        } else {
            calDifferenceField.setStyle(currentStyle + "-fx-text-fill: green;");
        }
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(Map<String, Double> totalNutritionCount) {
        pieChart.getData().clear(); // Clear existing data

        // Add data to the pie chart based on the provided map
        for (Map.Entry<String, Double> entry : totalNutritionCount.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(data);
        }

        // Bind labels to show percentages
        for (PieChart.Data data : pieChart.getData()) {
            data.nameProperty().bind(
                    javafx.beans.binding.Bindings.concat(
                            data.getName(), " ",
                            javafx.beans.binding.Bindings.format("(%.1f%%)",
                                    data.pieValueProperty().divide(
                                            pieChart.getData().stream().mapToDouble(PieChart.Data::getPieValue).sum())
                                            .multiply(100))));
        }
    }

    /** DietManagerView GETTER AND SETTERS FOR EXERCISE */
    public ListView<Exercise> getExerciseListView() {
        return exerciseListView;
    }

    public void setExerciseListView(List<Exercise> exerciseList) {

        // Clear existing items before adding new ones
        this.exerciseListView.getItems().clear();

        // Set custom cell factory
        this.exerciseListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Exercise exercise, boolean empty) {
                super.updateItem(exercise, empty);

                if (empty || exercise == null) {
                    setText(null);
                } else {
                    setText(exercise.getName() + " - Calories/Hour: " + exercise.getCalories());
                }
            }
        });

        // Add exercise items to the ListView
        this.exerciseListView.getItems().addAll(exerciseList);
    }

    public Button getSaveExerciseToDailyLogButton() {
        return saveExerciseToDailyLogButton;
    }

    public void setSaveExerciseToDailyLogButton(Button saveExerciseToDailyLogButton) {
        this.saveExerciseToDailyLogButton = saveExerciseToDailyLogButton;
    }

    public TextField getMinutesTextField() {
        return minutesTextField;
    }

    public void setMinutesTextField(TextField minutesTextField) {
        this.minutesTextField = minutesTextField;
    }

    public Button getAddNewExerciseButton() {
        return addNewExerciseButton;
    }

    public void setAddNewExerciseButton(Button addNewExerciseButton) {
        this.addNewExerciseButton = addNewExerciseButton;
    }


    /** GETTERS AND SETTERS FOR AddExerciseView */
    public TextField getExerciseNameField() {
        return exerciseNameField;
    }

    public void setExerciseNameField() {
        // to be implmeneted
    }

    public TextField getExerciseCaloriesField() {
        return exerciseCaloriesField;
    }

    public void setExerciseCaloriesField() {
        // to be implmeneted
    }

    public Button getAddExerciseWindowButton() {
        return addExerciseWindowButton;
    }

    public void setAddExerciseWindowButton() {
        // to be implemented
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Sets the JavaFX Stage for this controller.
     *
     * @param stage The JavaFX Stage to be set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /******
     * GETTERS AND SETTERS
     * 
     * @param exerciseModel
     **************/

    public DietManagerView(FoodModel foodModel, LogModel logModel, ExerciseModel exerciseModel) {
        this.foodModel = foodModel;
        this.logModel = logModel;
        this.exerciseModel = exerciseModel;
        this.foodModel.attach(this);
        this.logModel.attach(this);
        this.exerciseModel.attach(this);
    }

    /**
     * Method to get the user-selected date from the DatePicker.
     *
     * @return The selected date
     */
    public LocalDate getSelectedDate() {
        return datePicker.getValue();
    }

    /**
     *
     * Used as an event handler for closing the window.
     */
    @FXML
    public void closeWindow() {
        stage.close();
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DietManagerView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            // Set up the primary stage
            this.stage.setTitle("Diet Management App");
            this.stage.setScene(new Scene(root, 1315, 810));
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update method to be implemented by concrete observers.
     * This method is called by the subject whenever there is an update.
     */
    @Override
    public void update() {

        LocalDate selectedDate = this.getDatePicker().getValue();

        // Set the BasicFoodsListView
        this.setBasicFoodsListView(this.foodModel.getFoodList());

        // Set the ExerciesListView
        this.setExerciseListView(this.exerciseModel.getExerciseList());

        List<LogEntry> logEntries = new ArrayList<>();
        List<FoodEntry> foodEntries = this.logModel.getFoodEntries();
        List<ExerciseEntry> exerciseEntries = this.logModel.getExerciseEntries();

        // Adding foodEntries and exerciseEntries to logEntries
        logEntries.addAll(foodEntries);
        logEntries.addAll(exerciseEntries);

        // Set the Daily Log List
        this.setDailyFoodLogList(logEntries, selectedDate);

        // Set the weight text field
        this.setWeightTextField(this.logModel.getWeightForDate(selectedDate));

        // Set the calorie text field
        this.setDesiredCalorieIntakeTextField(this.logModel.getCalorieEntries(), selectedDate);

        // set the graph
        this.setPieChart(this.logModel.getTotalNutritionCount(selectedDate));

        if (!this.getDesiredCalorieIntakeTextField().getText().isEmpty()) {
            // set the total calories
            this.setTotalCalField(this.logModel.getFoodEntries(), selectedDate);

            this.setTotalCalBurnedField(this.logModel.getCaloriesBurned(selectedDate, this.logModel.getWeightForDate(selectedDate)));
            this.setCalDifferenceField();
        }
    }

    /**************** ADD FOOD WINDOW HANDLE METHODS********************** */

    public void addWindowAddBasicFoodButtonHandler(EventHandler<ActionEvent> handler) {
        this.addFoodWindowButton.setOnAction(handler);
    }

    public void addWindowAddRecipeButtonHandler(EventHandler<ActionEvent> handler) {
        this.createRecipeButton.setOnAction(handler);
    }

    public void addWindowAddIngredientHandler(EventHandler<ActionEvent> handler) {
        this.addIngridientButton.setOnAction(handler);
    }

    public void addRadioButtonHandler(EventHandler<ActionEvent> handler) {
        this.basicFoodRadioButton.setOnAction(handler);
        this.recipeRadioButton.setOnAction(handler);
    }

    /*************** DIET MANAGER VIEW HANDLE METHODS****** */
    public void addAddFoodToDailyListHandler(EventHandler<ActionEvent> handler) {
        this.addFoodToDailyList.setOnAction(handler);
    }

    public void addDeleteButtonHandler(EventHandler<ActionEvent> handler) {
        this.deleteButton.setOnAction(handler);
    }

    public void addSaveDailyLogButtonHandler(EventHandler<ActionEvent> handler) {
        this.saveDailyLogButton.setOnAction(handler);
    }

    public void addOpenWindowHandler(EventHandler<ActionEvent> handler) {
        this.openWindowButton.setOnAction(handler);
    }

    public void addDateHandler(EventHandler<ActionEvent> handler) {
        this.datePicker.setOnAction(handler);
    }

    /*************** DIET MANAGER VIEW -EXERCISE HANDLE METHODS****** */

    public void addWindowExerciseHandler(EventHandler<ActionEvent> handler) {
        this.addNewExerciseButton.setOnAction(handler);
    }

    public void addWindowExerciseAddExerciseHandler(EventHandler<ActionEvent> handler) {
        this.addExerciseWindowButton.setOnAction(handler);
    }

    public void addAddExerciseToDailyListHandler(EventHandler<ActionEvent> handler) {
        this.saveExerciseToDailyLogButton.setOnAction(handler);
    }

    public void showAddFoodWindow() {
        // Load the FXML file
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFoodView.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Food");
            stage.setScene(new Scene(root));

            // Create a toggle group
            toggleGroup = new ToggleGroup();

            // Add both radio buttons to the toggle group
            basicFoodRadioButton.setToggleGroup(toggleGroup);
            recipeRadioButton.setToggleGroup(toggleGroup);

            // Set default selection (if needed)
            basicFoodRadioButton.setSelected(true);

            this.addWindowAddIngredientHandler(new WindowAddIngredientHandler(this, this.foodModel));
            this.addWindowAddBasicFoodButtonHandler(new WindowAddBasicFoodButtonHandler(this.foodModel, this));
            this.addWindowAddRecipeButtonHandler(new WindowAddRecipeButtonHandler(this, foodModel));
            this.addRadioButtonHandler(new RadioButtonHandler(this));

            this.setIngredientsListView(this.foodModel.getFoodList());

            this.disableRecipeFields();
            // Show the stage
            stage.showAndWait(); // Use showAndWait() if you want to wait for the window to be closed before
                                 // returning
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAddExerciseWindow() {
        // Load the FXML file
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddExerciseView.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Exercise");
            stage.setScene(new Scene(root));

            // Connect the handler
            this.addWindowExerciseAddExerciseHandler(new WindowExerciseAddExerciseHandler(exerciseModel, this));

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableBasicFoodFields() {
        nameField.setDisable(false);
        caloriesField.setDisable(false);
        fatField.setDisable(false);
        carbsField.setDisable(false);
        proteinField.setDisable(false);
        addFoodWindowButton.setDisable(false);
    }

    public void disableBasicFoodFields() {
        nameField.setDisable(true);
        caloriesField.setDisable(true);
        fatField.setDisable(true);
        carbsField.setDisable(true);
        proteinField.setDisable(true);
        addFoodWindowButton.setDisable(true);
    }

    public void enableRecipeFields() {
        recipeNameField.setDisable(false);
        amountField.setDisable(false);
        createRecipeButton.setDisable(false);
        addIngridientButton.setDisable(false);
    }

    public void disableRecipeFields() {
        recipeNameField.setDisable(true);
        amountField.setDisable(true);
        createRecipeButton.setDisable(true);
        addIngridientButton.setDisable(true);
    }
}
