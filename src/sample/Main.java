package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.Menu;
import sample.Model.Staff;

public class Main extends Application {

    static Menu menu;
    static Staff staff;

    @Override
    public void start(Stage primaryStage) throws Exception{
        menu = Menu.loadMenu();
        staff = Staff.loadStaff();
        Parent root = FXMLLoader.load(getClass().getResource("View/menu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    static public Menu getMenu()
    {
        return menu;
    }

    public void deleteDish(String key)
    {
        menu.remove(key);
    }

    public Staff getStaff()
    {
        return staff;
    }

    public void dismissEmployee(int id)
    {
        staff.dismiss(id);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
