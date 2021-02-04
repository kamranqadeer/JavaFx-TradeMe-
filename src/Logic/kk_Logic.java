/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Home.SqlConection;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 *
 * @author KAMRAN QADEER
 */
public class kk_Logic {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    public ResultSet getFiledData(String t_Name, String f_Name) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select " + f_Name + " from `" + t_Name + "`");
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public String resentId(String t_Name, String f_Name) {
        String Value = "";
        try {
            Statement stmt;
            con = SqlConection.ConnectDB();
            stmt = con.createStatement();
            String sql1 = "SELECT " + f_Name + " FROM " + t_Name + " WHERE " + f_Name + " = (SELECT MAX(" + f_Name + ") FROM " + t_Name + ")";
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                Value = rs.getString("C1");
            }

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return Value;
    }

    public ResultSet getAllData(String t_Name) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `" + t_Name + "`");
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;

    }

    public ResultSet getTable_Data(String t_Name, String id, String C, String Dec_C) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `" + t_Name + "` Where " + C + "='" + id + "'ORDER BY " + Dec_C + " DESC");
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void uploadFieldData(String t_Name, String f_Name, String f_Value, String id_Name, String id) {
        try {
            con = SqlConection.ConnectDB();
            String sql = "update " + t_Name + " set"
                    + "  " + f_Name + "='" + f_Value + "' where " + id_Name + "='" + id + "'";

            pst = con.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    public ResultSet getAll_Table_Data(String t_Name) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `" + t_Name + "`");
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public String getTableValue(String t_Name, String f_Name, String f_Id, String id) {
        String Value = "";
        try {
            con = SqlConection.ConnectDB();
            Statement stmt;
            stmt = con.createStatement();
            String sql1 = "Select " + f_Name + " from " + t_Name + " where " + f_Id + "= '" + id + "'";
            rs = stmt.executeQuery(sql1);
            if (rs.next()) {
                Value = rs.getString(f_Name);
            }

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return Value;
    }

    public String Check_Id(String table_Name, String f_Name, String id) {
        String check = "";
        try {
            con = SqlConection.ConnectDB();
            Statement stmt;
            stmt = con.createStatement();
            String sql1 = "Select " + f_Name + " from " + table_Name + " where " + f_Name + "= '" + id + "'";
            rs = stmt.executeQuery(sql1);
            if (rs.next()) {
                check = rs.getString(f_Name);

            } else {
                check = "";
            }

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return check;
    }

    public void setScale(String s, ComboBox scale_List) {
        scale_List.getItems().clear();
        switch (s) {
            case "Per Pice":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per Pice", "Per Dozen");
                break;
            case "Per Dozen":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per Pice", "Per Dozen");
                break;
            case "Per gm":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per gm", "Per Kg");
                break;
            case "Per Kg":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per gm", "Per Kg");
                break;
            case "Per ml":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per ml", "Per Letter");
                break;
            case "Per Letter":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per ml", "Per Letter");
                ;
                break;
            case "Per Can":
                // code block
                scale_List.setValue(s);
                scale_List.getItems().addAll("Per Can");
                break;
            default:
            // code block
        }

    }

    public String setAmount(String scale, String v1, String v2) {
        if (v1.isEmpty()) {
            v1 = "0";
        } else if (v2.isEmpty()) {
            v2 = "0";
        }
        Double a1 = Double.valueOf(v1);
        Double a2 = Double.valueOf(v2);
        Double result = 0.00;
        switch (scale) {
            case "Per Pice":
                // code block
                result = a1 * a2;
                break;
            case "Per Dozen":
                // code block
                result = a1 * a2 * 12;

                break;
            case "Per gm":
                // code block
                result = a1 * a2;

                break;
            case "Per Kg":
                // code block
                result = a1 * a2 * 1000;

                break;
            case "Per ml":
                // code block
                result = a1 * a2;

                break;
            case "Per Letter":
                // code block
                result = a1 * a2 * 1000;

                ;
                break;
            case "Per Can":
                // code block
                result = a1 * a2;
                break;
            default:
            // code block
        }
        return numberFormat.format(result);
    }

    public void setTotal(JFXTextField total_Cost, ListView<String> list) {
        String s = "0";
        List<String> numbers = list.getItems();

        String listString = String.join(",", numbers);
        String list_Data[] = listString.split(",|\\            |\\*|\\=");

        for (int i = 0; i < list_Data.length / 4; i++) {
            s = String.valueOf(Double.valueOf(list_Data[i * 4 + 3]) + Double.valueOf(s));
        }
        if (s != "0") {
            total_Cost.setText(s);
        } else {
            total_Cost.setText("Total Cost");
        }

    }

    public String setCost(String scale, String v1) {
        if (v1.isEmpty()) {
            v1 = "0";
        }
        Double a1 = Double.valueOf(v1);
        Double result = 0.00;
        if (scale != null) {
            switch (scale) {
                case "Per Pice":
                    // code block
                    result = a1;
                    break;
                case "Per Dozen":
                    // code block
                    result = a1 * 12;

                    break;
                case "Per gm":
                    // code block
                    result = a1;

                    break;
                case "Per Kg":
                    // code block
                    result = a1 * 1000;

                    break;
                case "Per ml":
                    // code block
                    result = a1;

                    break;
                case "Per Letter":
                    // code block
                    result = a1 * 1000;

                    break;
                case "Per Can":
                    // code block
                    result = a1;
                    break;
                default:
                // code block
            }
        }
        return numberFormat.format(result);
    }

    public void add_update_Product(String p_Id, String p_Group_Name, String new_Amount, String pre_Amount) {
        try {
            con = SqlConection.ConnectDB();
            String sq = "insert into product_update(C2,C3,C4,C5)values('"
                    + p_Id
                    + "','" + p_Group_Name
                    + "','" + new_Amount
                    + "','" + pre_Amount + "')";
            pst = con.prepareStatement(sq);
            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    public void shop_Acc(String C3, String C4, String C5) {
        try {
            con = SqlConection.ConnectDB();

            String sq = "insert into shop_a(C3,C4,C5)values('"
                    + C3
                    + "','" + C4
                    + "','" + C5 + "')";
            pst = con.prepareStatement(sq);
            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        // System.out.println(type + "   " + s_Id + "   " + p_Id + "   " + item_Cost + "   " + Total_Cost + "  " + pay_Amount + "  " + left);
    }

    public void deleteData(String t_Name, String t_Coloum, String id) {

        String sq = "delete  from " + t_Name + " where " + t_Coloum + " = ?";
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement(sq);
            pst.setString(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    //get All Sale
    public String getSale(String name, String month_Year) {
        double total_Sale = 0;
        String Scale = "";
        try {
            rs = this.getAllData("sale");
            while (rs.next()) {
                String p_Name[] = rs.getString("C4").split("\n");
                String p_Quantity[] = rs.getString("C5").split("\n");
                String p_Scale[] = rs.getString("C6").split("\n|\\  ");
                String m_Y[] = rs.getString("C2").split("-");

                for (int i = 0; i < p_Name.length; i++) {
                    if (name.equals(p_Name[i]) && month_Year.equals(m_Y[1] + "_" + m_Y[0])) {
                        switch (p_Scale[i * 2 + 1]) {
                            case "Per Pice":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]) / 12;
                                Scale = "Dozen";
                                break;
                            case "Per Dozen":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]);
                                Scale = "Dozen";
                                break;
                            case "Per gm":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]) / 1000;
                                Scale = "Kg";
                                break;
                            case "Per Kg":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]);
                                Scale = "Kg";
                                break;
                            case "Per ml":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]) / 1000;
                                Scale = "Letter";
                                break;
                            case "Per Letter":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]);
                                Scale = "Letter";
                                break;
                            case "Per Can":
                                // code block
                                total_Sale += Double.valueOf(p_Quantity[i]);
                                Scale = "Can";
                                break;
                            default:
                                // code block
                                break;
                        }
                    }

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

        return numberFormat.format(total_Sale) + "  " + Scale;
    }
