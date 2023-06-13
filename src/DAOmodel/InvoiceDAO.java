/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;

import ClassModel.Invoice;
import JDBCUtil.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Quoc Bao
 */
public class InvoiceDAO implements DAOInterface<Invoice>{

    public static InvoiceDAO getInstance(){
        return new InvoiceDAO();
    }
    public static ArrayList<Invoice> findServiceByIdOrName(String str){
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM INVOICE WHERE INVOICE_CODE LIKE ? OR EMPLOYEE_NAME LIKE ?";
            String searchValue = "%" +str+ "%";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchValue);
            pst.setString(2, searchValue);
           
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String inCode = rs.getString("INVOICE_CODE");
                String createDate = rs.getString("INVOICE_CREATION_DATE");
                int total = rs.getInt("TOTAL_INVOICE_AMOUNT");
                String cusCode = rs.getString("CUSTOMER_CODE");
                String empCode = rs.getString("EMPLOYEE_CODE");
                String cusName = rs.getString("CUSTOMER_NAME");
                String  empName= rs.getString("EMPLOYEE_NAME");
                String phone = rs.getString("PHONE_NUMBER");
                
                Invoice invoice = new Invoice(inCode, createDate, total, cusCode, empCode, cusName, empName, phone);
                invoiceList.add(invoice);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return invoiceList;
    }
    
    
    @Override
    public int insert(Invoice t) {
       int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO INVOICE(INVOICE_CODE, INVOICE_CREATION_DATE, TOTAL_INVOICE_AMOUNT, CUSTOMER_CODE, EMPLOYEE_CODE, CUSTOMER_NAME, EMPLOYEE_NAME, PHONE_NUMBER)"
                    + " VALUES(?, ?, ?, ?, ?,?,?,?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getInvoiceCode());
            pst.setString(2, t.getDateCreate());
            pst.setInt(3, t.getTotal());
            pst.setString(4, t.getCusCode());
            pst.setString(5, t.getEmpCode());
            pst.setString(6, t.getCusName());
            pst.setString(7, t.getEmpName());
            pst.setString(8, t.getPhone());
            
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
    public int update(Invoice t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Invoice t) {
      int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM INVOICE WHERE INVOICE_CODE = ?";
            
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
    public ArrayList<Invoice> SelectAll() {
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM INVOICE";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String inCode = rs.getString("INVOICE_CODE");
                String createDate = rs.getString("INVOICE_CREATION_DATE");
                int total = rs.getInt("TOTAL_INVOICE_AMOUNT");
                String cusCode = rs.getString("CUSTOMER_CODE");
                String empCode = rs.getString("EMPLOYEE_CODE");
                String cusName = rs.getString("CUSTOMER_NAME");
                String  empName= rs.getString("EMPLOYEE_NAME");
                String phone = rs.getString("PHONE_NUMBER");
                
                Invoice invoice = new Invoice(inCode, createDate, total, cusCode, empCode, cusName, empName, phone);
                invoiceList.add(invoice);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return invoiceList;
    }

    @Override
    public Invoice SelectById(Invoice t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Invoice> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } 

    @Override
    public String getMaxIdFromDatabase() {
        String maxId = null;
        try {
            Connection conn =JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(INVOICE_CODE) AS max_id FROM INVOICE");
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
