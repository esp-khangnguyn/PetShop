/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;

import ClassModel.Account;
import JDBCUtil.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Quoc Bao
 */
public class AccountDAO implements DAOInterface<Account>{

    public static AccountDAO getInstance(){
        return new AccountDAO();
    }
    
    @Override
    public int insert(Account t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_NAME, ACCOUNT_PASSWORD, ROLE)"
                    + " VALUES(?, ?, ?, ?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getAccountId());
            pst.setString(2, t.getAccountName());
            pst.setString(3, t.getAccountPassword());
            pst.setString(4, t. getAccountRole());
            
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
    public int update(Account t) {
      int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "UPDATE ACCOUNT " 
                    + "SET ACCOUNT_NAME = ?, " + "ACCOUNT_PASSWORD= ?, "
                    + "ROLE = ?, " + "WHERE ACCOUNT_ID = ?";
                    
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            
            pst.setString(1, t.getAccountName());
            pst.setString(2, t.getAccountPassword());
            pst.setString(3, t.getAccountRole());
            pst.setString(4, t.getAccountId());
            
            
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
    public int delete(Account t) {
       int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            pst.setString(1, t.getAccountId());
            
            
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
    public ArrayList<Account> SelectAll() {
       ArrayList<Account> accountList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM ACCOUNT";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("ACCOUNT_ID");
                String name = rs.getString("ACCOUNT_NAME");
                String password = rs.getString("ACCOUNT_PASSWORD");
                String role = rs.getString("ROLE");
                Account account = new Account(code, name, password, role);
                accountList.add(account);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return accountList;
    }

    @Override
    public Account SelectById(Account t) {
        Account account = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID= ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getAccountId());
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("ACCOUNT_ID"); // hoặc rs.getString(number) number ở đây là thứ tự cột
                String name = rs.getString("ACCOUNT_NAME");
                String password = rs.getString("ACCOUNT_PASSWORD");
                String role = rs.getString("ROLE");
                
                account = new Account(code, name, password, role);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return account;
    }
    
    public boolean isUserNameExists(String userName){
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM ACCOUNT WHERE ACCOUNT_NAME= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            if (rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean checkLogin(String userName, String passWord){
        boolean isValidLogin = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NAME=? AND ACCOUNT_PASSWORD=? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, passWord);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                isValidLogin = true;
            }
            else {
                isValidLogin = false;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValidLogin;
    }

    @Override
    public ArrayList<Account> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        String maxId = null;
        try {
            Connection conn =JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(ACCOUNT_ID) AS max_id FROM ACCOUNT");
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
