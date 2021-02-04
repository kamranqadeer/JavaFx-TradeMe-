/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forgot;

import Home.SqlConection;
import Logic.Validator;
import Logic.kk_Logic;
import SignUp.Sign_UpController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import trademe.TradeMe;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class ForgotController implements Initializable {

    Validator validation = new Validator();
    Stage mainStage = new Stage();
    kk_Logic logic = new kk_Logic();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    boolean check = false;
    @FXML
    private JFXPasswordField main_Password;
    @FXML
    private Text info;
    @FXML
    private JFXTextField user_Name;
    @FXML
    private JFXPasswordField new_Password;
    @FXML
    private JFXButton login;
    @FXML
    private ImageView close;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private JFXPasswordField re_Password;
    @FXML
    private Text text4;
    @FXML
    private ImageView close2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.Validation();
        text1.setText("");
        text2.setText("");
        text3.setText("");
    }

    public boolean Validation() {
        boolean check = false;
        check = validation.UserName(user_Name, text1);
        check = validation.password(new_Password, text2);
        check = validation.password(main_Password, info);
        return check;
    }

    public boolean Validation_Call() {
        if (main_Password.getText().isEmpty()) {
            info.setText("Required Pin code");
            return false;
        } else if (!main_Password.getText().equals("kamran.qadeer.26@gmail.com100110101010")) {
            info.setText("Contact Installer wrong Pin code");
            return false;
        } else if (user_Name.getText().isEmpty()) {
            text1.setText("Required field !");
            return false;
        } else if (new_Password.getText().isEmpty()) {
            text2.setText("Required field !");
            return false;
        } else if (!re_Password.getText().equals(new_Password.getText())) {
            text3.setText("Password is not match !");
            return false;
        } else {
            return true;
        }
    }

    public void update_Password() {
        if (this.Validation_Call() && this.Validation() && check) {
            try {
                con = SqlConection.ConnectDB();
                String sql = "update account set C6='" + new_Password.getText()
                        + "',C7='" + "Active"
                        + "' where C2='" + user_Name.getText().toUpperCase() + "'";
                pst = con.prepareStatement(sql);
                pst.executeUpdate();
                pst.execute();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ForgotController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Stage mainStage = new Stage();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/Home/Home.fxml"));
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                // get a handle to the stage
                Stage stage = (Stage) login.getScene().getWindow();
                // do what you have to do
                stage.close();

            } catch (IOException ex) {
                Logger.getLogger(ForgotController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }

    @FXML
    private void login_On_Clicked(ActionEvent event) {
        this.update_Password();
    }

    @FXML
    private void login_On_Enter(KeyEvent event
    ) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.update_Password();
        }
    }

    @FXML
    private void close_On_Acc(MouseEvent event
    ) {
        try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Login/Log.fxml"));
            Scene scene = new Scene(root);
            mainStage.initStyle(StageStyle.TRANSPARENT);
            mainStage.setScene(scene);
            TradeMe.loginStage.close();
            mainStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Sign_UpController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void see_On_Out(MouseEvent event
    ) {
        text2.setText("");
    }

    @FXML
    private void see_On_In(MouseEvent event
    ) {
        text2.setText(new_Password.getText());
    }

    @FXML
    private void name_On_Type(KeyEvent event
    ) {
        if (user_Name.getText().isEmpty()) {
            info.setText("");
        } else {
            String name = logic.Check_Id("account", "C2", user_Name.getText());
            if (!name.isEmpty()) {
                info.setText(name + " ( Account is Verified )");
            }
        }
    }

    @FXML
    private void reEnter_On_type(KeyEvent event) {
        if (new_Password.getText().equals(re_Password.getText())) {
            text3.setText("");
            check = true;
        } else {
            text3.setText("Not matched");
        }
    }

    @FXML
    private void close2_On_Acc(MouseEvent event) {
        this.update_Password();
        // get a handle to the stage
        Stage stage = (Stage) close2.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
