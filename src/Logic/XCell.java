/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 *
 * @author KAMRAN QADEER
 */
public class XCell extends ListCell<String> {

    HBox hbox = new HBox();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    Button button = new Button("remove");
    String lastItem;
    kk_Logic logic = new kk_Logic();

    public XCell(JFXTextField F, ListView<String> list) {

        super();
        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        // button.setOnAction(e -> getListView().getItems().remove(getItem()));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getListView().getItems().remove(getItem());
                if (F != null) {
                    logic.setTotal(F, list);
                }

            }

        });

    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            label.setText(item != null ? item : "<null>");
            setGraphic(hbox);
        }
    }
}
