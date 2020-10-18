package example.Utils;

import javafx.scene.control.Alert;

public class CustomAlert {

    public static void showAlert(String title, String content, boolean status){
        Alert alert;
        if (status)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
