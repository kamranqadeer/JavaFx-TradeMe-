/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trademe;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author KAMRAN QADEER
 */
public class TradeMe extends Application {

    public static Boolean isSplashLoader = false;
    public static Stage loginStage;
    static boolean isSplashLoaded;
    public Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Login/Log.fxml"));
        loginStage = stage;
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        stage.setOpacity(0.9);
        this.stage = stage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
