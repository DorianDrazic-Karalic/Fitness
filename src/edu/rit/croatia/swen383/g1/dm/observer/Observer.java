package edu.rit.croatia.swen383.g1.dm.observer;

/**
 * The Observer interface represents an observer that needs to be notified
 * when there are updates from the subject.
 */
public interface Observer {

    /**
     * Update method to be implemented by concrete observers.
     * This method is called by the subject whenever there is an update.
     */
    public void update();
}
