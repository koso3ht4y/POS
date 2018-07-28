/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Flasher Linn
 */
public class UICommon extends Application {

    DBConnection dBConnection = new DBConnection();
    ObservableList observedList = FXCollections.observableArrayList();
    public Connection con = null;

    public void start(String formname,Stage stage) throws Exception {
//         Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pos/" + formname + ".fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/UI_control/UI_design.css");
        stage.setTitle(formname);
        stage.setScene(scene);
        stage.show();
        try {
            TableView tbv;
            ArrayList<Node> node = getallnode(root);
            ResultSet rs = dBConnection.getItemLabel(formname, 1);
            HashMap<String, String> datahasmap = new HashMap<String, String>();
            while (rs.next()) {
                datahasmap.put(rs.getString("ITEM_ID"), rs.getString("ITEM_NAME"));
            }
            for (int i = 0; i < node.size(); i++) {
                if (node.get(i).getClass().getTypeName().equals("javafx.scene.control.Label")) {
                    Label l = (Label) node.get(i);
                    l.setText(datahasmap.get(l.getId()));
                }
                if (node.get(i).getClass().getTypeName().equals("javafx.scene.control.Button")) {
                    Button B = (Button) node.get(i);
                    B.setText(datahasmap.get(B.getId()));
                }
                if (node.get(i).getClass().getTypeName().equals("javafx.scene.control.TableView")) {
                    tbv = (TableView) node.get(i);
                    observedList=tbv.getColumns();
                }

                if (node.get(i).getClass().getTypeName().equals("com.sun.javafx.scene.control.skin.TableColumnHeader")) {
                    TableColumn tbc=(TableColumn) observedList.get(2);
                    tbc.setText(datahasmap.get(tbc.getId()));
                }
            }

        } catch (SQLException ex) {
        }
    }

    public ArrayList<Node> getallnode(Parent parent) {
        ArrayList<Node> nodes = new ArrayList<Node>();
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

    public ResultSet get_comp(String form_name, int language) {
        try {
            dBConnection.getconnection();
            String command = "{call GET_COMP.GET_ITEM(?,?,?)}";
            CallableStatement csmt = con.prepareCall(command);
            csmt.setString(1, form_name);
            csmt.setInt(2, language);
            csmt.registerOutParameter(3, OracleTypes.CURSOR);
            csmt.execute();
            ResultSet rs = (ResultSet) csmt.getObject(3);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not suppoဗrted yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
