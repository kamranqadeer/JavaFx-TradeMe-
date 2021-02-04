/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import javafx.scene.control.Button;

/**
 *
 * @author KAMRAN QADEER
 */
public class TableList {

    /**
     * @return the Id
     */
    private String C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11;
    private Button delete;

    public TableList(String C1, String C2, String C3, String C4, String C5, String C6, String C7, String C8, String C9, String C10) {
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
        this.C6 = C6;
        this.C7 = C7;
        this.C8 = C8;
        this.C9 = C9;
        this.C10 = C10;
    }

    public TableList(String C0, String C1, String C2, String C3, String C4, String C5, String C6, String C7, String C8, String C9, String C10, String C11) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
        this.C6 = C6;
        this.C7 = C7;
        this.C8 = C8;
        this.C9 = C9;
        this.C10 = C10;
        this.C11 = C11;
    }

    public TableList(String C0, String C1, String C2, String C3, String C4, String C5, String C6, String C7, String C8) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
        this.C6 = C6;
        this.C7 = C7;
        this.C8 = C8;
    }

    public TableList(String C0, String C1, String C2, String C3, String C4) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
    }

  

    public TableList(String C0, String C1, String C2, String C3, String C4, Button delete) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.delete = delete;
    }

    public TableList(String C0, String C1, String C2, String C3, String C4, String C5, Button delete) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
        this.delete = delete;
    }

    public TableList(String C0, String C1, String C2, String C3, String C4, String C5) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
    }

    public TableList(String C0, String C1, String C2, String C3, String C4, String C5, String C6) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
        this.C6 = C6;

    }

    public TableList(String C0, String C1, String C2, String C3, String C4, String C5, String C6, String C7) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.C5 = C5;
        this.C6 = C6;
        this.C7 = C7;
    }

    public TableList(String C0, String C1, String C2) {

        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
    }

    public TableList(String C0, String C1) {

        this.C0 = C0;
        this.C1 = C1;
    }

    public TableList(String C0, String C1, String C2, String C3) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
    }

    public TableList(String C0, String C1, String C2, String C3, Button delete) {
        this.C0 = C0;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.delete = delete;
    }

// check data
    /**
     * @return the C0
     */
    public String getC0() {
        return C0;
    }

    /**
     * @param C0 the C0 to set
     */
    public void setC0(String C0) {
        this.C0 = C0;
    }

    /**
     * @return the C1
     */
    public String getC1() {
        return C1;
    }

    /**
     * @param C1 the C1 to set
     */
    public void setC1(String C1) {
        this.C1 = C1;
    }

    /**
     * @return the C2
     */
    public String getC2() {
        return C2;
    }

    /**
     * @param C2 the C2 to set
     */
    public void setC2(String C2) {
        this.C2 = C2;
    }

    /**
     * @return the C3
     */
    public String getC3() {
        return C3;
    }

    /**
     * @param C3 the C3 to set
     */
    public void setC3(String C3) {
        this.C3 = C3;
    }

    /**
     * @return the C4
     */
    public String getC4() {
        return C4;
    }

    /**
     * @param C4 the C4 to set
     */
    public void setC4(String C4) {
        this.C4 = C4;
    }

    /**
     * @return the C5
     */
    public String getC5() {
        return C5;
    }

    /**
     * @param C5 the C5 to set
     */
    public void setC5(String C5) {
        this.C5 = C5;
    }

    /**
     * @return the C6
     */
    public String getC6() {
        return C6;
    }

    /**
     * @param C6 the C6 to set
     */
    public void setC6(String C6) {
        this.C6 = C6;
    }

    /**
     * @return the C7
     */
    public String getC7() {
        return C7;
    }

    /**
     * @param C7 the C7 to set
     */
    public void setC7(String C7) {
        this.C7 = C7;
    }

    /**
     * @return the C8
     */
    public String getC8() {
        return C8;
    }

    /**
     * @param C8 the C8 to set
     */
    public void setC8(String C8) {
        this.C8 = C8;
    }

    /**
     * @return the C9
     */
    public String getC9() {
        return C9;
    }

    /**
     * @param C9 the C9 to set
     */
    public void setC9(String C9) {
        this.C9 = C9;
    }

    /**
     * @return the C10
     */
    public String getC10() {
        return C10;
    }

    /**
     * @param C10 the C10 to set
     */
    public void setC10(String C10) {
        this.C10 = C10;
    }

    /**
     * @return the C11
     */
    public String getC11() {
        return C11;
    }

    /**
     * @param C11 the C11 to set
     */
    public void setC11(String C11) {
        this.C11 = C11;
    }

    /**
     * @return the delete
     */
    public Button getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(Button delete) {
        this.delete = delete;
    }

}
