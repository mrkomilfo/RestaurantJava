package sample;

import javafx.scene.control.Alert;

public class Dialogs {
    public static void showErrorDialog(String title, String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }
}
