/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class PetDetail {
    private String invoiceCode;
    private String petCode;
    private String petName;
    private int price;
    private int quantity;
    private String detailId;

    public PetDetail() {
    }

    public PetDetail(String id, String invoiceCode, String petCode, String petName, int price, int quantity) {
        this.invoiceCode = invoiceCode;
        this.petCode = petCode;
        this.petName = petName;
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

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
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
        return "PetDetail{" + "invoiceCode=" + invoiceCode + ", petCode=" + petCode + ", petName=" + petName + ", price=" + price + ", quantity=" + quantity + ", detailId=" + detailId + '}';
    }
    
    
}
