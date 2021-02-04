/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainHome;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class MainHomeController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private AnchorPane sale_Pane;
    @FXML
    private Tab sale_Tabe;
    @FXML
    private Tab purchase_Tabe;
    @FXML
    private AnchorPane purchase_Pane;
    @FXML
    public StackPane stack_Pan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void sale_Acc(Event event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/HomeSale/home_sale.fxml"));
            sale_Pane.getChildren().add(root);
            
        } catch (IOException ex) {
            Logger.getLogger(MainHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void purchase_Acc(Event event) {
        try {
            purchase_Pane.getChildren().add(FXMLLoader.load(getClass().getResource("/HomePurchase/home_Purchase.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeSize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home/Home.fxml"));
            Region n = (Region) loader.load();
            sale_Pane.prefHeightProperty().bind(n.heightProperty());
            sale_Pane.prefWidthProperty().bind(n.prefWidthProperty());
            
        } catch (IOException ex) {
            Logger.getLogger(MainHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
