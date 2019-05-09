package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.*;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    static Menu menu;
    static Staff staff;
    static Map<Integer, Order> orders;
    static int number = 0;
    static Employee account;

    @Override
    public void start(Stage primaryStage) throws Exception{
        menu = new Menu();
        menu = Menu.loadMenu();
        staff = Staff.loadStaff();
        orders = new HashMap<>();

        Parent root = FXMLLoader.load(getClass().getResource("View/menu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    static public void changePassword(String login, String newPassword)
    {
        staff.get(login).password = newPassword;
    }

    static public Menu getMenu()
    {
        return menu;
    }

    static public  void addDish(Dish dish)
    {
        menu.add(dish);
    }

    static public void deleteDish(String key)
    {
        menu.remove(key);
    }

    static public Staff getStaff()
    {
        return staff;
    }

    static public void addEmployee(Employee newEmployee)
    {
        staff.recruit(newEmployee);
    }

    static public void dismissEmployee(String login)
    {
        staff.dismiss(login);
    }

    public static void addOrder(Bill bill)
    {
        orders.put(number, new Order(number, bill));
        number++;
    }

    public static Map<Integer, Order> getOrders()
    {
        return orders;
    }

    public static void closeOrder(int key)
    {
        orders.remove(key);
    }

    public static boolean login(String login, String password)
    {
        if ( !password.equals(staff.get(login).password))
        {
            return false;
        }
        account = staff.get(login);
        return true;
    }

    public static Employee getAccount()
    {
        return account;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
