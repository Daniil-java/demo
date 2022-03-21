package ru.spring.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ExpenseRepository {

    @Autowired
    DataSource ds;

    public List<Expense> getExpenses() {     //Весь список
        List<Expense> expenses = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT id, name, sum, categories.category, ts FROM expenses" +
                             "LEFT JOIN categories ON expenses.categoryId = categories.id"
             );
             ) {
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    expenses.add(new Expense(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getInt(4),
                                    rs.getString(5)
                            )
                    );
                }
            }
            return expenses;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Expense> getExpenses(String sortObj, String type) {  //Сортированный список
        List<Expense> expenses = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT id, name, sum, categories.category, ts FROM expenses " +
                             "JOIN categories ON expenses.categoryId = categories.id " +
                             "ORDER BY " + sortObj.toUpperCase() + " " + type.toUpperCase()
             );
             ) {
            try (ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    expenses.add(new Expense(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getInt(4),
                                    rs.getString(5)
                            )
                    );
                }
            }
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Expense> getExpenses(Filter filter) {  //Фильтрованный список
        List<Expense> expenses = new ArrayList<>();

        //Нельзя выбрать несколько категорий
        //Нельзя не выбирать категорию

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT e.id, e.name, e.sum, c.category, e.ts FROM expenses e JOIN categories c ON e.categoryId = c.id " +
                     " WHERE " +
                     "(sum BETWEEN ? AND ? ) AND " +
                     "(ts BETWEEN ? AND ?) AND " +
                     "categoryId = ? " +
                     "ORDER BY ? "
             );
        ) {

            ps.setDouble(1, filter.getSumFrom());
            ps.setDouble(2, filter.getSumTo());
            ps.setString(3, filter.getDateFrom().toString());
            ps.setString(4, filter.getDateTo().toString());
            ps.setString(5, filter.getCategory());
            ps.setString(6, filter.getOrder());

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    expenses.add(new Expense(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getInt(4),
                                    rs.getString(5)
                            )
                    );
                }
            }
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setNewExpense(Expense expense) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO expenses (name, sum, categoryId, ts) VALUES (?, ?, ?, ?)");
        ) {
            java.util.Date date = new Date();
            ps.setString(1, expense.getName());
            ps.setDouble(2, expense.getSum());
            ps.setInt(3, expense.getCategoryId());
            ps.setString(4, date.toString());
            ps.execute();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExpense(int id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM expenses WHERE id = ?");
             )  {
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Expense getExpense(int id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT name, sum, categories.category, ts FROM expenses e JOIN categories c ON e.categoryId = c.id WHERE id = ?");
        )  {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    Expense expense = new Expense(
                            id,
                            rs.getString(1),
                            rs.getDouble(2),
                            rs.getInt(3),
                            rs.getString(4)
                    );
                    return expense;
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean editExpense(int id, Expense expense) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE expenses SET" +
                             "name = ?, sum = ?, categoryId = ?, ts = ?" +
                             "WHERE id = ?");
        )  {
            ps.setString(1, expense.getName());
            ps.setDouble(2, expense.getSum());
            ps.setInt(3, expense.getCategoryId());
            ps.setString(4, expense.getTs());
            ps.setInt(5, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
