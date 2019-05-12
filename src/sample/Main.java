package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.*;

public class Main extends Application {

    private static Employee account;

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("View/menu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static boolean setAccount(Employee user)
    {
        account = user;
        if (user == null)
            return false;
        else
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
