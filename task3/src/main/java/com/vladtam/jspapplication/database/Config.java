package com.vladtam.jspapplication.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    static {
        try (InputStream input = DatabaseHandler.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IOException("Unable to find database.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            logger.error("Database configure error", e);
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

    private Config() {
        throw new IllegalStateException("Utility class");
    }
}