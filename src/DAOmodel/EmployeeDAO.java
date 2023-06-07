/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOmodel;

import ClassModel.Employee;
import JDBCUtil.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Quoc Bao
 */
public class EmployeeDAO implements DAOInterface<Employee>{

    public static EmployeeDAO getInstance(){
        return new EmployeeDAO();
    }
    
    public static boolean isExistedID(String code){
        boolean check = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE EMPLOYEE_CODE = ?";
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
    public int insert(Employee t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO EMPLOYEE(EMPLOYEE_CODE, EMPLOYEE_NAME, DATE_OF_BIRTH, ADDRESS, "
                    + "EMAIL, PHONE_NUMBER, DATE_OF_EMPLOYMENT, POSITION, SALARY, NOTES)" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            pst.setString(1, t.getCode());
            pst.setString(2, t.getName());
            pst.setString(3, t.getDateOfBirth());
            pst.setString(4, t.getAddress());
            pst.setString(5, t.getEmail());
            pst.setString(6, t.getPhoneNumber());
            pst.setString(7, t.getDateOfEmployee());
            pst.setString(8, t.getPosition());
            pst.setInt(9, t.getSalary());
            pst.setString(10, t.getNote());
            
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
    public int update(Employee t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "UPDATE EMPLOYEE " 
                    + "SET EMPLOYEE_NAME= ?, "
                    + "DATE_OF_BIRTH = ?, " + "ADDRESS = ?, " 
                    + "EMAIL = ?, " + "PHONE_NUMBER = ?, " 
                    + "DATE_OF_EMPLOYMENT = ?, " + "POSITION = ?, "
                    + "SALARY = ?, " + "NOTES= ?" + " WHERE EMPLOYEE_CODE = ?";
                    
            PreparedStatement pst = c.prepareStatement(sql);
            System.out.println("You have done: " + sql);
            
            
            pst.setString(1, t.getName());
            pst.setString(2, t.getDateOfBirth());
            pst.setString(3, t.getAddress());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getPhoneNumber());
            pst.setString(6, t.getDateOfEmployee());
            pst.setString(7, t.getPosition());
            pst.setInt(8, t.getSalary());
            pst.setString(9, t.getNote());
            pst.setString(10, t.getCode());
            
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
    public int delete(Employee t) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_CODE = ?";
            
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
    public ArrayList<Employee> SelectAll() {
        ArrayList<Employee> employeesList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            Statement st = c.createStatement();
            
            String sql = "SELECT * FROM EMPLOYEE";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String code = rs.getString("EMPLOYEE_CODE");
                String name = rs.getString("EMPLOYEE_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String dateOfEmployee = rs.getString("DATE_OF_EMPLOYMENT");
                String position = rs.getString("POSITION");
                int salary = rs.getInt("SALARY");
                String notes = rs.getString("NOTES");
                Employee employee = new Employee(code, name, dateOfBirth, address, email, phoneNumber, dateOfEmployee, position, salary, notes);
                employeesList.add(employee);
            }
            System.out.println("You have done: " + sql);
            
            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Query! Please try again");
        }
        return employeesList;
    }

    @Override
    public Employee SelectById(Employee t) {
        Employee employee = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_CODE= ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getCode());
            
            ResultSet rs = pst.executeQuery();
            System.out.println("You have done: " + sql);
            
            while(rs.next()){
                String code = rs.getString("EMPLOYEE_CODE"); // hoặc rs.getString(number) number ở đây là thứ tự cột
                String name = rs.getString("EMPLOYEE_NAME");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String dateOfEmployee = rs.getString("DATE_OF_EMPLOYMENT");
                String position = rs.getString("POSITION");
                int salary = rs.getInt("SALARY");
                String notes = rs.getString("NOTES");
                
                employee = new Employee(code, name, dateOfBirth, address, email, phoneNumber, dateOfEmployee, position, salary, notes);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot select by Id! Please try again!");
        }
       return employee;
    }

    @Override
    public ArrayList<Employee> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMaxIdFromDatabase() {
        String maxId = null;
        try {
            Connection conn =JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(EMPLOYEE_CODE) AS max_id FROM EMPLOYEE");
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
