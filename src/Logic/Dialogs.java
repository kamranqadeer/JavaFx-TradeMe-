/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.util.Optional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 *
 *
 * @author KAMRAN QADEER
 */
public class Dialogs {

    JFXDialogLayout content = new JFXDialogLayout();
    boolean check = false;
    print_Data print_Data = new print_Data();
    BoxBlur blur = new BoxBlur(3, 3, 3);
    kk_Logic logic = new kk_Logic();

    public void error_Dialog(StackPane main_Pane, AnchorPane pane, String message) {
        content.setHeading(new Text("Error"));
        content.setBody(new Text(message));
        JFXDialog dialog = new JFXDialog(main_Pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                event.consume();
                pane.setEffect(null);

            }
        });
        content.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                event.consume();
                pane.setEffect(null);
            }

        });
    }

    public void shop_Print_Dialog(StackPane main_Pane, AnchorPane pane, String message, String shop_Name, String c1, String c2) {
        content.setHeading(new Text("Shop (" + shop_Name + ")"));
        content.setBody(new Text(message));
        JFXDialog dialog = new JFXDialog(main_Pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button1 = new JFXButton("Cancle");
        JFXButton button2 = new JFXButton("All Sale");
        JFXButton button3 = new JFXButton("All Purchase");
        pane.setEffect(blur);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setEffect(null);
                dialog.close();
                event.consume();

            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String data[] = {shop_Name, "03444200515", "H#Cb429 Asifa Bad Wah Cantt", "Sale"};
                if (c1.isEmpty()) {
                    content.setBody(new Text("Error no current Sales"));
                } else {
                    content.setBody(new Text("Wait for print."));
                    print_Data.print("shop_Sale_Account", data);
                    pane.setEffect(null);
                    dialog.close();
                    event.consume();

                }
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String data[] = {shop_Name, "03444200515", "H#Cb429 Asifa Bad Wah Cantt", "Purchase"};
                if (c2.isEmpty()) {
                    content.setBody(new Text("Error no current Purchases"));
                } else {
                    content.setBody(new Text("Wait for print."));
                    print_Data.print("shop_Purchase_Account", data);
                    pane.setEffect(null);
                    dialog.close();
                    event.consume();

                }
            }
        });
        content.setActions(button1, button2, button3);
        dialog.show();
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                pane.setEffect(null);
                event.consume();

            }

        });
    }

    public void simple_Error(StackPane main_Pane, AnchorPane pane, String message) {
        content.setHeading(new Text("Error"));
        content.setBody(new Text(message));
        pane.setEffect(blur);
        JFXDialog dialog = new JFXDialog(main_Pane, content, JFXDialog.DialogTransition.TOP);
        JFXButton button = new JFXButton("Exit");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setEffect(null);
                dialog.close();
                event.consume();

            }
        });
        content.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                pane.setEffect(null);
                event.consume();

            }

        });
    }

    public boolean confirm_Dialog(StackPane main_Pane, AnchorPane pane, String message, String b1_title, String b2_title, PreparedStatement pst) {
        content.setHeading(new Text("Confirmation"));
        content.setBody(new Text(message));
        JFXDialog dialog = new JFXDialog(main_Pane, content, JFXDialog.DialogTransition.TOP);
        JFXButton button_1 = new JFXButton(b1_title);
        JFXButton button_2 = new JFXButton(b2_title);
        content.setActions(button_1, button_2);
        dialog.show();
        button_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                check = false;

            }
        });
        button_2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    pst.execute();
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Dialogs.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.close();
                check = true;
                event.consume();

            }
        });
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                pane.setEffect(null);
            }

        });
        return check;
    }

    public boolean confirm_Dialog(String Title, String Header, String Message, AnchorPane pane) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(Title);
        alert.setHeaderText(Header);
        alert.setContentText(Message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            pane.setEffect(null);
            return true;
        } else {
            // ... user chose CANCEL or closed the dialog
            pane.setEffect(null);
            return false;

        }
    }

    public void info_Dialog(String Title, String Message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(Title);
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();

    }
}
