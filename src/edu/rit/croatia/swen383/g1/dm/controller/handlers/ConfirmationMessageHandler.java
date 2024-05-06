package edu.rit.croatia.swen383.g1.dm.controller.handlers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConfirmationMessageHandler {
    public static void alert(String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
