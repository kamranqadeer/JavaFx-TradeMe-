/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trademe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author KAMRAN QADEER
 */
public class sqlConnection {

    Statement pst;
    ResultSet rs;
    private static Connection con = null;
    private static final sqlConnection handler = null;

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TradeMe?useSSL=false", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
    }

}