//get All Purchases

    public String getPurchase(String name, String month_Year) {
        double total_Purchase = 0;
        String Scale = "";
        try {
            rs = this.getAllData("purchase");
            while (rs.next()) {
                String p_Name[] = rs.getString("C4").split("\n");
                String p_Quantity[] = rs.getString("C5").split("\n");
                String p_Scale[] = rs.getString("C6").split("\n|\\  ");
                String m_Y[] = rs.getString("C2").split("-");
                for (int i = 0; i < p_Name.length; i++) {
                    if (name.equals(p_Name[i]) && month_Year.equals(m_Y[1] + "_" + m_Y[0])) {
                        switch (p_Scale[i * 2 + 1]) {
                            case "Per Pice":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]) / 12;
                                Scale = "Dozen";
                                break;
                            case "Per Dozen":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]);
                                Scale = "Dozen";
                                break;
                            case "Per gm":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]) / 1000;
                                Scale = "Kg";
                                break;
                            case "Per Kg":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]);
                                Scale = "Kg";
                                break;
                            case "Per ml":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]) / 1000;
                                Scale = "Letter";
                                break;
                            case "Per Letter":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]);
                                Scale = "Letter";
                                break;
                            case "Per Can":
                                // code block
                                total_Purchase += Double.valueOf(p_Quantity[i]);
                                Scale = "Can";
                                break;
                            default:
                                // code block
                                break;
                        }
                    }

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return numberFormat.format(total_Purchase) + "  " + Scale;
    }

    //String Left Cost
    public String getLeft_Cost(String name, double Amount) {
        double total_Amount = 0;
        try {
            rs = getAllData("product");
            while (rs.next()) {
                String p_Name[] = rs.getString("C4").split("\n");
                for (int i = 0; i < p_Name.length; i++) {
                    if (name.equals(rs.getString("C3"))) {
                        switch (rs.getString("C5")) {
                            case "Per Pice":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * 12 * Amount;
                                break;
                            case "Per Dozen":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * Amount;
                                break;
                            case "Per gm":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * 1000 * Amount;
                                break;
                            case "Per Kg":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * Amount;
                                break;
                            case "Per ml":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * 1000 * Amount;
                                break;
                            case "Per Letter":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * Amount;
                                break;
                            case "Per Can":
                                // code block
                                total_Amount = Double.valueOf(rs.getString("C4")) * Amount;
                                break;
                            default:
                                // code block
                                break;
                        }
                    }

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return numberFormat.format(total_Amount);

    }

    //get Month
    public String getMonth(String m) {
        switch (m) {
            case "01":
                // code block
                return "January";
            case "02":
                // code block
                return "February";

            case "03":
                // code block
                return "March";
            case "04":
                // code block
                return "April";
            case "05":
                // code block
                return "May";
            case "06":
                // code block
                return "June";
            case "07":
                // code block
                return "July";
            case "08":
                // code block
                return "August";
            case "09":
                // code block
                return "September";
            case "10":
                // code block
                return "October";
            case "11":
                // code block
                return "November";
            default:
                // code block
                return "December";
        }
    }

    public void AddPurchase(String t_Name, String C3, String C4, String C5, String C6, String C7, String C8, String C9, String C10, String C11) {
        try {
            con = SqlConection.ConnectDB();
            String sq = "insert into " + t_Name + "(C3,C4,C5,C6,C7,C8,C9,C10,C11)values('"
                    + C3
                    + "','" + C4
                    + "','" + C5
                    + "','" + C6
                    + "','" + C7
                    + "','" + C8
                    + "','" + C9
                    + "','" + C10
                    + "','" + C11 + "')";
            pst = con.prepareStatement(sq);
            pst.execute();

        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }

    }

    //set Table Size
    public static void Table_Resize(TableView<Logic.TableList> tableView, double value) {
        //Set the right policy
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().stream().forEach((column)
                -> {
            //Minimal width = columnheader
            Text t = new Text(column.getText());
            double max = t.getLayoutBounds().getWidth();
            for (int i = 0; i < tableView.getItems().size(); i++) {
                //cell must not be empty
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if (calcwidth > max) {
                        max = calcwidth;
                    }
                }
                column.setPrefWidth(max + value);
            }
            //set the new max-widht with some extra space
        });
    }

    //set Tabel coloum size
    public static void autoResizeColumns(TableView<Logic.TableList> tableView) {
        //Set the right policy
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().stream().forEach((column)
                -> {
            //Minimal width = columnheader
            Text t = new Text(column.getText());
            double max = t.getLayoutBounds().getWidth();
            for (int i = 0; i < tableView.getItems().size(); i++) {
                //cell must not be empty
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if (calcwidth > max) {
                        max = calcwidth;
                    }
                }
                column.setPrefWidth(max + 55.0d);
            }
            //set the new max-widht with some extra space
        });
    }

    //left Shop
    public ResultSet get_Left(String t_Name, String field1, String field2, String group_Filed) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("SELECT " + field1 + ",SUM(" + field2 + ") as sum from " + t_Name + " Group By " + group_Filed);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(kk_Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
