package de.lubowiecki.firststepsjfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    // TODO: Dynamisch Konfigurierer

    private static final String LOCATION;

    static {
        LOCATION = String.format("jdbc:sqlite:%s/bookdb.db", System.getProperty("user.home"));
    }

    private DBUtils() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(LOCATION);
    }
}
