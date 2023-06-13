/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;

import ClassModel.PetDetail;
import JDBCUtil.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Quoc Bao
 */
public class PetDetailDAO implements DAOInterface<PetDetail>{

    public static PetDetailDAO getInstance(){
        return new PetDetailDAO();
    }
        public static ArrayList<PetDetail> findServiceByIdOrName(String str){
        ArrayList<PetDetail> petDetailList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM PET_DETAIL WHERE PET_CODE LIKE ? OR PET_NAME LIKE ?";
            String searchValue = "%" +str+ "%";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchValue);
            pst.setString(2, searchValue);
           
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String inCode = rs.getString("INVOICE_CODE");
                String petCode = rs.getString("PET_CODE");
                String petName = rs.getString("PET_NAME");
                int price = rs.getInt("PRICE");
                int quantity = rs.getInt("QUANTITY");
                String detailId =  rs.getString("DETAIL_ID");
                
                PetDetail petDetail = new PetDetail(detailId, inCode, petCode, petName, price, quantity);
                petDetailList.add(petDetail);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return petDetailList;
    }

    @Override
    public int insert(PetDetail t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO PET_DETAIL(INVOICE_CODE, PET_CODE, PET_NAME, PRICE, QUANTITY, DETAIL_ID)"
                    + " VALUES(?, ?, ?, ?, ?,?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getInvoiceCode());
            pst.setString(2, t.getPetCode());
            pst.setString(3, t.getPetName());
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
    public int update(PetDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(PetDetail t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM PET_DETAIL WHERE INVOICE_CODE = ?";
            
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
    public ArrayList<PetDetail> SelectAll() {
        ArrayList<PetDetail> productList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM PET_DETAIL";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String inCode = rs.getString("INVOICE_CODE");
                String petCode = rs.getString("PET_CODE");
                String petName = rs.getString("PET_NAME");
                int price = rs.getInt("PRICE");
                int quantity = rs.getInt("QUANTITY");
                String detailId =  rs.getString("DETAIL_ID");
                
                PetDetail petDetail = new PetDetail(detailId, inCode, petCode, petName, price, quantity);
                productList.add(petDetail);
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
    public PetDetail SelectById(PetDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PetDetail> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        String maxId = null;
        try {
            Connection conn =JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(DETAIL_ID) AS max_id FROM PET_DETAIL");
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
