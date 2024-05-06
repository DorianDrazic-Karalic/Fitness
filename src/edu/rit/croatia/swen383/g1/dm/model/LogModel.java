package edu.rit.croatia.swen383.g1.dm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import edu.rit.croatia.swen383.g1.dm.observer.Subject;

public class LogModel extends Subject {
    private ArrayList<FoodEntry> foodEntries;
    private ArrayList<WeightEntry> weightEntries;
    private ArrayList<CalorieEntry> calorieEntries;
    private ArrayList<ExerciseEntry> exerciseEntries;
    private Map<String, Double> totalNutritionCount;
    private CSVManager logCSVManager;
    private String fileName;
    private double caloriesBurned;

    public LogModel(FoodModel foodModel, ExerciseModel exerciseModel) {
        this.foodEntries = new ArrayList<>();
        this.weightEntries = new ArrayList<>();
        this.calorieEntries = new ArrayList<>();
        this.exerciseEntries = new ArrayList<>();
        this.totalNutritionCount = new HashMap<>();
        this.totalNutritionCount.put("protein", 0.0);
        this.totalNutritionCount.put("carb", 0.0);
        this.totalNutritionCount.put("fat", 0.0);
        this.fileName = "log.csv";
        this.logCSVManager = new LogCSVManager(this, foodModel, exerciseModel);
        this.logCSVManager.loadEntries(fileName);
    }

    public ArrayList<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(ArrayList<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }

    public ArrayList<WeightEntry> getWeightEntries() {
        return weightEntries;
    }

    public void setWeightEntries(ArrayList<WeightEntry> weightEntries) {
        this.weightEntries = weightEntries;
    }

    public ArrayList<CalorieEntry> getCalorieEntries() {
        return calorieEntries;
    }

    public void setCalorieEntries(ArrayList<CalorieEntry> calorieEntries) {
        this.calorieEntries = calorieEntries;
    }

    public ArrayList<ExerciseEntry> getExerciseEntries() {
        return exerciseEntries;
    }

    public void setExerciseEntries(ArrayList<ExerciseEntry> exerciseEntries) {
        this.exerciseEntries = exerciseEntries;
    }

    public Map<String, Double> getTotalNutritionCount() {
        return totalNutritionCount;
    }

    public void setTotalNutritionCount(Map<String, Double> totalNutritionCount) {
        this.totalNutritionCount = totalNutritionCount;
    }

    public Map<String, Double> getTotalNutritionCount(LocalDate date) {
        totalNutritionCount.clear();
        for (FoodEntry entry : foodEntries) {
            if (entry.getDate().equals(date)) {
                Food foodConsumed = entry.getFoodConsumed();
                totalNutritionCount.put("protein",
                        totalNutritionCount.getOrDefault("protein", 0.0) + foodConsumed.getProtein());
                totalNutritionCount.put("carb",
                        totalNutritionCount.getOrDefault("protein", 0.0) + foodConsumed.getCarb());
                totalNutritionCount.put("fat",
                        totalNutritionCount.getOrDefault("protein", 0.0) + foodConsumed.getFat());
            }
        }
        return totalNutritionCount;
    }

    public double getCaloriesBurned(LocalDate date, double weight) {
        caloriesBurned = 0;

        for (ExerciseEntry entry : exerciseEntries) {
            if (entry.getDate().equals(date)) {
                Exercise exerciseDone = entry.getExercise();
                caloriesBurned += exerciseDone.getCalories() * weight * (entry.getTime() / 60);
            }
        }

        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public void addEntry(LogEntry entry) {
        if (entry instanceof FoodEntry) {
            foodEntries.add((FoodEntry) entry);
        } else if (entry instanceof WeightEntry) {
            weightEntries.add((WeightEntry) entry);
        } else if (entry instanceof CalorieEntry) {
            calorieEntries.add((CalorieEntry) entry);
        } else if (entry instanceof ExerciseEntry) {
            exerciseEntries.add((ExerciseEntry) entry);
        }

        this.logCSVManager.saveEntries(this.fileName);
        notifyObserver();
    }

    public void removeEntry(LogEntry entry) {
        if (entry instanceof FoodEntry) {
            foodEntries.remove(entry);
        } else if (entry instanceof WeightEntry) {
            weightEntries.remove(entry);
        } else if (entry instanceof CalorieEntry) {
            calorieEntries.remove(entry);
        } else if (entry instanceof ExerciseEntry) {
            exerciseEntries.remove((ExerciseEntry) entry);
        }

        this.logCSVManager.saveEntries(this.fileName);
        notifyObserver();
    }

    public void addWeightEntry(LocalDate date, double weight) {
        WeightEntry entry = new WeightEntry(date, weight);
        this.addEntry(entry);
    }

    public void addFoodEntry(LocalDate date, Food foodConsumed, double count) {
        FoodEntry entry = new FoodEntry(date, foodConsumed, count);
        this.addEntry(entry);
    }

    public void addCalorieEntry(LocalDate date, double calorie) {
        CalorieEntry entry = new CalorieEntry(date, calorie);
        this.addEntry(entry);
    }

    public void addExerciseEntry(LocalDate date, Exercise exerciseName, double time) {
        ExerciseEntry entry = new ExerciseEntry(date, exerciseName, time);
        this.addEntry(entry);
    }

    public void loadEntries() {
        notifyObserver();
    }

    public double getWeightForDate(LocalDate date) {
        // Default weight if no specific entry found
        double weightFinal = 68.0;
        LocalDate earliestDate = LocalDate.MIN;

        // Search for an entry on the specified date
        for (WeightEntry entry : weightEntries) {
            if (entry.getDate().isEqual(date)) {
                weightFinal = entry.getWeight();
                break; // Exit loop once the entry for the target date is found
            } else if (entry.getDate().isBefore(date) && entry.getDate().isAfter(earliestDate)) {
                earliestDate = entry.getDate();
                weightFinal = entry.getWeight();
            }
        }

        // If no entry found for the target date, set the weight to the most recent
        // earlier date's entry or default to 68.0
        return weightFinal;
    }

}
