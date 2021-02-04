/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.all_Updates;
import Logic.kk_Logic;
import Logic.print_Data;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class StockController implements Initializable {

    kk_Logic logic = new kk_Logic();
    MainDialogs dialog = new MainDialogs();
    all_Updates update = new all_Updates();
    String stock_Id = "";
    print_Data print = new print_Data();
    Connection con = null;
    String month = "", year = "", p_Name = "";
    private ObservableList<Logic.TableList> data;
    private ObservableList<Logic.TableList> In;
    private ObservableList<Logic.TableList> Out;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    ResultSet rs = null, rs2 = null;
    PreparedStatement pst = null;
    @FXML
    private TableColumn<Logic.TableList, String> no_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> product_Name_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> previous_Month_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> total_Left_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> left_Cost_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> left_Coll;
    @FXML
    private TableView<Logic.TableList> stock_Table;
    @FXML
    private JFXTextField searchFiled;
    @FXML
    private JFXButton print_Button;
    @FXML
    private SplitPane splitPane_1;
    @FXML
    private AnchorPane main_Anchor_1;
    @FXML
    private AnchorPane main_Anchor_2;
    @FXML
    private AnchorPane inner_Anchor_1;
    @FXML
    private AnchorPane inner_Anchor_2;
    @FXML
    private ScrollPane scroll_Pane;
    @FXML
    private SplitPane splitPane_2;
    @FXML
    private StackPane main_Pane;
    @FXML
    private Text date_Text;
    @FXML
    private TableView<Logic.TableList> in_Table;
    @FXML
    private TableColumn<Logic.TableList, String> in_Item_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> in_Quantity_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> in_Cost_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> in_Total_Coll;
    @FXML
    private TableView<Logic.TableList> out_Table;
    @FXML
    private TableColumn<Logic.TableList, String> out_Item_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> out_Quantity_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> out_Cost_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> out_Total_Coll;
    @FXML
    private JFXButton in_Print;
    @FXML
    private JFXButton out_Print;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DateFormat dateFormat1 = new SimpleDateFormat("MM");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
        Calendar cal1 = Calendar.getInstance();
        month = dateFormat1.format(cal1.getTime());
        year = dateFormat2.format(cal1.getTime());
        date_Text.setText("( " + logic.getMonth(month) + " " + year + " )");
        //bind table height
        stock_Table.prefHeightProperty().bind(scroll_Pane.heightProperty());
        //fixed Main
        main_Anchor_1.maxHeightProperty().bind(splitPane_1.heightProperty().multiply(0.08));
        main_Anchor_2.maxHeightProperty().bind(splitPane_1.heightProperty().multiply(0.92));
        //fixed Inner
        inner_Anchor_1.maxWidthProperty().bind(splitPane_2.widthProperty().multiply(0.70));
        inner_Anchor_2.maxWidthProperty().bind(splitPane_2.widthProperty().multiply(0.30));
        con = SqlConection.ConnectDB();
        data = FXCollections.observableArrayList();
        Out = FXCollections.observableArrayList();
        In = FXCollections.observableArrayList();
        //check
        this.setTable();
        this.inTable();
        this.outTable();
        this.loadData();
        this.SelectCell();
        this.search();
    }

    public void setTable() {
        no_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        product_Name_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        previous_Month_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        left_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
        total_Left_Coll.setCellValueFactory(new PropertyValueFactory<>("C4"));
        left_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C5"));
    }

    public void inTable() {
        in_Item_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        in_Quantity_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        in_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        in_Total_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
    }

    public void outTable() {
        out_Item_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        out_Quantity_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        out_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        out_Total_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
    }

    public void loadData() {
        try {
            rs = logic.getAllData("stock");
            int i = 0;
            while (rs.next()) {
                String C0 = String.valueOf(++i);
//                String C1 = rs.getString("C1");
//                String C2 = rs.getString("C2");
                String C3 = rs.getString("C3");
                String C4 = rs.getString("C4");
                String C5 = rs.getString("C5");
                String C6 = rs.getString("C6");
                String C7 = rs.getString("C7");
                String C8 = rs.getString("C8");

                data.add(new Logic.TableList(C0, C3, C4 + "  " + C8, C5 + "  " + C8, C6 + "  " + C8, C7 + " Rs"));
            }
            stock_Table.setItems(data);
            logic.Table_Resize(stock_Table, 90);

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SelectCell() {

        stock_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logic.TableList t = stock_Table.getItems().get(stock_Table.getSelectionModel().getSelectedIndex());
                p_Name = t.getC1();
                StockController.this.InStock(t.getC1());
                StockController.this.OutStock(t.getC1());
                //  out_Table.getItems().clear();
                //  StockController.this.UploadDetails(t.getC1(), "Out");
            }

        });
    }

    private void search() {
        searchFiled.setOnKeyReleased(e -> {
            if (searchFiled.getText().equals("")) {
                stock_Table.getItems().clear();
                loadData();
            } else {
                data.clear();
                String sql1 = "Select * from stock where C3 like '%" + searchFiled.getText() + "%'";
                try {
                    pst = con.prepareStatement(sql1);
                    rs = pst.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        String C0 = String.valueOf(++i);
//                String C1 = rs.getString("C1");
//                String C2 = rs.getString("C2");
                        String C3 = rs.getString("C3");
                        String C4 = rs.getString("C4");
                        String C5 = rs.getString("C5");
                        String C6 = rs.getString("C6");
                        String C7 = rs.getString("C7");
                        String C8 = rs.getString("C8");
                        data.add(new Logic.TableList(C0, C3, C4 + "  " + C8, C5 + "  " + C8, C6 + "  " + C8, C7 + " Rs"));
                    }
                    stock_Table.setItems(data);
                    logic.Table_Resize(stock_Table, 90);

                } catch (SQLException ex) {
                    Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void print_All(ActionEvent event) {
        if (data.isEmpty()) {
            dialog.Notofication("Print", logic.getMonth(month) + "/" + year + " no record is avalable", "Wrong");
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
                print.print("all_Stock", null);
            }
        }
    }

    public void InStock(String Id) {
        in_Table.getItems().clear();
        In.clear();
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select purchase_details.C3 as name,"
                    + "purchase_details.C4 as quantity,"
                    + "purchase_details.C5 as cost,"
                    + "purchase_details.C6 as unit,"
                    + "purchase_details.C7 as p_cost from purchase_details "
                    + "CROSS JOIN purchase where purchase.C1=purchase_details.C2 AND purchase_details.C3='" + Id + "' AND purchase.C3='" + month + "' AND purchase.C4='" + year + "'");

            rs = pst.executeQuery();
            while (rs.next()) {
                In.addAll(new Logic.TableList(rs.getString("name"),
                        rs.getString("quantity"),
                        rs.getString("cost") + "  " + rs.getString("unit"),
                        rs.getString("p_cost")));
            }
            rs.close();
            pst.close();
            con.close();
            in_Table.setItems(In);

        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void OutStock(String Id) {
        out_Table.getItems().clear();
        Out.clear();
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select sale_details.C3 as name,"
                    + "sale_details.C4 as quantity,"
                    + "sale_details.C5 as cost,"
                    + "sale_details.C6 as unit,"
                    + "sale_details.C7 as p_cost from sale_details "
                    + "CROSS JOIN sale where sale.C1=sale_details.C2 AND sale_details.C3='" + Id + "' AND sale.C3='" + month + "' AND sale.C4='" + year + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                Out.addAll(new Logic.TableList(rs.getString("name"),
                        rs.getString("quantity"),
                        rs.getString("cost") + "  " + rs.getString("unit"),
                        rs.getString("p_cost")));
            }
            rs.close();
            pst.close();
            con.close();
            out_Table.setItems(Out);
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void InPrint() {
        if (stock_Table.getSelectionModel().isEmpty()) {
            dialog.error_Dialog(main_Pane, "First select the product.", "SELECT");
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to print out.")) {
                String data[] = {p_Name, month, year, date_Text.getText()};
                print.print("stock_In", data);
            }
        }
    }

    public void OutPrint() {
        if (stock_Table.getSelectionModel().isEmpty()) {
            dialog.error_Dialog(main_Pane, "First select the product.", "SELECT");
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to print out.")) {
                String data[] = {p_Name, month, year, date_Text.getText()};
                print.print("stock_Out", data);
            }
        }
    }

    @FXML
    private void in_Print_On_Clicked(ActionEvent event) {
        this.InPrint();
        event.consume();
    }

    @FXML
    private void in_Print_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.InPrint();
        }
        event.consume();
    }

    @FXML
    private void out_Print_On_Clicked(ActionEvent event) {
        this.OutPrint();
        event.consume();
    }

    @FXML
    private void out_print_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.OutPrint();
        }
        event.consume();
    }
}
