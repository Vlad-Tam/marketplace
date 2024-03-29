<<<<<<<< HEAD:src/main/java/com/vladtam/jspapplication/daos/CityDAO.java
package com.vladtam.jspapplication.daos;

import com.vladtam.jspapplication.database.DatabaseHandler;
import com.vladtam.jspapplication.models.BaseModelInterface;
import com.vladtam.jspapplication.models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(CityDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT id, region, name FROM city WHERE id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT id, region, name FROM city;";
    private static final String INSERT_REQUEST = "INSERT INTO city (name, region) VALUES (?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM city WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE city SET name = ?, region = ? WHERE id = ?;";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String REGION_COLUMN = "region";

    private static final String CITY_NAME = "City";

    public City getFullInfo(int id) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_FULL_INFO_REQUEST)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new City(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(REGION_COLUMN));
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    public List<BaseModelInterface> getListInfo() {
        List<BaseModelInterface> citiesList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                citiesList.add(new City(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(REGION_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return citiesList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        City city = (City)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, city);
            return BaseDAO.getGeneratedKey(pstmt, CITY_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        City city = (City) bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, city);
            pstmt.setLong(3, city.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("City was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, City city) throws SQLException {
        pstmt.setString(1, city.getName());
        pstmt.setString(2, city.getRegion());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, CITY_NAME);
    }
========
package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.database.DatabaseHandler;
import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(CityDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT id, region, name FROM city WHERE id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT id, region, name FROM city;";
    private static final String INSERT_REQUEST = "INSERT INTO city (name, region) VALUES (?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM city WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE city SET name = ?, region = ? WHERE id = ?;";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String REGION_COLUMN = "region";

    private static final String CITY_NAME = "City";

    public City getFullInfo(int id) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_FULL_INFO_REQUEST)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new City(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(REGION_COLUMN));
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    public List<BaseModelInterface> getListInfo() {
        List<BaseModelInterface> citiesList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                citiesList.add(new City(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(REGION_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return citiesList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        City city = (City)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, city);
            return BaseDAO.getGeneratedKey(pstmt, CITY_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        City city = (City) bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, city);
            pstmt.setLong(3, city.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("City was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, City city) throws SQLException {
        pstmt.setString(1, city.getName());
        pstmt.setString(2, city.getRegion());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, CITY_NAME);
    }
>>>>>>>> main:task2/src/main/java/com/vladtam/marketplace/dao/CityDAO.java
}