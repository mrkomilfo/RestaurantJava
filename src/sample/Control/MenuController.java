package sample.Control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AdministrationButton;

    @FXML
    private Button CustomerButton;

    @FXML
    void AdministrationButtonClick(ActionEvent event) {

    }

    @FXML
    void CustomerButtonClick(ActionEvent event) {
        CustomerButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/customer.fxml"));

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
    void initialize() {

    }
}

