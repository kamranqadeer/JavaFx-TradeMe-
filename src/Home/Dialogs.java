/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 *
 * @author KAMRAN QADEER
 */
public class Dialogs {

    public void alert_Dialog(String Title, String Header, String Message) {

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(Title);
        alert.setHeaderText(Header);
        alert.setContentText(Message);
        alert.showAndWait();
    }

    public void info_Dialog(String Title, String Message) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(Title);
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();

    }

    public int confirm_Dialog(String Title, String Header, String Message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(Title);
        alert.setHeaderText(Header);
        alert.setContentText(Message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            return 1;
        } else {
            // ... user chose CANCEL or closed the dialog
            return 0;
        }
    }

    public void details_Dialog() throws IOException {

    }

}
