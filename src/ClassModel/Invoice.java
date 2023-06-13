/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

import DAOmodel.InvoiceDAO;

/**
 *
 * @author Quoc Bao
 */
public class Invoice {
    private String invoiceCode;
    private String dateCreate;
    private int total;
    private String cusCode;
    private String empCode;
    private String cusName;
    private String empName;
    private String phone;

    public Invoice() {
    }
    
    public Invoice(String invoiceCode, String dateCreate, int total, String cusCode, String empCode, String cusName, String empName, String phone) {
        this.invoiceCode = invoiceCode;
        this.dateCreate = dateCreate;
        this.total = total;
        this.cusCode = cusCode;
        this.empCode = empCode;
        this.cusName = cusName;
        this.empName = empName;
        this.phone = phone;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceCode=" + invoiceCode + ", dateCreate=" + dateCreate + ", total=" + total + ", cusCode=" + cusCode + ", empCode=" + empCode + ", cusName=" + cusName + ", empName=" + empName + ", phone=" + phone + '}';
    }
    
    
    
}
