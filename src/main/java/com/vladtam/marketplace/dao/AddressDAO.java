package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.database.DatabaseHandler;
import com.vladtam.marketplace.models.Address;
import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    //private BaseDAO baseDAO = new BaseDAO();
    public static final Logger logger = LoggerFactory.getLogger(AddressDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT addr.*, ci.name AS city_name, ci.region " +
            "AS city_region FROM address addr JOIN city ci ON addr.id_city = ci.id WHERE addr.id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT addr.*, ci.name AS city_name, ci.region AS city_region " +
            "FROM address addr JOIN city ci ON addr.id_city = ci.id;";
    private static final String INSERT_REQUEST = "INSERT INTO address (street, house_number, flat_number, id_city) " +
            "VALUES (?, ?, ?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM address WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE address " +
            "SET street = ?, house_number = ?, flat_number = ?, id_city = ? WHERE id = ?";

    private static final String ID_COLUMN = "id";
    private static final String STREET_COLUMN = "street";
    private static final String HOUSE_COLUMN = "house_number";
    private static final String FLAT_COLUMN = "flat_number";

    private static final String CITY_ID_COLUMN = "id_city";
    private static final String CITY_NAME_COLUMN = "city_name";
    private static final String CITY_REGION_COLUMN = "city_region";

    private static final String ADDRESS_NAME = "Address";

    public Address getFullInfo(int id) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_FULL_INFO_REQUEST)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new Address(rs.getInt(ID_COLUMN), new City(rs.getInt(CITY_ID_COLUMN), rs.getString(CITY_NAME_COLUMN),
                            rs.getString(CITY_REGION_COLUMN)), rs.getString(STREET_COLUMN), rs.getInt(HOUSE_COLUMN), rs.getInt(FLAT_COLUMN));
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    public List<BaseModelInterface> getListInfo() {
        List<BaseModelInterface> addressesList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                addressesList.add(new Address(rs.getInt(ID_COLUMN), new City(rs.getInt(CITY_ID_COLUMN), rs.getString(CITY_NAME_COLUMN),
                        rs.getString(CITY_REGION_COLUMN)), rs.getString(STREET_COLUMN), rs.getInt(HOUSE_COLUMN), rs.getInt(FLAT_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return addressesList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        Address address = (Address)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, address);
            return BaseDAO.getGeneratedKey(pstmt, ADDRESS_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        Address address = (Address)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, address);
            pstmt.setLong(5, address.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("Address was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, Address address) throws SQLException {
        pstmt.setString(1, address.getStreet());
        pstmt.setInt(2, address.getHouseNumber());
        pstmt.setInt(3, address.getFlatNumber());
        pstmt.setLong(4, address.getCity().getId());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, ADDRESS_NAME);
    }
}
