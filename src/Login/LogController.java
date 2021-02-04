/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Dialogs.MainDialogs;
import Home.SqlConection;
import Logic.Validator;
import Logic.kk_Logic;
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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import trademe.TradeMe;

/**
 * FXML Controller class
 *
 * @author KAMRAN QADEER
 */
public class LogController implements Initializable {

    Validator validation = new Validator();
    Stage mainStage = new Stage();
    kk_Logic logic = new kk_Logic();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    MainDialogs dialogs = new MainDialogs();
    @FXML
    private JFXButton sign_Up;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField user_Name;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton forgot_Pass;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private AnchorPane loginStage;
    @FXML
    private JFXButton login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginStage.setVisible(true);

        if (!TradeMe.isSplashLoader) {
            Splash();
        }
        //validation
        this.Validation();
    }

    private void Splash() {
        try {
            TradeMe.isSplashLoader = true;
            AnchorPane splash = FXMLLoader.load(getClass().getResource("LoginGif.fxml"));
            loginStage.getChildren().setAll(splash);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), splash);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            FadeTransition fadeOut = new FadeTransition(Duration.millis(2500), splash);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fadeOut.play();
                }
            });
            fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (LogController.this.check_Active()) {
                            Stage mainStage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("/Home/Home.fxml"));
                            Scene scene = new Scene(root);
                            // mainStage.initStyle(StageStyle.TRANSPARENT);
                            mainStage.setScene(scene);
                            TradeMe.loginStage.close();
                            mainStage.show();
                        } else {
                            AnchorPane mainfome = FXMLLoader.load(getClass().getResource("Log.fxml"));
                            loginStage.getChildren().setAll(mainfome);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean check_Active() {
        boolean check = false;
        try {
            con = SqlConection.ConnectDB();
            pst = con.prepareStatement("Select * from `account`");
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("C7").equals("Active")) {
                    check = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public void login() {
        if (this.Validation() && this.validate_Call()) {
            String check = "",
                    access = "";
            try {
                con = SqlConection.ConnectDB();
                try {
                    pst = con.prepareStatement("Select * from `Account` Where C2='" + user_Name.getText().toUpperCase() + "' AND C6='" + password.getText() + "'");
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        check = rs.getString("C7");
                        access = rs.getString("C8");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (check.isEmpty()) {
                    text3.setText("Given information is wrong !");
                } else {
                    if (access.equals("Blocked")) {
                        text3.setText("You are Blocked by admin contact him !");
                    } else {
                        this.UpdateState();
                        Stage mainStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/Home/Home.fxml"));
                        Scene scene = new Scene(root);
                        // mainStage.initStyle(StageStyle.TRANSPARENT);
                        mainStage.setScene(scene);
                        TradeMe.loginStage.close();
                        mainStage.show();
                        dialogs.Notofication("WELCOME", user_Name.getText() + " now yor are active on Trade Me", "Id");
                        //mainStage.setResizable(false);
                        // mainStage.setTitle("HOME PAGE");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void UpdateState() {
        try {
            con = SqlConection.ConnectDB();
            String sql = "update account set C7='" + "Active"
                    + "' where C2='" + user_Name.getText() + "' AND C6='" + password.getText() + "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Validation() {
        boolean check = false;
        check = validation.UserName(user_Name, text1);
        check = validation.password(password, text2);
        return check;
    }

    public boolean validate_Call() {
        if (user_Name.getText().isEmpty()) {
            text1.setText("Requir UserName");
            return false;
        } else if (password.getText().isEmpty()) {
            text2.setText("Requir password");
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void forgot_On_Clicked(ActionEvent event) {
        if (user_Name.getText().isEmpty()) {
            text1.setText("Enter your name for recover password");
        } else {
            String status = logic.getTableValue("account", "C3", "C2", user_Name.getText().toUpperCase());
            if (!status.equals("Admin")) {
                text3.setText("User password only admin can recover !");
            } else {
                try {
                    Stage mainStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Forgot/forgot.fxml"));
                    Scene scene = new Scene(root);
                    mainStage.initStyle(StageStyle.TRANSPARENT);
                    mainStage.setScene(scene);
                    TradeMe.loginStage.close();
                    mainStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        text2.setText(password.getText());
    }

    @FXML
    private void password_On_Acction(ActionEvent event
    ) {
        this.login();
    }

    @FXML
    private void login_On_Enter(KeyEvent event
    ) {
        this.login();
        event.consume();
    }

    @FXML
    private void singUp_On_Clicked(ActionEvent event
    ) {

        try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/SignUp/sign_Up.fxml"));
            Scene scene = new Scene(root);
            mainStage.initStyle(StageStyle.TRANSPARENT);
            mainStage.setScene(scene);
            TradeMe.loginStage.close();
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void login_On_Clicked(ActionEvent event
    ) {
        this.login();
        event.consume();
    }

    @FXML
    private void close_On_Acc(MouseEvent event
    ) {
        System.exit(0);
    }

}
