/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static common.CommonVariable.CannotConnectDatabase;
import static common.CommonVariable.DatabaseConnected;
import database.DBConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Flasher Linn
 */
public final class SetItemText {

    private final Class controller;

    public SetItemText(Class controller) {
        this.controller = controller;
        getItemText();
    }

    public void getItemText() {
        DBConnection bConnection = new DBConnection();
        String status = bConnection.getconnection();
        List<Label> labelList = getItemID(controller);
        if (status.equals(CannotConnectDatabase)) {

        } else if (status.equals(DatabaseConnected)) {

        }
    }

    public List getItemID(Class Contller) {
        try {
            List<Label> labelList = new ArrayList<>();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pos/" + "FXMLDocument.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ArrayList<Node> node = getallnode(root);
            for (Node node1 : node) {
                if (node1.getClass().getTypeName().equals("javafx.scene.control.Label")) {
                    labelList.add((Label) node1);
                }
            }
            return labelList;
        } catch (IOException ex) {
            Logger.getLogger(SetItemText.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<Node> getallnode(Parent parent) {
        ArrayList<Node> nodes = new ArrayList<>();
        addallDescendent(parent, nodes);
        return nodes;
    }

    private static void addallDescendent(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent) {
                addallDescendent((Parent) node, nodes);
            }
        }
    }

}
