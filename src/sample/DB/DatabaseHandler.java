package sample.DB;

import sample.Model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler extends Configs{
    static Connection dbConnection;

    public static Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionPath = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionPath, dbUser, dbPass);

        return dbConnection;
    }

    private static String translate(String rus) {
        if (rus.equals("Холодные закуски")) return Const.SALAD_TABLE;
        if (rus.equals("Первое блюдо")) return Const.FIRSTCOURSE_TABLE;
        if (rus.equals("Гарниры")) return Const.GARNISH_TABLE;
        if (rus.equals("Горячие блюда")) return Const.HOTDISHES_TABLE;
        if (rus.equals("Напитки")) return Const.DRINKS_TABLE;
        if (rus.equals("Десерты")) return Const.DESSERTS_TABLE;
        return null;
    }

    public static void addDish (String type, String name, double price, int output) {
        String request = "INSERT INTO " + translate(type) + "(" + Const.DISH_TYPE + "," + Const.DISH_NAME + "," +
                Const.DISH_PRICE + "," + Const.DISH_OUTPUT + ")" + "VALUES(?,?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);

            prSt.setString(1, type);
            prSt.setString(2, name);
            prSt.setDouble(3, price);
            prSt.setInt(4, output);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addDish (Dish dish) {
        String request = "INSERT INTO " + translate(dish.getType()) + "(" + Const.DISH_TYPE + "," + Const.DISH_NAME + "," +
                Const.DISH_PRICE + "," + Const.DISH_OUTPUT + ")" + "VALUES(?,?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);

            prSt.setString(1, dish.getType());
            prSt.setString(2, dish.getName());
            prSt.setDouble(3, dish.getPrice());
            prSt.setInt(4, dish.getOutput());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDish(String type, String name)
    {
        String request = "DELETE FROM " + translate(type) + " WHERE " + Const.DISH_NAME + " = ?";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            prSt.setString(1, name);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Menu loadDishes (String type) {
        Menu dishes = new Menu();
        ResultSet resultSet = null;
        String request = "SELECT * FROM " + type;

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                dishes.add(new Dish(resultSet.getString(Const.DISH_TYPE), resultSet.getString(Const.DISH_NAME),
                        resultSet.getDouble(Const.DISH_PRICE), resultSet.getInt(Const.DISH_OUTPUT)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    public static Employee getUser(String login, String password){
        Employee user = new Employee();
        int counter = 0;

        ResultSet resultSet = null;

        String request = "SELECT * FROM " + Const.STAFF_TABLE + " WHERE " + Const.EMPLOYEE_LOGIN + "= ? AND " +
                Const.EMPLOYEE_PASSWORD + "= ?";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(request);
            prSt.setString(1, login);
            prSt.setString(2, password);

            resultSet = prSt.executeQuery();

            while (resultSet.next())
            {
                user.name = resultSet.getString(Const.EMPLOYEE_NAME);
                user.surname = resultSet.getString(Const.EMPLOYEE_SURNAME);
                user.patronymic = resultSet.getString(Const.EMPLOYEE_PATRONYMIC);
                user.birthDate = Instant.ofEpochMilli(resultSet.getDate(Const.EMPLOYEE_BIRTHDAY).
                        getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                user.position = resultSet.getString(Const.EMPLOYEE_POSITION);
                user.salary = resultSet.getDouble(Const.EMPLOYEE_SALARY);
                user.login = resultSet.getString(Const.EMPLOYEE_LOGIN);
                user.password = resultSet.getString(Const.EMPLOYEE_PASSWORD);
                user.menuRoot = resultSet.getBoolean(Const.EMPLOYEE_MENUACCESS);
                user.menuRootReadOnly = resultSet.getBoolean(Const.EMPLOYEE_MENUREADONLY);
                user.employeesRoot = resultSet.getBoolean(Const.EMPLOYEE_STAFFACCESS);
                user.employeesRootReadOnly = resultSet.getBoolean(Const.EMPLOYEE_STAFFREADONLY);
                user.ordersRoot = resultSet.getBoolean(Const.EMPLOYEE_ORDERSACCESS);
                user.ordersRootReadOnly = resultSet.getBoolean(Const.EMPLOYEE_ORDERSREADONLY);
                counter++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (counter == 0)
            return null;
        else
            return user;
    }

    public static void addEmployee(String surname, String name, String patronymic,
                                   LocalDate birthDate, String position, double salary, String login, String password,
                                   boolean menuRoot, boolean menuRootReadOnly, boolean employeesRoot,
                                   boolean employeesRootReadOnly, boolean ordersRoot, boolean ordersRootReadOnly)
    {
        String request = "INSERT INTO " + Const.STAFF_TABLE + "(" + Const.EMPLOYEE_SURNAME + "," + Const.EMPLOYEE_NAME +
                "," + Const.EMPLOYEE_PATRONYMIC + "," + Const.EMPLOYEE_BIRTHDAY + "," + Const.EMPLOYEE_POSITION + "," +
                Const.EMPLOYEE_SALARY + "," + Const.EMPLOYEE_LOGIN + "," + Const.EMPLOYEE_PASSWORD + "," +
                Const.EMPLOYEE_MENUACCESS + "," + Const.EMPLOYEE_MENUREADONLY + "," + Const.EMPLOYEE_STAFFACCESS + "," +
                Const.EMPLOYEE_STAFFREADONLY + "," + Const.EMPLOYEE_ORDERSACCESS + "," + Const.EMPLOYEE_ORDERSREADONLY +
                ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);

            prSt.setString(1, surname);
            prSt.setString(2, name);
            prSt.setString(3, patronymic);
            prSt.setDate(4, java.sql.Date.valueOf(birthDate));
            prSt.setString(5, position);
            prSt.setDouble(6, salary);
            prSt.setString(7, login);
            prSt.setString(8, password);
            prSt.setBoolean(9, menuRoot);
            prSt.setBoolean(10, menuRootReadOnly);
            prSt.setBoolean(11, employeesRoot);
            prSt.setBoolean(12, employeesRootReadOnly);
            prSt.setBoolean(13, ordersRoot);
            prSt.setBoolean(14, ordersRootReadOnly);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(Employee employee)
    {
        String request = "INSERT INTO " + Const.STAFF_TABLE + "(" + Const.EMPLOYEE_SURNAME + "," + Const.EMPLOYEE_NAME +
                "," + Const.EMPLOYEE_PATRONYMIC + "," + Const.EMPLOYEE_BIRTHDAY + "," + Const.EMPLOYEE_POSITION + "," +
                Const.EMPLOYEE_SALARY + "," + Const.EMPLOYEE_LOGIN + "," + Const.EMPLOYEE_PASSWORD + "," +
                Const.EMPLOYEE_MENUACCESS + "," + Const.EMPLOYEE_MENUREADONLY + "," + Const.EMPLOYEE_STAFFACCESS + "," +
                Const.EMPLOYEE_STAFFREADONLY + "," + Const.EMPLOYEE_ORDERSACCESS + "," + Const.EMPLOYEE_ORDERSREADONLY +
                ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);

            prSt.setString(1, employee.surname);
            prSt.setString(2, employee.name);
            prSt.setString(3, employee.patronymic);
            prSt.setDate(4, java.sql.Date.valueOf(employee.birthDate));
            prSt.setString(5, employee.position);
            prSt.setDouble(6, employee.salary);
            prSt.setString(7, employee.login);
            prSt.setString(8, employee.password);
            prSt.setBoolean(9, employee.menuRoot);
            prSt.setBoolean(10, employee.menuRootReadOnly);
            prSt.setBoolean(11, employee.employeesRoot);
            prSt.setBoolean(12, employee.employeesRootReadOnly);
            prSt.setBoolean(13, employee.ordersRoot);
            prSt.setBoolean(14, employee.ordersRootReadOnly);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(String login)
    {
        String request = "DELETE FROM " + Const.STAFF_TABLE + " WHERE " + Const.EMPLOYEE_LOGIN + " = ?";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            prSt.setString(1, login);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void changePassword(String login, String newPassword)
    {
        String request = "UPDATE " + Const.STAFF_TABLE + " SET " + Const.EMPLOYEE_PASSWORD +
                " = ? WHERE " + Const.EMPLOYEE_LOGIN + " = ?";
        PreparedStatement prSt = null;
        try{
            prSt = getDbConnection().prepareStatement(request);
            prSt.setString(1, newPassword);
            prSt.setString(2, login);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Staff loadStaff(){
        Staff staff = new Staff();

        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.STAFF_TABLE;

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();

            while (resultSet.next())
            {
                Employee employee = new Employee();
                employee.surname = resultSet.getString(Const.EMPLOYEE_SURNAME);
                employee.name = resultSet.getString(Const.EMPLOYEE_NAME);
                employee.patronymic = resultSet.getString(Const.EMPLOYEE_PATRONYMIC);
                employee.birthDate = Instant.ofEpochMilli(resultSet.getDate(Const.EMPLOYEE_BIRTHDAY).
                        getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                employee.position = resultSet.getString(Const.EMPLOYEE_POSITION);
                employee.salary = resultSet.getDouble(Const.EMPLOYEE_SALARY);
                employee.login = resultSet.getString(Const.EMPLOYEE_LOGIN);
                employee.password = resultSet.getString(Const.EMPLOYEE_PASSWORD);
                employee.menuRoot = resultSet.getBoolean(Const.EMPLOYEE_MENUACCESS);
                employee.menuRootReadOnly = resultSet.getBoolean(Const.EMPLOYEE_MENUREADONLY);
                employee.employeesRoot = resultSet.getBoolean(Const.EMPLOYEE_STAFFACCESS);
                employee.employeesRootReadOnly = resultSet.getBoolean(Const.EMPLOYEE_STAFFREADONLY);
                employee.ordersRoot = resultSet.getBoolean(Const.EMPLOYEE_ORDERSACCESS);
                employee.ordersRootReadOnly = resultSet.getBoolean(Const.EMPLOYEE_ORDERSREADONLY);
                employee.setSNP(employee.surname + " " + employee.name + " " + employee.patronymic);
                staff.add(employee);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public static void addOrder(Bill bill)
    {
        SimpleDateFormat formatForOrderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String request = "INSERT INTO " + Const.ORDERS_TABLE + "(" + Const.ORDER_TIME + ") " + "VALUES(?)";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            prSt.setString(1, formatForOrderTime.format(new Date()));
            prSt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ResultSet resultSet = null;
        int lastNumber = 0;
        request = "SELECT MAX(" + Const.ORDER_NUMBER + ") AS NUMBER FROM " + Const.ORDERS_TABLE;
        try {
            prSt = getDbConnection().prepareStatement(request);
            resultSet = prSt.executeQuery();
            while (resultSet.next())
                lastNumber = resultSet.getInt("NUMBER");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request = "CREATE TABLE " + Const.ORDER_TABLE + lastNumber + "(" +
                Const.DISH_TYPE + " VARCHAR(32) NOT NULL, " +
                Const.DISH_NAME + " VARCHAR(32) NOT NULL, " +
                Const.DISH_PRICE + " DOUBLE NOT NULL, " +
                Const.DISH_OUTPUT + " INT(11) NOT NULL, " +
                Const.DISH_QUANTITY + " INT(11) NOT NULL, " +
                "PRIMARY KEY(" + Const.DISH_NAME + "))";
        prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            prSt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Section section: bill.values()) {

            request = "INSERT INTO " + Const.ORDER_TABLE + lastNumber + "(" + Const.DISH_TYPE + "," + Const.DISH_NAME + "," +
                    Const.DISH_PRICE + "," + Const.DISH_OUTPUT + "," + Const.DISH_QUANTITY + ")" + "VALUES(?,?,?,?,?)";

            prSt = null;
            try {
                prSt = getDbConnection().prepareStatement(request);

                prSt.setString(1, section.getType());
                prSt.setString(2, section.getName());
                prSt.setDouble(3, section.getPrice());
                prSt.setInt(4, section.getOutput());
                prSt.setInt(5, section.getQuantity());

                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<Integer, Order> loadOrders()
    {
        Map<Integer, Order> orders = new HashMap<>();
        ResultSet resultSet = null;
        String request = "SELECT * FROM " + Const.ORDERS_TABLE;

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                orders.put(resultSet.getInt(Const.ORDER_NUMBER),
                        new Order(resultSet.getInt(Const.ORDER_NUMBER), resultSet.getString(Const.ORDER_TIME),
                                getBill(resultSet.getInt(Const.ORDER_NUMBER))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static Bill getBill(int number)
    {
        Bill bill = new Bill();
        ResultSet resultSet = null;
        String request = "SELECT * FROM " + Const.ORDER_TABLE + number;

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                bill.add(new Section(resultSet.getString(Const.DISH_TYPE), resultSet.getString(Const.DISH_NAME),
                        resultSet.getDouble(Const.DISH_PRICE), resultSet.getInt(Const.DISH_OUTPUT),
                        resultSet.getInt(Const.DISH_QUANTITY)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bill;
    }

    public static void closeOrder(int number)
    {
        String request = "DROP TABLE " + Const.ORDER_TABLE + number;
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request = "DELETE FROM " + Const.ORDERS_TABLE + " WHERE " + Const.ORDER_NUMBER + " = ?";
        prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(request);
            prSt.setInt(1, number);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
