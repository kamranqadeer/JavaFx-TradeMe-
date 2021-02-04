/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author KAMRAN QADEER
 */
public class Data {

    ResultSet rs = null;
    kk_Logic logic = new kk_Logic();

    public ObservableList<String> getAllCodes() {
        ObservableList<String> all_Codes = FXCollections.observableArrayList(
                "0341", "0342", "0343", "0344", "0345", "0346", "0347", "0348", "0349",
                "0311", "0312", "0313", "0314", "0315", "0316", "0317", "0318", "0319",
                "0331", "0332", "0333", "0334", "0335", "0336", "0337", "0338", "0339",
                "0321", "0322", "0323", "0324", "0325", "0326", "0327", "0328", "0329");
        return all_Codes;

    }

    public ObservableList<String> getScale() {
        ObservableList<String> scale = FXCollections.observableArrayList();
        try {
            rs = logic.getAllData("scale");
            while (rs.next()) {
                scale.add(rs.getString("C1"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scale;
    }
    public ObservableList<String> getMonth() {
        ObservableList<String> scale = FXCollections.observableArrayList(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        );
        return scale;
    }

    public ObservableList<String> getYear() {
        ObservableList<String> scale = FXCollections.observableArrayList(
                "2020", "2021", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032");
        return scale;
    }
}
