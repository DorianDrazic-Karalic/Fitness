package edu.rit.croatia.swen383.g1.dm.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Subject class represents a subject that observers can subscribe to.
 */
public abstract class Subject {

    /**
     * List that holds all the observers attached to this Subject
     */
    private List<Observer> observers;

    /**
     * Constructs a new Subject instance.
     */
    public Subject() {
        observers = new ArrayList<>();
    }

    /**
     * Adds a new observer to the list of this subject's observers.
     *
     * @param observer The observer to be added.
     * @throws NullPointerException if the provided observer is null.
     */
    public void attach(Observer observer) {

        if (observer == null) {
            throw new NullPointerException();
        }

        if (!observers.contains(observer)) {
            observers.add(observer);
        }

    }

    /**
     * Notifies all observers of this subject.
     */
    public void notifyObserver() {
        observers.forEach(observer -> observer.update());
    }

    /**
     * Removes an observer from the list of this subject's observers.
     *
     * @param observer The observer to be removed.
     */
    public void detach(Observer observer) {
        observers.remove(observer);

    }

}
