package de.lubowiecki.firststepsjfx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
    private TableView<Book> books;

    private BookRepository bookRepository;

    private Book current = new Book();

    @FXML
    protected void save() {

        // Neues Buch mit Daten füllen
        Book book = current;
        book.setTitle((title.getText().length() > 0) ? title.getText() : null);
        book.setDescription((description.getText().length() > 0) ? description.getText() : null);
        book.setIsbn(isbn.getText());
        book.setAuthor(author.getText());
        book.setPublisher(publisher.getText());

        try {
            // TODO: Validierung
            // Buch speichern
            bookRepository.save(book);
            clear();

            // Ausgabe aktuallisieren
            show();
        }
        catch (SQLException e) {
            // TODO: Fehler in der GUI ausgeben
            e.printStackTrace();
        }
    }

    @FXML
    protected void edit() {
        current = books.getSelectionModel().getSelectedItem();
        // Formular wird belegt
        title.setText(current.getTitle());
        description.setText(current.getDescription());
        isbn.setText(current.getIsbn());
        author.setText(current.getAuthor());
        publisher.setText(current.getPublisher());
    }

    @FXML
    protected void delete() {
        Book book = books.getSelectionModel().getSelectedItem();

        try {
            // TODO: Vor dem Löschen nachfragen
            bookRepository.delete(book);
            show();
        }
        catch (SQLException e) {
            // TODO: Fehler in der GUI ausgeben
            e.printStackTrace();
        }
    }

    // TODO: Bearbeiten von Altdaten
    // TODO: Konfigbereich

    private void show() {
        try {
            // TODO: Ausgabe in eine TableView

            // Alle Bücher in einen String umwandeln
            /* String str = bookRepository.find().stream().map(b -> b.toString())
                    .collect(Collectors.joining("\n")); */

            List<Book> list = bookRepository.find();
            books.setItems(FXCollections.observableList(list));
        }
        catch (SQLException e) {
            // TODO: Fehler in der GUI ausgeben
            e.printStackTrace();
        }
    }

    @FXML
    private void clear() {
        title.clear();
        description.clear();
        isbn.clear();
        author.clear();
        publisher.clear();
        current = new Book();
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