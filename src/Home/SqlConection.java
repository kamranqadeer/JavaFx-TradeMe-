/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author KAMRAN QADEER
 */
public class SqlConection {

    Statement st;
    ResultSet res;
    private static Connection conn = null;
    private static final SqlConection handler = null;

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trademe?useSSL=false", "root", "");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
