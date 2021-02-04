/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;

import Logic.kk_Logic;
import Logic.print_Data;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author KAMRAN QADEER
 */
public class MainDialogs {

    JFXDialogLayout content = new JFXDialogLayout();
    boolean check = false;
    print_Data print_Data = new print_Data();
    BoxBlur blur = new BoxBlur(3, 3, 3);
    kk_Logic logic = new kk_Logic();

    public void error_Dialog(StackPane pane, String message, String Header) {
        pane.setEffect(blur);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(null);
        alert.setHeaderText(Header);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            pane.setEffect(null);
            alert.close();
        } else {
            // ... user chose CANCEL or closed the dialog
            pane.setEffect(null);
            alert.close();
        }
    }

    public boolean confirm_Dialog(StackPane pane, String Message) {
        pane.setEffect(blur);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(null);
        alert.setHeaderText("Confirmation");
        alert.setContentText(Message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            pane.setEffect(null);
            return true;
        } else {
            // ... user chose CANCEL or closed the dialog
            pane.setEffect(null);
            alert.close();
            return false;

        }
    }

    public boolean info_Dialog(StackPane pane, String Message) {
        pane.setEffect(blur);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setTitle(null);
        alert.setHeaderText("Information");
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

    public void Notofication(String title, String Message, String src) {
        InputStream file = getClass().getResourceAsStream("/resources/icon/" + src + ".png");

      //  File file = new File("/resources.icon/" + src + ".png");
        Image i = new Image(file);
        ImageView image = new ImageView(i);
        Notifications notefications = Notifications.create()
                .title(title)
                .text(Message)
                .darkStyle()
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .graphic(image)
                .onAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Check");
                    }
                });
        notefications.show();
    }
}
