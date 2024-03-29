package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.database.DatabaseHandler;
import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements BaseDAOInterface {
    private DatabaseHandler dbHandler = new DatabaseHandler();
    public static final Logger logger = LoggerFactory.getLogger(CategoryDAO.class);
    private static final String GET_FULL_INFO_REQUEST = "SELECT id, name, description FROM category WHERE id = ?;";
    private static final String GET_LIST_INFO_REQUEST = "SELECT id, name, description FROM category;";
    private static final String INSERT_REQUEST = "INSERT INTO category (name, description) VALUES (?, ?);";
    private static final String DELETE_REQUEST = "DELETE FROM category WHERE id = ?;";
    private static final String UPDATE_REQUEST = "UPDATE category SET name = ?, description = ? WHERE id = ?;";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";

    private static final String CATEGORY_NAME = "Category";

    public Category getFullInfo(int id) {
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_FULL_INFO_REQUEST)){
            preparedStatement.setInt(1, id);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new Category(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(DESCRIPTION_COLUMN));
                }
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return null;
    }

    public List<BaseModelInterface> getListInfo() {
        List<BaseModelInterface> categoriesList = new ArrayList<>();
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_LIST_INFO_REQUEST);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()) {
                categoriesList.add(new Category(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(DESCRIPTION_COLUMN)));
            }
        } catch (SQLException e) {
            logger.error("Database read error", e);
        }
        return categoriesList;
    }

    @Override
    public int createNew(BaseModelInterface bsModel) {
        Category category = (Category)bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            initializePreparedStatement(pstmt, category);
            return BaseDAO.getGeneratedKey(pstmt, CATEGORY_NAME);
        } catch (SQLException e) {
            logger.error("Database create object error", e);
        }
        return -1;
    }

    @Override
    public void update(BaseModelInterface bsModel) {
        Category category = (Category) bsModel;
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_REQUEST)) {
            initializePreparedStatement(pstmt, category);
            pstmt.setLong(3, category.getId());
            if (pstmt.executeUpdate() > 0) {
                logger.trace("Category was updated successfully!");
            }
        } catch (SQLException e) {
            logger.error("Database update object error", e);
        }
    }

    private void initializePreparedStatement(PreparedStatement pstmt, Category category) throws SQLException {
        pstmt.setString(1, category.getName());
        pstmt.setString(2, category.getDescription());
    }

    @Override
    public void delete(int id) {
        BaseDAO.delete(id, DELETE_REQUEST, CATEGORY_NAME);
    }
}
