/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class Product {
    private String productCode;
    private String productName;
    private int productPrice;
    private String productNotes;
    private int importPrice;
    private int quantity;
    private String dateAdded;
    

    public Product() {
    }

    public Product(String productCode, String productName, int productPrice, String productNotes, int importPrice, int quantity, String dateAdded) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productNotes = productNotes;
        this.importPrice = importPrice;
        this.quantity = quantity;
        this.dateAdded = dateAdded;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductNotes() {
        return productNotes;
    }

    public void setProductNotes(String productNotes) {
        this.productNotes = productNotes;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Product{" + "productCode=" + productCode + ", productName=" + productName + ", productPrice=" + productPrice + ", productNotes=" + productNotes + ", importPrice=" + importPrice + ", quantity=" + quantity + ", dateAdded=" + dateAdded + '}';
    }

    

    
    
}
