package com.vladtam.marketplace.databaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    Connection dbconnection;
    public static final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class);

    public Connection getConnection(){
        String connectionString = "jdbc:postgresql://" + Config.dbHost + ":" + Config.dbPort +
                "/" + Config.dbName + "?autoReconnect=true&useSSL=false";
        try {
            dbconnection = DriverManager.getConnection(connectionString, Config.dbUser, Config.dbPass);
        } catch(SQLException e){
            logger.error("Database connect SQLException error", e);
        }
        return dbconnection;
    }
}
