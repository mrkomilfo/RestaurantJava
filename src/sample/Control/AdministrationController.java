package sample.Control;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
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
import sample.Main;
import sample.Model.*;
import sample.Model.Menu;

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
        loader.setLocation(getClass().getResource("/sample/View/changePassword.fxml"));

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
        Stage oldStage = (Stage)backButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/authorization.fxml"));

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
    void addDishButtonClicked(ActionEvent event) {
        Stage oldStage = (Stage)addDishButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/newDish.fxml"));

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
            Main.deleteDish(selectedDish.getName());
            menu.get(selectedDish.getType()).remove(selectedDish.getName());
            menuTable.setItems(FXCollections.observableArrayList(menu.get(menuTypePicker.getValue()).values()));
            selectedDish = null;
            clearMenuFields();
        }
    }

    @FXML
    void editDishButtonClicked(ActionEvent event) {
        if (selectedDish == null) {
            showErrorDialog("Блюдо не выбрано!", "Для начала выберите какое-нибудь блюдо.");
            return;
        }
        if (dishNameField.getText().length() == 0)
        {
            showErrorDialog("Неверные данные!", "Блюдо должно иметь название.");
            return;
        }
        if (!dishOutputField.getText().matches("\\d+") )
        {
            showErrorDialog("Неверные данные!", "Неверно задан выход блюда. Это должно быть целое число.");
            return;
        }
        if (!dishCostField.getText().matches("\\d+(\\.\\d+)?") )
        {
            showErrorDialog("Неверные данные!", "Неверно задана стоимость.");
            return;
        }
        menu.get(selectedDish.getType()).remove(selectedDish.getName());
        Main.deleteDish(selectedDish.getName());
        selectedDish = null;
        Dish newDish = new Dish(dishTypePicker.getPromptText(), dishNameField.getText(),
                Double.parseDouble(dishCostField.getText()), Integer.parseInt(dishOutputField.getText()));
        menu.get(newDish.getType()).add(newDish);
        Main.addDish(newDish);
        menuTable.setItems(FXCollections.observableArrayList(menu.get(selectedDish.getType()).values()));
        clearMenuFields();
        menuTable.getSelectionModel().clearSelection();
    }

    @FXML
    void addEmployeeButtonClicked(ActionEvent event) {
        Stage oldStage = (Stage)addEmployeeButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/newEmployee.fxml"));

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
        if (selectedEmployee != null) {
            Main.dismissEmployee(selectedEmployee.login);
            staff.dismiss(selectedEmployee.login);
            staffTable.setItems(FXCollections.observableArrayList(staff.values()));
            selectedEmployee = null;
            clearEmployeesFields();
        }
    }

    @FXML
    void editEmployeeButtonClicked(ActionEvent event) {
        if (selectedEmployee == null)
        {
            showErrorDialog("Сотрудник не выбран!", "Выберите сотрудника.");
            return;
        }
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
        Main.dismissEmployee(selectedEmployee.login);
        staff.dismiss(selectedEmployee.login);
        selectedEmployee = null;
        Employee newEmployee = new Employee(surnameField.getText(), nameField.getText(), patronomycField.getText(),
                birthdayPicker.getValue(), positionField.getText(), Double.parseDouble(salaryField.getText()),
                loginField.getText(), passwordField.getText(), menuAccess.isSelected(), menuReadonly.isSelected(),
                employeesAccess.isSelected(), employeesReadonly.isSelected(), ordersAccess.isSelected(), ordersReadonly.isSelected());
        Main.addEmployee(newEmployee);
        staff.recruit(newEmployee);
        clearEmployeesFields();
        staffTable.getSelectionModel().clearSelection();
    }

    @FXML
    void closeOrderButtonClicked(ActionEvent event) {
        if (selectedOrder != null) {
            Main.closeOrder(selectedOrder.getNumber());
            orders = Main.getOrders();
            allOrdersTable.setItems(FXCollections.observableArrayList(orders.values()));
            orderTable.getItems().clear();
            selectedOrder = null;
        }
    }

    private Map<String, Menu> loadMenu()
    {
        Map<String, Menu> menu = new HashMap<>();
        menu.put("Холодные закуски", new Menu());
        menu.put("Первое блюдо", new Menu());
        menu.put("Гарниры", new Menu());
        menu.put("Горячие блюда", new Menu());
        menu.put("Напитки", new Menu());
        menu.put("Десерты", new Menu());
        Menu startMenu = Main.getMenu();
        for (Dish dish : startMenu.values())
        {
            menu.get(dish.getType()).add(dish);
        }
        return menu;
    }

    static void showErrorDialog(String title, String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
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

    void clearMenuFields()
    {
        dishTypePicker.setPromptText(null);
        dishNameField.clear();
        dishOutputField.clear();
        dishCostField.clear();
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
        surnameLabel.setText(account.surname);
        nameLabel.setText(account.name);
        patronymicLabel.setText(account.patronymic);
        birthdayLabel.setText(account.birthDate.toString());
        positionLabel.setText(account.position);
        salaryLabel.setText(Double.toString(account.salary));
        loginLabel.setText(account.login);
        passwordLabel.setText(account.password);

        if (!account.menuRoot)
        {
            menuTab.setDisable(true);
        }
        else if (account.menuRootReadOnly)
        {
            menuButtonsPanel.setDisable(true);
        }
        if (!account.employeesRoot)
        {
            emplyeersTab.setDisable(true);
        }
        else if (account.employeesRootReadOnly)
        {
            employeesButtonsPanel.setDisable(true);
        }
        if (!account.ordersRoot)
        {
            ordersTab.setDisable(true);
        }
        else if (account.ordersRootReadOnly)
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
                    dishTypePicker.setPromptText(newValue.getType());
                    dishNameField.setText(newValue.getName());
                    dishOutputField.setText(Integer.toString(newValue.getOutput()));
                    dishCostField.setText(Double.toString(newValue.getPrice()));
                    selectedDish = newValue;
                }
            }
        });

        employeeSNPColumn.setCellValueFactory(new PropertyValueFactory<>("SNP"));
        employeePositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        staff = Main.getStaff();
        staffTable.setItems(FXCollections.observableArrayList(staff.values()));

        loginField.setDisable(true);

        staffTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
                if (newValue != null) {
                    surnameField.setText(newValue.surname);
                    nameField.setText(newValue.name);
                    patronomycField.setText(newValue.patronymic);
                    birthdayPicker.setValue(newValue.birthDate);
                    positionField.setText(newValue.position);
                    salaryField.setText(Double.toString(newValue.salary));
                    loginField.setText(newValue.login);
                    passwordField.setText(newValue.password);
                    selectedEmployee = newValue;
                    menuAccess.setSelected(newValue.menuRoot);
                    menuReadonly.setSelected(newValue.menuRootReadOnly);
                    employeesAccess.setSelected(newValue.employeesRoot);
                    employeesReadonly.setSelected(newValue.employeesRootReadOnly);
                    ordersAccess.setSelected(newValue.ordersRoot);
                    ordersReadonly.setSelected(newValue.ordersRootReadOnly);
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

        orders = Main.getOrders();
        allOrdersTable.setItems(FXCollections.observableArrayList(orders.values()));

        sectionNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sectionOutputColumn.setCellValueFactory(new PropertyValueFactory<>("output"));
        sectionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        sectionCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        allOrdersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                orderTable.setItems(FXCollections.observableArrayList(newValue.values()));
                selectedOrder = newValue;
            }
        });


    }
}
