package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.DB.Const;
import sample.DB.DatabaseHandler;
import sample.Dialogs;
import sample.Main;
import sample.Models.*;
import sample.Models.Menu;

public class AdministrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab mainTab;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label patronymicLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label positionLabel;

    @FXML
    private Label salaryLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Tab menuTab;

    @FXML
    private ComboBox<String> menuTypePicker;

    @FXML
    private TableView<Dish> menuTable;

    @FXML
    private TableColumn<Dish, String> dishNameColumn;

    @FXML
    private TableColumn<Dish, Integer> dishOutputColumn;

    @FXML
    private TableColumn<Dish, Integer> dishCostColumn;

    @FXML
    private HBox menuButtonsPanel;

    @FXML
    private Button deleteDishButton;

    @FXML
    private Button editDishButton;

    @FXML
    private Button addDishButton;

    @FXML
    private TextField dishOutputField;

    @FXML
    private TextField dishCostField;

    @FXML
    private ComboBox<String> dishTypePicker;

    @FXML
    private TextField dishNameField;

    @FXML
    private Tab emplyeersTab;

    @FXML
    private TableView<Employee> staffTable;

    @FXML
    private TableColumn<Employee, String> employeeSNPColumn;

    @FXML
    private TableColumn<Employee, String> employeePositionColumn;

    @FXML
    private HBox employeesButtonsPanel;

    @FXML
    private Button deleteEmployeeButton;

    @FXML
    private Button editEmployeeButton;

    @FXML
    private Button addEmployeeButton;

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
    private Tab ordersTab;

    @FXML
    private TableView<Order> allOrdersTable;

    @FXML
    private TableColumn<Order, Integer> orderNumberColumn;

    @FXML
    private TableColumn<Order, String> orderTimeColumn;

    @FXML
    private TableView<Section> orderTable;

    @FXML
    private TableColumn<Section, String> sectionNameColumn;

    @FXML
    private TableColumn<Section, Integer> sectionOutputColumn;

    @FXML
    private TableColumn<Section, Integer> sectionNumberColumn;

    @FXML
    private TableColumn<Section, Double> sectionCostColumn;

    @FXML
    private Button closeOrderButton;

    @FXML
    private Button backButton;

    @FXML
    void changePasswordButtonClick(ActionEvent event) {
        Stage oldStage = (Stage)changePasswordButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/changePassword.fxml"));

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
    void backButtonClick(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void addDishButtonClicked(ActionEvent event) {
        Stage oldStage = (Stage)addDishButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/newDish.fxml"));

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
    void deleteDishButtonClicked(ActionEvent event) {
        if (selectedDish != null) {
            DatabaseHandler.deleteDish(selectedDish.getType(), selectedDish.getName());
            menu.get(selectedDish.getType()).remove(selectedDish.getName());
            menuTable.setItems(FXCollections.observableArrayList(menu.get(menuTypePicker.getValue()).values()));
            selectedDish = null;
            clearMenuFields();
        }
        else
        {
            Dialogs.showErrorDialog("Ошибка!", "Блюдо не выбрано.");
            return;
        }
    }

    @FXML
    void editDishButtonClicked(ActionEvent event) {
        if (selectedDish == null) {
            Dialogs.showErrorDialog("Ошибка!", "Блюдо не выбрано.");
            return;
        }
        if (dishNameField.getText().length() == 0)
        {
            Dialogs.showErrorDialog("Неверные данные!", "Блюдо должно иметь название.");
            return;
        }
        if (!dishOutputField.getText().matches("\\d+") )
        {
            Dialogs.showErrorDialog("Неверные данные!", "Неверно задан выход блюда. Это должно быть целое число.");
            return;
        }
        if (!dishCostField.getText().matches("\\d+(\\.\\d+)?") )
        {
            Dialogs.showErrorDialog("Неверные данные!", "Неверно задана стоимость.");
            return;
        }
        menu.get(selectedDish.getType()).remove(selectedDish.getName());
        DatabaseHandler.deleteDish(selectedDish.getType(), selectedDish.getName());
        Dish newDish = new Dish(dishTypePicker.getValue(), dishNameField.getText(),
                Double.parseDouble(dishCostField.getText()), Integer.parseInt(dishOutputField.getText()));
        menu.get(newDish.getType()).add(newDish);
        DatabaseHandler.addDish(newDish);
        menuTable.setItems(FXCollections.observableArrayList(menu.get(selectedDish.getType()).values()));
        clearMenuFields();
        menuTable.getSelectionModel().clearSelection();
        selectedDish = null;
    }

    @FXML
    void addEmployeeButtonClicked(ActionEvent event) {
        Stage oldStage = (Stage)addEmployeeButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/newEmployee.fxml"));

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
    void deleteEmployeeButtonClicked(ActionEvent event) {
        if (selectedEmployee.getLogin().equals(account.getLogin()))
        {
            Dialogs.showErrorDialog("Ошибка!", "Невозможно удалить собственный аккаунт.");
            return;
        }
        if (selectedEmployee != null) {
            DatabaseHandler.deleteEmployee(selectedEmployee.getLogin());
            staff.dismiss(selectedEmployee.getLogin());
            staffTable.setItems(FXCollections.observableArrayList(staff.values()));
            selectedEmployee = null;
            clearEmployeesFields();
        }
        else
        {
            Dialogs.showErrorDialog("Ошибка!", "Сотрудник не выбран!");
            return;
        }
    }

    @FXML
    void editEmployeeButtonClicked(ActionEvent event) {
        if (selectedEmployee == null)
        {
            Dialogs.showErrorDialog("Сотрудник не выбран!", "Выберите сотрудника.");
            return;
        }
        if (surnameField.getText().length() == 0 || nameField.getText().length() == 0 || patronomycField.getText().length() == 0 ||
                birthdayPicker.getEditor().getText().length() == 0 || positionField.getText().length() == 0 || loginField.getText().length() == 0)
        {
            Dialogs.showErrorDialog("Неверные данные!", "Все поля должны быть заполнены.");
            return;
        }
        if (!salaryField.getText().matches("\\d+(\\.\\d+)?") )
        {
            Dialogs.showErrorDialog("Неверные данные!", "Оклад задан неверно.");
            return;
        }
        if (passwordField.getText().length() < 4)
        {
            Dialogs.showErrorDialog("Неверные данные!", "Пароль должен состоять как минимум из 4 символов.");
            return;
        }
        DatabaseHandler.deleteEmployee(selectedEmployee.getLogin());
        staff.dismiss(selectedEmployee.getLogin());

        Employee newEmployee = new Employee(surnameField.getText(), nameField.getText(), patronomycField.getText(),
                birthdayPicker.getValue(), positionField.getText(), Double.parseDouble(salaryField.getText()),
                loginField.getText(), passwordField.getText(), menuAccess.isSelected(), menuReadonly.isSelected(),
                employeesAccess.isSelected(), employeesReadonly.isSelected(), ordersAccess.isSelected(), ordersReadonly.isSelected());
        DatabaseHandler.addEmployee(newEmployee);
        staff.add(newEmployee);
        clearEmployeesFields();
        staffTable.getSelectionModel().clearSelection();
        if (selectedEmployee.getLogin().equals(account.getLogin()))
        {
            closeWindow();
        }
        else
        {
            selectedEmployee = null;
            staffTable.setItems(FXCollections.observableArrayList(staff.values()));
            staffTable.refresh();
        }
    }

    @FXML
    void closeOrderButtonClicked(ActionEvent event) {
        if (selectedOrder != null) {
            DatabaseHandler.closeOrder(selectedOrder.getNumber());
            orders = DatabaseHandler.loadOrders();
            allOrdersTable.setItems(FXCollections.observableArrayList(orders.values()));
            orderTable.getItems().clear();
            selectedOrder = null;
        }
    }

    private Map<String, Menu> loadMenu()
    {
        Map<String, Menu> menu = new HashMap<>();
        menu.put("Холодные закуски", DatabaseHandler.loadDishes(Const.SALAD_TABLE));
        menu.put("Первое блюдо", DatabaseHandler.loadDishes(Const.FIRSTCOURSE_TABLE));
        menu.put("Гарниры", DatabaseHandler.loadDishes(Const.GARNISH_TABLE));
        menu.put("Горячие блюда", DatabaseHandler.loadDishes(Const.HOTDISHES_TABLE));
        menu.put("Напитки", DatabaseHandler.loadDishes(Const.DRINKS_TABLE));
        menu.put("Десерты", DatabaseHandler.loadDishes(Const.DESSERTS_TABLE));
        return menu;
    }

    void clearMenuFields()
    {
        dishTypePicker.getSelectionModel().select(null);
        dishNameField.clear();
        dishOutputField.clear();
        dishCostField.clear();
    }

    void clearEmployeesFields()
    {
        surnameField.clear();
        nameField.clear();
        patronomycField.clear();
        birthdayPicker.setValue(null);
        positionField.clear();
        salaryField.clear();
        loginField.clear();
        passwordField.clear();
        menuAccess.setSelected(false);
        menuReadonly.setSelected(false);
        employeesAccess.setSelected(false);
        employeesReadonly.setSelected(false);
        ordersAccess.setSelected(false);
        ordersReadonly.setSelected(false);
    }

    void closeWindow()
    {
        Stage oldStage = (Stage)backButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/authorization.fxml"));

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

    private Dish selectedDish;
    private Employee selectedEmployee;
    private Order selectedOrder;
    private Map<String, Menu> menu;
    private Staff staff;
    private Map<Integer, Order> orders;
    private Employee account;

    @FXML
    void initialize(){
        account = Main.getAccount();
        mainTab.isSelected();
        surnameLabel.setText(account.getSurname());
        nameLabel.setText(account.getName());
        patronymicLabel.setText(account.getPatronymic());
        birthdayLabel.setText(account.getBirthDate().toString());
        positionLabel.setText(account.getPosition());
        salaryLabel.setText(Double.toString(account.getSalary()));
        loginLabel.setText(account.getLogin());
        passwordLabel.setText(account.getPassword());

        if (!account.isMenuRoot())
        {
            menuTab.setDisable(true);
        }
        else if (account.isMenuRootReadOnly())
        {
            menuButtonsPanel.setDisable(true);
        }
        if (!account.isEmployeesRoot())
        {
            emplyeersTab.setDisable(true);
        }
        else if (account.isEmployeesRootReadOnly())
        {
            employeesButtonsPanel.setDisable(true);
        }
        if (!account.isOrdersRoot())
        {
            ordersTab.setDisable(true);
        }
        else if (account.isOrdersRootReadOnly())
        {
            closeOrderButton.setDisable(true);
        }

        menuTypePicker.setItems(FXCollections.observableArrayList("Холодные закуски", "Первое блюдо" , "Гарниры",
                                                                         "Горячие блюда", "Напитки", "Десерты"));
        dishTypePicker.setItems(FXCollections.observableArrayList("Холодные закуски", "Первое блюдо" , "Гарниры",
                "Горячие блюда", "Напитки", "Десерты"));
        dishNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dishOutputColumn.setCellValueFactory(new PropertyValueFactory<>("output"));
        dishCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        menu = loadMenu();

        menuTypePicker.setOnAction(event->{
            menuTable.setItems(FXCollections.observableArrayList(menu.get(menuTypePicker.getValue()).values()));
            menuTable.refresh();
        });

        menuTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                if (newValue != null) {
                    dishTypePicker.getSelectionModel().select(newValue.getType());
                    dishNameField.setText(newValue.getName());
                    dishOutputField.setText(Integer.toString(newValue.getOutput()));
                    dishCostField.setText(Double.toString(newValue.getPrice()));
                    selectedDish = newValue;
                }
            }
        });

        employeeSNPColumn.setCellValueFactory(new PropertyValueFactory<>("SNP"));
        employeePositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        staff = DatabaseHandler.loadStaff();
        staffTable.setItems(FXCollections.observableArrayList(staff.values()));

        loginField.setDisable(true);

        staffTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
                if (newValue != null) {
                    surnameField.setText(newValue.getSurname());
                    nameField.setText(newValue.getName());
                    patronomycField.setText(newValue.getPatronymic());
                    birthdayPicker.setValue(newValue.getBirthDate());
                    positionField.setText(newValue.getPosition());
                    salaryField.setText(Double.toString(newValue.getSalary()));
                    loginField.setText(newValue.getLogin());
                    passwordField.setText(newValue.getPassword());
                    selectedEmployee = newValue;
                    menuAccess.setSelected(newValue.isMenuRoot());
                    menuReadonly.setSelected(newValue.isMenuRootReadOnly());
                    employeesAccess.setSelected(newValue.isEmployeesRoot());
                    employeesReadonly.setSelected(newValue.isEmployeesRootReadOnly());
                    ordersAccess.setSelected(newValue.isOrdersRoot());
                    ordersReadonly.setSelected(newValue.isOrdersRootReadOnly());
                }
            }
        });

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

        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        orderTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        orders = DatabaseHandler.loadOrders();
        allOrdersTable.setItems(FXCollections.observableArrayList(orders.values()));

        sectionNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sectionOutputColumn.setCellValueFactory(new PropertyValueFactory<>("output"));
        sectionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sectionCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        allOrdersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                if (newValue != null) {
                    orderTable.setItems(FXCollections.observableArrayList(newValue.values()));
                    selectedOrder = newValue;
                }
            }
        });
    }
}
