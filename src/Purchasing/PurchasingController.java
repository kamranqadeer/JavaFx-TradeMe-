/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Purchasing;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class PurchasingController implements Initializable {

    ObservableList<String> products = FXCollections.observableArrayList();
    ObservableList<String> listView = FXCollections.observableArrayList();
    ObservableList<String> shops = FXCollections.observableArrayList("non");
    print_Data print = new print_Data();
    all_Updates all_Updates = new all_Updates();
    Scales scale = new Scales();
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    private ObservableList<Logic.TableList> data;
    private ObservableList<Logic.TableList> Details;
    String name = "",
            quantity = "",
            cost = "",
            unit = "",
            total = "",
            give = "",
            y = "",
            m = "";
    String Id = "", total_Amount = "", check_Pay = "0",
            row_Id = "",
            previous_Sale = "",
            previous_Purchase = "",
            previous_Scale = "",
            C2 = "",
            C3 = "",
            C4 = "",
            C5 = "",
            C6 = "",
            C7 = "",
            C8 = "";
    double advance = 0.0, advacne_Left = 0.0;
    kk_Logic logic = new kk_Logic();
    Validator validator = new Validator();
    MainDialogs dialogs = new MainDialogs();
    BoxBlur blur = new BoxBlur(3, 3, 3);
    Data list_Data = new Data();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TableView<Logic.TableList> sale_Table;
    @FXML
    private TableColumn<Logic.TableList, String> no_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> date_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> id_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> Total_Cost;
    @FXML
    private TableColumn<Logic.TableList, String> reci_Amount_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> left_Amount_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> shop_Name_Coll;
    @FXML
    private JFXTextField sale_Amount;
    @FXML
    private JFXTextField p_Quantity;
    @FXML
    private JFXTextField r_Amount;
    @FXML
    private ComboBox product_List;
    @FXML
    private ComboBox scale_List;
    @FXML
    private ComboBox shop_List;
    @FXML
    private Text text_1;
    @FXML
    private Text text_2;
    @FXML
    private Text text_3;
    @FXML
    private Text text_4;
    @FXML
    private Text text_5;
    @FXML
    private Text text_6;
    @FXML
    private StackPane main_Pane;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private MenuItem delete_B;
    @FXML
    private JFXButton add_Button;
    @FXML
    private JFXButton clear_Button1;
    @FXML
    private JFXButton update_B1;
    @FXML
    private JFXButton save_B1;
    @FXML
    private JFXTextField total_Cost;
    @FXML
    private JFXTextField left_Amount;
    @FXML
    private MenuItem print_Select;
    @FXML
    private SplitPane splitPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private GridPane leftPane;
    @FXML
    private ScrollPane scroll_Pane;
    @FXML
    private JFXTextField searchFiled;
    @FXML
    private TableView<Logic.TableList> list_Table;
    @FXML
    private TableColumn<Logic.TableList, String> noList;
    @FXML
    private TableColumn<Logic.TableList, String> listItem;
    @FXML
    private TableColumn<Logic.TableList, String> listQunatity;
    @FXML
    private TableColumn<Logic.TableList, String> listCost;
    @FXML
    private TableColumn<Logic.TableList, String> listAmount;
    @FXML
    private TableColumn<Logic.TableList, String> removeList;
    @FXML
    private JFXTextField final_Cost;
    @FXML
    private JFXButton print_All;
    @FXML
    private Text advance_Text;
    @FXML
    private DatePicker date_Picker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setting Date And time
        DateFormat dateFormat1 = new SimpleDateFormat("dd HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Calendar cal1 = Calendar.getInstance();
        C2 = dateFormat1.format(cal1.getTime());
        C3 = dateFormat2.format(cal1.getTime());
        m = C3;
        C4 = dateFormat3.format(cal1.getTime());
        y = C4;
        shop_List.requestFocus();
        //bind table height
        sale_Table.prefHeightProperty().bind(scroll_Pane.heightProperty());
        //fixed splitpan
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.65));
        data = FXCollections.observableArrayList();
        Details = FXCollections.observableArrayList();
        scale_List.setItems(scale.getAllScale());
        //setting products and shops in combox;
        this.setProducts_Shops();
        // product_List.setItems(products);
        product_List.setTooltip(new Tooltip());
        product_List.getItems().addAll(products);
        new combox<String>(product_List);
