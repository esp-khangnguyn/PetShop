/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;

import ClassModel.Product;
import JDBCUtil.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Quoc Bao
 */
public class ProductDAO implements DAOInterface<Product>{

    public static ProductDAO getInstance(){
        return new ProductDAO();
    }
    public static boolean isExistedID(String code){
        boolean check = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_CODE = ?";
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
    
    @Override
    public int insert(Product t) {
       int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO PRODUCT(PRODUCT_CODE, PRODUCT_NAME, PRICE, NOTES, IMPORT_PRICE, QUANTITY, DATE_ADDED)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getProductCode());
            pst.setString(2, t.getProductName());
            pst.setInt(3, t.getProductPrice());
            pst.setString(4, t.getProductNotes());
            pst.setInt(5, t.getImportPrice());
            pst.setInt(6, t.getQuantity());
            pst.setString(7, t.getDateAdded());
            
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
    public int update(Product t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "UPDATE PRODUCT " 
                    + "SET PRODUCT_NAME = ?, " 
                    + "PRICE = ?, " + "NOTE= ?, " + 
                    "IMPORT_PRICE = ?, " + "QUANTITY = ?, "+ "DATE_ADDED=?" + "WHERE PRODUCT_CODE = ?";
                    
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            
            pst.setString(1, t.getProductName());
            pst.setInt(2, t.getProductPrice());
            pst.setString(3, t.getProductNotes());
            pst.setInt(4, t.getImportPrice());
            pst.setInt(5, t.getQuantity());
            pst.setString(6, t.getDateAdded());
            pst.setString(7, t.getProductCode());
            
            
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
    public int delete(Product t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM PRODUCT WHERE PRODUCT_CODE = ?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            pst.setString(1, t.getProductCode());
            
            
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
    public ArrayList<Product> SelectAll() {
       ArrayList<Product> productList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM PRODUCT";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("PRODUCT_CODE");
                String name = rs.getString("PRODUCT_NAME");
                int price = rs.getInt("PRICE");
                String notes = rs.getString("NOTES");
                int importPrice = rs.getInt("IMPORT_PRICE");
                int quantity =  rs.getInt("QUANTITY");
                String dateAdded = rs.getString("DATE_ADDDED");
                Product product = new Product(code, name, importPrice, notes, importPrice, quantity, dateAdded);
                productList.add(product);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return productList;
    }

    @Override
    public Product SelectById(Product t) {
        Product product = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_CODE= ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getProductCode());
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("PRODUCT_CODE");
                String name = rs.getString("PRODUCT_NAME");
                int price = rs.getInt("PRICE");
                String notes = rs.getString("NOTES");
                int importPrice = rs.getInt("IMPORT_PRICE");
                int quantity =  rs.getInt("QUANTITY");
                String dateAdded = rs.getString("DATE_ADDDED");
                
                product = new Product(code, name, importPrice, notes, importPrice, quantity, dateAdded);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return product;
    }

    @Override
    public ArrayList<Product> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
