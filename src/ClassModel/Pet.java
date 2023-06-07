/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class Pet {
    private String code;
    private String name;
    private String type;
    private String dateOfAcc;// Ngày nhập thú cưng về tiệm
    private int price;
    private String notes;

    public Pet() {
    }

    public Pet(String code, String name, String type, String dateOfAcc, int price, String notes) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.dateOfAcc = dateOfAcc;
        this.price = price;
        this.notes = notes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateOfAcc() {
        return dateOfAcc;
    }

    public void setDateOfAcc(String dateOfAcc) {
        this.dateOfAcc = dateOfAcc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Pet{" + "code=" + code + ", name=" + name + ", type=" + type + ", dateOfAcc=" + dateOfAcc + ", price=" + price + ", notes=" + notes + '}';
    }
    
}
