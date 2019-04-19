package sample.Control;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
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
import sample.Main;
import sample.Model.Bill;
import sample.Model.Dish;
import sample.Model.Section;
import sample.Model.Menu;

public class CustomerController {

    private Bill bill;
    private Menu menu;
    private String buffer;

    private TableView.TableViewSelectionModel<Dish> saladSelectionModel;
    private TableView.TableViewSelectionModel<Dish> firstCourseSelectionModel;
    private TableView.TableViewSelectionModel<Dish> garnishSelectionModel;
    private TableView.TableViewSelectionModel<Dish> hotDishSelectionModel;
    private TableView.TableViewSelectionModel<Dish> drinkSelectionModel;
    private TableView.TableViewSelectionModel<Dish> dessertSelectionModel;
    private TableView.TableViewSelectionModel<Section> billSelectionModel;

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
        if (buffer != null)
        {
            bill.add(menu.get(buffer));
            billTable.setItems(FXCollections.observableArrayList(bill.values()));
            billTable.refresh();
            totalLabel.setText(Double.toString(bill.getCost()));
        }

    }

    @FXML
    void removeDishButtonClick(ActionEvent event) {
        if (buffer != null)
        {
            bill.substract(buffer);
            billTable.setItems(FXCollections.observableArrayList(bill.values()));
            billTable.refresh();
            totalLabel.setText(Double.toString(bill.getCost()));
        }
    }

    @FXML
    void backButtonClick(ActionEvent event) {
        backButton.getScene().getWindow().hide();

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
        stage.showAndWait();
    }

    @FXML
    void cancelOrderButtonClick(ActionEvent event) {
        bill.clear();
        billTable.setItems(FXCollections.observableArrayList(bill.values()));
        billTable.refresh();
        totalLabel.setText("0.0");
    }

    @FXML
    void confirmOrderButtonClick(ActionEvent event) {
        //TODO: sending to admin
        bill.clear();
        billTable.setItems(FXCollections.observableArrayList(bill.values()));
        billTable.refresh();
        totalLabel.setText("0.0");
    }

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
        billNumber.setCellValueFactory(new PropertyValueFactory<>("number"));

        bill = new Bill();

        billTable.setItems(FXCollections.observableArrayList(bill.values()));

        saladTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                saladSelectionModel = saladTable.getSelectionModel();
                ObservableList selectedCells = saladSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(saladTable);
            }
        });

        firstCourseTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                firstCourseSelectionModel = firstCourseTable.getSelectionModel();
                ObservableList selectedCells = firstCourseSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(firstCourseTable);
            }
        });

        garnishTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                garnishSelectionModel = garnishTable.getSelectionModel();
                ObservableList selectedCells = garnishSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(garnishTable);
            }
        });

        hotDishTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                hotDishSelectionModel = hotDishTable.getSelectionModel();
                ObservableList selectedCells = hotDishSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(hotDishTable);
            }
        });

        drinkTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                drinkSelectionModel = drinkTable.getSelectionModel();
                ObservableList selectedCells = drinkSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(drinkTable);
            }
        });

        dessertTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                dessertSelectionModel = dessertTable.getSelectionModel();
                ObservableList selectedCells = dessertSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(dessertTable);
            }
        });

        billTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dish>() {
            @Override
            public void changed(ObservableValue<? extends Dish> observable, Dish oldValue, Dish newValue) {
                billSelectionModel = billTable.getSelectionModel();
                ObservableList selectedCells = billSelectionModel.getSelectedCells();
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                buffer = tablePosition.getTableColumn().getCellData(newValue).toString();
                deselect(billTable);
            }
        });

        menu = Main.getMenu();
        for (Dish dish: menu.values())
        {
            switch (dish.getType())
            {
                case "Холодные закуски":
                {
                    saladTable.getItems().add(dish);
                    break;
                }
                case "Первое блюдо":
                {
                    firstCourseTable.getItems().add(dish);
                    break;
                }
                case "Гарниры":
                {
                    garnishTable.getItems().add(dish);
                    break;
                }
                case "Горячие блюда":
                {
                    hotDishTable.getItems().add(dish);
                    break;
                }
                case "Напитки":
                {
                    drinkTable.getItems().add(dish);
                    break;
                }
                case "Десерты":
                {
                    dessertTable.getItems().add(dish);
                    break;
                }

            }
        }
    }

    protected void deselect(TableView tv)
    {
        if (tv != saladTable)
        {
            saladTable.getSelectionModel().clearSelection();
        }
        if (tv != firstCourseTable)
        {
            firstCourseTable.getSelectionModel().clearSelection();
        }
        if (tv != garnishTable)
        {
            garnishTable.getSelectionModel().clearSelection();
        }
        if (tv != hotDishTable)
        {
            hotDishTable.getSelectionModel().clearSelection();
        }
        if (tv != drinkTable)
        {
            drinkTable.getSelectionModel().clearSelection();
        }
        if (tv != dessertTable)
        {
            dessertTable.getSelectionModel().clearSelection();
        }
        if (tv != billTable)
        {
            billTable.getSelectionModel().clearSelection();
        }
    }
}


