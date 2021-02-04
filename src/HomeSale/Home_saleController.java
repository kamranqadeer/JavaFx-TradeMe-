/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeSale;

import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.Data;
import Logic.Scales;
import Logic.Validator;
import Logic.all_Updates;
import Logic.combox;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class Home_saleController implements Initializable {

    kk_Logic logic = new kk_Logic();
    Scales scale = new Scales();
    MainDialogs dialogs = new MainDialogs();
    all_Updates update = new all_Updates();
    double advance = 0.0, advacne_Left = 0.0;
    String name = "",
            quantity = "",
            cost = "",
            unit = "",
            total = "",
            give = "0.0",
            time = "";
    String row_Id = "",
            p_Previous_Cost = "",
            p_Previous_Purchase = "",
            p_Previous_Scale = "",
            C2 = "",
            C3 = "",
            C4 = "",
            C5 = "",
            C6 = "0.0",
            C7 = "0.0",
            C8 = "0.0";

    DecimalFormat numberFormat = new DecimalFormat("#.0");
    int i = 0;
    print_Data print = new print_Data();
    Validator validation = new Validator();
    Data Data = new Data();
    ObservableList<String> products = FXCollections.observableArrayList();
    ObservableList<String> shops = FXCollections.observableArrayList("non");
    ObservableList<String> listView = FXCollections.observableArrayList();
    Connection con = null;
    private ObservableList<Logic.TableList> data;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private Text p_Address;
    @FXML
    private ComboBox p_Product_List;
    @FXML
    private ComboBox p_Shop_List;
    @FXML
    private ComboBox p_Scale_List;
    @FXML
    private Text p_Previous_Amount;
    @FXML
    private JFXButton p_Print;
    @FXML
    private Text p_Con;
    @FXML
    private Text p_Total_Cost;
    @FXML
    private JFXTextField p_Give;
    @FXML
    private Text p_Left;
    @FXML
    private JFXTextField p_Cost;
    @FXML
    private JFXTextField p_Quantity;
    @FXML
    private TableView<Logic.TableList> p_Table;
    @FXML
    private TableColumn<Logic.TableList, String> p_No_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> p_Item_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> p_Quantity_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> p_Cost_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> p_Amount_Coll;
    @FXML
    private TableColumn<Logic.TableList, Button> remove_Coll;
    @FXML
    private Text p_Shop;
    @FXML
    private JFXButton p_Add;
    @FXML
    private JFXTextField p_Amount;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text5;
    @FXML
    private Text text4;
    @FXML
    private Text text6;
    @FXML
    private Text scale_Text;
    @FXML
    private StackPane main_Pane;
    @FXML
    private Text advance_Text;
    @FXML
    private DatePicker date_Picker;
    @FXML
    private CheckBox check;
    @FXML
    private Text pay_Text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setting Date And time
        DateFormat dateFormat0 = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Calendar cal1 = Calendar.getInstance();
        time = dateFormat0.format(cal1.getTime());
        C2 = dateFormat1.format(cal1.getTime()) + " " + time;
        C3 = dateFormat2.format(cal1.getTime());
        C4 = dateFormat3.format(cal1.getTime());
        date_Picker.setPromptText("Today " + C3 + "/" + dateFormat1.format(cal1.getTime()) + "/" + C4);
        data = FXCollections.observableArrayList();
        //fixed splitpan
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.65));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35));
        //setting products and shops in combox;
        this.setProduct_Shop_List();
        // product_List.setItems(products);
        p_Product_List.setPromptText("Select Product");
        p_Product_List.setTooltip(new Tooltip());
        p_Product_List.getItems().addAll(products);
        new combox<String>(p_Product_List);
