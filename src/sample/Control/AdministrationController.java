package sample.Control;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sample.Model.Dish;
import sample.Model.Menu;
import sample.Model.Order;
import sample.Model.Section;

public class AdministrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ComboBox<?> dishTypePicker;

    @FXML
    private TextField dishNameField;

    @FXML
    private Tab emplyeersTab;

    @FXML
    private TableView<?> staffTable;

    @FXML
    private TableColumn<?, ?> employeeSNPColumn;

    @FXML
    private TableColumn<?, ?> employeePositionColumn;

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
    private CheckBox staffReadonly;

    @FXML
    private CheckBox orderReadonly;

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
    void closeOrderButtonClicked(ActionEvent event) {
        Main.closeOrder(selectedOrder.getNumber());
        loadOrders();
        orderTable.getItems().clear();
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
        Dish newDish = new Dish(dishTypePicker.getPromptText(), dishNameField.getText(), Double.parseDouble(dishCostField.getText()), Integer.parseInt(dishOutputField.getText()));
        menu.get(selectedDish.getType()).remove(selectedDish.getName());
        Main.deleteDish(selectedDish.getName());
        menu.get(newDish.getType()).add(newDish);
        Main.addDish(newDish);
        menuTable.setItems(FXCollections.observableArrayList(menu.get(selectedDish.getType()).values()));
    }

    @FXML
    void addEmployeeButtonClicked(ActionEvent event) {

    }

    @FXML
    void deleteEmployeeButtonClicked(ActionEvent event) {

    }

    @FXML
    void editEmployeeButtonClicked(ActionEvent event) {

    }

    private Map<Integer, Order> orders;
    private Order selectedOrder;
    private Dish selectedDish;
    private Map<String, Menu> menu;

    void loadOrders()
    {
        orders = Main.getOrders();
        allOrdersTable.setItems(FXCollections.observableArrayList(orders.values()));
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

    @FXML
    void initialize() {
        menuTypePicker.setItems(FXCollections.observableArrayList("Холодные закуски", "Первое блюдо" , "Гарниры",
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
                dishTypePicker.setPromptText(newValue.getType());
                dishNameField.setText(newValue.getName());
                dishOutputField.setText(Integer.toString(newValue.getOutput()));
                dishCostField.setText(Double.toString(newValue.getPrice()));
                selectedDish = newValue;
            }
        });

        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        orderTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        loadOrders();

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
