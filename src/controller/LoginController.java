/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import controller.SetItemText;
import controller.UICommon;

/**
 *
 * @author Flasher Linn
 */
public class LoginController implements Initializable {
    
//    @FXML
//    private Label lbl_username;
//    @FXML
 //   private TextField txt_username;
//    @FXML
//    private Label lbl_password;
//    @FXML
//    private TextField txt_password;
//    @FXML
//    private Label lbl_show;
//    @FXML
//    private Pane root;
     private UICommon uICommon = new UICommon();
    
    @FXML
    private void btnLoginClick(ActionEvent event) {
//        String username=txt_username.getText();
//        String password=txt_password.getText();
//        lbl_show.setText("username= "+username+"password ="+password);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             
         } catch (Exception ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
        
}
