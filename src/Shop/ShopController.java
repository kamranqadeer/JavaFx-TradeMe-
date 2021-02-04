/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shop;

import AllProducts.AllProductsController;
import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.Data;
import Logic.Validator;
import Logic.all_Fields;
import Logic.all_Updates;
import Logic.getData;
import Logic.kk_Logic;
import Logic.print_Data;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class ShopController implements Initializable {

    Validator validator = new Validator();
    getData get_Data = new getData();
    DecimalFormat numberFormat = new DecimalFormat("#.0");
    boolean check = false;
    Connection con = null;
    MainDialogs dialog = new MainDialogs();
    ResultSet rs = null, rs2 = null;
    PreparedStatement pst = null;
    Data hide_Data = new Data();
    ObservableList<String> listView = FXCollections.observableArrayList();
    all_Updates all_updates = new all_Updates();
    all_Fields fields = new all_Fields();
    String shop_Name = "", Id_1, sale_Recived = "0", purchase_Recived = "0", advance = "0";
    print_Data print_Data = new print_Data();
    kk_Logic logic = new kk_Logic();
    private ObservableList<Logic.TableList> data;
    private ObservableList<Logic.TableList> data2;

    @FXML
    private TableView<Logic.TableList> shop_Table;
    @FXML
    private TableColumn<Logic.TableList, String> serial_Number;
    @FXML
    private TableColumn<Logic.TableList, String> s_Id;

    @FXML
    private TableColumn<Logic.TableList, String> date;
    @FXML
    private TableColumn<Logic.TableList, String> s_O_Name;
    @FXML
    private TableColumn<Logic.TableList, String> sale_Left;
    @FXML
    private TableColumn<Logic.TableList, String> purchase_Left;
    @FXML
    private TableColumn<Logic.TableList, String> advance_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> shop_Contact;
    @FXML
    private TableColumn<Logic.TableList, String> shop_Address;

    @FXML
    private Text text_1;
    @FXML
    private Text text_2;
    @FXML
    private Text text_3;
    @FXML
    private Text text_4;
    @FXML
    private StackPane main_Pane;
    @FXML
    public AnchorPane mainAnchor;
    @FXML
    private JFXTextField contact;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField shop_Owner_Name;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton clear;
    @FXML
    private JFXTextField searchFiled;
    @FXML
    private JFXButton print_All;
    @FXML
    private MenuItem delete_Button;
    @FXML
    private ComboBox phone_Code;
    @FXML
    private Text sale_Left_Text;
    @FXML
    private JFXButton sale;
    @FXML
    private Text purchase_Left_Text;
    @FXML
    private JFXButton purchase;
    @FXML
    private JFXTextField give_Sale;
    @FXML
    private JFXTextField give_Purchase;
    @FXML
    private GridPane sale_Grid;
    @FXML
    private RowConstraints list_Row;
    @FXML
    private RowConstraints sale_Row;
    @FXML
    private RowConstraints purchase_Row;
    @FXML
    private GridPane purchase_Grid;
    @FXML
    private Text text_5;
    @FXML
    private Text text_6;
    @FXML
    private TableView<Logic.TableList> listTable;
    @FXML
    private TableColumn<Logic.TableList, String> listNo;
    @FXML
    private TableColumn<Logic.TableList, String> listId;
    @FXML
    private TableColumn<Logic.TableList, String> listDate;
    @FXML
    private TableColumn<Logic.TableList, String> listType;
    @FXML
    private TableColumn<Logic.TableList, String> listTotal;
    @FXML
    private TableColumn<Logic.TableList, String> listRecived;
    @FXML
    private TableColumn<Logic.TableList, String> listLeft;
    @FXML
    private GridPane leftPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private ScrollPane scroll_Pane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Text advance_Text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //update left sale and purchase
        this.upate_Left("sale", "C4");
        this.upate_Left("purchase", "C5");
        // TODO
        //bind table height
        shop_Table.prefHeightProperty().bind(scroll_Pane.heightProperty());
        //fixed splitpan
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.65));
        data = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        setTable();
        setListTable();
        loadData();
        SelectCell();
        this.listTabelSelect();
        this.search();
        this.Validation();
        //setting codes
        phone_Code.setItems(hide_Data.getAllCodes());
        sale_Grid.setVisible(false);
        purchase_Grid.setVisible(false);
        sale_Row.setPrefHeight(0);
        purchase_Row.setPrefHeight(0);
        list_Row.setPrefHeight(350);
        this.Pay_Purchase_Change();
        this.Pay_Sale_Change();
    }
