/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author KAMRAN QADEER
 */
import Dialogs.MainDialogs;
import Home.SqlConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class all_Updates {

    MainDialogs dialogs = new MainDialogs();
    Connection con = null;
    ResultSet rs = null, rs2 = null;
    PreparedStatement pst = null;
    Scales scale = new Scales();
    DecimalFormat numberFormat = new DecimalFormat("#.0");
    kk_Logic logic = new kk_Logic();
    DateFormat dateFormat1 = new SimpleDateFormat("dd");
    DateFormat dateFormat2 = new SimpleDateFormat("MM");
    DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
    Calendar cal1 = Calendar.getInstance();
    String day = dateFormat1.format(cal1.getTime()),
            month = dateFormat2.format(cal1.getTime()),
            year = dateFormat3.format(cal1.getTime());

    //Month Data is updated
    public void adding_All_Month(String p_Name, String operation) {
        con = SqlConection.ConnectDB();
        double total = 0.0,
                total_Cost = 0.0;
        String max_Scale = "";
        try {
            String date = logic.getMonth(month) + "_" + year, check = "";
            pst = con.prepareStatement("Select C3 from `all_month` Where C2='" + date + "' AND C3='" + p_Name + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                check = rs.getString("C3");
            }
            if (check.isEmpty()) {
                String sq = "insert into all_month(C2,C3,C4,C5,C6,C7)values('"
                        + date
                        + "','" + p_Name
                        + "','" + "0"
                        + "','" + "0"
                        + "','" + "0"
                        + "','" + "0" + "')";
                pst = con.prepareStatement(sq);
                pst.execute();
            }
            if (operation.equals("purchase")) {
                pst = con.prepareStatement("Select purchase_details.C4 as quantity,"
                        + "purchase_details.C7 as total,"
                        + "purchase_details.C6 as unit from purchase_details "
                        + "CROSS JOIN purchase where purchase.C1=purchase_details.C2 AND purchase_details.C3='" + p_Name + "' AND purchase.C3='" + month + "' AND purchase.C4='" + year + "'"
                );
            } else {
                pst = con.prepareStatement("Select sale_details.C4 as quantity,"
                        + "sale_details.C7 as total,"
                        + "sale_details.C6 as unit from sale_details "
                        + "CROSS JOIN sale where sale.C1=sale_details.C2 AND sale_details.C3='" + p_Name + "' AND sale.C3='" + month + "' AND sale.C4='" + year + "'"
                );
            }

            rs = pst.executeQuery();
            while (rs.next()) {
                max_Scale = scale.getMaxScale(rs.getString("unit"));
                if (max_Scale.equals(rs.getString("unit"))) {
                    total = total + Double.valueOf(rs.getString("quantity"));
                } else {
                    int value = Integer.valueOf(logic.getTableValue("scale", "C3", "C2", max_Scale));
                    total = total + (Double.valueOf(rs.getString("quantity")) / value);
                }
                total_Cost = total_Cost + Double.valueOf(rs.getString("total"));
            }
            pst.close();
            rs.close();
            String sql = "";
            if (operation.equals("purchase")) {
                sql = "update all_month set C4='" + numberFormat.format(total) + "  " + max_Scale
                        + "',C5='" + numberFormat.format(total_Cost) + "  Rs"
                        + "' where C3='" + p_Name + "' AND C2='" + date + "'";
            } else {
                sql = "update all_month set C6='" + numberFormat.format(total) + "  " + max_Scale
                        + "',C7='" + numberFormat.format(total_Cost) + "  Rs"
                        + "' where C3='" + p_Name + "' AND C2='" + date + "'";
            }
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            pst.execute();
            pst.close();
            total = 0;
            total_Cost = 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Stock_All_updates(String operation) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `product`");
            rs2 = pst.executeQuery();
            while (rs2.next()) {
                this.adding_All_Month(rs2.getString("C3"), operation);
            }
            rs2.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Adding UpdateProduct Table Data
    public void Item_Update_Coll(String p_Id, String p_Group_Name, String new_Amount, String pre_Amount) {
        try {
            con = SqlConection.ConnectDB();
            String sq = "insert into product_update(C2,C3,C4,C5)values('"
                    + p_Id
                    + "','" + p_Group_Name
                    + "','" + new_Amount
                    + "','" + pre_Amount + "')";
            pst = con.prepareStatement(sq);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Shop Update
    public void shop_Update(String C3, String C4, String C5) {
        try {
            con = SqlConection.ConnectDB();
            String sq = "insert into shop_a(C3,C4,C5)values('"
                    + C3
                    + "','" + C4
                    + "','" + C5 + "')";
            pst = con.prepareStatement(sq);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //upload shop left filed data 
    public void leftShop(String t_Name, String f_Name, String f_Value, String id_Name, String id) {

        try {
            String sql = "update " + t_Name + " set"
                    + "  " + f_Name + "='" + f_Value + "  " + f_Name + "='" + f_Value + "' where " + id_Name + "='" + id + "'";

            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Adding Purchase and Delete Sale
    public void updateStock(String Id, String operation, String t_Name) {
        DecimalFormat numberFormat = new DecimalFormat("#.000");
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from  " + t_Name + " Where C2='" + Id + "'ORDER BY C1 DESC");
            rs = pst.executeQuery();
            while (rs.next()) {
                String p_Name = rs.getString("C3");
                //if product is not added
                if (operation.equals("Adding")) {
                    if (logic.getTableValue("stock", "C3", "C3", rs.getString("C3")).isEmpty()) {
                        String sq = "insert into stock(C1,C2,C3,C4,C5,C6,C7,C8)values('"
                                + month
                                + "','" + year
                                + "','" + rs.getString("C3")
                                + "','" + "0"
                                + "','" + "0"
                                + "','" + "0"
                                + "','" + "0"
                                + "','" + scale.getMaxScale(rs.getString("C6")) + "')";
                        con = SqlConection.ConnectDB();
                        pst = con.prepareStatement(sq);
                        pst.execute();
                        pst.close();
                    }
                }
                //getting previous amount
                double current_Month = 0,
                        previous_Month = 0,
                        total_Purchase = 0;
                String stock_Month = "",
                        stock_Year = "";
                pst = con.prepareStatement("Select * from  stock Where C3='" + rs.getString("C3") + "'ORDER BY C1 DESC");
                rs2 = pst.executeQuery();
                String main_Scale = "";
                while (rs2.next()) {
                    stock_Month = rs2.getString("C1");
                    stock_Year = rs2.getString("C2");
                    previous_Month = Double.valueOf(rs2.getString("C4"));
                    current_Month = Double.valueOf(rs2.getString("C5"));
                    main_Scale = rs2.getString("C8");
                }
                rs2.close();
                //adding stock
                if (operation.equals("Adding")) {
                    if (!stock_Month.equals(month) || !stock_Year.equals(year)) {
                        previous_Month = previous_Month + current_Month;
                        current_Month = 0;
                    }
                    if (rs.getString("C6").equals(main_Scale)) {
                        current_Month = current_Month + Double.valueOf(rs.getString("C4"));
                    } else {
                        //convert low scale to heigh
                        int value = Integer.valueOf(logic.getTableValue("scale", "C3", "C2", main_Scale));
                        current_Month = current_Month + (Double.valueOf(rs.getString("C4")) / value);
                    }
                    total_Purchase = current_Month + previous_Month;

                    //deleting stock
                } else {
                    double delete = 0.0;
                    if (rs.getString("C6").equals(main_Scale)) {
                        delete = Double.valueOf(rs.getString("C4"));

                    } else {
                        //convert low scale to heigh
                        int value = Integer.valueOf(logic.getTableValue("scale", "C3", "C2", main_Scale));
                        delete = Double.valueOf(rs.getString("C4")) / value;
                    }
                    if (delete > previous_Month) {
                        delete = delete - previous_Month;
                        previous_Month = 0;
                        current_Month = current_Month - delete;
                    } else {
                        previous_Month = previous_Month - delete;
                    }
                    total_Purchase = current_Month + previous_Month;
                }
                //left purchase cost
                double total_Cost = 0.0;
                pst = con.prepareStatement("Select * from  product Where C3='" + p_Name + "'ORDER BY C1 DESC");
                rs2 = pst.executeQuery();
                while (rs2.next()) {
                    if (rs2.getString("C5").equals(main_Scale)) {
                        total_Cost = Double.valueOf(rs2.getString("C4"));
                    } else {
                        String temp = scale.getScale_Amount(main_Scale, rs2.getString("C5"), Double.valueOf(rs2.getString("C4")));
                        total_Cost = Double.valueOf(temp);
                    }
                }
                rs2.close();

                String sql = "update stock set C1='" + month
                        + "',C2='" + year
                        + "',C4='" + numberFormat.format(previous_Month)
                        + "',C5='" + numberFormat.format(current_Month)
                        + "',C6='" + numberFormat.format(total_Purchase)
                        + "',C7='" + numberFormat.format(total_Purchase * total_Cost)
                        + "' where C3='" + p_Name + "'";
                con = SqlConection.ConnectDB();
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
                pst.execute();
                pst.close();

                if (t_Name.equals("sale_details")) {
                    String temp[] = scale.getMaxScale(main_Scale).split(" ");
                    String limit = logic.getTableValue("product", "C8", "C3", rs.getString("C3"));
                    if (total_Purchase < Integer.valueOf(limit)) {
                        dialogs.Notofication("Stock",
                                rs.getString("C3") + " only left in stock " + numberFormat.format(total_Purchase) + " "
                                + temp[1],
                                "notification");
                    }
                }
                total_Purchase = 0.0;
                current_Month = 0.0;
                total_Purchase = 0.0;

            }
            rs.close();
            rs2.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //profit update
    public void update_Profit(String profit) {
        try {
            String check = "";
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select C5 from `profit` Where C2='" + day + "' AND C3='" + month + "' AND C4='" + year + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                check = rs.getString("C5");
            }
            if (check.isEmpty()) {
                String sq = "insert into profit(C2,C3,C4,C5)values('"
                        + day
                        + "','" + month
                        + "','" + year
                        + "','" + profit + "')";
                pst = con.prepareStatement(sq);
                pst.execute();
            } else {
                double amount = 0.0;
                pst = con.prepareStatement("Select sale_details.C8 as profit,"
                        + "sale.C2 as day,"
                        + "sale.C3 as month from sale_details "
                        + "CROSS JOIN sale where sale.C1=sale_details.C2 AND sale.C3='" + month + "' AND sale.C4='" + year + "'"
                );
                rs = pst.executeQuery();
                while (rs.next()) {
                    String temp[] = rs.getString("day").split(" ");
                    if (temp[0].equals(day)) {
                        amount = Double.valueOf(rs.getString("profit")) + amount;
                    }
                }
                String sql = "update profit set C5='" + numberFormat.format(amount)
                        + "' where C2='" + day + "' AND C3='" + month + "' AND C4='" + year + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //pay full amount

    public void Full_Update(Double recived, String check, String shop_Name) {
        double left = 0;
        try {
//            con = SqlConection.ConnectDB();
//            pst = con.prepareStatement("Select * from "+check+" Where C5='" + shop_Name + "'");
//            rs = pst.executeQuery();
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
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(all_Updates.class
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
            Logger.getLogger(all_Updates.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }

    //update products
    public void Update_Products(String id, String check, String product, String newCost, String newScale, String quantity, String total_Cost) {
        String purchase_Cost = "0.0",
                sale_Cost = "0.0";
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `product` Where C3='" + product + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                String product_Id = rs.getString("C1"),
                        pre_Scale = rs.getString("C5"),
                        pre_Purchase = rs.getString("C4");
                String stock_Check = logic.Check_Id("stock", "C3", rs.getString("C3"));
                switch (check) {
                    case "purchase": {
                        double diff = Double.valueOf(rs.getString("C6")) - Double.valueOf(rs.getString("C4"));
                        if (!newScale.equals(rs.getString("C5")) || !newCost.equals(rs.getString("C4"))) {
                            //getting scale max value
                            diff = Double.valueOf(scale.getScale_Amount(newScale, rs.getString("C5"), diff));
                            if (stock_Check.equals(null)) {
                                purchase_Cost = newCost;
                            } else {
                                purchase_Cost = this.get_Avrage_Purchase(product, newCost, newScale, quantity, total_Cost);
                            }
                            sale_Cost = numberFormat.format(Double.valueOf(purchase_Cost) + diff);
                            this.product(product_Id, purchase_Cost, sale_Cost, newScale);
                            //update purchase 
                            this.Item_Update_Coll(id,
                                    product,
                                    newCost + "  " + newScale,
                                    scale.getScale_Amount(newScale, pre_Scale, Double.valueOf(pre_Purchase)) + "  " + newScale
                            );
                        }

                        break;
                    }
                    case "sale": {
                        if (!newScale.equals(rs.getString("C5")) || !newCost.equals(rs.getString("C6"))) {
                            // purchase_Cost = newCost;
                            purchase_Cost = scale.getScale_Amount(newScale, rs.getString("C5"), Double.valueOf(rs.getString("C4")));
                            sale_Cost = newCost;
                            this.product(product_Id, purchase_Cost, sale_Cost, newScale);
                        }
                        break;
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
        }
    }
    //update purchase Left and Give

    public void product(String Id, String v1, String v2, String s) {
        try {
            con = SqlConection.ConnectDB();
            String sql = "update product set"
                    + "  C4='" + v1
                    + "',C5='" + s
                    + "',C6='" + v2
                    + "' where C1='" + Id + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //geting avrage purchaseCost
    public String get_Avrage_Purchase(String p_Name, String newCost, String newScale, String quantity, String total_Cost) {
        String purchase_Cost = newCost;
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `stock` Where C3='" + p_Name + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                if (Double.valueOf(rs.getString("C6")) == 0) {
                    purchase_Cost = newCost;
                    break;
                } else {
                    String stock_Scale = rs.getString("C8");
                    if (newScale.equals(stock_Scale)) {
                        //if stock scale and puting new purchase scale is same
                        purchase_Cost = numberFormat.format((Double.valueOf(rs.getString("C7")) + Double.valueOf(total_Cost)) / (Double.valueOf(rs.getString("C6")) + Double.valueOf(quantity)));
                    } else {
                        //if stock scale and puting new purchase scale is not same
                        String v = logic.getTableValue("scale", "C3", "C2", rs.getString("C8"));
                        System.out.println("v " + v);
                        double q = 0.0;
                        q = Double.valueOf(quantity) / Double.valueOf(v);
                        purchase_Cost = numberFormat.format((Double.valueOf(rs.getString("C7")) + Double.valueOf(total_Cost)) / (Double.valueOf(rs.getString("C6")) + Double.valueOf(q)));
                        purchase_Cost = scale.getScale_Amount(newScale, stock_Scale, Double.valueOf(purchase_Cost));
                        break;
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(all_Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purchase_Cost;

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
