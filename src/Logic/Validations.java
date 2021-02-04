/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.validation.ValidationSupport;

/**
 *
 * @author KAMRAN QADEER
 */
public class Validations {

    RequiredFieldValidator validator = new RequiredFieldValidator();
    boolean check = true;
    ValidationSupport validationsupport = new ValidationSupport();
    RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
    ImageView imageview = new ImageView("/resources/icon/confirm.png");

    public void productNameFiled(JFXTextField p_Name, String text) {
        imageview.setFitHeight(12);
        imageview.setFitWidth(12);
        requiredFieldValidator.setMessage(text);
        requiredFieldValidator.setIcon(imageview);
        p_Name.getValidators().add(requiredFieldValidator);
        p_Name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    check = false;
                    p_Name.validate();
                } else if (!newValue.matches("[A-Za-z\\s]+")) {
                    check = false;
                    p_Name.setText(oldValue);
                } else {
                    check = true;
                    p_Name.setText(newValue.trim().replaceAll(" +", " "));
                    p_Name.validate();
                }
            }

        }
        );

    }

    public boolean user_Name_Filed(JFXTextField name, Text text_1) {
        if (name.getText().isEmpty()) {
            name.getStyleClass().add("error");
            text_1.setText("Input is required !");
            return false;
        } else if (name.getText().length() <= 4) {
            name.getStyleClass().add("error_Field");
            text_1.setText("Min 6 Character in allow !");
            return false;
        } else if (name.getText().length() >= 20) {
            name.getStyleClass().add("error_Field");
            text_1.setText(" Max 20 Charecter is allow!");
            return false;
        } else if (!name.getText().matches("[A-Za-z\\s]+")) {
            name.getStyleClass().add("error_Field");
            text_1.setText("Only alphabets!");
            return false;
        } else {
            name.getStyleClass().remove("error_Field");

            text_1.setText("");
            return true;
        }

    }

    public boolean Name(String name, Text text_1) {
        if (name.isEmpty()) {
            text_1.setText("Input is required !");
            return false;
        } else if (name.length() <= 4) {
            text_1.setText("Min 6 Character in allow !");
            return false;
        } else if (name.length() >= 20) {
            text_1.setText(" Max 20 Charecter is allow!");
            return false;
        } else if (!name.matches("[A-Za-z\\s]+")) {
            text_1.setText("Only alphabets!");
            return false;
        } else {
            text_1.setText("");
            return true;
        }

    }

    public boolean password_Filed(JFXPasswordField pass, Text text_2) {
        if (pass.getText().isEmpty()) {
            pass.getStyleClass().add("error");
            text_2.setText("Input is required !");
            return false;
        } else if (pass.getText().length() <= 4) {
            pass.getStyleClass().add("error_Field");
            text_2.setText("Min 6 Character in allow !");
            return false;
        } else if (pass.getText().length() >= 30) {
            pass.getStyleClass().add("error_Field");
            text_2.setText(" Max 30 Charecter is allow!");
            return false;
        } else if (!pass.getText().matches("\\S+")) {
            pass.getStyleClass().add("error_Field");

            text_2.setText(" Space is not allow!");
            return false;
        } else {
            pass.getStyleClass().remove("error_Field");
            text_2.setText("");
            return true;
        }

    }

    public boolean address_Filed(String add, Text text) {
        String addressToken = "[\\p{Punct}&&[#,.()-]]+\\d*+\\s?+[\\p{Alpha}+\\s?]*";
        if (add.isEmpty()) {
            text.setText("Input is required !");
            return false;
        } else if (!Pattern.compile(addressToken).matcher(add).find()) {
            text.setText("only Address accept!");
            return false;
        } else if (add.length() < 10) {
            text.setText("Give full address !");
            return false;
        } else if (add.length() > 60) {
            text.setText("Address is to long !");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean email(String add, Text text) {
        if (add.isEmpty()) {
            text.setText("Input is required !");
            return false;
        } else if (!Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(add).find()) {
            text.setText("only Address accept!");
            return false;
        } else if (add.length() < 10) {
            text.setText("Give full address !");
            return false;
        } else if (add.length() > 60) {
            text.setText("Address is to long !");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean contact_Filed(String con, Text text) {
        String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
        if (con.isEmpty()) {
            text.setText("Input is required !");
            return false;
        } else if (!Pattern.compile(regexStr).matcher(con).find()) {
            text.setText("Contact number is not correct !");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean cost_Filed(String amount, Text text) {

        if (amount.isEmpty()) {
            text.setText("Input is required !");
            return false;
        } else if (!Pattern.compile("-?\\d+(\\.\\d+)?").matcher(amount).find()) {
            text.setText("only Amount accept!");
            return false;
        } else if (Double.parseDouble(amount) == 0) {
            text.setText("Amount is Zero !");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean product_Name(String p_Name, Text text) {

        if (p_Name.isEmpty()) {
            text.setText("Input is required !");
            return false;
        } else if (p_Name.length() <= 4) {
            text.setText("Min 4 Character in allow !");
            return false;
        } else if (p_Name.length() >= 40) {
            text.setText(" Max 40 Charecter is allow!");
            return false;
        } else if (!p_Name.matches("[A-Za-z\\s]+")) {
            text.setText("Only alphabets!");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean product_Id(String id, Text text) {
        if (id.isEmpty()) {
            text.setText("Input is required !");
            return false;
        } else if (id.length() >= 40) {
            text.setText(" Max 40 Charecter is allow!");
            return false;
        } else if (Pattern.compile("[^A-Za-z0-9]").matcher(id).find()) {
            text.setText("Only alphabets or Numaric!");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean list_Down(String select, String check, Text text) {
        if (select.equals(check)) {
            text.setText("Pleas select !");
            return false;
        } else {
            text.setText("");
            return true;
        }
    }

    public boolean textFiled(JFXTextField filed, Text text, String F_Name) {

        switch (F_Name) {
            case "text":
                filed.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        if (newValue.isEmpty()) {
                            text.setText("Field is required !");
                            filed.getStyleClass().remove("simpleInput");
                            filed.getStyleClass().add("simpleInputError");
                            check = false;
                        } else if (newValue.length() <= 4) {
                            text.setText("Min 6 Character in allow !");
                            filed.getStyleClass().remove("simpleInput");
                            filed.getStyleClass().add("simpleInputError");
                            check = false;

                        } else if (newValue.length() >= 20) {
                            text.setText(" Max 20 Charecter is allow!");
                            filed.getStyleClass().remove("simpleInput");
                            filed.getStyleClass().add("simpleInputError");
                            check = false;

                        } else if (!newValue.matches("[A-Za-z\\s]+")) {
                            text.setText("Only alphabets!");
                            filed.getStyleClass().remove("simpleInput");
                            filed.getStyleClass().add("simpleInputError");
                            check = false;

                        } else {
                            text.setText("");
                            filed.getStyleClass().remove("simpleInputError");
                            filed.getStyleClass().add("simpleInput");
                            check = true;

                        }

                    }
                });

            default:
            // code block

        }

//        filed.getValidators().add(validator);
//        validator.setMessage("value is required ?");
//        filed.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                switch (newValue) {
//                    case "":
//                        text.setText("Requir filed");
//                        filed.getStyleClass().remove("simpleInput");
//                        filed.getStyleClass().add("simpleInputError");
//                        break;
//
//                    default:
//                        // code block
//                        text.setText("");
//                        filed.getStyleClass().remove("simpleInputError");
//                        filed.getStyleClass().add("simpleInput");
//                }
////                if (!newValue) {
////                    text.setText("Requir filed");
////                    filed.getStyleClass().remove("simpleInput");
////                    filed.getStyleClass().add("simpleInputError");
////                } else if (newValue) {
////                    text.setText("");
////                    filed.getStyleClass().remove("simpleInputError");
////                    filed.getStyleClass().add("simpleInput");
////                    System.out.println("check");
////                }
//            }
//        });
        return check;
    }

//    public void filed_error(JFXTextField filed, Text text) {
//        text.setText("Requir filed");
//        filed.getStyleClass().remove("simpleInput");
//        filed.getStyleClass().add("simpleInputError");
//    }
//
//    public void filed_correct(JFXTextField filed, Text text) {
//        text.setText("");
//        filed.getStyleClass().remove("simpleInputError");
//        filed.getStyleClass().add("simpleInput");
//    }
    //setting valdation
    public void FiledValidator(String type, JFXTextField filed) {
        filed.getValidators().add(validator);
        validator.setMessage("input is required");
        if (type.equals("Name")) {

            filed.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable,
                        String oldValue, String newValue) {
                    if (newValue.isEmpty()) {
                        filed.validate();
                        System.out.println("Check");

                    }
                }

            });
        } else {

        }

    }
}
