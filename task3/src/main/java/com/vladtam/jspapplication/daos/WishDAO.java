package com.vladtam.jspapplication.daos;

import com.vladtam.jspapplication.database.DatabaseHandler;
import com.vladtam.jspapplication.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishDAO {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    private static final String INSERT_REQUEST = "INSERT INTO wish_list (id_user, id_advertisement) VALUES (?, ?)";
    private static final String DELETE_REQUEST = "DELETE FROM wish_list WHERE id_user = ? AND id_advertisement = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT w.id_user, w.id_advertisement, u.name AS user_name, u.surname, ad.name AS advertisement_name, ad.price FROM wish_list AS w JOIN user_account AS u ON w.id_user = u.id JOIN advertisement AS ad ON w.id_advertisement = ad.id;";
    private static final String WISH_NAME = "Wish";
    public static final Logger logger = LoggerFactory.getLogger(WishDAO.class);

    public List<Wish> getListInfo() {
        List<Wish> wishesList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                wishesList.add(new Wish(new User(Integer.parseInt(rs.getString("id_user")), rs.getString("user_name"), rs.getString("surname")), new Advertisement(Integer.parseInt(rs.getString("id_advertisement")), rs.getString("advertisement_name"), rs.getDouble("price"))));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return wishesList;
    }

    public int createNew(int userId, int advertisementId) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, userId, advertisementId);
            return BaseDAO.getGeneratedKey(pstmt, WISH_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    public void delete(int userId, int advertisementId) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_REQUEST)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, advertisementId);
            if (pstmt.executeUpdate() > 0) {
                logger.trace("{} was deleted successfully!", WISH_NAME);
            }
        } catch (SQLException e) {
            logger.error("Database delete object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, int userId, int advertisementId) throws SQLException {
        pstmt.setInt(1, userId);
        pstmt.setInt(2, advertisementId);
    }
}
