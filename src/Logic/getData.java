/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

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

/**
 *
 * @author KAMRAN QADEER
 */
public class getData {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    int limit = 0;


    public void getData(String t_Name, ObservableList<Logic.TableList> data) {
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `" + t_Name + "`");
            rs = pst.executeQuery();
          
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.CloseConnection();
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

}
