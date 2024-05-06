package edu.rit.croatia.swen383.g1.dm.model;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCSVManager extends CSVManager {

    private ExerciseModel exerciseModel;

    public ExerciseCSVManager(ExerciseModel exerciseModel) {
        this.exerciseModel = exerciseModel;
    }

    @Override
    public void loadEntries(String fileName) {
        List<String[]> records = loadCSV(fileName); // Call CSVManager's loadCSV method
        for (String[] parts : records) {
            if (!isValidDataStructure(parts)) {
                continue;
            }
            Exercise exercise = parseExercise(parts);
            exerciseModel.addExercise(exercise);
        }
    }

    @Override
    public void saveEntries(String fileName) {
        List<String> lines = new ArrayList<>();
        for (Exercise exercise : exerciseModel.getExerciseList()) {
            lines.add(exercise.toCSVString());
        }
        saveCSV(fileName, lines);
    }

    @Override
    public boolean isValidDataStructure(String[] parts) {
        if (parts.length != 3) {
            return false;
        }
        return true;
    }

    private Exercise parseExercise(String[] parts) {
        if (parts.length == 3 && parts[0].trim().equals("e")) {
            String name = parts[1].trim();
            double calories = Double.parseDouble(parts[2].trim());
            return new Exercise(name, calories);
        } else {
            System.err.println("Invalid exercise record: " + String.join(",", parts));
        }
        return null;
    }

}
