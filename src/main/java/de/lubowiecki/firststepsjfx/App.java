package de.lubowiecki.firststepsjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //Locale.setDefault(Locale.FRANCE);

        // Mit Bundle
        ResourceBundle bundle = ResourceBundle.getBundle("de.lubowiecki.firststepsjfx.ui");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"), bundle);

        // Ohne Bundle
        // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //stage.setTitle("Buch Verwaltung!");
        stage.setTitle(bundle.getString("app.title"));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}