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
import Home.SqlConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Scales {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    kk_Logic logic = new kk_Logic();
    ObservableList<String> data = FXCollections.observableArrayList();
    double amount = 0;

    public ObservableList<String> getScales(String scale) {
        data.clear();
        String Id = logic.getTableValue("scale", "C4", "C2", scale);
        data.add(scale);
        if (!Id.isEmpty()) {
            data.add(logic.getTableValue("scale", "C2", "C1", Id));
        }
        return data;
    }

    public String getMaxScale(String scale) {
        String Max = "";
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from scale Where C2='" + scale + "'ORDER BY C1 DESC");
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("C4").isEmpty()) {
                    Max = scale;
                } else {
                    int value = Integer.valueOf(logic.getTableValue("scale", "C3", "C1", rs.getString("C4")));
                    if (Integer.valueOf(rs.getString("C3")) > value) {
                        Max = scale;
                    } else {
                        Max = logic.getTableValue("scale", "C2", "C1", rs.getString("C4"));
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Scales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(Scales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Max;
    }

    public ObservableList<String> getAllScale() {
        try {
            data.clear();
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select C2 from `scale`");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(rs.getString("C2"));
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Scales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public String getScale_Amount(String Change, String Main, double amount) {
        if (Change != null && amount != 0 && Main != null) {

            if (Change.equals(Main)) {
                this.amount = amount;
            } else {
                int v1 = Integer.valueOf(logic.getTableValue("scale", "C3", "C2", Change));
                int v2 = Integer.valueOf(logic.getTableValue("scale", "C3", "C2", Main));
                if (v1 < v2) {
                    this.amount = amount / v2;
                } else {
                    this.amount = amount * v1;
                }
            }
        }
        return numberFormat.format(this.amount);
    }

}
