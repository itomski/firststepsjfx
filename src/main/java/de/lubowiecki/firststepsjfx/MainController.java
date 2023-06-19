package de.lubowiecki.firststepsjfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private TextField isbn;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextArea output;

    private BookRepository bookRepository;

    @FXML
    protected void save() {

        // Neues Buch mit Daten füllen
        Book book = new Book();
        book.setTitle((title.getText().length() > 0) ? title.getText() : null);
        book.setDescription((description.getText().length() > 0) ? description.getText() : null);
        book.setIsbn(isbn.getText());
        book.setAuthor(author.getText());
        book.setPublisher(publisher.getText());

        try {
            // Buch speichern
            bookRepository.save(book);
            clear();

            // TODO: Validierung

            // Ausgabe aktuallisieren
            show();
        }
        catch (SQLException e) {
            // TODO: Fehler in der GUI ausgeben
            e.printStackTrace();
        }
    }

    // TODO: Bearbeiten von Altdaten
    // TODO: Löschen von Altdaten
    // TODO: Konfigbereich
    // TODO: Mehrsprachigkeit

    private void show() {
        try {
            // TODO: AUsgabe in eine TableView

            String str = bookRepository.find().stream().map(b -> b.toString())
                    .collect(Collectors.joining("\n"));

            output.setText(str); // Ausgabe in das TextArea
        }
        catch (SQLException e) {
            // TODO: Fehler in der GUI ausgeben
            e.printStackTrace();
        }
    }

    private void clear() {
        title.clear();
        description.clear();
        isbn.clear();
        author.clear();
        publisher.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // wird automatisch ausgeführt, sobald der Controller instanziert und an die GUI rangebunden wurde
        try {
            bookRepository = new BookRepository();
            show(); // Altdaten anzeigen
        }
        catch (SQLException e) {
            // TODO: Fehler in der GUI ausgeben
            e.printStackTrace();
        }
    }
}