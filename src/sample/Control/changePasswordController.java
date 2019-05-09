package sample.Control;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import sample.Main;
import sample.Model.Employee;

public class changePasswordController {

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
        if (!oldPasswordField.getText().equals(account.password))
        {
            showErrorDialog("Ошибка!", "Старый пароль введён неверно.");
            return;
        }
        if (!newPasswordField.getText().equals(newPasswordConfirmationField.getText()))
        {
            showErrorDialog("Ошибка!", "Пароль не подтверждён.");
            return;
        }
        if (newPasswordField.getText().equals(oldPasswordField.getText()))
        {
            showErrorDialog("Ошибка!", "Старый и новый пароли должны различаться.");
            return;
        }
        if (newPasswordField.getText().length() < 4)
        {
            showErrorDialog("Ошибка!", "Пароль должен состоять как минимум из 4 символов.");
            return;
        }
        Main.changePassword(account.login, newPasswordField.getText());
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
        loader.setLocation(getClass().getResource("/sample/View/" + page + ".fxml"));

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

    static void showErrorDialog(String title, String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }
}