//      setting shopo list
        shop_List.setTooltip(new Tooltip());
        shop_List.getItems().addAll(shops);
        new combox<String>(shop_List);
        //Validations 
        this.allValidations();
//      lochal funtion 
        this.setTable();
        this.setListTable();
        this.loadData(m, y);
        this.SelectCell();
        this.change_Product_Name();
        this.change_Scale();
        this.search();
        this.change_Shop();
        this.AddButtonEnter();
        this.AddSaveEnter();
    }

    public void setTable() {

        no_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        id_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        date_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        shop_Name_Coll.setCellValueFactory(new PropertyValueFactory<>("C5"));
        Total_Cost.setCellValueFactory(new PropertyValueFactory<>("C6"));
        reci_Amount_Coll.setCellValueFactory(new PropertyValueFactory<>("C7"));
        left_Amount_Coll.setCellValueFactory(new PropertyValueFactory<>("C8"));
    }

    public void setListTable() {
        noList.setCellValueFactory(new PropertyValueFactory<>("C0"));
        listItem.setCellValueFactory(new PropertyValueFactory<>("C1"));
        listQunatity.setCellValueFactory(new PropertyValueFactory<>("C2"));
        listCost.setCellValueFactory(new PropertyValueFactory<>("C3"));
        listAmount.setCellValueFactory(new PropertyValueFactory<>("C4"));
        removeList.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    public void loadData(String m, String y) {
        data.clear();
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("select * from purchase where C3='" + m + "' AND C4='" + y + "'");
            rs = pst.executeQuery();
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
                data.add(new Logic.TableList(C0, C1, C4 + "/" + C3 + "/" + C2, C3, C4, C5, C6, C7, C8));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sale_Table.setItems(data);
        logic.Table_Resize(sale_Table, 70);

    }

    public void SaveAndPrint() {

        if (this.C5.isEmpty()) {
            text_5.setText("Pleas select.");
        } else {
            if (Details.isEmpty()) {
                dialogs.error_Dialog(main_Pane, "First add the item.", "PURCHASE LIST IS EMPTY");
            } else {
                try {
                    con = SqlConection.ConnectDB();
                    String sq = "insert into purchase(C2,C3,C4,C5,C6,C7,C8,C9)values('"
                            + C2
                            + "','" + C3
                            + "','" + C4
                            + "','" + C5
                            + "','" + C6
                            + "','" + C7
                            + "','" + C8
                            + "','" + give + "')";
                    pst = con.prepareStatement(sq);
                    if (dialogs.confirm_Dialog(main_Pane, "You want to add and take a print.")) {
                        pst.execute();
                        pst.close();
                        setTable();
                        loadData(m, y);
                        //taking print
                        this.print();
                        //update Advance
                        this.Shop_Advance();
                        //update all month
                        // all_Updates.adding_All_Month(C4, C5, "sale");
                        this.clear_Filed("");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    this.CloseConnection();
                }
            }
        }

    }

    public void Shop_Advance() {
        if (advacne_Left > 0 || advance > 0) {
            try {
                con = SqlConection.ConnectDB();
                String sql = "update shop set"
                        + "  C8='" + numberFormat.format(advacne_Left)
                        + "' where C3='" + C5 + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.CloseConnection();
            }
        }
    }

    public void print() {
        try {
            String Id = logic.resentId("purchase", "C1");
            String date = "";
            rs = logic.getTable_Data("purchase", Id, "C1", "C1");

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
            print.print("item_Purchase", data);
            all_Updates.shop_Update("Purchase", C5, Id);
            all_Updates.updateStock(Id, "Adding", "purchase_details");
        } catch (SQLException ex) {
            Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    public void Add_Sale_Details(String Id) {
        try {
            con = SqlConection.ConnectDB();
            for (int i = 0; i < Details.size(); i++) {
                String temp[] = Details.get(i).getC3().split("  ");
                String sq = "insert into purchase_details(C2,C3,C4,C5,C6,C7)values('"
                        + Id
                        + "','" + Details.get(i).getC1()
                        + "','" + Details.get(i).getC2()
                        + "','" + temp[0]
                        + "','" + temp[1]
                        + "','" + Details.get(i).getC4() + "')";
                pst = con.prepareStatement(sq);
                pst.execute();
                all_Updates.adding_All_Month(Details.get(i).getC1(), "purchase");
                all_Updates.Update_Products(Id, "purchase", Details.get(i).getC1(), temp[0], temp[1], Details.get(i).getC2(), Details.get(i).getC4());

            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    @FXML
    private void save_B_A(ActionEvent event) {
        this.SaveAndPrint();
    }

    @FXML
    private void save_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.SaveAndPrint();
        }
    }

    @FXML
    private void pay_On_Enter(ActionEvent event) {
        this.SaveAndPrint();
    }

    public void AddSaveEnter() {
        save_B1.setOnKeyPressed(e -> {
            if (!C5.isEmpty() && !C6.isEmpty() && !C7.isEmpty() && !C8.isEmpty()) {
                if (e.getCode().equals(KeyCode.ENTER)) {
                    this.SaveAndPrint();
                } else {
                    shop_List.requestFocus();
                }
                e.consume();
            }
        });

    }

    private void search() {
        searchFiled.setOnKeyReleased(e -> {
            if (searchFiled.getText().equals("")) {
                loadData(m, y);
            } else {
                data.clear();
                String sql1 = "Select * from purchase where C1 like '%" + searchFiled.getText() + "%' And C3='" + m + "' And C4='" + y + "'"
                        + "UNION Select * from purchase where C5 like '%" + searchFiled.getText() + "%' And C3='" + m + "' And C4='" + y + "'";
                try {
                    con = SqlConection.ConnectDB();
                    pst = con.prepareStatement(sql1);
                    rs = pst.executeQuery();
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
                        data.add(new Logic.TableList(C0, C1, C4 + "/" + C3 + "/" + C2, C3, C4, C5, C6, C7, C8));

                    }
                    sale_Table.setItems(data);

                } catch (SQLException ex) {
                    Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    this.CloseConnection();
                }
            }
        });
    }

    public void clear_Filed(String check) {
        if (check.equals("Half")) {
            product_List.setValue(null);
            sale_Amount.clear();
            scale_List.setDisable(true);
            p_Quantity.clear();
            p_Quantity.setDisable(true);
            total_Cost.clear();
            add_Button.setDisable(true);
            text_1.setText("");
            text_2.setText("");
            text_3.setText("");
            text_4.setText("");
        } else {
            product_List.setValue(null);
            sale_Amount.clear();
            scale_List.setDisable(true);
            p_Quantity.clear();
            p_Quantity.setDisable(true);
            total_Cost.clear();
            add_Button.setDisable(true);
            listView.clear();
            Details.clear();
            list_Table.getItems().clear();
            shop_List.setValue(null);
            final_Cost.clear();
            r_Amount.clear();
            left_Amount.clear();
            text_1.setText("");
            text_2.setText("");
            text_3.setText("");
            text_4.setText("");
            text_5.setText("");
            text_6.setText("");
            advance_Text.setText("");
            C5 = "";
            C6 = "";
            C7 = "";
            C8 = "";

        }

    }

    public void Update() {

        try {
            con = SqlConection.ConnectDB();
            String sql = "update purchase set C5='" + C5
                    + "',C6='" + C6
                    + "',C7='" + C7
                    + "',C8='" + C8
                    + "',C9='" + give
                    + "' where C1='" + Id + "'";
            pst = con.prepareStatement(sql);
            if (total_Amount.equals(C6) && check_Pay.equals(C7)) {
                dialogs.error_Dialog(main_Pane, "No change in Data.", "UPDATE");
            } else {
                if (dialogs.confirm_Dialog(main_Pane, "Do you want to update.")) {

                    //Deleting operation in stock
                    all_Updates.updateStock(Id, "Deleting", "purchase_details");
                    //first delete this purchase on Id
                    logic.deleteData("purchase_details", "C2", Id);
                    //Update
                    pst.executeUpdate();
                    pst.execute();
                    setTable();
                    loadData(m, y);
                    //update Advance
                    this.Shop_Advance();
                    //adding new Purchase
                    this.Add_Sale_Details(Id);
                    //Adding operation in stock
                    all_Updates.updateStock(Id, "Adding", "purchase_details");
                    //clear All fields
                    this.clear_Filed("");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
            all_Updates.Stock_All_updates("purchase");
        }
    }

    @FXML
    private void update_B_A(ActionEvent event) throws JRException {
        if (sale_Table.getSelectionModel().isEmpty()) {
            dialogs.error_Dialog(main_Pane, "First select the product.", "SELECT");
        } else {
            if (listView.isEmpty()) {
                dialogs.error_Dialog(main_Pane, "First add the product.", "PUECHASE LIST IS EMPTY");
            } else {
                this.Update();
            }
        }
    }

    @FXML
    private void update_On_Enter(KeyEvent event
    ) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (sale_Table.getSelectionModel().isEmpty()) {
                dialogs.error_Dialog(main_Pane, "First select the product.", "SELECT");
            } else {
                if (listView.isEmpty()) {
                    dialogs.error_Dialog(main_Pane, "First add the product.", "PURCHASE LIST IS EMPTY");
                } else {
                    this.Update();
                }
            }
        }
    }

    @FXML
    private void clear_On_Enter(KeyEvent event
    ) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.clear_Filed("");
        }
    }

    @FXML
    private void clear_B_A(ActionEvent event
    ) {
        this.clear_Filed("");

    }

    private void SelectCell() {

        sale_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Logic.TableList t = sale_Table.getItems().get(sale_Table.getSelectionModel().getSelectedIndex());
                    Id = t.getC1();
                    String check = logic.Check_Id("purchase_details", "C2", Id);
                    if (check.isEmpty()) {
                        dialogs.Notofication("Record", "This is your previous record, Cannot update it", "notification");
                        PurchasingController.this.clear_Filed("");
                    } else {
                        listView.clear();
                        list_Table.getItems().clear();
                        rs = logic.getTable_Data("purchase_details", Id, "C2", "C1");
                        while (rs.next()) {
                            listView.add(rs.getString("C3")
                                    + "-" + rs.getString("C4")
                                    + "-" + rs.getString("C5")
                                    + "-" + rs.getString("C6")
                                    + "-" + rs.getString("C7"));
                        }
                        rs.close();
                        PurchasingController.this.table_Upload();
                        total_Amount = t.getC4();
                        C8 = t.getC8();
                        C5 = t.getC5();
                        C7 = t.getC7();
                        check_Pay = C7;
                        r_Amount.setText(C7);
                        left_Amount.setText(C8);
                        shop_List.setValue(C5);
                        give = logic.getTableValue("purchase", "C9", "C1", Id);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );
    }

    public boolean validation_Call() {
        if (C5.isEmpty()) {
            text_5.setText("Pleas select !");
            return false;
        } else if (name.isEmpty()) {
            text_1.setText("Pleas select !");
            return false;
        } else if (unit.isEmpty()) {
            text_2.setText("Pleas select !");
            return false;
        } else if (cost.isEmpty()) {
            text_3.setText("Fiels is requir !");
            return false;
        } else if (quantity.isEmpty()) {
            text_4.setText("Pleas select !");
            return false;
        } else {
            return true;
        }
    }

    public void allValidations() {
        validator.Cost(sale_Amount, text_3);
        validator.Digite(p_Quantity, text_4);
        validator.Cost(r_Amount, text_6);

    }

    @FXML
    private void delete_B_A(ActionEvent event) throws JRException {
        if (dialogs.confirm_Dialog(main_Pane, "You want to delete this sale.")) {
            // ... user chose OK
            //deleting operation in Stock
            all_Updates.updateStock(Id, "Deleting", "purchase_details");
            logic.deleteData("purchase", "C1", Id);
            logic.deleteData("shop_a", "C5", Id);
            logic.deleteData("purchase_details", "C2", Id);
            this.setTable();
            this.loadData(m, y);
            this.clear_Filed("");
            all_Updates.Stock_All_updates("purchase");
        } else {
            this.clear_Filed("");
            // ... user chose CANCEL or closed the dialog

        }
    }

    public boolean AddNewItem() {
        if (this.validation_Call()) {
            String temp = scale.getScale_Amount(unit, previous_Scale, Double.valueOf(previous_Purchase));
            if (Double.valueOf(temp) > Double.valueOf(cost)) {
                dialogs.error_Dialog(main_Pane, "Sorry this sale make a Loss.", "LOSS");
                return false;
            } else {
                listView.add(this.name + "-" + this.quantity + "-" + this.cost + "-" + this.unit + "-" + this.total);
                //    System.out.println(name + "   " + quantity + "   " + cost + "    " + unit + "   " + total);
                this.table_Upload();
                this.clear_Filed("Half");
                return true;
            }
        } else {
            return false;
        }

    }

    @FXML
    private void add_Button_A(ActionEvent event) throws JRException {
        if (this.AddNewItem()) {
            product_List.requestFocus();
        }
        event.consume();
    }

    public void AddButtonEnter() {
        add_Button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (this.AddNewItem()) {
                    product_List.requestFocus();
                } else {
                }
            } else if (event.getCode().equals(KeyCode.SPACE)) {
                if (this.AddNewItem()) {
                    r_Amount.requestFocus();
                }
            }
            event.consume();
        });

    }

    @FXML
    private void qunatity_On_Enter(ActionEvent event) {
        this.AddNewItem();
        product_List.requestFocus();
        event.consume();
    }

    private void table_Upload() {
        Details.clear();
        double total_Cost = 0.0;
        name = "";
        quantity = "";
        unit = "";
        cost = "";
        total = "";
        for (int i = 0; i < listView.size(); i++) {
            String temp[] = listView.get(i).split("-");
            Button remove = new Button();
            remove.getStyleClass().add("remove_Button");
            remove.setId(temp[0] + temp[4]);
            remove.setOnAction(e -> {
                row_Id = ((Control) e.getSource()).getId();
                this.deleteRow();
                r_Amount.clear();
            });
            total_Cost += Double.valueOf(temp[4]);
            Details.add(new Logic.TableList(String.valueOf(i + 1), temp[0], temp[1], temp[2] + "  " + temp[3], temp[4], remove));
        }
        C6 = numberFormat.format(total_Cost);
        final_Cost.setText(C6);
        r_Amount.clear();
        C7 = "0.0";
        C8 = C6;
        left_Amount.setText(C8);
        list_Table.setItems(Details);
    }

    public void deleteRow() {
        for (int i = 0; i < listView.size(); i++) {
            String temp[] = listView.get(i).split("-");
            if ((temp[0] + temp[4]).equals(row_Id)) {
                listView.remove(i);
                this.setTable();
                this.table_Upload();
                break;
            }
        }
    }

    public void change_Product_Name() {
        product_List.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    previous_Sale = "";
                    name = newValue;
                    p_Quantity.setDisable(false);
                    scale_List.setDisable(false);
                    add_Button.setDisable(false);
                    try {
                        rs = logic.getTable_Data("product", newValue, "C3", "C2");
                        while (rs.next()) {
                            String C4 = rs.getString("C4"),
                                    C5 = rs.getString("C5");
                            sale_Amount.clear();
                            sale_Amount.setText(C4);
                            scale_List.setItems(scale.getScales(C5));
                            scale_List.setValue(C5);
                            previous_Sale = rs.getString("C6");
                            previous_Scale = C5;
                            previous_Purchase = C4;
                            cost = C4;
                            unit = C5;
                            quantity = "1";
                            p_Quantity.setText("1");
                            total = C4;
                            total_Cost.setText(C4);
                        }
                        rs.close();
                        p_Quantity.setText("1");

                    } catch (SQLException ex) {
                        Logger.getLogger(PurchasingController.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        );

    }

    public void change_Scale() {
        scale_List.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    unit = newValue;
                    if (!previous_Purchase.isEmpty()) {
                        String temp = scale.getScale_Amount(newValue, previous_Scale, Double.valueOf(previous_Purchase));
                        sale_Amount.setText(temp);
                        total_Cost.setText(temp);
                    }
                    p_Quantity.setText("1");
                }
            }

        }
        );

    }

    public void change_Shop() {
        shop_List.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                text_5.setText("");
                if (newValue != null) {
                    C5 = newValue;
                    String temp = logic.getTableValue("shop", "C8", "C3", newValue);
                    if (!temp.equals("")) {
                        advance = Double.valueOf(temp);
                    }
                    if (advance > 0) {
                        advance_Text.setText("Note: Advance is left (" + temp + " Rs)");
                    } else {
                        advance_Text.setText("");
                    }
                }
            }
        });

    }

    @FXML
    private void cost_On_Change(KeyEvent event) {
        this.p_Cost(sale_Amount.getText(), p_Quantity.getText());
    }

    @FXML
    private void quantity_On_Change(KeyEvent event) {
        this.p_Cost(sale_Amount.getText(), p_Quantity.getText());
    }

    //Total Product Cost
    public void p_Cost(String amount, String quantity) {
        if (!amount.isEmpty() && !quantity.isEmpty()) {
            String s = numberFormat.format(Double.valueOf(amount) * Double.valueOf(quantity));
            total_Cost.setText(s);
            total = s;
        } else if (!amount.isEmpty() && quantity.isEmpty()) {
            String s = numberFormat.format(Double.valueOf(amount));
            total_Cost.setText(s);
            total = s;
        } else if (amount.isEmpty() && !quantity.isEmpty()) {
            total_Cost.setText("");
            total = "0";

        }
        this.cost = amount;
        this.quantity = quantity;
    }

    @FXML
    private void pay_On_Change(KeyEvent event) {
        if (!r_Amount.getText().isEmpty()) {
            give = r_Amount.getText();
            double temp = 0;
            temp = Double.valueOf(final_Cost.getText()) - Double.valueOf(r_Amount.getText()) - advance;
            if (temp >= 0) {
                left_Amount.setText(numberFormat.format(temp));
                text_6.setText("");
                C8 = numberFormat.format(temp);//getVlaue
                C7 = numberFormat.format(Double.valueOf(r_Amount.getText()) + advance);
                advacne_Left = 0;
            } else {
                left_Amount.setText(numberFormat.format(temp));
                text_6.setText("Incresing !");
                advacne_Left = temp * -1;
                C7 = final_Cost.getText();
                C8 = "0.0";//getVlaue
            }
        } else {
            left_Amount.setText(final_Cost.getText());
            C8 = final_Cost.getText();
            C7 = "0.0";
        }

    }

    private void setProducts_Shops() {
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
            Logger.getLogger(PurchasingController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void print_Select_Acc(ActionEvent event) {
        this.purchase_Print(Id);
    }

    public void purchase_Print(String id) {
        try {
            String date = "";
            rs = logic.getTable_Data("purchase", id, "C1", "C1");

            while (rs.next()) {
                date = rs.getString("C4") + "/" + rs.getString("C3") + "/" + rs.getString("C2");
            }
            rs.close();
            String check = logic.Check_Id("purchase_details", "C2", Id);
            if (check.isEmpty()) {
                String data[] = {Id, "0344200515", "Wah Cantt", date};
                print.print("previous_Purchase_Bill", data);
            } else {
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
                print.print("item_Purchase", data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchasingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AllPrint() {
        if (data.isEmpty()) {
            dialogs.Notofication("Print", logic.getMonth(m) + "/" + y + " No record is avalable", "Wrong");
        } else {
            if (dialogs.confirm_Dialog(main_Pane, "Do you want a print.")) {
                String data[] = {m, y};
                print.print("all_Purchase", data);
            }
        }
    }

    @FXML
    private void all_Print(ActionEvent event) {
        this.AllPrint();
    }

    @FXML
    private void print_All_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.AllPrint();
        }
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

    @FXML
    private void date_On_Change(ActionEvent event) {
        if (date_Picker.getValue().toString().isEmpty()) {

        } else {
            String temp[] = date_Picker.getValue().toString().split("-");
            String year = temp[0],
                    month = temp[1],
                    day = temp[2];
            m = month;
            y = year;
            this.setTable();
            this.loadData(m, y);
        }
    }
}
