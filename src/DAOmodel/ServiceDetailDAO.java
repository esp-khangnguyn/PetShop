/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;


import ClassModel.ServiceDetail;
import DAOmodel.DAOInterface;
import JDBCUtil.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Quoc Bao
 */
public class ServiceDetailDAO implements DAOInterface<ServiceDetail>{

    public static ServiceDetailDAO getInstance(){
        return new ServiceDetailDAO();
    }
    @Override
    public int insert(ServiceDetail t) {
        int result = 0;
        System.out.println(t);
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO SERVICE_DETAIL(INVOICE_CODE, SERVICE_CODE, SERVICE_NAME, PRICE, QUANTITY, DETAIL_ID)"
                    + " VALUES(?, ?, ?, ?, ?,?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getInvoiceCode());
            pst.setString(2, t.getServiceCode());
            pst.setString(3, t.getServiceName());
            pst.setInt(4, t.getPrice());
            pst.setInt(5, t.getQuantity());
            pst.setString(6, t.getDetailId());
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
    public int update(ServiceDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(ServiceDetail t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM SERVICE_DETAIL WHERE INVOICE_CODE = ?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            pst.setString(1, t.getInvoiceCode());
            
            
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
    public ArrayList<ServiceDetail> SelectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ServiceDetail SelectById(ServiceDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ServiceDetail> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        String maxId = null;
        try {
            Connection conn =JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(DETAIL_ID) AS max_id FROM SERVICE_DETAIL");
            if(rs.next()){
                maxId = rs.getString("max_id");
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }
    
}
