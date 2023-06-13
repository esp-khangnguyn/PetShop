/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;
import java.sql.*;
import ClassModel.Pet;
import JDBCUtil.JDBCUtil;
import java.util.ArrayList;

/**
 *
 * @author Quoc Bao
 */
public class PetDAO implements DAOInterface<Pet>{

    public static PetDAO getInstance(){
        return new PetDAO();
    }
    
    public static ArrayList<Pet> findServiceByIdOrName(String str){
        ArrayList<Pet> petList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM PET WHERE PET_CODE LIKE ? OR PET_NAME LIKE ?";
            String searchValue = "%" +str+ "%";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchValue);
            pst.setString(2, searchValue);
           
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("PET_CODE");
                String name = rs.getString("PET_NAME");
                String type = rs.getString("PET_TYPE");
                String dateOfAcq = rs.getString("DATE_OF_ACQUISITION");
                int price = rs.getInt("PRICE");
                String notes = rs.getString("NOTES");
                
                Pet pet = new Pet(code, name, type, dateOfAcq, price, notes);
                petList.add(pet);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return petList;
    }
    
    public static Pet selectByID(String pCode){
        Pet pet = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PET WHERE PET_CODE = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, pCode);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                String code = rs.getString("PET_CODE");
                String name = rs.getString("PET_NAME");
                String type = rs.getString("PET_TYPE");
                String dateOfAcq = rs.getString("DATE_OF_ACQUISITION");
                int price = rs.getInt("PRICE");
                String notes = rs.getString("NOTES");
                pet = new Pet(code, name, type, dateOfAcq, price, notes);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pet;
    }
    
    public static boolean isExistedID(String code){
        boolean check = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM PET WHERE PET_CODE = ?";
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
    public int insert(Pet t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO PET(PET_CODE, PET_NAME, PET_TYPE, DATE_OF_ACQUISITION, PRICE, NOTES) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getCode());
            pst.setString(2, t.getName());
            pst.setString(3, t.getType());
            pst.setString(4, t.getDateOfAcc());
            pst.setInt(5, t.getPrice());
            pst.setString(6, t.getNotes());
           
            
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
    public int update(Pet t) {
       int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "UPDATE PET" + 
                    " SET PET_NAME=?, PET_TYPE=?, DATE_OF_ACQUISITION=?, PRICE=?, NOTES=? WHERE PET_CODE=?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(6, t.getCode());
            pst.setString(1, t.getName());
            pst.setString(2, t.getType());
            pst.setString(3, t.getDateOfAcc());
            pst.setInt(4, t.getPrice());
            pst.setString(5, t.getNotes());
           
            
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
    public int delete(Pet t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM PET WHERE PET_CODE = ?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
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
    public ArrayList<Pet> SelectAll() {
        ArrayList<Pet> petList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM PET";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("PET_CODE");
                String name = rs.getString("PET_NAME");
                String type = rs.getString("PET_TYPE");
                String dateOfAcq = rs.getString("DATE_OF_ACQUISITION");
                int price = rs.getInt("PRICE");
                String notes = rs.getString("NOTES");
                Pet pet = new Pet(code, name, type, dateOfAcq, price, notes);
                petList.add(pet);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return petList;
    }

    @Override
    public Pet SelectById(Pet t) {
        Pet pet = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM PET WHERE PET_CODE= ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getCode());
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("PET_CODE"); // hoặc rs.getString(number) number ở đây là thứ tự cột
                String name = rs.getString("PET_NAME");
                String type = rs.getString("PET_TYPE");
                String dateOfAcq = rs.getString("DATE_OF_ACQUISITION");
                int price = rs.getInt("PRICE");
                String note = rs.getString("NOTES");
                
                pet = new Pet(code, name, type, dateOfAcq, price, note);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return pet;
    }

    @Override
    public ArrayList<Pet> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
