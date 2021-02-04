/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Company;

import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.Data;
import Logic.Validator;
import Logic.kk_Logic;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class CompanyController implements Initializable {

    private ObservableList<Logic.TableList> data;
    kk_Logic logic = new kk_Logic();
    Validator validator = new Validator();
    MainDialogs dialogs = new MainDialogs();
    BoxBlur blur = new BoxBlur(3, 3, 3);
    Data list_Data = new Data();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String Id = "", value = "";
    @FXML
    private ScrollPane scroll_Pane;
    @FXML
    private TableView<Logic.TableList> user_Table;
    @FXML
    private TableColumn<Logic.TableList, String> serial_Number;
    @FXML
    private TableColumn<Logic.TableList, String> name_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> contact_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> email_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> password_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> status_Coll;
    @FXML
    private ImageView close;
    @FXML
    private Text status_Text;
    @FXML
    private MenuItem delete_B;
    @FXML
    private MenuItem status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //binde scrollpane
        //bind table height
        user_Table.prefHeightProperty().bind(scroll_Pane.heightProperty());
        data = FXCollections.observableArrayList();
        this.SelectCell();
        this.setTable();
        this.loadData();
    }

    public void setTable() {

        serial_Number.setCellValueFactory(new PropertyValueFactory<>("C0"));
        name_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        email_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        contact_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
        password_Coll.setCellValueFactory(new PropertyValueFactory<>("C4"));
        status_Coll.setCellValueFactory(new PropertyValueFactory<>("C5"));
    }

    public void loadData() {
        data.clear();
        try {
            int i = 1;
            rs = logic.getAllData("account");
            while (rs.next()) {
                String C1 = rs.getString("C1");
                String C2 = rs.getString("C2");
                String C3 = rs.getString("C3");
                String C4 = rs.getString("C4");
                String C5 = rs.getString("C5");
                String C6 = rs.getString("C6");
                //String C7 = rs.getString("C7");
                String C8 = rs.getString("C8");
                if (C3.equals("Admin") || C3.equals("Installer")) {
                    status_Text.setText("Using " + C3);
                } else {
                    data.addAll(new Logic.TableList(String.valueOf(i++), C2, C4, C5, C6, C8));
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        user_Table.setItems(data);
    }

    public void UpdateTable(String Id, String Condition) {
        try {
            String sql = "update account set C8='" + Condition
                    + "' where C2='" + Id + "'";
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            pst.execute();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close_On_Acc(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void delete_B_A(ActionEvent event) {
        if (!Id.isEmpty()) {
            logic.deleteData("account", "C2", Id);
            this.setTable();
            this.loadData();
        }
    }

    private void SelectCell() {
        user_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logic.TableList t = user_Table.getItems().get(user_Table.getSelectionModel().getSelectedIndex());
                Id = t.getC1();
                value = t.getC5();
                if (value.equals("Blocked")) {
                    status.setText("Activate");
                } else {
                    status.setText("Blocked");
                }

            }

        });
    }

    @FXML
    private void status_B_Action(ActionEvent event) {
        if (value.equals("Active")) {
            this.UpdateTable(Id, "Blocked");
        } else {
            this.UpdateTable(Id, "Active");
        }
        this.setTable();
        this.loadData();
    }

}
