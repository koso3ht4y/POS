/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static common.CommonVariable.CannotConnectDatabase;
import static common.CommonVariable.DatabaseConnected;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Flasher Linn
 */
public class DBConnection {
    
    private static Connection connection;
    static DriverManager driverManager;
    
    public String getconnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "possph", "1234");
            if(connection==null){
                return CannotConnectDatabase;
            }else{
                return DatabaseConnected;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    
    public ResultSet getItemLabel(String form_name, int language) {
        try {
            getconnection();
            String command = "{call GET_COMP.GET_ITEM(?,?,?)}";
            CallableStatement csmt = connection.prepareCall(command);
            csmt.setString(1, form_name);
            csmt.setInt(2, language);
            csmt.registerOutParameter(3, OracleTypes.CURSOR);
            csmt.execute();
            ResultSet rs = (ResultSet) csmt.getObject(3);
            return rs;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
