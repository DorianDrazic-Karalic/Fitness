package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogCSVManager extends CSVManager {

    private LogModel logModel;
    private FoodModel foodModel;
    private ExerciseModel exerciseModel;

    public LogCSVManager(LogModel logModel, FoodModel foodModel, ExerciseModel exerciseModel) {
        this.logModel = logModel;
        this.foodModel=foodModel;
        this.exerciseModel=exerciseModel;
    }

    // method that loads and processes log entries from CSV
    @Override
    public void loadEntries(String fileName) {
        List<String[]> records = loadCSV(fileName);
        for (String[] parts : records) {
            if (!isValidDataStructure(parts)) {
                continue;
            }
            String type = parts[3].trim();
            LocalDate date = parseDate(parts);
            if (date == null) {
                System.err.println("Invalid date format in CSV record.");
                continue;
            }
            switch (type) {
                case "w":
                    parseWeightEntry(parts, date);
                    break;
                case "c":
                    parseCalorieEntry(parts, date);
                    break;
                case "f":
                    parseFoodEntry(parts, date);
                    break;
                    case "e":
                    parseExerciseEntry(parts, date);
                    break;
            }
        }
    }

    // method that saves log entries (food, weight, calorie) to a CSV file
    @Override
    public void saveEntries(String fileName) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'saveEntries'");
        List<String> lines = new ArrayList<>();
        for (FoodEntry foodEntry : logModel.getFoodEntries()) {
            lines.add(foodEntry.toCSVString());
        }
        for (WeightEntry weightEntry : logModel.getWeightEntries()) {
            lines.add(weightEntry.toCSVString());
        }
        for (CalorieEntry calorieEntry : logModel.getCalorieEntries()) {
            lines.add(calorieEntry.toCSVString());
        }
        for (ExerciseEntry exerciseEntry : logModel.getExerciseEntries()) {
            lines.add(exerciseEntry.toCSVString());
        }
        saveCSV(fileName, lines);
    }

    @Override
    public boolean isValidDataStructure(String[] parts) {
        if (parts.length < 2) {
            return false;
        }
        String type = parts[3].trim();
        switch (type) {
            case "w":
                if (parts.length != 5) {
                    return false;
                }
                break;
            case "c":
                if (parts.length != 5) {
                    return false;
                }
                break;
            case "f":
                if (parts.length != 6) {
                    return false;
                }
                break;
            case "e":
                if (parts.length != 6) {
                    return false;
                }
                break;

            default:
                return false;
        }
        return true;
    }

    // method that parses and returns a LocalDate from CSV record parts
    private LocalDate parseDate(String[] parts) {
        try {
            return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    // method that parses a weight entry from CSV parts and adds it to the log model
    private void parseWeightEntry(String[] parts, LocalDate date) {
        try {
            double weight = Double.parseDouble(parts[4]);
            WeightEntry weightEntry = new WeightEntry(date, weight);
            logModel.addEntry(weightEntry);
        } catch (NumberFormatException ex) {
            System.err.println("Error parsing weight entry: " + ex.getMessage());
        }
    }

    // method that parses a calorie entry from CSV parts and adds it to the log
    // model
    private void parseCalorieEntry(String[] parts, LocalDate date) {
        try {
            double calories = Double.parseDouble(parts[4]);
            CalorieEntry calorieEntry = new CalorieEntry(date, calories);
            logModel.addEntry(calorieEntry);
        } catch (NumberFormatException ex) {
            System.err.println("Error parsing calorie entry: " + ex.getMessage());
        }
    }

    // Parses a food entry from CSV parts and adds it to the log model
    private void parseFoodEntry(String[] parts, LocalDate date) {
        String foodName = parts[4];
        double count = Double.parseDouble(parts[5]);
        Food basicFood = findItemByName(foodName, foodModel.getFoodList(), "f");
        if (basicFood != null) {
            FoodEntry foodEntry = new FoodEntry(date, basicFood, count);
            logModel.addEntry(foodEntry);
        } else {
            System.err.println("Food not found: " + foodName);
        }
    }

    private void parseExerciseEntry(String[] parts, LocalDate date){
        String exerciseName = parts[4];
        double time = Double.parseDouble(parts[5]);
        Exercise exercise = findItemByName(exerciseName, exerciseModel.getExerciseList(), "e");
        if (exercise != null) {
            ExerciseEntry exerciseEntry = new ExerciseEntry(date, exercise, time);
            logModel.addEntry(exerciseEntry);
        } else {
            System.err.println("Food not found: " + exerciseName);
        }
    }   
}