//    SetMain Table Set

    private void setTable() {
        serial_Number.setCellValueFactory(new PropertyValueFactory<>("C0"));
        s_Id.setCellValueFactory(new PropertyValueFactory<>("C1"));
        date.setCellValueFactory(new PropertyValueFactory<>("C2"));
        s_O_Name.setCellValueFactory(new PropertyValueFactory<>("C3"));
        sale_Left.setCellValueFactory(new PropertyValueFactory<>("C4"));
        purchase_Left.setCellValueFactory(new PropertyValueFactory<>("C5"));
        shop_Contact.setCellValueFactory(new PropertyValueFactory<>("C6"));
        shop_Address.setCellValueFactory(new PropertyValueFactory<>("C7"));
        advance_Coll.setCellValueFactory(new PropertyValueFactory<>("C8"));
    }
//    Set List Table

    private void setListTable() {
        listNo.setCellValueFactory(new PropertyValueFactory<>("C0"));
        listId.setCellValueFactory(new PropertyValueFactory<>("C1"));
        listDate.setCellValueFactory(new PropertyValueFactory<>("C2"));
        listType.setCellValueFactory(new PropertyValueFactory<>("C3"));
        listTotal.setCellValueFactory(new PropertyValueFactory<>("C4"));
        listRecived.setCellValueFactory(new PropertyValueFactory<>("C5"));
        listLeft.setCellValueFactory(new PropertyValueFactory<>("C6"));
    }
//  Load main Table

    private void loadData() {
        data.clear();
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from shop");
            rs = pst.executeQuery();
            if (rs.equals(null)) {
                print_All.setDisable(true);
            } else {
                print_All.setDisable(false);
            }
            int i = 0;
            while (rs.next()) {
                String C0 = String.valueOf(++i);
                String C1 = rs.getString("C1");
                String C2 = rs.getString("C2");
                String C3 = rs.getString("C3");
                String C4 = rs.getString("C4");
                String C5 = rs.getString("C5");
                String C6 = rs.getString("C6");
                String C7 = rs.getString("C7");
                String C8 = rs.getString("C8");
                data.add(new Logic.TableList(C0, C1, C2, C3, C4, C5, C6, C7, C8));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
        shop_Table.setItems(data);
        logic.autoResizeColumns(shop_Table);

    }
// Validation On Text change

    public void Validation() {
        check = validator.productNameFiled(shop_Owner_Name, text_1);
        check = validator.Address(address, text_2);
        check = validator.Contact(contact, text_4);
        validator.Cost(give_Sale, text_5);
        validator.Cost(give_Purchase, text_6);

    }

    //On Save And Update Call
    public boolean validate_Call() {
        if (shop_Owner_Name.getText().isEmpty()) {
            text_1.setText("Field required !");
            return false;
        } else if (address.getText().isEmpty()) {
            text_2.setText("Field required !");
            return false;
        } else if (phone_Code.getSelectionModel().isEmpty()) {
            text_3.setText("Select Code !");
            return false;
        } else if (contact.getText().isEmpty()) {
            text_4.setText("Field required !");
            return false;
        } else {
            return true;
        }

    }
// Clear Fileds

    private void clear_Field() {
        shop_Owner_Name.clear();
        give_Sale.clear();
        address.clear();
        contact.clear();
        phone_Code.setValue("03**");
        purchase_Left_Text.setText("Amount");
        sale_Left_Text.setText("Amount");
        sale_Grid.setVisible(false);
        sale_Row.setPrefHeight(0);
        purchase_Grid.setVisible(false);
        purchase_Row.setPrefHeight(0);
        list_Row.setPrefHeight(350);
        data2.clear();
        searchFiled.clear();
        listTable.getItems().clear();
        shop_Name = "";
        text_1.setText("");
        text_2.setText("");
        text_3.setText("");
        text_4.setText("");
        text_5.setText("");
        text_6.setText("");
        advance_Text.setText("");

    }
// Search Data On Main Coloum

    private void search() {
        searchFiled.setOnKeyReleased(e -> {
            if (searchFiled.getText().equals("")) {
                loadData();
            } else {
                data.clear();
                String sql1 = "Select * from shop where C1 like '%" + searchFiled.getText() + "%'"
                        + "UNION Select * from shop where C3 like '%" + searchFiled.getText() + "%'";
                try {
                    con = SqlConection.ConnectDB();
                    pst = con.prepareStatement(sql1);
                    rs = pst.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        String no = String.valueOf(++i);
                        String C1 = rs.getString("C1");
                        String C2 = rs.getString("C2");
                        String C3 = rs.getString("C3");
                        String C4 = rs.getString("C4");
                        String C5 = rs.getString("C5");
                        String C6 = rs.getString("C6");
                        String C7 = rs.getString("C7");
                        String C8 = rs.getString("C8");
                        data.add(new Logic.TableList(no, C1, C2, C3, C4, C5, C6, C7, C8));
                    }
                    shop_Table.setItems(data);

                } catch (SQLException ex) {
                    Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    this.CloseConnection();
                }
            }
        });
    }