//      setting shopo list
        p_Shop_List.setPromptText("Select Shop");
        p_Shop_List.setTooltip(new Tooltip());
        p_Shop_List.getItems().addAll(shops);
        new combox<String>(p_Shop_List);
        //changing shop
        this.change_Shop();
        //changing Product
        this.change_Product();
        //changing Scale
        this.change_Scale();
        //setting Tabel
        this.setTable();
        // Validation
        Validation();
        validation.Cost(p_Amount, pay_Text);

    }

    //print and Save data
    public void Save_Print() {
        if (check.isSelected()) {
            this.addPreviousBill();
        } else {
            if (data.isEmpty()) {
                dialogs.error_Dialog(main_Pane, "First Add the Items.", "SALE LIST IS EMPTY");
            } else if (p_Give.getText().isEmpty()) {
                text6.setText("Pay Amount is requir !");
                p_Give.setText("0.0");
            } else {

                try {

                    if (dialogs.confirm_Dialog(main_Pane, "You want to take a print and Save it.")) {
                        con = SqlConection.ConnectDB();
                        String sq = "insert into sale(C2,C3,C4,C5,C6,C7,C8,C9)values('"
                                + C2
                                + "','" + C3
                                + "','" + C4
                                + "','" + C5
                                + "','" + C6
                                + "','" + C7
                                + "','" + C8
                                + "','" + give + "')";
                        pst = con.prepareStatement(sq);
                        pst.execute();
                        this.print();
                        //   this.update_Product();
                        this.Shop_Advance();
                        this.clear("Full");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    this.CloseConnection();

                }

            }
        }

    }
    //Save And print clicked

    @FXML
    private void p_Print_Acc(ActionEvent event) {
        this.Save_Print();
        text6.setText("");

    }

    //Save And print Enter
    @FXML
    private void print_Acc_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.Save_Print();
            text6.setText("");
        }
    }

    public void addPreviousBill() {
        if (C5.isEmpty()) {
            text1.setText("Select the shop !");
        } else if (p_Amount.getText().isEmpty()) {
            dialogs.Notofication("Pay", "Pleas enter the previous bill", "Wrong");
        } else {
            try {
                if (dialogs.confirm_Dialog(main_Pane, "You want to take a print and Save it.")) {
                    con = SqlConection.ConnectDB();
                    String sq = "insert into sale(C2,C3,C4,C5,C6,C7,C8,C9)values('"
                            + C2
                            + "','" + C3
                            + "','" + C4
                            + "','" + C5
                            + "','" + "0.0"
                            + "','" + "0.0"
                            + "','" + p_Amount.getText()
                            + "','" + "0.0" + "')";
                    pst = con.prepareStatement(sq);
                    pst.execute();
                    //finding id
                    rs = logic.getFiledData("sale", "C1");
                    String Id = "";
                    while (rs.next()) {
                        Id = rs.getString("C1");
                    }
                    rs.close();
                    //finding date
                    String date = "";
                    rs = logic.getTable_Data("sale", Id, "C1", "C1");

                    while (rs.next()) {
                        date = rs.getString("C4") + "/" + rs.getString("C3") + "/" + rs.getString("C2");
                    }
                    rs.close();
                    String data[] = {Id, "0344200515", "Wah Cantt", date};
                    print.print("previous_Sale_Bill", data);
                    update.shop_Update("Sale", C5, Id);
                }
                p_Amount.clear();
                p_Shop_List.setValue(null);

            } catch (SQLException ex) {
                Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.CloseConnection();

            }
        }
    }

    public void print() {
        try {
            String Id = logic.resentId("sale", "C1");
            String date = "";
            rs = logic.getTable_Data("sale", Id, "C1", "C1");

            while (rs.next()) {
                date = rs.getString("C4") + "/" + rs.getString("C3") + "/" + rs.getString("C2");
            }
            rs.close();
            this.Add_Sale_Details(Id);

            String amount = "",
                    title = "";
            double v = Double.valueOf(C7) - Double.valueOf(give);
            if (v > 0) {
                amount = "+  " + String.valueOf(v);
                title = "Previous Left";
            } else if (v < 0) {
                amount = "-  " + String.valueOf(-1 * v);
                title = "Advance save";
            } else {
                amount = "0.0 Rs";
                title = "No Left And No Advance";
            }

            String data[] = {Id, "0344200515", "Wah Cantt", date, title, amount};
            print.print("item_Sale", data);
            update.shop_Update("Sale", C5, Id);
            update.updateStock(Id, "Deleting", "sale_details");
        } catch (SQLException ex) {
            Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    public void Add_Sale_Details(String Id) {
        try {
            double profit = 0.0;
            con = SqlConection.ConnectDB();
            for (int i = 0; i < data.size(); i++) {
                String temp[] = data.get(i).getC3().split("  ");
                String sq = "insert into sale_details(C2,C3,C4,C5,C6,C7,C8)values('"
                        + Id
                        + "','" + data.get(i).getC1()
                        + "','" + data.get(i).getC2()
                        + "','" + temp[0]
                        + "','" + temp[1]
                        + "','" + data.get(i).getC4()
                        + "','" + data.get(i).getC5() + "')";
                profit = profit + Double.valueOf(data.get(i).getC5());
                pst = con.prepareStatement(sq);
                pst.execute();
                update.adding_All_Month(data.get(i).getC1(), "sale");
                update.Update_Products(Id, "sale", data.get(i).getC1(), temp[0], temp[1], data.get(i).getC2(), data.get(i).getC4());

                //Update and Add profit table
                update.update_Profit(numberFormat.format(profit));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    public void Shop_Advance() {
        if (Double.valueOf(advacne_Left) > 0) {
            update.Full_Update(advacne_Left, "sale", C5);
        }
//        if (advacne_Left > 0 || advance > 0) {
//            try {
//                con = SqlConection.ConnectDB();
//                String sql = "update shop set"
//                        + "  C8='" + numberFormat.format(advacne_Left)
//                        + "' where C3='" + C5 + "'";
//                pst = con.prepareStatement(sql);
//                pst.executeUpdate();
//            } catch (SQLException ex) {
//                Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                this.CloseConnection();
//            }
//        }
    }

    public void update_Product() {
        //updating product cost if new product cost is incressing or decressing
        for (int i = 0; i < data.size(); i++) {
            try {
                String temp[] = data.get(i).getC3().split("  ");
                rs = logic.getTable_Data("product", data.get(i).getC1(), "C3", "C1");
                while (rs.next()) {
                    if (!rs.getString("C6").equals(temp[0]) || !rs.getString("C5").equals(temp[1])) {
                        String cost = scale.getScale_Amount(temp[1], rs.getString("C5"), Double.valueOf(rs.getString("C4")));
                        String sql = "update product set C4='" + cost
                                + "',C5='" + temp[1]
                                + "',C6='" + temp[0]
                                + "' where C3='" + data.get(i).getC1() + "'";
                        con = SqlConection.ConnectDB();
                        pst = con.prepareStatement(sql);
                        pst.executeUpdate();
                        pst.execute();
                        break;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.CloseConnection();
            }
        }

    }

    private void setProduct_Shop_List() {
        try {
            rs = logic.getFiledData("product", "C3");
            while (rs.next()) {
                products.add(rs.getString("C3"));
            }

            rs = logic.getFiledData("shop", "C3");
            while (rs.next()) {
                shops.add(rs.getString("C3"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void change_Product() {
        p_Product_List.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text2.setText("");
                if (newValue != null) {
                    //check in stock
                    p_Quantity.setText("1");
                    name = newValue;//getValue
                    try {
                        p_Scale_List.setDisable(false);
                        p_Quantity.setDisable(false);
                        p_Cost.setDisable(false);
                        p_Give.setDisable(false);
                        rs = logic.getTable_Data("product", newValue, "C3", "C1");
                        while (rs.next()) {
                            String C5 = rs.getString("C5");
                            String C6 = rs.getString("C6");
                            p_Scale_List.setItems(scale.getScales(C5));
                            p_Scale_List.setValue(C5);
                            p_Previous_Amount.setText(C6);
                            p_Previous_Purchase = rs.getString("C4");
                            p_Previous_Cost = C6;
                            p_Cost.setText(C6);
                            p_Total_Cost.setText(C6);
                            scale_Text.setText(C5);
                            p_Previous_Scale = C5;
                            cost = C6;//getValue
                            unit = C5;//getValue
                            quantity = "1";
                            p_Quantity.setText("1");
                            give = "0.0";
                        }
                        p_Quantity.clear();
                        p_Quantity.setText("1");
                        rs.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }
        );

    }

    public void change_Scale() {
        p_Scale_List.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text3.setText("");
                if (newValue != null) {
                    unit = newValue;//get Vlaue
                    if (!p_Previous_Cost.isEmpty()) {
                        String temp = scale.getScale_Amount(newValue, p_Previous_Scale, Double.valueOf(p_Previous_Cost));
                        p_Previous_Amount.setText(temp);
                        p_Cost.setText(temp);
                        scale_Text.setText(newValue);
                        p_Quantity.setText("1");
                    }
                }
            }
        }
        );

    }

    public void change_Shop() {
        p_Shop_List.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text1.setText("");
                if (newValue != null) {
                    C5 = newValue;//getValue
                    p_Shop.setText(newValue);
                    check.setText(C5 + " paying previous bill.");
                    try {
                        rs = logic.getTable_Data("shop", newValue, "C3", "C1");
                        while (rs.next()) {
                            p_Address.setText(rs.getString("C7"));
                            p_Con.setText(rs.getString("C6"));
                            String previous_Left = rs.getString("C4");
                            String a = rs.getString("C8");
                            advance = Double.valueOf(a);
                            if (Double.valueOf(previous_Left) > 0) {
                                advance_Text.setText("Note: Previous amount is left (" + previous_Left + " Rs)");
                            } else if (Double.valueOf(a) > 0) {
                                advance_Text.setText("Note: Advance is left (" + a + " Rs)");
                            } else {
                                advance_Text.setText("");
                                advance = 0.0;
                            }
                        }
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Home_saleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );
    }

    public void clear(String check) {
        if (check.equals("Half")) {
            p_Product_List.setValue(null);
            p_Scale_List.setValue(null);
            p_Scale_List.setDisable(true);
            p_Cost.clear();
            p_Cost.setDisable(true);
            p_Total_Cost.setText("0000  Rs");
            p_Previous_Amount.setText("Amount");
            p_Quantity.clear();
            p_Quantity.setDisable(true);
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
        } else if (check.equals("Full")) {

            C5 = "";
            C6 = "";
            C7 = "";
            C8 = "";
            data.clear();
            listView.clear();
            p_Shop_List.setValue(null);
            p_Shop_List.requestFocus();
            p_Give.clear();
            p_Give.setDisable(true);
            p_Left.setText("0.0");
            p_Product_List.setValue(null);
            p_Scale_List.setValue(null);
            p_Scale_List.setDisable(true);
            p_Cost.clear();
            p_Cost.setDisable(true);
            p_Total_Cost.setText("0000  Rs");
            p_Previous_Amount.setText("Amount");
            p_Quantity.clear();
            p_Quantity.setDisable(true);
            p_Give.setDisable(true);
            p_Amount.clear();
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
            advance_Text.setText("");
            pay_Text.setText("");
        }

    }

    public void setTable() {
        p_No_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        p_Item_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        p_Quantity_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        p_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
        p_Amount_Coll.setCellValueFactory(new PropertyValueFactory<>("C4"));
        remove_Coll.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void table_Upload() {
        data.clear();
        double total_Cost = 0.0;
        // p_Table.getItems().clear();

        for (int i = 0; i < listView.size(); i++) {
            String temp[] = listView.get(i).split("-");
            Button remove = new Button();
            remove.getStyleClass().add("remove_Button");
            remove.setId(temp[0]);
            remove.setOnAction(e -> {
                row_Id = ((Control) e.getSource()).getId();
                this.deleteRow();
                p_Give.clear();
            });
            total_Cost += Double.valueOf(temp[4]);
            data.add(new Logic.TableList(String.valueOf(i + 1), temp[0], temp[1], temp[2] + "  " + temp[3], temp[4], temp[5], remove));
        }
        C6 = numberFormat.format(total_Cost);
        p_Amount.setText(C6);
        p_Give.clear();
        C7 = "0.0";
        C8 = C6;
        p_Left.setText(C8);
        p_Table.setItems(data);
        // logic.autoResizeColumns(p_Table);
    }

    public boolean Validation() {
        boolean check = false;
        check = validation.Cost(p_Cost, text4);
        check = validation.Digite(p_Quantity, text5);
        check = validation.Cost(p_Give, text6);
        return check;
    }

    public boolean ComboValidate() {
        boolean check = false;
        check = validation.ComboBox(p_Shop_List, text1);
        check = validation.ComboBox(p_Product_List, text2);
        check = validation.ComboBox(p_Scale_List, text3);
        return check;
    }
// Add Item Validate Call

    public boolean validate_Call() {
        if (C5.isEmpty()) {
            text1.setText("Please Select !");
            return false;
        } else if (name.isEmpty()) {
            text2.setText("Please Select !");
            return false;

        } else if (unit.isEmpty()) {
            text3.setText("Please Select !");
            return false;

        } else if (cost.isEmpty()) {
            text4.setText("Field is requir !");
            return false;

        } else if (quantity.isEmpty()) {
            text5.setText("Field is requir !");
            return false;
        } else {
            return true;
        }

    }

// Adding Item
    public boolean Add_Item() {
        boolean check = false;

        if (this.validate_Call()) {
            String stock = logic.getTableValue("stock", "C6", "C3", name);
            if (stock.isEmpty() || Double.valueOf(stock) == 0) {
                // dialogs.error_Dialog(main_Pane, name + " is empty in stock.", "NO STOCK");
                this.clear("Half");
                p_Product_List.requestFocus();
                dialogs.Notofication("No Stock", name + " is empty in stock please first purchase it.", "notification");

            } else {
                String temp = scale.getScale_Amount(unit, p_Previous_Scale, Double.valueOf(p_Previous_Purchase));
                if (Double.valueOf(temp) > Double.valueOf(cost)) {
                    dialogs.error_Dialog(main_Pane, "Sorry this sale make a Loss.", "LOSS");
                    check = false;
                } else {
                    double profit = Double.valueOf(quantity) * (Double.valueOf(cost) - Double.valueOf(temp));
                    //    System.out.println(name + "  " + quantity + "  " + cost + "  " + unit + "   " + total);
                    listView.add(this.name + "-" + this.quantity + "-" + this.cost + "-" + this.unit + "-" + this.total + "-" + numberFormat.format(profit));
                    this.table_Upload();
                    this.clear("Half");
                    check = true;
                }
            }
        } else {
            check = false;
        }

        return check;

    }
//Enter Add

    @FXML
    private void p_Add_Acc(ActionEvent event) {
        if (this.Add_Item()) {
            p_Product_List.requestFocus();
            text6.setText("");
        }
        event.consume();
    }
//Enter and Space on Adding Item

    @FXML
    private void p_Add_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {

            if (this.Add_Item()) {
                p_Product_List.requestFocus();
                text6.setText("");
            }

        } else if (event.getCode().equals(KeyCode.SPACE)) {
            if (this.Add_Item()) {

                p_Give.requestFocus();
                text6.setText("");
            }

        }
        event.consume();
    }

    //Eneter Add On Quantity
    @FXML
    private void quantity_Enter(ActionEvent event) {
        this.Add_Item();
        p_Product_List.requestFocus();

        event.consume();
    }
//Delete Row

    public void deleteRow() {
        for (int i = 0; i < listView.size(); i++) {
            String temp[] = listView.get(i).split("-");
            if (temp[0].equals(row_Id)) {
                listView.remove(i);
                this.setTable();
                this.table_Upload();
                break;
            }
        }
    }
//Change Pay Amount

    @FXML
    private void pay_On_Change(KeyEvent event) {
        if (!p_Give.getText().isEmpty()) {
            give = p_Give.getText();
            double temp = 0;
            temp = Double.valueOf(p_Amount.getText()) - Double.valueOf(p_Give.getText()) - advance;
            if (temp >= 0) {
                p_Left.setText(numberFormat.format(temp));
                text6.setText("");
                C8 = numberFormat.format(temp);//getVlaue
                C7 = numberFormat.format(Double.valueOf(p_Give.getText()) + advance);
                advacne_Left = 0;
            } else {
                p_Left.setText(numberFormat.format(temp));
                text6.setText("Amount is incresing !");
                advacne_Left = temp * -1;
                C7 = p_Amount.getText();
                C8 = "0.0";//getVlaue

            }
        } else {
            C8 = p_Amount.getText();
            p_Left.setText(C8);
            C7 = "0.0";
        }

    }
//Change Cost

    @FXML
    private void cost_On_Change(KeyEvent event) {
        this.p_Cost(p_Cost.getText(), p_Quantity.getText());

    }
//Change Quantity

    @FXML
    private void qunatity_On_Change(KeyEvent event) {
        this.p_Cost(p_Cost.getText(), p_Quantity.getText());
    }

//Total Product Cost
    public void p_Cost(String amount, String quantity) {
        if (!amount.isEmpty() && !quantity.isEmpty()) {
            String s = numberFormat.format(Double.valueOf(amount) * Double.valueOf(quantity));
            p_Total_Cost.setText(s);
            total = s;
        } else if (!amount.isEmpty() && quantity.isEmpty()) {
            String s = numberFormat.format(Double.valueOf(amount));
            p_Total_Cost.setText(s);
            total = s;
        } else if (amount.isEmpty() && !quantity.isEmpty()) {
            p_Total_Cost.setText("");
            total = "0";

        }
        this.cost = amount;
        this.quantity = quantity;
    }
    //close All Connections

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

    @FXML
    private void date_On_Change(ActionEvent event) {
        if (!date_Picker.getValue().toString().isEmpty()) {
            String temp[] = date_Picker.getValue().toString().split("-");
            C4 = temp[0];
            C3 = temp[1];
            C2 = temp[2] + " " + time;

        }
    }

    @FXML
    private void check_on_Clicked(ActionEvent event) {
        if (check.isSelected()) {
            dialogs.Notofication("Record", "Now you can pay previous amount", "info");
            this.clear("Full");
            p_Amount.setEditable(true);
            p_Product_List.setDisable(true);
            p_Add.setDisable(true);

        } else {
            p_Amount.setFocusTraversable(false);
            p_Amount.setEditable(false);
            p_Product_List.setDisable(true);
            p_Product_List.setDisable(false);
            p_Add.setDisable(false);

        }
    }
}
