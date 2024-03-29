package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.database.DatabaseHandler;
import com.vladtam.marketplace.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT ua.*, ad.street, ad.house_number, ad.flat_number, " +
            "ad.id_city, ci.region, ci.name AS city_name, adv.id AS advertisement_id, adv.name AS advertisement_name, " +
            "adv.description AS advertisement_description, adv.price AS advertisement_price, " +
            "adv.status AS advertisement_status, wl.id_advertisement AS wish_list_advertisement_id, " +
            "wished_adv.name AS wished_advertisement_name, wished_adv.price AS wished_advertisement_price, " +
            "rev.id AS review_id, rev.rating AS review_rating, rev.comment AS review_comment, " +
            "rev.id_sender AS review_sender_id, sender.name AS sender_name, sender.surname AS sender_surname " +
            "FROM user_account ua JOIN address ad ON ua.id_address = ad.id JOIN  city ci ON ad.id_city = ci.id " +
            "LEFT JOIN advertisement adv ON ua.id = adv.id_vendor " +
            "LEFT JOIN  wish_list wl ON ua.id = wl.id_user " +
            "LEFT JOIN advertisement wished_adv ON wl.id_advertisement = wished_adv.id " +
            "LEFT JOIN review rev ON ua.id = rev.id_receiver " +
            "LEFT JOIN user_account sender ON rev.id_sender = sender.id WHERE ua.id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT id, name, surname FROM user_account;";
    private static final String INSERT_REQUEST = "INSERT INTO user_account (name, surname, phone_number, email, password, id_address) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_REQUEST = "DELETE FROM user_account WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE user_account SET name = ?, surname = ?, phone_number = ?, email = ?, " +
            "password = ?, id_address = ? WHERE id = ?;";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private static final String PHONE_NUMBER_COLUMN = "phone_number";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    private static final String REGISTRATION_DATE_COLUMN = "registration_date";

    private static final String USER_NAME = "User";

    public User getFullInfo(int userId) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(GET_FULL_INFO_REQUEST)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = createUser(rs);
                    do {
                        addAdvertisement(rs, user.getSalesList());
                        addWishListAdvertisement(rs, user.getWishList());
                        addReview(userId, rs, user.getCommentsList());
                    } while (rs.next());
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    private User createUser(ResultSet rs) throws SQLException {
        City city = new City(rs.getInt("id_city"), rs.getString("city_name"), rs.getString("region"));
        Address address = new Address(rs.getInt("id_address"), city, rs.getString("street"), rs.getInt("house_number"), rs.getInt("flat_number"));
        BasicUserInfo basicUserInfo = new BasicUserInfo(rs.getString(NAME_COLUMN),  rs.getString(SURNAME_COLUMN),
                rs.getString(PHONE_NUMBER_COLUMN), rs.getString(EMAIL_COLUMN), rs.getString(PASSWORD_COLUMN));
        return new User(rs.getInt(ID_COLUMN), basicUserInfo,
                rs.getDate(REGISTRATION_DATE_COLUMN), address, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private void addAdvertisement(ResultSet rs, Set<Advertisement> salesList) throws SQLException {
        int advertisementId = rs.getObject("advertisement_id") != null ? rs.getInt("advertisement_id") : 0;
        if (advertisementId != 0) {
            salesList.add(new Advertisement(advertisementId, rs.getString("advertisement_name"), rs.getString("advertisement_description"), rs.getDouble("advertisement_price"), rs.getBoolean("advertisement_status")));
        }
    }

    private void addWishListAdvertisement(ResultSet rs, Set<Advertisement> wishList) throws SQLException {
        int wishListAdvertisementId = rs.getObject("wish_list_advertisement_id") != null ? rs.getInt("wish_list_advertisement_id") : 0;
        if (wishListAdvertisementId != 0) {
            wishList.add(new Advertisement(wishListAdvertisementId, rs.getString("wished_advertisement_name"), rs.getDouble("wished_advertisement_price")));
        }
    }

    private void addReview(int userId, ResultSet rs, Set<Review> commentsList) throws SQLException {
        int reviewId = rs.getObject("review_id") != null ? rs.getInt("review_id") : 0;
        if (reviewId != 0) {
            User sender = new User(rs.getInt("review_sender_id"), rs.getString("sender_name"), rs.getString("sender_surname"));
            User receiver = new User(userId, rs.getString(NAME_COLUMN), rs.getString(SURNAME_COLUMN));
            commentsList.add(new Review(reviewId, receiver, sender, rs.getByte("review_rating"), rs.getString("review_comment")));
        }
    }

    public List<BaseModelInterface> getListInfo(){
        List<BaseModelInterface> userList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                userList.add(new User(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(SURNAME_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return userList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        User user = (User)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, user);
            return BaseDAO.getGeneratedKey(pstmt, USER_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        User user = (User) bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, user);
            pstmt.setLong(7, user.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("User was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, User user) throws SQLException {
        pstmt.setString(1, user.getBasicInfo().getName());
        pstmt.setString(2, user.getBasicInfo().getSurname());
        pstmt.setString(3, user.getBasicInfo().getPhoneNumber());
        pstmt.setString(4, user.getBasicInfo().getEmail());
        pstmt.setString(5, user.getBasicInfo().getPassword());
        pstmt.setLong(6, user.getAddress().getId());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, USER_NAME);
    }
}