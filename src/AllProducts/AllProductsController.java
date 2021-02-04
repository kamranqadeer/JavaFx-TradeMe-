/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllProducts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import Home.SqlConection;
import Dialogs.MainDialogs;
import Logic.Scales;
import Logic.Validator;
import Logic.all_Updates;
import Logic.kk_Logic;
import Logic.print_Data;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class AllProductsController implements Initializable {

    MainDialogs dialogs = new MainDialogs();
    //geting Scales
    Scales scale = new Scales();
    //All Values
    String C3 = "",
            C4 = "",
            C5 = "",
            C6 = "",
            C7 = "",
            name = "",
            product_Id = "",
            Id = "",
            previous_Sale = "",
            previous_Purchase = "",
            previous_Scale = "",
            previous_Quailty = "";
    //All Updates
    all_Updates update_Product = new all_Updates();
    boolean check = false;
    Connection con = null;
    MainDialogs dialog = new MainDialogs();
    Validator validator = new Validator();
    ResultSet rs = null;
    PreparedStatement pst = null;
    kk_Logic logic = new kk_Logic();
    print_Data print_Data = new print_Data();
    private ObservableList<Logic.TableList> data;
    private ObservableList<Logic.TableList> data2;
    ObservableList<String> group_Suggession = FXCollections.observableArrayList();
    ObservableList<String> quality = FXCollections.observableArrayList("Class A", "Calss B", "Class C", "Class D");
    @FXML
    private TableView<Logic.TableList> product_Table;
    @FXML
    private TableView<Logic.TableList> list_Table;
    @FXML
    private JFXTextField product_Name;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton clear;
    @FXML
    private TableColumn<Logic.TableList, String> serial_Number;
    @FXML
    private TableColumn<Logic.TableList, String> p_Id;
    @FXML
    private TableColumn<Logic.TableList, String> date;
    @FXML
    private TableColumn<Logic.TableList, String> p_Name;
    @FXML
    private TableColumn<Logic.TableList, String> p_Quality;
    @FXML
    private TableColumn<Logic.TableList, String> p_Scale;
    @FXML
    private TableColumn<Logic.TableList, String> purchase_Cost;
    @FXML
    private TableColumn<Logic.TableList, String> sale_Cost;
    @FXML
    private ComboBox p_Quality_List;
    @FXML
    private ComboBox scale_List;
    @FXML
    private MenuItem delete_Button;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private StackPane main_Pane;
    @FXML
    private JFXTextField searchFiled;
    @FXML
    private JFXTextField sale_Amount;
    @FXML
    private JFXTextField product_Group;
    @FXML
    private JFXButton print_updat;
    @FXML
    private JFXButton print_All;
    @FXML
    private JFXButton save_Button;
    @FXML
    private JFXTextField purchase_Amount;
    @FXML
    private ScrollPane scroll_Pane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private Text text2;
    @FXML
    private Text text1;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;
    @FXML
    private Text text6;
    @FXML
    private TableColumn<Logic.TableList, String> listDate;
    @FXML
    private TableColumn<Logic.TableList, String> listName;
    @FXML
    private TableColumn<Logic.TableList, String> listNo;
    @FXML
    private TableColumn<Logic.TableList, String> newCost;
    @FXML
    private TableColumn<Logic.TableList, String> previousCost;
    @FXML
    private MenuItem notification;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Cloase Connection
        con = SqlConection.ConnectDB();
        //Set Scale
        scale_List.setItems(scale.getAllScale());
        //bind table height
        product_Table.prefHeightProperty().bind(scroll_Pane.heightProperty());
        //fixed splitpan
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.65));
        data = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        p_Quality_List.setItems(quality);
        setTable();
        this.setListTabel();
        loadData();
        this.Add_update_List("");
        SelectCell();
        this.allValidations();
        this.search();
        //adding suggession
        TextFields.bindAutoCompletion(product_Group, group_Suggession);

    }

    //    SetTable
    public void setTable() {
        serial_Number.setCellValueFactory(new PropertyValueFactory<>("C0"));
        p_Id.setCellValueFactory(new PropertyValueFactory<>("C1"));
        date.setCellValueFactory(new PropertyValueFactory<>("C2"));
        p_Name.setCellValueFactory(new PropertyValueFactory<>("C3"));
        purchase_Cost.setCellValueFactory(new PropertyValueFactory<>("C4"));
        p_Scale.setCellValueFactory(new PropertyValueFactory<>("C5"));
        sale_Cost.setCellValueFactory(new PropertyValueFactory<>("C6"));
        p_Quality.setCellValueFactory(new PropertyValueFactory<>("C7"));
    }

    public void setListTabel() {
        listNo.setCellValueFactory(new PropertyValueFactory<>("C0"));
        listDate.setCellValueFactory(new PropertyValueFactory<>("C1"));
        listName.setCellValueFactory(new PropertyValueFactory<>("C2"));
        newCost.setCellValueFactory(new PropertyValueFactory<>("C3"));
        previousCost.setCellValueFactory(new PropertyValueFactory<>("C4"));
    }

    public void Save() {
        if (this.validate_Call()) {
            try {
                String id = logic.Check_Id("product", "C3", product_Group.getText().trim() + " " + product_Name.getText().trim());
                if (!id.isEmpty()) {
                    dialog.error_Dialog(main_Pane, "Sorry this Product already exist.", "DUBLICATE");
                } else {
                    //geting one pice cost if scale is Dozen
                    C3 = product_Group.getText().trim() + " " + product_Name.getText().trim();
                    C4 = purchase_Amount.getText();
                    C5 = scale_List.getSelectionModel().getSelectedItem().toString();
                    C6 = sale_Amount.getText().toString();
                    C7 = p_Quality_List.getSelectionModel().getSelectedItem().toString();
                    if (Double.valueOf(C4) > Double.valueOf(C6)) {
                        dialog.error_Dialog(main_Pane, "Sale amount must be more than purchasing amount.", "WRONG INPUT");
                    } else {
                        if (dialog.confirm_Dialog(main_Pane, "You want to Add this " + C3 + " product in databse.")) {

                            con = SqlConection.ConnectDB();
                            String sq = "insert into product(C3,C4,C5,C6,C7,C8)values('"
                                    + C3
                                    + "','" + C4
                                    + "','" + C5
                                    + "','" + C6
                                    + "','" + C7
                                    + "','" + "10" + "')";
                            pst = con.prepareStatement(sq);
                            pst.execute();
                            loadData();
                            setTable();
                            clear_Filed();
                        }
                    }
                }

            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } finally {
                this.CloseConnection();
            }
        }
    }

    @FXML
    private void save_Button_Acction(ActionEvent event) {
        this.Save();

    }

    @FXML
    private void save_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.Save();
        }
    }

    public void Update() {
        try {

            if (this.validate_Call()) {
                //geting one pice cost if scale is Dozen
                C3 = product_Group.getText().trim() + " " + product_Name.getText().trim();
                C4 = purchase_Amount.getText();
                C5 = scale_List.getSelectionModel().getSelectedItem().toString();
                C6 = sale_Amount.getText().toString();
                C7 = p_Quality_List.getSelectionModel().getSelectedItem().toString();
                if (Double.valueOf(C4) > Double.valueOf(C6)) {
                    dialog.error_Dialog(main_Pane, "Sale amount must be more than purchasing amount.", "LOSS");
                } else {
                    if (C4.equals(previous_Purchase) && C5.equals(previous_Scale) && C6.equals(previous_Sale) && C7.equals(previous_Quailty)) {
                        dialog.error_Dialog(main_Pane, "No data is change.", "UPDATE");
                    } else {
                        if (dialog.confirm_Dialog(main_Pane, "Do you want to update this product " + C3 + ".")) {
                            con = SqlConection.ConnectDB();
                            String sql = "update product set C4='" + C4
                                    + "',C5='" + C5
                                    + "',C6='" + C6
                                    + "',C7='" + C7
                                    + "' where C1='" + product_Id + "'";
                            pst = con.prepareStatement(sql);
                            pst.executeUpdate();
                            setTable();
                            loadData();
                            if (!C4.equals(previous_Purchase)) {
                                update_Product.Item_Update_Coll(
                                        product_Id,
                                        C3,
                                        C4 + "  " + C5,
                                        previous_Purchase + "  " + previous_Scale);
                            }
                            clear_Filed();
                        }
                    }

                }

            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    @FXML
    private void update_Button(ActionEvent event) {
        this.Update();
    }

    @FXML
    private void update_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.Update();
        }
    }

    public void loadData() {
        data.clear();
        try {
            rs = logic.getAllData("product");
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
                String split[] = C3.split(" ");
                group_Suggession.add(split[0]);
                data.add(new Logic.TableList(no, C1, C2, C3, C4, C5, C6, C7));
                check = true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
        product_Table.setItems(data);
        logic.autoResizeColumns(product_Table);
    }

    @FXML
    private void clear_Button(ActionEvent event) {
        this.clear_Filed();
    }

    @FXML
    private void clear_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.clear_Filed();
        }
    }

    public void clear_Filed() {
        product_Name.clear();
        product_Group.clear();
        sale_Amount.clear();
        purchase_Amount.clear();
        p_Quality_List.setValue("Quality");
        scale_List.setValue("Scale");
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        C3 = "";
        C4 = "";
        C5 = "";
        C6 = "";
        C7 = "";
        product_Id = "";
        scale_List.setItems(scale.getAllScale());
        list_Table.getItems().clear();
        data2.clear();
    }

    private void SelectCell() {

        product_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logic.TableList t = product_Table.getItems().get(product_Table.getSelectionModel().getSelectedIndex());
                product_Id = t.getC1();
                String name_Group[] = t.getC3().split(" ");
                Id = t.getC3();
                AllProductsController.this.Add_update_List(Id);
                product_Name.setText(name_Group[1]);
                product_Group.setText(name_Group[0]);
                purchase_Amount.setText(t.getC4());
                sale_Amount.setText(t.getC6());
                scale_List.setValue(t.getC5());
                p_Quality_List.setValue(t.getC7());
                //setting previos values
                previous_Sale = t.getC6();
                previous_Purchase = t.getC4();
                previous_Scale = t.getC5();
                previous_Quailty = t.getC7();
                //Change Scale
                scale_List.setItems(scale.getScales(previous_Scale));
                scale_List.setValue(previous_Scale);

            }
        });
    }

    @FXML
    private void delete_Button_Ac(ActionEvent event) {
        Logic.TableList selectedForDeletion = product_Table.getSelectionModel().getSelectedItem();
        String sq = "delete  from product where C1 = ?";
        try {
            if (product_Name.getText().equals("")) {

                return;
            } else {
                con = SqlConection.ConnectDB();
                pst = con.prepareStatement(sq);
                pst.setString(1, product_Id);

                if (dialog.confirm_Dialog(main_Pane, "Do you want to delet.")) {
                    // ... user chose OK
                    pst.executeUpdate();
                    logic.deleteData("stock", "C3", Id);
                    loadData();
                    this.clear_Filed();
                } else {
                    this.clear_Filed();
                    // ... user chose CANCEL or closed the dialog

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(AllProductsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }

    }

    public void allValidations() {
        validator.productNameFiled(product_Group, text1);
        validator.productNameFiled(product_Name, text2);
        validator.Cost(purchase_Amount, text3);
        validator.Cost(sale_Amount, text4);

    }

    public boolean validate_Call() {
        if (product_Group.getText().isEmpty()) {
            text1.setText("Field is required !");
            return false;
        } else if (product_Name.getText().isEmpty()) {
            text2.setText("Field is required !");
            return false;
        } else if (purchase_Amount.getText().isEmpty()) {
            text3.setText("Field is required !");
            return false;
        } else if (sale_Amount.getText().isEmpty()) {
            text4.setText("Field is required !");
            return false;
        } else if (scale_List.getSelectionModel().isEmpty()) {
            text5.setText("Please select !");
            return false;
        } else if (p_Quality_List.getSelectionModel().isEmpty()) {
            text6.setText("Please select !");
            return false;
        } else {
            return true;
        }
    }

    private void search() {
        searchFiled.setOnKeyReleased(e -> {
            if (searchFiled.getText().equals("")) {
                try {
                    loadData();
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                data.clear();
                String sql1 = "Select * from product where C3 like '%" + searchFiled.getText() + "%'"
                        + "UNION Select * from product where C1 like '%" + searchFiled.getText() + "%'";

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
                        data.add(new Logic.TableList(no, C1, C2, C3, C4, C5, C6, C7));
                    }
                    product_Table.setItems(data);

                } catch (SQLException ex) {
                    Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    this.CloseConnection();
                }
            }
        });
    }

    public void Add_update_List(String id) {
        try {
            list_Table.getItems().clear();
            data2.clear();
            rs = logic.getTable_Data("product_update", id, "C3", "C1");
            int i = 0;
            while (rs.next()) {
                if (++i > 30) {
                    logic.deleteData("product_update", "C1", rs.getString("C1"));
                } else {
                    String no = String.valueOf(i);
                    String C1 = rs.getString("C1");
                    String C2 = rs.getString("C3");
                    String C3 = rs.getString("C4");
                    String C4 = rs.getString("C5");
                    data2.add(new Logic.TableList(no, C1, C2, C3, C4));

                }

            }
            list_Table.setItems(data2);
            logic.autoResizeColumns(list_Table);
        } catch (SQLException ex) {
            Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }

    }

    @FXML
    private void print_Update_Acction(ActionEvent event
    ) {
        String data[] = {Id, "03444200515", "h#cb429 Asifabad wah cantt"};
        if (Id == "") {
            dialog.error_Dialog(main_Pane, "Sorry first select the product.", "SELECT");

        } else {
            String name = logic.getTableValue("product_update", "C3", "C3", Id);
            if (name.isEmpty()) {
                dialog.error_Dialog(main_Pane, "Sorry no update is avalable.", "RECORD");
            } else {
                if (dialog.confirm_Dialog(main_Pane, "You want to take a print")) {
                    print_Data.print("product_Update", data);
                    this.clear_Filed();
                }
            }
        }

    }

    @FXML
    private void all_Print_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            String data[] = {product_Id, "03444200515", "h#cb429 Asifabad wah cantt"};
            if (product_Id == "") {
                dialog.error_Dialog(main_Pane, "Sorry first select the product.", "SELECT");
            } else {
                String name = logic.getTableValue("product_update", "C3", "C2", product_Id);
                if (name.isEmpty()) {
                    dialog.error_Dialog(main_Pane, "Sorry no update is avalable.", "NO RECORD");
                } else {
                    if (dialog.confirm_Dialog(main_Pane, "You want to take a print")) {
                        print_Data.print("product_Update", data);
                        this.clear_Filed();
                    }
                }
            }
        }
    }

    @FXML
    private void print_All_Acction(ActionEvent event) {
        if (check) {
            if (dialog.confirm_Dialog(main_Pane, "You want to take a print")) {
                print_Data.print("products", null);
                this.clear_Filed();
            }
        } else {
            dialog.error_Dialog(main_Pane, "Sorry first add the product.", "NO RECORD");
        }

    }

    @FXML
    private void print_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (check) {
                if (dialog.confirm_Dialog(main_Pane, "You want to take a print")) {
                    print_Data.print("products", null);
                    this.clear_Filed();
                }
            } else {
                dialog.error_Dialog(main_Pane, "Sorry first add the product.", "NO RECORD");
            }
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
    private void noti_Acction(ActionEvent event) {
        String previous_Limite = logic.getTableValue("product", "C8", "C3", Id);
        String max_Scale[] = scale.getMaxScale(previous_Scale).split(" ");
        TextInputDialog dialog = new TextInputDialog(previous_Limite);
        dialog.setTitle("Updating");
        dialog.setHeaderText(Id + "\nNotification alart on " + previous_Limite + max_Scale[1]);
        dialog.setContentText("Please enter limite in Digite");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        boolean check = false;
        if (result.isPresent() && result.get().matches("\\d*")) {
            System.out.println("Your name: " + result.get());
            check = true;
        }

// The Java 8 way to get the response value (with lambda expression).
        if (check) {
            result.ifPresent(name -> {

                try {
                    con = SqlConection.ConnectDB();
                    String sql = "update product set C8='" + name
                            + "' where C3='" + Id + "'";
                    pst = con.prepareStatement(sql);
                    pst.executeUpdate();
                    dialogs.Notofication("Update successfully", "Notification is updated on " + name + " " + max_Scale[1], "okay");

                } catch (SQLException ex) {
                    Logger.getLogger(AllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    this.CloseConnection();
                }
            }
            );
        } else {
            dialogs.error_Dialog(main_Pane, "You enter wrong input", "Update error.");
        }
    }

}
