/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class Service {
    private String serviceCode;
    private String serviceName;
    private String serviceNotes;
    private int servicePrice;

    public Service() {
    }

    public Service(String serviceCode, String serviceName,  String serviceNotes, int servicePrice) {
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.serviceNotes = serviceNotes;
        this.servicePrice = servicePrice;
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

    public String getServiceNotes() {
        return serviceNotes;
    }

    public void setServiceNotes(String serviceNotes) {
        this.serviceNotes = serviceNotes;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceCode=" + serviceCode + ", serviceName=" + serviceName + ", serviceNotes=" + serviceNotes + ", servicePrice=" + servicePrice + '}';
    }
    
    
}
