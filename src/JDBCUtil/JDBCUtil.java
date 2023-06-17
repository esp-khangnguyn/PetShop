/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBCUtil;
import java.sql.*;

/**
 *
 * @author Quoc Bao
 */
public class JDBCUtil {
    public static Connection getConnection(){
        Connection connect = null;
        try {
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@localhost:1521:orclnew";
            String user = "khang";
            String password = "123456";
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connect successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connected fail! Please try again");
        }
        return connect;
    }
    
    public static void closeConnection(Connection connect){
        try {
            if (connect != null){
                connect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Haven't had a connection yet!");
        }
    }
}
