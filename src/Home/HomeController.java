/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import Dialogs.MainDialogs;
import Logic.kk_Logic;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import trademe.TradeMe;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class HomeController implements Initializable {

    Connection con = null;
    ResultSet rs = null;
    BoxBlur blur = new BoxBlur(3, 3, 3);
    PreparedStatement pst = null;
    kk_Logic logic = new kk_Logic();
    MainDialogs dialogs = new MainDialogs();
    boolean check = false;
    @FXML
    private JFXButton shop;
    @FXML
    private JFXButton stock;
    @FXML
    private JFXButton purchasing;
    @FXML
    private JFXButton sale;
    @FXML
    private JFXButton allMonths;
    @FXML
    private JFXButton all_Products;
    @FXML
    private AnchorPane holderPan;
    @FXML
    private AnchorPane Mian_Pane;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton profit;
    @FXML
    private BorderPane main_Stage;
    @FXML
    private StackPane main_Pane;
    @FXML
    private Text status_Text;
    @FXML
    private Text name_Text;
    @FXML
    private JFXButton info_Button;
    @FXML
    private JFXButton log_Out;
    @FXML
    private JFXButton menu;
    @FXML
    private VBox menu_Pane;
    @FXML
    private JFXButton help_Button;
    @FXML
    private ImageView data_Button;
    @FXML
    private JFXButton Data;
    @FXML
    private VBox v_button;
    @FXML
    private JFXButton log_Out1;
    @FXML
    private ImageView switch_Button;
    @FXML
    private JFXButton back_Up;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.CheckActive();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/MainHome/MainHome.fxml")));

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void setVisible() {
        v_button.setDisable(true);
//        all_Products.setVisible(false);
//        sale.setVisible(false);
//        purchasing.setVisible(false);
//        shop.setVisible(false);
//        stock.setVisible(false);
//        allMonths.setVisible(false);
//        profit.setVisible(false);
//        Data.setVisible(false);
    }

    @FXML
    private void homeButton(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/MainHome/MainHome.fxml")));
        } catch (IOException g) {

        }
    }

    private void dashBoad_Clicked(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/MainHome/MainHome.fxml")));
        } catch (IOException g) {

        }
    }

    @FXML
    private void shopButton(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/Shop/Shop.fxml")));
        } catch (IOException g) {

        }
    }

    @FXML
    private void stockButton(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/Stock/Stock.fxml")));
        } catch (IOException g) {

        }

    }

    @FXML
    private void purchasingButton(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/Purchasing/Purchasing.fxml")));
        } catch (IOException g) {

        }
    }

    @FXML
    private void saleButton(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/Sale/Sale.fxml")));
        } catch (IOException g) {

        }
    }

    @FXML
    private void allMonthsButton(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/AllMonths/AllMonths.fxml")));
        } catch (IOException g) {

        }
    }

    @FXML
    private void all_Product_Acc(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/AllProducts/AllProducts.fxml")));
        } catch (IOException g) {

        }
    }

    @FXML
    private void profit_On_Clicked(ActionEvent event) {
        try {
            holderPan.getChildren().clear();
            holderPan.getChildren().add(FXMLLoader.load(getClass().getResource("/Profit/Profit.fxml")));
        } catch (IOException g) {

        }
    }

    public void LogOut() {
        try {
            rs = logic.getTable_Data("account", "Active", "C7", "C1");
            while (rs.next()) {
                if (dialogs.confirm_Dialog(main_Pane, "You want to LogOut " + rs.getString("C2") + ".")) {
                    con = SqlConection.ConnectDB();
                    String sql = "update account set C7='" + "Deactive"
                            + "' where C1='" + rs.getString("C1") + "'";
                    pst = con.prepareStatement(sql);
                    pst.executeUpdate();
                    pst.execute();
                    con.close();
                    System.exit(0);
                } else {
                    break;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CheckActive() {
        try {
            rs = logic.getTable_Data("account", "Active", "C7", "C1");
            while (rs.next()) {
                name_Text.setText(rs.getString("C2"));
                status_Text.setText(rs.getString("C3"));
                if (rs.getString("C3").equals("User")) {
                    this.setVisible();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void get_Info(ActionEvent event) {
        try {
            menu_Pane.setVisible(false);
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/UserInfo/Information.fxml"));
            Scene scene = new Scene(root);
            mainStage.initStyle(StageStyle.TRANSPARENT);
            mainStage.setScene(scene);
            mainStage.show();
            holderPan.setEffect(null);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout_On_Click(ActionEvent event) {
        this.LogOut();
    }

    public void In() {
        if (check == false) {
            check = true;
            holderPan.setEffect(blur);
        } else {
            check = false;
            holderPan.setEffect(null);
        }
        menu_Pane.setVisible(check);
    }

    @FXML
    private void help_On_Clicked(ActionEvent event) {
        try {
            menu_Pane.setVisible(false);
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Help/help.fxml"));
            Scene scene = new Scene(root);
            mainStage.initStyle(StageStyle.TRANSPARENT);
            mainStage.setScene(scene);
            mainStage.show();
            holderPan.setEffect(null);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void data_On_Clicked(ActionEvent event) {
        try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Company/company.fxml"));
            Scene scene = new Scene(root);
            mainStage.initStyle(StageStyle.TRANSPARENT);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switch_On_Clicked(ActionEvent event) {
        try {
            rs = logic.getTable_Data("account", "Active", "C7", "C1");
            while (rs.next()) {
                con = SqlConection.ConnectDB();
                String sql = "update account set C7='" + "Deactive"
                        + "' where C1='" + rs.getString("C1") + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
                pst.execute();
                con.close();
                try {
                    // get a handle to the stage
                    Stage stage = (Stage) switch_Button.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    Stage mainStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Login/Log.fxml"));
                    Scene scene = new Scene(root);
                    mainStage.initStyle(StageStyle.TRANSPARENT);
                    mainStage.setScene(scene);
                    TradeMe.loginStage.close();
                    mainStage.show();

                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back_Up_Acc(ActionEvent event) throws URISyntaxException, Exception {
        this.Backupdbtosql();
    }

    public void Backupdbtosql() {
        try {

            /*NOTE: Getting path to the Jar file being executed*/
 /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = HomeController.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


            /*NOTE: Creating Database Constraints*/
            String dbName = "trademe";
            String dbUser = "root";
            String dbPass = "";

            /*NOTE: Creating Path Constraints for folder saving*/
 /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = jarDir + "\\backup";

            /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

            /*NOTE: Creating Path Constraints for backup saving*/
 /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "C:wamp64/bin/mysql/mysql5.7.14/bin/mysqlbump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

    @FXML
    private void menu_On_Clicked(ActionEvent event) {
        this.In();
    }
}
