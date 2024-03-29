package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DB.DatabaseHandler;
import sample.Dialogs;
import sample.Main;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    private Button enterButton;

    @FXML
    void backButtonClick(ActionEvent event) {
        Stage oldStage = (Stage)backButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/menu.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        oldStage.close();
    }

    @FXML
    void enterButtonClick(ActionEvent event) {

        if (Main.setAccount(DatabaseHandler.getUser(loginField.getText(), passwordField.getText())))
        {
            Stage oldStage = (Stage) enterButton.getScene().getWindow();
            oldStage.hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Views/administration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else
        {
            Dialogs.showErrorDialog("Ошибка!", "Неверный логин или пароль.");
            return;
        }
    }

    @FXML
    void initialize() {
    }
}

