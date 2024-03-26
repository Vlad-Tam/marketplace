package com.vladtam.marketplace.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private Connection dbconnection;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class);

    public Connection getConnection(){
        String connectionString = "jdbc:postgresql://" + Config.getDbHost() + ":" + Config.getDbPort() +
                "/" + Config.getDbName() + "?autoReconnect=true&useSSL=false";
        try {
            dbconnection = DriverManager.getConnection(connectionString, Config.getDbUser(), Config.getDbPass());
        } catch(SQLException e){
            logger.error("Database connect SQLException error", e);
        }
        return dbconnection;
    }
}
