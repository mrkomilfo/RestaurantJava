package sample.Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import sample.DB.DatabaseHandler;
import sample.Dialogs;
import sample.Main;
import sample.Models.Employee;

public class ChangePasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField newPasswordConfirmationField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    void cancelButtonClick(ActionEvent event) {
        closeWindow("administration");
    }

    @FXML
    void saveButtonClick(ActionEvent event) {
        if (!oldPasswordField.getText().equals(account.getPassword()))
        {
            Dialogs.showErrorDialog("Ошибка!", "Старый пароль введён неверно.");
            return;
        }
        if (!newPasswordField.getText().equals(newPasswordConfirmationField.getText()))
        {
            Dialogs.showErrorDialog("Ошибка!", "Пароль не подтверждён.");
            return;
        }
        if (newPasswordField.getText().equals(oldPasswordField.getText()))
        {
            Dialogs.showErrorDialog("Ошибка!", "Старый и новый пароли должны различаться.");
            return;
        }
        if (newPasswordField.getText().length() < 4)
        {
            Dialogs.showErrorDialog("Ошибка!", "Пароль должен состоять как минимум из 4 символов.");
            return;
        }
        DatabaseHandler.changePassword(account.getLogin(), newPasswordField.getText());
        closeWindow("authorization");
    }

    private Employee account;

    @FXML
    void initialize() {
        account = Main.getAccount();
    }

    void closeWindow(String page)
    {
        Stage oldStage = (Stage)saveButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/" + page + ".fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}