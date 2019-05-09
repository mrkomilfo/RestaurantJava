package sample.Control;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.Model.Dish;
import sample.Model.Employee;

public class newEmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField patronomycField;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TextField positionField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox menuAccess;

    @FXML
    private CheckBox employeesAccess;

    @FXML
    private CheckBox ordersAccess;

    @FXML
    private CheckBox menuReadonly;

    @FXML
    private CheckBox employeesReadonly;

    @FXML
    private CheckBox ordersReadonly;

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
        if (surnameField.getText().length() == 0 || nameField.getText().length() == 0 || patronomycField.getText().length() == 0 ||
            birthdayPicker.getEditor().getText().length() == 0 || positionField.getText().length() == 0 || loginField.getText().length() == 0)
        {
            showErrorDialog("Неверные данные!", "Все поля должны быть заполнены.");
            return;
        }
        if (!salaryField.getText().matches("\\d+(\\.\\d+)?") )
        {
            showErrorDialog("Неверные данные!", "Оклад задан неверно.");
            return;
        }
        if (passwordField.getText().length() < 4)
        {
            showErrorDialog("Неверные данные!", "Пароль должен состоять как минимум из 4 символов.");
            return;
        }
        Employee newEmployee = new Employee(surnameField.getText(), nameField.getText(), patronomycField.getText(),
                birthdayPicker.getValue(), positionField.getText(), Double.parseDouble(salaryField.getText()),
                loginField.getText(), passwordField.getText(), menuAccess.isSelected(), menuReadonly.isSelected(),
                employeesAccess.isSelected(), employeesReadonly.isSelected(), ordersAccess.isSelected(), ordersReadonly.isSelected());
        Main.addEmployee(newEmployee);
        closeWindow();
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
        //TODO: checkboxes changes event
        menuAccess.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && menuReadonly.isSelected())
                {
                    menuReadonly.setSelected(false);
                }

            }
        });
        menuReadonly.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue && !menuAccess.isSelected())
                {
                    menuAccess.setSelected(true);
                }

            }
        });
        employeesAccess.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && employeesReadonly.isSelected())
                {
                    employeesReadonly.setSelected(false);
                }

            }
        });
        employeesReadonly.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue && !employeesAccess.isSelected())
                {
                    employeesAccess.setSelected(true);
                }

            }
        });
        ordersAccess.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && ordersReadonly.isSelected())
                {
                    ordersReadonly.setSelected(false);
                }

            }
        });
        ordersReadonly.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue && !ordersAccess.isSelected())
                {
                    ordersAccess.setSelected(true);
                }

            }
        });
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
}

