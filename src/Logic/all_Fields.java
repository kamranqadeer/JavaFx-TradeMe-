/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Home.HomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 *
 * @author KAMRAN QADEER
 */
public class all_Fields {

    BoxBlur blur = new BoxBlur(3, 3, 3);

    public void AddShop() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Dialogs/shop_Dialog.fxml"));
            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(root);
            dialog.initStyle(StageStyle.TRANSPARENT);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(all_Fields.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
