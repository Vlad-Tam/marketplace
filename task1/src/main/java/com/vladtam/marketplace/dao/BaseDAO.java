package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.database.DatabaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
    private BaseDAO(){}
    private static DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    public static void delete(int id, String deleteRequest, String objectName) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteRequest)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() > 0) {
                logger.trace("{} was deleted successfully!", objectName);
            }
        } catch (SQLException e) {
            logger.error("Database delete object error", e);
        }
    }

    public static int getGeneratedKey(PreparedStatement pstmt, String objectName) throws SQLException{
        if (pstmt.executeUpdate() > 0) {
            logger.trace("A new {} was inserted successfully!", objectName);
        }
        try(ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        return -1;
    }
}
