/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Flasher Linn
 */
public class LoginController implements Initializable {

    @FXML
    private Label lbl_username;
    @FXML
    private TextField txt_username;
    @FXML
    private Label lbl_password;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_show;
    @FXML
    private Pane root;

    @FXML
    private Button btn_login;
    private final UICommon uICommon = new UICommon();
    private final DBConnection dbConnection = new DBConnection();

    @FXML
    private void btnLoginClick(ActionEvent event) {
        try {
            lbl_show.setText("");
            ResultSet rs = null;
            String username = txt_username.getText();
            String password = txt_password.getText();
            rs = dbConnection.getILoginDetail(username, password);
            if (rs.next()) {
                lbl_show.setText("Successfull");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
