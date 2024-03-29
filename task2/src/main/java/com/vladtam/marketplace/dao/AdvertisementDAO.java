<<<<<<<< HEAD:src/main/java/com/vladtam/jspapplication/daos/AdvertisementDAO.java
package com.vladtam.jspapplication.daos;

import com.vladtam.jspapplication.database.DatabaseHandler;
import com.vladtam.jspapplication.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdvertisementDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(AdvertisementDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT advert.*, categ.name AS category_name, categ.description AS category_description, vendor.name AS vendor_name, vendor.surname AS vendor_surname, wl.id_user AS wish_list_user_id, wisher.name AS wisher_name, wisher.surname AS wisher_surname FROM advertisement advert JOIN category categ ON advert.id_category = categ.id JOIN user_account vendor ON advert.id_vendor = vendor.id LEFT JOIN wish_list wl ON advert.id = wl.id_advertisement LEFT JOIN user_account wisher ON wl.id_user = wisher.id WHERE advert.id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT id, name, price FROM advertisement;";
    private static final String INSERT_REQUEST = "INSERT INTO advertisement " +
            "(name, description, price, status, id_vendor, id_category) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM advertisement WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE advertisement SET name = ?, description = ?, price = ?, status = ?, " +
            "id_vendor = ?, id_category = ?  WHERE id = ?;";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String PRICE_COLUMN = "price";
    private static final String STATUS_COLUMN = "status";

    private static final String VENDOR_ID_COLUMN = "id_vendor";
    private static final String VENDOR_NAME_COLUMN = "vendor_name";
    private static final String VENDOR_SURNAME_COLUMN = "vendor_surname";

    private static final String CATEGORY_ID_COLUMN = "id_category";
    private static final String CATEGORY_NAME_COLUMN = "category_name";
    private static final String CATEGORY_DESCRIPTION_COLUMN = "category_description";
    private static final String ADVERTISEMENT_NAME = "Advertisement";

    public Advertisement getFullInfo(int id) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_FULL_INFO_REQUEST)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Advertisement advertisement = createAdvertisement(rs);
                    do {
                        addWishingPeopleList(rs, advertisement.getWishingPeopleList());
                    } while (rs.next());
                    return advertisement;
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    private Advertisement createAdvertisement(ResultSet rs) throws SQLException {
        User vendor = new User(rs.getInt(VENDOR_ID_COLUMN), rs.getString(VENDOR_NAME_COLUMN), rs.getString(VENDOR_SURNAME_COLUMN));
        Category category = new Category(rs.getInt(CATEGORY_ID_COLUMN), rs.getString(CATEGORY_NAME_COLUMN), rs.getString(CATEGORY_DESCRIPTION_COLUMN));
        BasicAdvertisementInfo basicInfo = new BasicAdvertisementInfo(rs.getString(NAME_COLUMN),  rs.getString(DESCRIPTION_COLUMN), rs.getDouble(PRICE_COLUMN));
        return new Advertisement(rs.getInt(ID_COLUMN), basicInfo, rs.getBoolean(STATUS_COLUMN), category, vendor, new HashSet<>());
    }

    private void addWishingPeopleList(ResultSet rs, Set<User> wishingPeopleList) throws SQLException {
        int userId = rs.getObject("wish_list_user_id") != null ? rs.getInt("wish_list_user_id") : 0;
        if (userId != 0) {
            wishingPeopleList.add(new User(userId, rs.getString("wisher_name"), rs.getString("wisher_surname")));
        }
    }

    public List<BaseModelInterface> getListInfo() {
        List<BaseModelInterface> advertisementsList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                advertisementsList.add(new Advertisement(rs.getInt(ID_COLUMN),
                        rs.getString(NAME_COLUMN), rs.getDouble(PRICE_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return advertisementsList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        Advertisement advertisement = (Advertisement)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, advertisement);
            return BaseDAO.getGeneratedKey(pstmt, ADVERTISEMENT_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        Advertisement advertisement = (Advertisement)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, advertisement);
            pstmt.setLong(7, advertisement.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("Advertisement was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, Advertisement advertisement) throws SQLException {
        pstmt.setString(1, advertisement.getBasicInfo().getName());
        pstmt.setString(2, advertisement.getBasicInfo().getDescription());
        pstmt.setDouble(3, advertisement.getBasicInfo().getPrice());
        pstmt.setBoolean(4, advertisement.getSaleStatus());
        pstmt.setLong(5, advertisement.getVendor().getId());
        pstmt.setLong(6, advertisement.getCategory().getId());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, ADVERTISEMENT_NAME);
    }
}
========
package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.database.DatabaseHandler;
import com.vladtam.marketplace.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(AdvertisementDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT advert.*, categ.name AS category_name, " +
            "categ.description AS category_description, vendor.name AS vendor_name, vendor.surname AS vendor_surname " +
            "FROM advertisement advert JOIN category categ ON advert.id_category = categ.id JOIN user_account vendor " +
            "ON advert.id_vendor = vendor.id WHERE advert.id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT id, name, price FROM advertisement;";
    private static final String INSERT_REQUEST = "INSERT INTO advertisement " +
            "(name, description, price, status, id_vendor, id_category) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM advertisement WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE advertisement SET name = ?, description = ?, price = ?, status = ?, " +
            "id_vendor = ?, id_category = ?  WHERE id = ?;";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String PRICE_COLUMN = "price";
    private static final String STATUS_COLUMN = "status";

    private static final String VENDOR_ID_COLUMN = "id_vendor";
    private static final String VENDOR_NAME_COLUMN = "vendor_name";
    private static final String VENDOR_SURNAME_COLUMN = "vendor_surname";

    private static final String CATEGORY_ID_COLUMN = "id_category";
    private static final String CATEGORY_NAME_COLUMN = "category_name";
    private static final String CATEGORY_DESCRIPTION_COLUMN = "category_description";
    private static final String ADVERTISEMENT_NAME = "Advertisement";

    public Advertisement getFullInfo(int id) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_FULL_INFO_REQUEST)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return  new Advertisement(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN),
                            rs.getString(DESCRIPTION_COLUMN), rs.getDouble(PRICE_COLUMN),
                            rs.getBoolean(STATUS_COLUMN), new Category(rs.getInt(CATEGORY_ID_COLUMN),
                            rs.getString(CATEGORY_NAME_COLUMN), rs.getString(CATEGORY_DESCRIPTION_COLUMN)),
                            new User(rs.getInt(VENDOR_ID_COLUMN), rs.getString(VENDOR_NAME_COLUMN),
                            rs.getString(VENDOR_SURNAME_COLUMN)));
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    public List<BaseModelInterface> getListInfo() {
        List<BaseModelInterface> advertisementsList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                advertisementsList.add(new Advertisement(rs.getInt(ID_COLUMN),
                        rs.getString(NAME_COLUMN), rs.getDouble(PRICE_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return advertisementsList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        Advertisement advertisement = (Advertisement)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, advertisement);
            return BaseDAO.getGeneratedKey(pstmt, ADVERTISEMENT_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        Advertisement advertisement = (Advertisement)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, advertisement);
            pstmt.setLong(7, advertisement.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("Advertisement was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, Advertisement advertisement) throws SQLException {
        pstmt.setString(1, advertisement.getName());
        pstmt.setString(2, advertisement.getDescription());
        pstmt.setDouble(3, advertisement.getPrice());
        pstmt.setBoolean(4, advertisement.getSaleStatus());
        pstmt.setLong(5, advertisement.getVendor().getId());
        pstmt.setLong(6, advertisement.getCategory().getId());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, ADVERTISEMENT_NAME);
    }
}
>>>>>>>> main:task2/src/main/java/com/vladtam/marketplace/dao/AdvertisementDAO.java
