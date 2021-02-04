/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Help;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class HelpController implements Initializable {

    @FXML
    private JFXButton okay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Close(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) okay.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
