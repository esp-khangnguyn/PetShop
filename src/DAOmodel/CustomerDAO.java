/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;

import ClassModel.Customer;
import ClassModel.Employee;
import JDBCUtil.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Quoc Bao
 */
public class CustomerDAO implements DAOInterface<Customer>{
    public static CustomerDAO getInstance(){
        return new CustomerDAO();
    }
    
    public static ArrayList<Customer> findServiceByIdOrName(String str){
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME LIKE ? OR PHONE_NUMBER LIKE ?";
            String searchValue = "%" +str+ "%";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchValue);
            pst.setString(2, searchValue);
           
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("CUSTOMER_CODE");
                String name = rs.getString("CUSTOMER_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String notes = rs.getString("NOTES");
                int inCount = rs.getInt("INVOICE_COUNT");
                Customer customer = new Customer(code, name, dateOfBirth, address, email, phoneNumber, notes);
                customerList.add(customer);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return customerList;
    }
    
    public static ArrayList<Customer>getCusByPhone(String str){
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM CUSTOMER WHERE PHONE_NUMBER = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, str);
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("CUSTOMER_CODE");
                String name = rs.getString("CUSTOMER_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String notes = rs.getString("NOTES");
                int inCount = rs.getInt("INVOICE_COUNT");
                Customer customer = new Customer(code, name, dateOfBirth, address, email, phoneNumber, notes);
                customerList.add(customer);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return customerList;
    }
    
    

    public static ArrayList<Customer> SelectAllFav() {
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM CUSTOMER WHERE INVOICE_COUNT > 5";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("CUSTOMER_CODE");
                String name = rs.getString("CUSTOMER_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String notes = rs.getString("NOTES");
                int inCount = rs.getInt("INVOICE_COUNT");
                Customer customer = new Customer(code, name, dateOfBirth, address, email, phoneNumber, notes,inCount);
                customerList.add(customer);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return customerList;
    }
    public static boolean isExistedID(String code){
        boolean check = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM CUSTOMER WHERE CUSTOMER_CODE = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, code);
            
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0){
                check = true;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public static boolean isExisted(String name, String phone){
        boolean check = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM CUSTOMER WHERE PHONE_NUMBER= ? AND CUSTOMER_NAME=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            pst.setString(2, name);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0){
                check = true;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public static Customer getCustomer(String inName, String phone){
        Customer cus = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CUSTOMER WHERE PHONE_NUMBER= ? AND CUSTOMER_NAME=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            pst.setString(2, inName);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String code = rs.getString("CUSTOMER_CODE"); // hoặc rs.getString(number) number ở đây là thứ tự cột
                String name = rs.getString("CUSTOMER_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String notes = rs.getString("NOTES");
                int inCount = rs.getInt("INVOICE_COUNT");
                cus = new Customer(code, name, dateOfBirth, address, email, phoneNumber, notes,inCount);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cus;
    }
    @Override
    public int insert(Customer t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO CUSTOMER(CUSTOMER_CODE, CUSTOMER_NAME, DATE_OF_BIRTH, ADDRESS, EMAIL, PHONE_NUMBER, NOTES,INVOICE_COUNT)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?,?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getCode());
            pst.setString(2, t.getName());
            pst.setString(3, t.getDateOfBirth());
            pst.setString(4, t.getAddress());
            pst.setString(5, t.getEmail());
            pst.setString(6, t.getPhoneNumber());
            pst.setString(7, t.getNotes());
            pst.setInt(8, t.getInvoiceCount());
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
    public int update(Customer t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "UPDATE CUSTOMER " 
                    + "SET CUSTOMER_NAME = ?, " + "DATE_OF_BIRTH= ?, "
                    + "ADDRESS = ?, " 
                    + "EMAIL = ?, " + "PHONE_NUMBER = ?, "
                    + "NOTES= ?, INVOICE_COUNT = ?" + " WHERE CUSTOMER_CODE = ?";
                    
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            
            pst.setString(1, t.getName());
            pst.setString(2, t.getDateOfBirth());
            pst.setString(3, t.getAddress());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getPhoneNumber());
            pst.setString(6, t.getNotes());
            pst.setString(8, t.getCode());
            pst.setInt(7, t.getInvoiceCount());
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
    public int delete(Customer t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_CODE = ?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
//            pst.setString(1, t.getCode());
            pst.setString(1, t.getCode());

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
    public ArrayList<Customer> SelectAll() {
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM CUSTOMER";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("CUSTOMER_CODE");
                String name = rs.getString("CUSTOMER_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String notes = rs.getString("NOTES");
                int inCount = rs.getInt("INVOICE_COUNT");
                Customer customer = new Customer(code, name, dateOfBirth, address, email, phoneNumber, notes);
                customerList.add(customer);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return customerList;
    }

    @Override
    public Customer SelectById(Customer t) {
       Customer customer = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_CODE= ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getCode());
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("CUSTOMER_CODE"); // hoặc rs.getString(number) number ở đây là thứ tự cột
                String name = rs.getString("CUSTOMER_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String notes = rs.getString("NOTES");
                int inCount = rs.getInt("INVOICE_COUNT");

                customer = new Customer(code, name, dateOfBirth, address, email, phoneNumber, notes,inCount);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return customer;
    }

    @Override
    public ArrayList<Customer> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
         String maxId = null;
        try {
            Connection conn =JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(CUSTOMER_CODE) AS max_id FROM CUSTOMER");
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
