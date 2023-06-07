/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;
import ClassModel.Service;
import JDBCUtil.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Quoc Bao
 */
public class ServiceDAO implements DAOInterface<Service>{

    public static ServiceDAO getInstance(){
        return new ServiceDAO();
    }
    
    @Override
    public int insert(Service t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO SERVICE(SERVICE_CODE, SERVICE_NAME, TYPE, PRICE, NOTES)"
                    + " VALUES(?, ?, ?, ?, ?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getServiceCode());
            pst.setString(2, t.getServiceName());
            pst.setString(3, t.getServiceType());
            pst.setInt(4, t.getServicePrice());
            pst.setString(5, t.getServiceNotes());
            
            result = pst.executeUpdate();
            System.out.println("Have " + result + " been changed!");
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot insert! Please try again");
        }
        return result;
    }

    @Override
    public int update(Service t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "UPDATE SERVICE " 
                    + "SET SERVICE_NAME = ?, " + "TYPE= ?, "
                    + "PRICE = ?, " + "NOTE= ?, " + "WHERE SERVICE_CODE = ?";
                    
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            
            pst.setString(1, t.getServiceName());
            pst.setString(2, t.getServiceType());
            pst.setInt(3, t.getServicePrice());
            pst.setString(4, t.getServiceNotes());
            pst.setString(5, t.getServiceCode());
            
            
            result = pst.executeUpdate();
            System.out.println("Have " + result + " been changed!");
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot update! Please try again");
        }
        return result;
    }

    @Override
    public int delete(Service t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM SERVICE WHERE SERVICE_CODE = ?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            pst.setString(1, t.getServiceCode());
            
            
            result = pst.executeUpdate();
            System.out.println("Have " + result + " been changed!");
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot delete! Please try again");
        }
        return result;
    }

    @Override
    public ArrayList<Service> SelectAll() {
        ArrayList<Service> serviceList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM SERVICE";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("SERVICE_CODE");
                String name = rs.getString("SERVICE_NAME");
                String type = rs.getString("TYPE");
                int price = rs.getInt("PRICE");
                String role = rs.getString("NOTES");
                Service service = new Service(code, name, type, role, price);
                serviceList.add(service);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return serviceList;
    }

    @Override
    public Service SelectById(Service t) {
        Service service = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM SERVICE WHERE SERVICE_CODE= ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getServiceCode());
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("SERVICE_CODE");
                String name = rs.getString("SERVICE_NAME");
                String type = rs.getString("TYPE");
                int price = rs.getInt("PRICE");
                String role = rs.getString("NOTES");
                
                service = new Service(code, name, type, role, price);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return service;
    }

    @Override
    public ArrayList<Service> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
