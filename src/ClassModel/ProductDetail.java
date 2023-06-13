/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class ProductDetail {
    private String invoiceCode;
    private String detailCode;
    private String productCode;
    private String productName;
    private int price;
    private int quantity;

    public ProductDetail() {
    }

    public ProductDetail(String id,String invoiceCode, String productCode, String productName, int price, int quantity) {
        this.detailCode = id;
        this.invoiceCode = invoiceCode;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "invoiceCode=" + invoiceCode + ", detailCode=" + detailCode + ", productCode=" + productCode + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + '}';
    }

    
    
    
}
