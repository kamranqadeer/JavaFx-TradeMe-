/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 *
 * @author KAMRAN QADEER
 */
public class Validator {

    boolean check = true;

    public boolean Cost(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (!newValue.matches("\\d*(\\.\\d*)?")) {
                field.setText(oldValue);
            } else if (newValue.equals(".")) {
                check = false;
            } else if (Double.valueOf(newValue) == 0.0 && !newValue.equals(".")) {
                field.setText("0");
            } else if (!newValue.isEmpty()) {
                text.setText("");

                check = true;
            }

        });
        return check;
    }

    public boolean Digite(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (!newValue.matches("\\d*")) {
                field.setText(oldValue);

            } else if (Double.valueOf(newValue) == 0) {
                field.setText(oldValue);
            } else if (!newValue.isEmpty()) {
                text.setText("");
                check = true;
            }
        });
        return check;
    }

    public boolean email(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (!Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(newValue).find()) {
                text.setText("only email accept!");
                check = false;
            } else if (newValue.length() < 10) {
                text.setText("Give full address !");
                check = false;
            } else if (newValue.length() > 60) {
                text.setText("Address is to long !");
                check = false;
            } else {
                text.setText("");
                check = true;
            }
        });

        return check;
    }

    public boolean Contact(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (!newValue.matches("\\d*")) {
                field.setText(oldValue);
                check = false;
            } else if (newValue.length() > 7) {
                field.setText(oldValue);
            } else if (!newValue.isEmpty()) {
                text.setText("");
                check = true;
            }
        });
        return check;
    }

    public boolean Contact_Code(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (!newValue.matches("\\d*")) {
                field.setText(oldValue);
                check = false;
            } else if (newValue.length() > 4) {
                field.setText(oldValue);
            } else if (!newValue.isEmpty()) {
                text.setText("");
                check = true;
            }
        });
        return check;
    }

    public boolean ComboBox(ComboBox comb, Text text) {
        if (comb.getSelectionModel().isEmpty()) {
            check = false;
            text.setText("Pleas select !");
        } else {
            text.setText("");
            check = true;
        }
        return check;
    }

    public boolean productNameFiled(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (newValue.length() < 3) {
                text.setText("Min 2!");
                check = false;

            } else if (newValue.length() > 41) {
                text.setText(" Max 40!");
                check = false;

            } else {
                text.setText("");
                field.setText(field.getText().toString().trim().replaceAll(" +", " "));
                field.setText(field.getText().toString().trim().replaceAll("[^a-zA-Z0-9]", " "));
                check = true;

            }
        });
        return check;
    }

    public boolean UserName(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (newValue.length() < 3) {
                text.setText("Min 2!");
                check = false;

            } else if (newValue.length() > 41) {
                text.setText(" Max 40!");
                check = false;

            } else {
                text.setText("");
                field.setText(field.getText().toString().trim().replaceAll(" +", " "));
                field.setText(field.getText().toString().trim().replaceAll("[^a-zA-Z0-9]", " "));
                check = true;

            }
        });
        return check;
    }

    public boolean password(JFXPasswordField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (newValue.length() < 7) {
                text.setText("Min 6!");
                check = false;

            } else if (newValue.length() > 41) {
                text.setText(" Max 40!");
                check = false;

            } else if (!newValue.matches("\\S+")) {
                text.setText(" Space is not allow!");
                check = false;
            } else {
                text.setText("");
                check = true;

            }
        });
        return check;
    }

    public boolean Address(JFXTextField field, Text text) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                text.setText("Field is required !");
                check = false;
            } else if (newValue.length() < 3) {
                text.setText("Min 2!");
                check = false;

            } else if (newValue.length() > 51) {
                text.setText(" Max 50!");
                check = false;

            } else {
                text.setText("");
                field.setText(field.getText().toString().trim().replaceAll(" +", "  "));
                field.setText(field.getText().toString().trim().replaceAll("[^a-zA-Z0-9]", " "));
                check = true;

            }
        });
        return check;
    }
}
