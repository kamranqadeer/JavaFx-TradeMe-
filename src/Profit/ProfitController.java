/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profit;

import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.all_Updates;
import Logic.kk_Logic;
import Logic.print_Data;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ProfitController implements Initializable {

    kk_Logic logic = new kk_Logic();
    MainDialogs dialog = new MainDialogs();
    all_Updates update = new all_Updates();
    String stock_Id = "";
    print_Data print = new print_Data();
    double total_Amount = 0.0;
    Connection con = null;
    String month = "", year = "", day = "", p_Name = "",
            select_Day = "",
            select_Month = "",
            select_Year = "";
    ObservableList<String> products = FXCollections.observableArrayList();
    private ObservableList<Logic.TableList> data1;
    private ObservableList<Logic.TableList> data2;
    private ObservableList<Logic.TableList> Details;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    ResultSet rs = null, rs2 = null;
    PreparedStatement pst = null;
    @FXML
    private StackPane main_Pane;
    @FXML
    private SplitPane split_Pane;
    @FXML
    private AnchorPane pane_1;
    @FXML
    private AnchorPane pane_2;
    @FXML
    private DatePicker date_Picker;
    @FXML
    private ScrollPane scroll_Pane1;
    @FXML
    private TableView<Logic.TableList> month_Table;
    @FXML
    private TableColumn<Logic.TableList, String> month_Day_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> month_Profit_Coll;
    @FXML
    private ScrollPane scroll_Pane2;
    @FXML
    private TableView<Logic.TableList> day_table;
    @FXML
    private TableColumn<Logic.TableList, String> day_No_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> day_Item_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> day_Quantity_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> day_Cost_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> day_Total_Coll;
    @FXML
    private TableColumn<Logic.TableList, String> day_Profit_Coll;
    @FXML
    private StackedBarChart<String, Number> today_Chart;
    @FXML
    private StackedBarChart<String, Number> month_Chart;
    @FXML
    private JFXButton print_Button;
    @FXML
    private SplitPane split_Pane2;
    @FXML
    private AnchorPane pane_2_1;
    @FXML
    private AnchorPane pane_2_2;
    @FXML
    private Text date_Text;
    @FXML
    private Text day_Text;
    @FXML
    private Text total_Profit_Text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data1 = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        Details = FXCollections.observableArrayList();
        //fixed Main
        pane_1.maxHeightProperty().bind(split_Pane.heightProperty().multiply(0.10));
        pane_2.maxHeightProperty().bind(split_Pane.heightProperty().multiply(0.90));
        //fixed splitpan
        pane_2_1.maxWidthProperty().bind(split_Pane2.widthProperty().multiply(0.70));
        pane_2_2.maxWidthProperty().bind(split_Pane2.widthProperty().multiply(0.30));
        //bind table height
        month_Table.prefHeightProperty().bind(scroll_Pane1.heightProperty());
        //bind table height
        day_table.prefHeightProperty().bind(scroll_Pane2.heightProperty());
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Calendar cal1 = Calendar.getInstance();
        day = dateFormat1.format(cal1.getTime());
        month = dateFormat2.format(cal1.getTime());
        year = dateFormat3.format(cal1.getTime());
        select_Day = day;
        select_Month = month;
        select_Year = year;
        //set month and day
        date_Text.setText(logic.getMonth(month) + " " + year);
        day_Text.setText("Day:  " + day);
        //add list view
        this.update_Profit(month, year);
        //select Cell
        this.SelectCell();
        //setting month table
        this.set_Month_Table();
        //load month
        this.loadMonth(month, year);
        //setting day table
        this.set_Day_Table();
        //load day table
        this.loadDay(day);
    }

    public void set_Month_Table() {

        month_Day_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        month_Profit_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));

    }

    public void set_Day_Table() {
        day_No_Coll.setCellValueFactory(new PropertyValueFactory<>("C0"));
        day_Item_Coll.setCellValueFactory(new PropertyValueFactory<>("C1"));
        day_Quantity_Coll.setCellValueFactory(new PropertyValueFactory<>("C2"));
        day_Cost_Coll.setCellValueFactory(new PropertyValueFactory<>("C3"));
        day_Total_Coll.setCellValueFactory(new PropertyValueFactory<>("C4"));
        day_Profit_Coll.setCellValueFactory(new PropertyValueFactory<>("C5"));
    }

    public void loadMonth(String month, String year) {
        total_Amount = 0.0;
        month_Chart.getData().clear();
        data1.clear();
        //set chart
        month_Chart.setTitle(logic.getMonth(month) + " " + year + " Profit");
        XYChart.Series series = new XYChart.Series();
        series.setName("Showing how much you earn");
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `profit` Where C3='" + month + "' AND C4='" + year + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                String C2 = rs.getString("C2");
                String C5 = rs.getString("C5");
                data1.add(new Logic.TableList(C2, C5 + "  Rs"));
                //setting chart 
                series.getData().add(new XYChart.Data("Day:" + C2 + "\n" + C5 + " Rs", Double.valueOf(C5)));
                total_Amount = total_Amount + Double.valueOf(C5);
            }
            total_Profit_Text.setText(numberFormat.format(total_Amount) + "  Rs");
        } catch (SQLException ex) {
            Logger.getLogger(ProfitController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
        month_Table.setItems(data1);
        month_Chart.getData().add(series);
    }

    public void loadDay(String day) {
        products.clear();
        today_Chart.getData().clear();
        data2.clear();
        day_table.getItems().clear();
        day_Text.setText("Day:  " + day);

        int j = 0;
        for (int i = 0; i < Details.size(); i++) {
            String temp[] = Details.get(i).getC0().split(" ");
            if (temp[0].equals(day)) {
                data2.addAll(new Logic.TableList(String.valueOf(++j),
                        Details.get(i).getC2(),
                        Details.get(i).getC3(),
                        Details.get(i).getC4(),
                        Details.get(i).getC5(),
                        Details.get(i).getC6()));
                //add data for chart
                products.add(Details.get(i).getC2());
            }
        }
        day_table.setItems(data2);
        this.load_Day_Chart(day);
    }

    public void load_Day_Chart(String day) {
        //set chart
        today_Chart.setTitle("Day " + day + " Profit");
        XYChart.Series series = new XYChart.Series();
        series.setName("Showing how much you earn");
        // total sale
        double total = 0.0;
        //remove dublicates
        Set<String> hashSet = new LinkedHashSet(products);
        ArrayList<String> removedDuplicates = new ArrayList(hashSet);
        for (int i = 0; i < removedDuplicates.size(); i++) {
            for (int j = 0; j < data2.size(); j++) {
                if (removedDuplicates.get(i).equals(data2.get(j).getC1())) {
                    total += Double.valueOf(data2.get(j).getC5());
                }
            }
            series.getData().add(new XYChart.Data(removedDuplicates.get(i) + "\n" + String.valueOf(total) + " Rs", total));
            total = 0.0;
        }
        today_Chart.getData().add(series);
    }

    private void SelectCell() {

        month_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Logic.TableList t = month_Table.getItems().get(month_Table.getSelectionModel().getSelectedIndex());
                ProfitController.this.loadDay(t.getC0());
                select_Day = t.getC0();

            }

        });
    }

    public void update_Profit(String month, String year) {
        Details.clear();
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select sale_details.C3 as name,"
                    + "sale_details.C4 as quantity,"
                    + "sale_details.C5 as p_Cost,"
                    + "sale_details.C6 as unit,"
                    + "sale_details.C7 as total,"
                    + "sale_details.C8 as profit,"
                    + "sale.C2 as day,"
                    + "sale.C3 as month from sale_details "
                    + "CROSS JOIN sale where sale.C1=sale_details.C2 AND sale.C3='" + month + "' AND sale.C4='" + year + "'"
            );
            rs = pst.executeQuery();
            while (rs.next()) {
                Details.addAll(new Logic.TableList(rs.getString("day"),
                        rs.getString("month"),
                        rs.getString("name"),
                        rs.getString("quantity"),
                        rs.getString("p_Cost") + "  " + rs.getString("unit"),
                        rs.getString("total"),
                        rs.getString("profit")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfitController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

   
    @FXML
    private void date_On_Change(ActionEvent event) {
        if (!date_Picker.getValue().toString().isEmpty()) {
            String temp[] = date_Picker.getValue().toString().split("-");
            String year = temp[0],
                    month = temp[1],
                    day = temp[2];
            select_Day = day;
            select_Month = month;
            select_Year = year;
            date_Text.setText(logic.getMonth(select_Month) + " " + year);
            day_Text.setText("Day:  " + day);
            this.loadMonth(month, year);
            this.update_Profit(month, year);
            this.loadDay(day);
        }
    }

    @FXML
    private void print_On_Click(ActionEvent event) {
        if (Details.isEmpty()) {
            dialog.Notofication("Print", date_Text.getText() + " No record is avalable", "Wrong");
        } else {

            if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
                String data[] = {select_Month, select_Year, logic.getMonth(select_Month) + " " + select_Year, total_Profit_Text.getText()};
                print.print("profit", data);
            }
        }
        event.consume();
    }

    @FXML
    private void print_On_Enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (Details.isEmpty()) {
            dialog.Notofication("Print", date_Text.getText() + " No record is avalable", "Wrong");
            } else {
                if (dialog.confirm_Dialog(main_Pane, "You want to take a print.")) {
                    String data[] = {select_Month, select_Year, logic.getMonth(select_Month) + " " + select_Year, total_Profit_Text.getText()};
                    print.print("profit", data);
                }
            }
        }
        event.consume();
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