// Select Coloum Data For Update /Pay Amount

    private void SelectCell() {
        shop_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!shop_Table.getSelectionModel().isEmpty()) {
                    Logic.TableList t = shop_Table.getItems().get(shop_Table.getSelectionModel().getSelectedIndex());
                    Id_1 = "";
                    data2.clear();
                    listTable.getItems().clear();
                    ShopController.this.Add_Shop_List(t.getC3());
                    shop_Owner_Name.setText(t.getC3());
                    shop_Name = t.getC3();
                    if (Double.valueOf(t.getC4()) == 0) {
                        sale_Left_Text.setText("0");
                        sale_Grid.setVisible(false);
                        sale_Row.setPrefHeight(0);
                        list_Row.setPrefHeight(350);
                    } else {
                        sale_Left_Text.setText(t.getC4());
                        sale_Recived = t.getC4();
                        sale_Grid.setVisible(true);
                        sale_Row.setPrefHeight(80);
                        list_Row.setPrefHeight(250);
                    }
                    if (Double.valueOf(t.getC5()) == 0) {
                        purchase_Left_Text.setText("0");
                        purchase_Grid.setVisible(false);
                        purchase_Row.setPrefHeight(0);
                        list_Row.setPrefHeight(350);

                    } else {
                        purchase_Left_Text.setText(t.getC5());
                        purchase_Recived = t.getC5();
                        purchase_Grid.setVisible(true);
                        purchase_Row.setPrefHeight(80);
                        list_Row.setPrefHeight(250);

                    }
                    String p_Number[] = t.getC6().split("-");
                    phone_Code.setValue(p_Number[0]);
                    contact.setText(p_Number[1]);
                    address.setText(t.getC7());
                    advance = t.getC8();
                    if (Double.valueOf(advance) != 0) {
                        advance_Text.setText("Note  :" + shop_Name + " adnvace amount is left (" + advance + " Rs )");
                    } else {
                        advance_Text.setText("");
                    }
                }
            }
        });
    }
//Adding list Table data

    public void Add_Shop_List(String id) {
        rs = logic.getTable_Data("shop_a", id, "C4", "C2");
        int i = 0;
        try {
            while (rs.next()) {
                String t_Name = "";
                if (rs.getString("C3").equals("Sale")) {
                    t_Name = "sale";
                } else {
                    t_Name = "purchase";
                }
                rs2 = logic.getTable_Data(t_Name, rs.getString("C5"), "C1", "C3");
                String C0 = String.valueOf(++i);
                String C1 = rs.getString("C5");
                String C2 = rs.getString("C2");
                while (rs2.next()) {
                    String C3 = t_Name.toUpperCase();
                    String C4 = rs2.getString("C6");
                    String C5 = rs2.getString("C7");
                    String C6 = rs2.getString("C8");
                    data2.add(new Logic.TableList(C0, C1, C2, C3, C4, C5, C6));
                }
            }
            listTable.setItems(data2);
            logic.autoResizeColumns(listTable);
            rs.close();
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//print All

    @FXML
    private void print_All_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
                print_Data.print("shops", null);
            }
        }
        event.consume();
    }

//print all shop record
    @FXML
    private void print_All_Acction(ActionEvent event) {
        if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
            print_Data.print("shops", null);
        }
        event.consume();
    }

