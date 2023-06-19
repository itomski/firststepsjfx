module de.lubowiecki.firststepsjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens de.lubowiecki.firststepsjfx to javafx.fxml;
    exports de.lubowiecki.firststepsjfx;
}