/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.UICommon;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flasher Linn
 */
public class Pos extends Application {
    
    private final UICommon uICommon = new UICommon();
    
    @Override
    public void start(Stage stage) throws Exception {
               try {
                   uICommon.start("Login", stage);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