//delete shop record
    @FXML
    private void delete_Button_Ac(ActionEvent event) throws SQLException {
        if (shop_Name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please SELECT THE ROW", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to Delete this shop =" + shop_Name)) {
                // ... user chose OK
                logic.deleteData("shop", "C3", shop_Name);
                logic.deleteData("shop_a", "C4", shop_Name);
                this.setTable();
                this.loadData();
                this.clear_Field();
            } else {
                this.clear_Field();
                // ... user chose CANCEL or closed the dialog
            }

        }
    }
//list Table Select

    public void listTabelSelect() {
        listTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logic.TableList t = listTable.getItems().get(listTable.getSelectionModel().getSelectedIndex());
                if (t.getC3().equals("SALE")) {
                    Id_1 = t.getC1();
                    sale_Left_Text.setText(t.getC6());
                    purchase_Grid.setVisible(false);
                    sale_Grid.setVisible(true);
                    sale_Row.setPrefHeight(80);
                    purchase_Row.setPrefHeight(0);
                    list_Row.setPrefHeight(350);
                    sale_Recived = t.getC6();
                } else {
                    Id_1 = t.getC1();
                    purchase_Left_Text.setText(t.getC6());
                    purchase_Grid.setVisible(true);
                    purchase_Row.setPrefHeight(80);
                    sale_Grid.setVisible(false);
                    sale_Row.setPrefHeight(0);
                    list_Row.setPrefHeight(350);
                    purchase_Recived = t.getC6();
                }
            }
        });
    }
