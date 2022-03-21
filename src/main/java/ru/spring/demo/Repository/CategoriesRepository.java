package ru.spring.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Repository
public class CategoriesRepository {

    @Autowired
    DataSource ds;

    public List<Category> getCategories() {
        List<Category> allCategories = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories");
        )  {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allCategories.add(new Category(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            return allCategories;
        } catch (SQLException e) {
            e.printStackTrace();
            return allCategories;
        }
    }

    public boolean setNewCategory(Category category) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO categories (category) VALUES (?)");
        )  {
            ps.setString(1, category.getCategory().toUpperCase());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(int id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM categories WHERE id = ?");
             )  {
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getCategoryId(Category category) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT id FROM categories WHERE category = ?");
             )  {
            ps.setString(1, category.getCategory());

            try (ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Category getCategory(int id) {
        Category category = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories WHERE id =" + id);
        )  {
            try (ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    category = new Category(rs.getInt(1), rs.getString(2));
                }
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean editCategory(int id, Category category) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE categories SET" +
                             "category = ?" +
                             "WHERE id = ?");
             )  {
            ps.setString(1, category.getCategory());
            ps.setInt(2, id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
