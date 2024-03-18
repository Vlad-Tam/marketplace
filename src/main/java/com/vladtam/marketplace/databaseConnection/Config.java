package com.vladtam.marketplace.databaseConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseHandler.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IOException("Unable to find database.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getDbHost() {
        return properties.getProperty("datasource.dbHost");
    }

    public static String getDbPort() {
        return properties.getProperty("datasource.dbPort");
    }

    public static String getDbUser() {
        return properties.getProperty("datasource.dbUser");
    }

    public static String getDbPass() {
        return properties.getProperty("datasource.dbPass");
    }

    public static String getDbName() {
        return properties.getProperty("datasource.dbName");
    }
}