// pay Selected Amount Method

    public void payAmount(Double amount, String check) {
        double left = 0, pay = 0, recived = 0;
        if (check.equals("sale")) {
            try {
                recived = amount;
                ResultSet rs = logic.getTable_Data(check, Id_1, "C1", "C3");
                while (rs.next()) {
                    left = Double.valueOf(rs.getString("C8"));
                    if (left > 0) {
                        if (left > recived) {
                            if (check.equals("sale")) {
                                this.update_Sale(Id_1,
                                        rs.getString("C7") + "\n" + numberFormat.format(left),
                                        numberFormat.format(left - recived));
                            } else {
                                this.update_Purchase(Id_1,
                                        rs.getString("C7") + "\n" + numberFormat.format(left),
                                        numberFormat.format(left - recived));
                            }
                            break;
                        } else {
                            pay = recived - left;
                            //call and other method
                            if (check.equals("sale")) {
                                this.update_Sale(Id_1,
                                        rs.getString("C7") + "\n" + numberFormat.format(left),
                                        "0.0");
                            } else {
                                this.update_Purchase(Id_1,
                                        rs.getString("C7") + "\n" + numberFormat.format(left),
                                        "0.0");
                            }
                            this.Full_Update(pay, check);
                        }
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//pay full amount

    public void Full_Update(Double recived, String check) {
        double left = 0;
        try {
            ResultSet rs = logic.getTable_Data(check, shop_Name, "C5", "C3");
            while (rs.next()) {
                left = Double.valueOf(rs.getString("C8"));
                if (left != 0.0) {
                    if (left > recived) {
                        if (check.equals("sale")) {
                            this.update_Sale(rs.getString("C1"),
                                    rs.getString("C7") + "\n" + numberFormat.format(recived),
                                    numberFormat.format(left - recived));
                            recived = 0.0;
                            break;
                        } else {
                            this.update_Purchase(rs.getString("C1"),
                                    rs.getString("C7") + "\n" + numberFormat.format(recived),
                                    numberFormat.format(left - recived));
                            recived = 0.0;
                            break;
                        }

                    } else {
                        if (check.equals("sale")) {
                            this.update_Sale(rs.getString("C1"),
                                    rs.getString("C7") + "\n" + numberFormat.format(left),
                                    "0.0");
                        } else {
                            this.update_Purchase(rs.getString("C1"),
                                    rs.getString("C7") + "\n" + numberFormat.format(left),
                                    "0.0");
                        }
                        recived = recived - left;
                    }
                }
            }
            if (recived != 0) {
                //   String temp = logic.getTableValue("shop", "C8", "C3", shop_Name);
                con = SqlConection.ConnectDB();
                String sql = "update shop set"
                        + "  C8='" + numberFormat.format(recived)
                        + "' where C3='" + shop_Name + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
                con.close();
                pst.close();
            } else {
                con = SqlConection.ConnectDB();
                String sql = "update shop set"
                        + "  C8='" + "0"
                        + "' where C3='" + shop_Name + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
                con.close();
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //paying sale Left
    public void pay_Left_Sale() {
        if (give_Sale.getText().isEmpty()) {
            advance_Text.setText("Enter Amount !");
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to pay amount =" + give_Sale.getText())) {
                if (Id_1.isEmpty()) {
                    this.Full_Update(Double.valueOf(give_Sale.getText()) + Double.valueOf(advance), "sale");
                    this.upate_Left("sale", "C4");
                    this.clear_Field();
                    this.setTable();
                    this.loadData();
                    this.CloseConnection();
                } else {
                    this.payAmount(Double.valueOf(give_Sale.getText()) + Double.valueOf(advance), "sale");
                    this.upate_Left("sale", "C4");
                    this.clear_Field();
                    this.setTable();
                    this.loadData();
                    this.CloseConnection();
                }
                this.Add_Shop_List(shop_Name);
            }
            advance_Text.setText("");
            give_Sale.clear();
        }
    }
// pay sal Action

    @FXML
    private void pay_Sale_Acc(ActionEvent event) {
        this.pay_Left_Sale();
    }
//pay sale on enter

    @FXML
    private void pay_Sale_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.pay_Left_Sale();
        }

    }
//pay sale field Enter

    @FXML
    private void sale_Field_On_Enter(ActionEvent event) {
        this.pay_Left_Sale();
    }
    //pay_Left_Sale 

    public void pay_Left_Purchase() {
        if (give_Purchase.getText().isEmpty()) {
            advance_Text.setText("Enter Amount !");
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to pay amount =" + give_Purchase.getText())) {
                if (Id_1.isEmpty()) {
                    this.Full_Update(Double.valueOf(give_Purchase.getText()) + Double.valueOf(advance), "purchase");
                    this.upate_Left("purchase", "C5");
                    this.clear_Field();
                    this.setTable();
                    this.loadData();
                    this.CloseConnection();
                } else {
                    this.payAmount(Double.valueOf(give_Purchase.getText()) + Double.valueOf(advance), "purchase");
                    this.upate_Left("purchase", "C5");
                    this.clear_Field();
                    this.setTable();
                    this.loadData();
                    this.CloseConnection();

                }
                this.Add_Shop_List(shop_Name);
            }
            advance_Text.setText("");
            give_Purchase.clear();
        }
    }
//pay Purchase Acction

    @FXML
    private void pay_Purchase_Acc(ActionEvent event) {
        this.pay_Left_Purchase();

    }

    @FXML
    private void pay_Purchase_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.pay_Left_Purchase();
        }
    }

//pay purchase field enter
    @FXML
    private void purchase_Field_On_Enter(ActionEvent event) {
        this.pay_Left_Purchase();
    }
//update sale and purchase left

    public void upate_Left(String t_Name, String f_Name) {
        try {
            double amount = 0;

            rs = logic.getAllData("shop");
            while (rs.next()) {
                double sale_Left = Double.valueOf(rs.getString("C4"));
                rs2 = logic.getTable_Data(t_Name, rs.getString("C3"), "C5", "C1");
                while (rs2.next()) {
                    double temp = Double.valueOf(rs2.getString("C8"));
                    if (temp > 0) {
                        amount += temp;
                    }
                }
                if (amount != sale_Left) {
                    logic.uploadFieldData("shop", f_Name, numberFormat.format(amount), "C3", rs.getString("C3"));
                }
                amount = 0;
                rs2.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }

    }
    //update sale Left and Give

    public void update_Sale(String Id, String v1, String v2) {
        try {
            con = SqlConection.ConnectDB();
            String sql = "update sale set"
                    + "  C7='" + v1
                    + "',C8='" + v2
                    + "' where C1='" + Id + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }
//update purchase Left and Give

    public void update_Purchase(String Id, String v1, String v2) {
        try {
            con = SqlConection.ConnectDB();
            String sql = "update purchase set"
                    + "  C7='" + v1
                    + "',C8='" + v2
                    + "' where C1='" + Id + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    //Save Data Method
    public void Save() {
        if (this.validate_Call() && check) {
            try {
                con = SqlConection.ConnectDB();
                Statement stmt;
                stmt = con.createStatement();
                String sql1 = "Select C3 from shop where C3= '" + shop_Owner_Name.getText() + "'";
                rs = stmt.executeQuery(sql1);
                if (rs.next()) {
                    dialog.error_Dialog(main_Pane, "Sorry this Shop already exist.","DUBLICATE");

                } else {
                    con = SqlConection.ConnectDB();
                    String sale_Amount = give_Sale.getText();
                    String purchase_Amount = give_Purchase.getText();

                    if (sale_Amount.isEmpty()) {
                        sale_Amount = "0";
                    }
                    if (purchase_Amount.isEmpty()) {
                        purchase_Amount = "0";
                    }
                    String sq = "insert into shop(C3,C4,C5,C6,C7,C8)values('"
                            + shop_Owner_Name.getText() + "','"
                            + sale_Amount + "','"
                            + purchase_Amount + "','"
                            + phone_Code.getSelectionModel().getSelectedItem() + "-" + contact.getText() + "','"
                            + address.getText() + "','"
                            + "0" + "')";
                    pst = con.prepareStatement(sq);
                    if (dialog.confirm_Dialog(main_Pane, "You want to add this shop " + shop_Owner_Name.getText())) {
                        pst.execute();
                        loadData();
                        setTable();
                        clear_Field();
                    } else {
                        this.clear_Field();

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.CloseConnection();
            }

        }
    }
    //save enter acction

    @FXML
    private void save_Enter(KeyEvent event) {
        this.Save();
    }

    //save clicked acction
    @FXML
    private void sale_Clicked(ActionEvent event) {

        this.Save();
        event.consume();
    }
    //Update method

    public void update() {
        if (this.validate_Call() && check) {
            try {
                con = SqlConection.ConnectDB();
                String sale_Amount = give_Sale.getText();
                String purchase_Amount = give_Purchase.getText();
                if (sale_Amount.isEmpty()) {
                    sale_Amount = "0";
                }
                if (purchase_Amount.isEmpty()) {
                    purchase_Amount = "0";
                }
                String sql = "update shop set"
                        + "  C3='" + shop_Owner_Name.getText()
                        + "',C4='" + sale_Amount
                        + "',C5='" + purchase_Amount
                        + "',C6='" + phone_Code.getSelectionModel().getSelectedItem() + "-" + contact.getText()
                        + "',C7='" + address.getText()
                        + "' where C3='" + shop_Name + "'";

                pst = con.prepareStatement(sql);
                if (dialog.confirm_Dialog(main_Pane, "You want to update this Shop " + shop_Owner_Name.getText())) {
                    pst.executeUpdate();
                    setTable();
                    loadData();
                    clear_Field();
                } else {
                    this.clear_Field();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.CloseConnection();
            }
        }
    }
//update enter acction

    @FXML
    private void update_Enter(KeyEvent event) {
        this.update();
    }
//update clicked acction

    @FXML
    private void update_Clicked(ActionEvent event) {
        this.update();
        event.consume();
    }
    //clear field enter acction

    @FXML
    private void clear_Enter(KeyEvent event) {
        this.clear_Field();
    }
    //clear field clicked acction

    @FXML
    private void clear_Clicked(ActionEvent event) {
        this.clear_Field();
        event.consume();
    }

//pay sale input field change method
    public void Pay_Sale_Change() {
        give_Sale.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                Double temp = Double.valueOf(sale_Recived) - Double.valueOf(newValue);
                sale_Left_Text.setText(numberFormat.format(temp));
                if (temp < 0) {
                    text_5.setText("Incresing Amount");
                }
            } else {
                sale_Left_Text.setText(sale_Recived);
                text_5.setText("");
            }
        });
    }

//pay Purchase input field change method
    public void Pay_Purchase_Change() {
        give_Purchase.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                Double temp = Double.valueOf(purchase_Recived) - Double.valueOf(newValue);
                purchase_Left_Text.setText(numberFormat.format(temp));
                if (temp < 0) {
                    text_6.setText("Incresing Amount");
                }
            } else {
                purchase_Left_Text.setText(purchase_Recived);
                text_6.setText("");
            }
        });
    }

    public void CloseConnection() {
        try {
            rs.close();
        } catch (Exception e) {
            /* ignored */ }
        try {
            pst.close();
        } catch (Exception e) {
            /* ignored */ }
        try {
            con.close();
        } catch (Exception e) {
            /* ignored */ }
    }

}
