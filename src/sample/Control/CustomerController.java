package sample.Control;

import java.io.IOException;
import java.net.URL;

import java.util.Date;
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
import javafx.stage.Stage;
import sample.DB.Const;
import sample.DB.DatabaseHandler;
import sample.Main;
import sample.Model.*;
import sample.Model.Menu;

public class CustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion CustomerAccordion;

    @FXML
    private TableView<Dish> saladTable;

    @FXML
    private TableColumn<Dish, String> saladName;

    @FXML
    private TableColumn<Dish, Integer> saladOutput;

    @FXML
    private TableColumn<Dish, Integer> saladPrice;

    @FXML
    private TableView<Dish> firstCourseTable;

    @FXML
    private TableColumn<Dish, String> firstCourseName;

    @FXML
    private TableColumn<Dish, Integer> firstCourseOutput;

    @FXML
    private TableColumn<Dish, Integer> firstCoursePrice;

    @FXML
    private TableView<Dish> garnishTable;

    @FXML
    private TableColumn<Dish, String> garnishName;

    @FXML
    private TableColumn<Dish, Integer> garnishOutput;

    @FXML
    private TableColumn<Dish, Integer> garnishPrice;

    @FXML
    private TableView<Dish> hotDishTable;

    @FXML
    private TableColumn<Dish, String> hotDishName;

    @FXML
    private TableColumn<Dish, Integer> hotDishOutput;

    @FXML
    private TableColumn<Dish, Integer> hotDishPrice;

    @FXML
    private TableView<Dish> drinkTable;

    @FXML
    private TableColumn<Dish, String> drinkName;

    @FXML
    private TableColumn<Dish, Integer> drinkOutput;

    @FXML
    private TableColumn<Dish, Integer> drinkPrice;

    @FXML
    private TableView<Dish> dessertTable;

    @FXML
    private TableColumn<Dish, String> dessertName;

    @FXML
    private TableColumn<Dish, Integer> dessertOutput;

    @FXML
    private TableColumn<Dish, Integer> dessertPrice;

    @FXML
    private Button addDishButton;

    @FXML
    private Button removeDishButton;

    @FXML
    private TableView<Section> billTable;

    @FXML
    private TableColumn<Section, String> billName;

    @FXML
    private TableColumn<Section, Integer> billOutput;

    @FXML
    private TableColumn<Section, Integer> billNumber;

    @FXML
    private TableColumn<Section, Integer> billPrice;

    @FXML
    private Label totalLabel;

    @FXML
    private Button confirmOrderButton;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Button backButton;

    @FXML
    void addDishButtonClick(ActionEvent event) {
        if (selectedDish != null)
        {
            bill.add(selectedDish);
            billTable.setItems(FXCollections.observableArrayList(bill.values()));
            billTable.refresh();
            totalLabel.setText(Double.toString(bill.getCost()));
        }
    }

    @FXML
    void removeDishButtonClick(ActionEvent event) {
        if (selectedDish != null)
        {
            bill.substract(selectedDish.getName());
            billTable.setItems(FXCollections.observableArrayList(bill.values()));
            billTable.refresh();
            totalLabel.setText(Double.toString(bill.getCost()));
        }
    }

    @FXML
    void backButtonClick(ActionEvent event) {
        Stage oldStage = (Stage)backButton.getScene().getWindow();
        oldStage.hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/menu.fxml"));

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
    void cancelOrderButtonClick(ActionEvent event) {
        bill.clear();
        billTable.getItems().clear();
        billTable.refresh();
        totalLabel.setText("0.0");
    }

    @FXML
    void confirmOrderButtonClick(ActionEvent event) {
        DatabaseHandler.addOrder(bill);
        bill.clear();
        billTable.getItems().clear();
        totalLabel.setText("0.0");
    }

    private Bill bill;
    private Dish selectedDish;

    @FXML
    void initialize() {
        saladName.setCellValueFactory(new PropertyValueFactory<>("name"));
        saladOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        saladPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        firstCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstCourseOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        firstCoursePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        garnishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        garnishOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        garnishPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        hotDishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        hotDishOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        hotDishPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        drinkName.setCellValueFactory(new PropertyValueFactory<>("name"));
        drinkOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        drinkPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        dessertName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dessertOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        dessertPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        billName.setCellValueFactory(new PropertyValueFactory<>("name"));
        billOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
        billPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        billNumber.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        bill = new Bill();

        billTable.setItems(FXCollections.observableArrayList(bill.values()));

        saladTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        firstCourseTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        garnishTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        hotDishTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        drinkTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        dessertTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        billTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                selectedDish = newValue;
            }
        });

        saladTable.getItems().addAll(DatabaseHandler.loadDishes(Const.SALAD_TABLE).values());
        firstCourseTable.getItems().addAll(DatabaseHandler.loadDishes(Const.FIRSTCOURSE_TABLE).values());
        garnishTable.getItems().addAll(DatabaseHandler.loadDishes(Const.GARNISH_TABLE).values());
        hotDishTable.getItems().addAll(DatabaseHandler.loadDishes(Const.HOTDISHES_TABLE).values());
        drinkTable.getItems().addAll(DatabaseHandler.loadDishes(Const.DRINKS_TABLE).values());
        dessertTable.getItems().addAll(DatabaseHandler.loadDishes(Const.DESSERTS_TABLE).values());
    }
}
