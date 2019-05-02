package sample.Control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Model.Dish;

public class newDishController {

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
        if (nameField.getText().length() == 0)
        {
            showErrorDialog("Неверные данные!", "Блюдо должно иметь название.");
            return;
        }
        if (!outputField.getText().matches("\\d+") )
        {
            showErrorDialog("Неверные данные!", "Неверно задан выход блюда. Это должно быть целое число.");
            return;
        }
        if (!costField.getText().matches("\\d+(\\.\\d+)?") )
        {
            showErrorDialog("Неверные данные!", "Неверно задана стоимость.");
            return;
        }
        Dish newDish = new Dish(typePicker.getPromptText(), nameField.getText(), Double.parseDouble(costField.getText()), Integer.parseInt(outputField.getText()));
        Main.addDish(newDish);

        closeWindow();
    }

    void closeWindow()
    {
        Stage oldStage = (Stage)saveButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/administration.fxml"));

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

    @FXML
    void initialize() {
        typePicker.setItems(FXCollections.observableArrayList("Холодные закуски", "Первое блюдо", "Гарниры", "Горячие блюда", "Напитки", "Десерты"));
        typePicker.setPromptText("Холодные закуски");
    }
}

