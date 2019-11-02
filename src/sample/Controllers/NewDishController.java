package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Dialogs;
import sample.Models.Dish;
import sample.DB.DatabaseHandler;

public class NewDishController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField outputField;

    @FXML
    private TextField costField;

    @FXML
    private ComboBox<String> typePicker;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    void cancelButtonClick(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void saveButtonClick(ActionEvent event) {
        if (typePicker.getValue().length() == 0)
        {
            Dialogs.showErrorDialog("Неверные данные!", "Выберите тип блюда.");
            return;
        }
        if (nameField.getText().length() == 0)
        {
            Dialogs.showErrorDialog("Неверные данные!", "Блюдо должно иметь название.");
            return;
        }
        if (!outputField.getText().matches("\\d+") )
        {
            Dialogs.showErrorDialog("Неверные данные!", "Неверно задан выход блюда. Это должно быть целое число.");
            return;
        }
        if (!costField.getText().matches("\\d+(\\.\\d+)?") )
        {
            Dialogs.showErrorDialog("Неверные данные!", "Неверно задана стоимость.");
            return;
        }
        DatabaseHandler.addDish(new Dish(typePicker.getValue(), nameField.getText(),
                Double.parseDouble(costField.getText()), Integer.parseInt(outputField.getText())));

        closeWindow();
    }

    void closeWindow()
    {
        Stage oldStage = (Stage)saveButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/administration.fxml"));

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

    @FXML
    void initialize() {
        typePicker.setItems(FXCollections.observableArrayList("Холодные закуски", "Первое блюдо", "Гарниры", "Горячие блюда", "Напитки", "Десерты"));
        typePicker.setPromptText(null);
    }
}

