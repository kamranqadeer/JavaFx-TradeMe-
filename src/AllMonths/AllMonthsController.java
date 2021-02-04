/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllMonths;

import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.Data;
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
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class AllMonthsController implements Initializable {

    String month = "",
            year = "";
    kk_Logic logic = new kk_Logic();
    all_Updates update = new all_Updates();
    MainDialogs dialog = new MainDialogs();
    BoxBlur blur = new BoxBlur(3, 3, 3);
    String month_Id = "";
    int s_Count = 5, p_Count = 5;
    print_Data print = new print_Data();
    Data p_Data = new Data();
    ObservableList<String> listView = FXCollections.observableArrayList();
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    Connection con = null;
    private ObservableList<Logic.TableList> data;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    ResultSet rs = null, rs2 = null;
    PreparedStatement pst = null;

    @FXML
    private JFXButton print_Button;
    @FXML
    private TableView<Logic.TableList> month_Table;
    @FXML
    private TableColumn<Logic.TableList, String> id_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> date_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> product_Name_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> no_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> purchase_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> purchase_Cost_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> sale_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> sale_Cost_Coll;
    @FXML
    private BarChart<String, Number> purchase_Chart;
    @FXML
    private BarChart<String, Number> sale_Chart;
    @FXML
    private JFXButton sale_Left;
    @FXML
    private JFXButton sale_Right;
    @FXML
    private JFXButton purchase_Left;
    @FXML
    private JFXButton purchase_Right;
    @FXML
    private JFXTextField searchFiled;
    @FXML
    private MenuItem print_Data;
    @FXML
    private SplitPane splitPane1;
    @FXML
    private GridPane split_1_Pane_1;
    @FXML
    private SplitPane splitPane2;
    @FXML
    private AnchorPane split_2_Pane1;
    @FXML
    private AnchorPane split_2_Pane_2;
    @FXML
    private ScrollPane scroll_Pane;
    @FXML
    private StackPane main_Pane;
    @FXML
    private DatePicker date_Picker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //bind table height
        month_Table.prefHeightProperty().bind(scroll_Pane.heightProperty());
        //fixed Main
        split_1_Pane_1.maxHeightProperty().bind(splitPane1.heightProperty().multiply(0.10));
        splitPane2.maxHeightProperty().bind(splitPane1.heightProperty().multiply(0.90));
        //fixed Inner
        split_2_Pane1.maxWidthProperty().bind(splitPane2.widthProperty().multiply(0.70));
        split_2_Pane_2.maxWidthProperty().bind(splitPane2.widthProperty().multiply(0.30));
        con = SqlConection.ConnectDB();
        data = FXCollections.observableArrayList();
        //settig date
        DateFormat dateFormat1 = new SimpleDateFormat("MM");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
        Calendar cal1 = Calendar.getInstance();
        month = logic.getMonth(dateFormat1.format(cal1.getTime()));
        year = dateFormat2.format(cal1.getTime());
        this.setTable();
        this.loadData(month + "_" + year);
        this.setChartScale(month + "_" + year);
        this.search();
        this.SelectCell();
    }

    public void setTable() {
        no_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        id_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        date_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        product_Name_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
        purchase_Coll.setCellValueFactory(new PropertyValueFactory<>("C4"));
        purchase_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C5"));
        sale_Coll.setCellValueFactory(new PropertyValueFactory<>("C6"));
        sale_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C7"));

    }

    public void loadData(String m_Y) {
        try {
            rs = logic.getTable_Data("all_month", m_Y, "C2", "C1");
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
                data.add(new Logic.TableList(C0, C1, C2, C3, C4, C5, C6, C7));

            }
            month_Table.setItems(data);
            logic.Table_Resize(month_Table, 65);
        } catch (SQLException ex) {
            Logger.getLogger(AllMonthsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPurchase_Chart(String id) {
        purchase_Chart.setTitle("Purchase Status");
        XYChart.Series series = new XYChart.Series();
        series.setName("  Showing top 5 Purchases");
        for (int i = 0; i < list1.size(); i++) {
            String temp[] = list1.get(i).split("_");
            if (temp[0].equals(id)) {
                series.getData().add(new XYChart.Data(temp[1], Double.valueOf(temp[2])));
            }
        }
        purchase_Chart.getData().add(series);
    }

    public void setSale_Chart(String id) {
        sale_Chart.setTitle("Sale Status");
        XYChart.Series series = new XYChart.Series();
        series.setName("  Showing top 5 Sales");
        for (int i = 0; i < list2.size(); i++) {
            String temp[] = list2.get(i).split("_");
            if (temp[0].equals(id)) {
                series.getData().add(new XYChart.Data(temp[1], Double.valueOf(temp[2])));
            }
        }
        sale_Chart.getData().add(series);
    }

    public void setChartScale(String m_Y) {
        ObservableList<Double> temp_List = FXCollections.observableArrayList();
        ObservableList<String> temp_Data = FXCollections.observableArrayList();
        int count = 1, id = 5;
        try {
            rs = logic.getTable_Data("all_month", m_Y, "C2", "C1");
            //setting purchase chart
            while (rs.next()) {
                String temp[] = rs.getString("C3").split(" ");
                String cost[] = rs.getString("C5").split("  ");
                temp_Data.add(temp[0] + "\n" + temp[1] + "_" + cost[0]);
                temp_List.add(Double.valueOf(cost[0]));

            }
            Collections.sort(temp_List, Collections.reverseOrder());
            for (int i = 0; i < temp_List.size(); i++) {
                for (int j = 0; j < temp_Data.size(); j++) {
                    String temp[] = temp_Data.get(j).split("_");
                    if (temp_List.get(i).equals(Double.valueOf(temp[1]))) {
                        if (count <= id) {
                            count++;
                            list1.add(id + "_" + temp_Data.get(j));
                        } else {
                            id += 5;
                            count++;
                            list1.add(id + "_" + temp_Data.get(j));
                        }
                    }
                }
                if (list1.size() == temp_List.size()) {
                    break;
                }
            }
            //setting sale chart
            temp_List.clear();
            temp_Data.clear();
            count = 1;
            id = 5;
            rs = logic.getTable_Data("all_month", m_Y, "C2", "C1");
            while (rs.next()) {

                String temp[] = rs.getString("C3").split(" ");
                String cost[] = rs.getString("C7").split("  ");
                temp_Data.add(temp[0] + "\n" + temp[1] + "_" + cost[0]);
                temp_List.add(Double.valueOf(cost[0]));
            }

            Collections.sort(temp_List, Collections.reverseOrder());
            for (int i = 0; i < temp_List.size(); i++) {
                for (int j = 0; j < temp_Data.size(); j++) {
                    String temp[] = temp_Data.get(j).split("_");
                    if (temp_List.get(i).equals(Double.valueOf(temp[1]))) {
                        if (count <= id) {
                            count++;
                            list2.add(id + "_" + temp_Data.get(j));
                        } else {
                            id += 5;
                            count++;
                            list2.add(id + "_" + temp_Data.get(j));
                        }
                    }
                }
                if (list2.size() == temp_List.size()) {
                    break;
                }
            }

            this.setSale_Chart("5");
            this.setPurchase_Chart("5");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AllMonthsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void print_All(ActionEvent event) {
        if (data.isEmpty()) {
            dialog.Notofication("Record", "No record in" + month + "_" + year + " is avalable", "Wrong");
        } else {
            if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
                String data[] = {month + "_" + year};
                print.print("all_Month", data);
            }
        }

    }

    @FXML
    private void p_Out(MouseEvent event) {
        purchase_Left.setVisible(false);
        purchase_Right.setVisible(false);
        purchase_Chart.setOpacity(1);
    }

    @FXML
    private void p_In(MouseEvent event) {
        purchase_Left.setVisible(true);
        purchase_Right.setVisible(true);
        purchase_Chart.setOpacity(0.4);
    }

    @FXML
    private void s_Out(MouseEvent event) {
        sale_Left.setVisible(false);
        sale_Right.setVisible(false);
        sale_Chart.setOpacity(1);
    }

    @FXML
    private void s_In(MouseEvent event) {
        sale_Left.setVisible(true);
        sale_Right.setVisible(true);
        sale_Chart.setOpacity(0.4);

    }

    @FXML
    private void p_Left(ActionEvent event) {
        if (s_Count > 5) {
            s_Count -= 5;
            purchase_Chart.getData().clear();
            this.setPurchase_Chart(String.valueOf(s_Count));
        }

    }

    @FXML
    private void p_Right(ActionEvent event) {
        if (s_Count < list1.size()) {
            s_Count += 5;
            purchase_Chart.getData().clear();
            this.setPurchase_Chart(String.valueOf(s_Count));
        }

    }

    @FXML
    private void s_Left(ActionEvent event) {
        if (p_Count > 5) {
            p_Count -= 5;
            sale_Chart.getData().clear();
            this.setSale_Chart(String.valueOf(p_Count));
        }
    }

    @FXML
    private void s_Right(ActionEvent event) {
        if (p_Count < list2.size()) {
            p_Count += 5;
            sale_Chart.getData().clear();
            this.setSale_Chart(String.valueOf(p_Count));
        }

    }

    private void search() {
        searchFiled.setOnKeyReleased(e -> {
            if (searchFiled.getText().equals("")) {
                data.clear();
                loadData(month + "_" + year);
            } else {
                data.clear();
                String sql1 = "Select * from all_month where C3 like '%" + searchFiled.getText() + "%' And C2='" + month + "_" + year + "'"
                        + "UNION Select * from all_month where C4 like '%" + searchFiled.getText() + "%' And C2='" + month + "_" + year + "'"
                        + "UNION Select * from all_month where C6 like '%" + searchFiled.getText() + "%' And C2='" + month + "_" + year + "'";
                try {

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
                        data.add(new Logic.TableList(C0, C1, C2, C3, C4, C5, C6, C7));

                    }
                    month_Table.setItems(data);

                } catch (SQLException ex) {
                    Logger.getLogger(AllMonthsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void SelectCell() {

        month_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Logic.TableList t = month_Table.getItems().get(month_Table.getSelectionModel().getSelectedIndex());
                AllMonthsController.this.month_Id = t.getC1();
            }
        });
    }

    @FXML
    private void print_Button_Ac(ActionEvent event) {
        if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
            String data[] = {month_Id};
            print.print("all_Month_Spec", data);
        }

    }

    @FXML
    private void date_On_Change(ActionEvent event) {
        if (date_Picker.getValue().toString().isEmpty()) {

        } else {
            String temp[] = date_Picker.getValue().toString().split("-");
            year = temp[0];
            month = logic.getMonth(temp[1]);
            data.clear();
            AllMonthsController.this.setTable();
            AllMonthsController.this.loadData(month + "_" + year);
            sale_Chart.getData().clear();
            purchase_Chart.getData().clear();
            list1.clear();
            list2.clear();
            AllMonthsController.this.setChartScale(month + "_" + year);

        }
    }

}
