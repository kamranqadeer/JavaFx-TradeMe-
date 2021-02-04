/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Home.SqlConection;
import Sale.SaleController;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author KAMRAN QADEER
 */
public class print_Data {

    Connection con = SqlConection.ConnectDB();

    public void print(String report_Name, String Data[]) {
        JRViewer viewer = null;
        try {
            //  String source_File = "F:\\ALLProjects\\javaProject\\TradeMe\\src\\reports\\" + report_Name + ".jrxml";
            InputStream file = this.getClass().getClassLoader().getResourceAsStream("reports/" + report_Name + ".jrxml");
            // JasperReport jr = JasperCompileManager.compileReport(source_File);
            HashMap<String, Object> para = new HashMap<>();

            if (report_Name == "product_Update") {
                para.put("Id", Data[0]);
                para.put("Contact", Data[1]);
                para.put("Address", Data[2]);
            } else if (report_Name == "all_Purchase") {
                para.put("month", Data[0]);
                para.put("year", Data[1]);
            } else if (report_Name == "all_Month") {
                para.put("Id", Data[0]);
            } else if (report_Name == "all_Month_Spec") {
                para.put("Id", Data[0]);
            } else if (report_Name == "all_Sale") {
                para.put("month", Data[0]);
                para.put("year", Data[1]);
            } else if (report_Name == "item_Sale") {
                para.put("Id", Data[0]);
                para.put("Con", Data[1]);
                para.put("Add", Data[2]);
                para.put("Date", Data[3]);
                para.put("amount_title", Data[4]);
                para.put("amount", Data[5]);
            } else if (report_Name == "previous_Sale_Bill") {
                para.put("Id", Data[0]);
                para.put("Con", Data[1]);
                para.put("Add", Data[2]);
                para.put("Date", Data[3]);
            } else if (report_Name == "previous_Purchase_Bill") {
                para.put("Id", Data[0]);
                para.put("Con", Data[1]);
                para.put("Add", Data[2]);
                para.put("Date", Data[3]);
            } else if (report_Name == "item_Purchase") {
                para.put("Id", Data[0]);
                para.put("Con", Data[1]);
                para.put("Add", Data[2]);
                para.put("Date", Data[3]);
                para.put("amount_title", Data[4]);
                para.put("amount", Data[5]);
            } else if (report_Name == "stock_In") {
                para.put("Id", Data[0]);
                para.put("Month", Data[1]);
                para.put("Year", Data[2]);
                para.put("Date", Data[3]);
            } else if (report_Name == "stock_Out") {
                para.put("Id", Data[0]);
                para.put("Month", Data[1]);
                para.put("Year", Data[2]);
                para.put("Date", Data[3]);
            } else if (report_Name == "profit") {
                para.put("month", Data[0]);
                para.put("year", Data[1]);
                para.put("Date", Data[2]);
                para.put("Total", Data[3]);
            }
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, para, con);
            viewer = new JRViewer(jp);
            // new JasperViewerFX(primaryStage).viewReport("Simple report", jprint);
            JasperViewer.viewReport(jp, false);
            viewer.setOpaque(true);
            viewer.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(SaleController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(print_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
