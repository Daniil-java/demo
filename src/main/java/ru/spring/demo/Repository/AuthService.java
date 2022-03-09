package ru.spring.demo.Repository;

import org.springframework.stereotype.Repository;
import ru.spring.demo.Objects.Category;
import ru.spring.demo.Objects.Expense;
import ru.spring.demo.Objects.Filter;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Repository
public class AuthService {

    private static Connection connection;
    private static Statement statement;

    public static void connection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Expense> getExpenses() {     //Весь список
        List<Expense> expenses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, money, category, date FROM expenses"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expenses.add(new Expense(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5)
                        )
                );
            }
            rs.close();
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Expense> getExpenses(String sortObj, String type) {  //Сортированный список
        List<Expense> expenses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, money, category, date FROM expenses ORDER BY " + sortObj.toUpperCase() + " " + type.toUpperCase()
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expenses.add(new Expense(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4),
                                rs.getString(5)
                        )
                );
            }
            rs.close();
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Expense> getExpenses(Filter filter) {  //Фильтрованный список
        List<Expense> expenses = new ArrayList<>();

        //Нельзя выбрать несколько категорий
        //Нельзя не выбирать категорию

//        String sqlRequest = "SELECT id, name, money, category, date FROM expenses WHERE " +
//                "(money BETWEEN " + filter.getMoneyFrom() + " AND " + filter.getMoneyTo() + " ) " +
//                "AND (date BETWEEN '" + filter.getDateFrom() + "' AND '" + filter.getDateTo() + "') " +
//                "AND category = '" + filter.getCategory() + "'";

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, money, category, date FROM expenses WHERE " +
                    "(money BETWEEN ? AND ? ) AND " +
                    "(date BETWEEN ? AND ?) AND " +
                    "category = ?");
            ps.setDouble(1, filter.getMoneyFrom());
            ps.setDouble(2, filter.getMoneyTo());
            ps.setString(3, filter.getDateFrom());
            ps.setString(4, filter.getDateTo());
            ps.setString(5, filter.getCategory());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expenses.add(new Expense(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4),
                                rs.getString(5)
                        )
                );
            }
            rs.close();
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean setNewExpense(Expense expense) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO expenses (name, money, category, date) VALUES (?, ?, ?, ?)");
            Date date = new Date();
            ps.setString(1, expense.getName());
            ps.setDouble(2, expense.getMoney());
            ps.setString(3, expense.getType());
            ps.setString(4, date.toString());
            ps.execute();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteExpense(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM expenses WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Expense getExpense(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT name, money, category, date FROM expenses WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                        id,
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                return expense;
            }
            rs.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static int getExpenseId(Expense expense) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT name, money, category, date FROM expenses WHERE id = ?");
//            ps.setInt(1, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }

    public static boolean editExpense(int id, Expense expense) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE expenses SET" +
                    "name = ?, money = ?, category = ?, date = ?" +
                            "WHERE id = ?");
            ResultSet rs = ps.executeQuery();
            ps.setString(1, expense.getName());
            ps.setDouble(2, expense.getMoney());
            ps.setString(3, expense.getType());
            ps.setString(4, expense.getDate());
            ps.setInt(5, id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Category> getCategories() {
        try {
            List<Category> allCategories = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM categories"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allCategories.add(new Category(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            return allCategories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean setNewCategory(Category category) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO categories (category) VALUES (?)");
            ps.setString(1, category.getCategory().toUpperCase());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCategory(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM categories WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getCategoryId(Category category) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM categories WHERE category = ?");
            ps.setString(1, category.getCategory());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Category getCategory(int id) {
        try {
            Category category = null;
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories WHERE id =" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
            }
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    private static void createTable() throws SQLException {
//        statement.executeUpdate(
//                "CREATE TABLE IF NOT EXISTS expenses(" +
//                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "name VARCHAR(250)," +
//                        "money DOUBLE," +
//                        "type INTEGER," +
//                        "date DATETIME NOT NULL," +
//                        "FOREIGN KEY (type) REFERENCES categories(id)" +
//                        ")"
//
//        );
//        statement.executeUpdate("CREATE INDEX expenses_money_idx ON expenses(money)");
//        statement.executeUpdate("CREATE INDEX expenses_date_idx ON expenses(date)");
//        statement.executeUpdate("CREATE INDEX expenses_type_idx ON expenses(type)");
//    }
//
//    private static void createTableCategories() throws SQLException {
//        statement.executeUpdate(
//                "CREATE TABLE IF NOT EXISTS categories(" +
//                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "name VARCHAR(250)" +
//                        ")"
//        );
//        statement.executeUpdate("CREATE INDEX categories_name_idx ON categories(name);");
//    }

}
