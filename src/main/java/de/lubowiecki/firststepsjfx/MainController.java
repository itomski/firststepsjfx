package de.lubowiecki.firststepsjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML // Nur Elemente mit dieser Annotation sind in der GUI ansprechbar
    private Label title;

    @FXML
    private Label namenListe;

    @FXML
    private TextField name;

    private List<String> namen = new ArrayList<>();

    @FXML
    protected void showGreeting() {
        if(name.getText().trim().length() > 0) {
            title.setText("Hallo " + name.getText());
            namen.add(name.getText());
            showNames();
        }
    }

    private void showNames() {
        String output = namen.stream().reduce("", (a, b) -> a + b + "\n");
        namenListe.setText(output);
    }
}