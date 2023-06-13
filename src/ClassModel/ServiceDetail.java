/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class ServiceDetail {
    private String invoiceCode;
    private String serviceCode;
    private String serviceName;
    private int price;
    private int quantity;
    private String detailId;

    public ServiceDetail() {
    }

    public ServiceDetail(String id, String invoiceCode, String serviceCode, String serviceName, int price, int quantity) {
        this.invoiceCode = invoiceCode;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.price = price;
        this.quantity = quantity;
        this.detailId = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    @Override
    public String toString() {
        return "ServiceDetail{" + "invoiceCode=" + invoiceCode + ", serviceCode=" + serviceCode + ", serviceName=" + serviceName + ", price=" + price + ", quantity=" + quantity + ", detailId=" + detailId + '}';
    }
    
    
}
