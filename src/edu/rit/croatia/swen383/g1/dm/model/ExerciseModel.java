package edu.rit.croatia.swen383.g1.dm.model;

import java.util.ArrayList;

import edu.rit.croatia.swen383.g1.dm.observer.Subject;

public class ExerciseModel extends Subject{
    private ArrayList<Exercise> exerciseList;
    private CSVManager exerciseCSVManager;
    private String fileName;


    public ExerciseModel() {
        exerciseList = new ArrayList<>();
        this.fileName = "exercise.csv";
       this.exerciseCSVManager = new ExerciseCSVManager(this);
       this.exerciseCSVManager.loadEntries(fileName);  

    }

    public ArrayList<Exercise> getExerciseList(){
        return exerciseList;
    }

    public void setExerciseList(ArrayList<Exercise> exerciseList){ 
        this.exerciseList = exerciseList;
    }

    public void addExercise(Exercise exercise){
        exerciseList.add(exercise); 
        this.exerciseCSVManager.saveEntries(this.fileName); 
        notifyObserver(); 

    }

    public void addExercise(String name, double cal){
        exerciseList.add(new Exercise(name, cal)); 
        this.exerciseCSVManager.saveEntries(this.fileName); 
        notifyObserver(); 

    }

    public void removeExercise(String exerciseName){
        exerciseList.removeIf(exercise -> exercise.getName().equalsIgnoreCase(exerciseName)); 
        this.exerciseCSVManager.saveEntries(this.fileName);
        notifyObserver();
        

    }
}
