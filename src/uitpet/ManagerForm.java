/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uitpet;

import ClassModel.Account;
import ClassModel.Customer;
import ClassModel.Employee;
import ClassModel.Invoice;
import ClassModel.Pet;
import ClassModel.PetDetail;
import ClassModel.Product;
import ClassModel.ProductDetail;
import ClassModel.Service;
import ClassModel.ServiceDetail;
import DAOmodel.AccountDAO;
import DAOmodel.CustomerDAO;
import DAOmodel.EmployeeDAO;
import DAOmodel.InvoiceDAO;
import DAOmodel.PetDAO;
import DAOmodel.PetDetailDAO;
import DAOmodel.ProductDAO;
import DAOmodel.ProductDetailDAO;
import DAOmodel.ServiceDAO;
import DAOmodel.ServiceDetailDAO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khang
 */
public class ManagerForm extends javax.swing.JFrame {

    /**
     * Creates new form ManagerForm
     */
    int state = 1;
    boolean valid = true;
    public static Employee currentEmp = null;
    public static Account currentAcc = null;
    public static ArrayList<Product> productArrayOrigin = ProductDAO.getInstance().SelectAll();
    public static ArrayList<Product> productArray = ProductDAO.SelectAvble();
    public static ArrayList<Product> productArrayDel = new ArrayList<>();
    public static ArrayList<Pet> petArray = PetDAO.getInstance().SelectAll();
    public static ArrayList<Pet> petArrayDel = new ArrayList<>();
    public static ArrayList<Service> serviceArray = ServiceDAO.getInstance().SelectAll();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    Date date = new Date();  
    int deletedProduct[] = new int[productArray.size()];
    int deletedPet[] = new int[petArray.size() + 1 ];
    int rowDel = 0;
    int insertedPet[] = new int[petArray.size() + 1];
    public void removeClicked( int index ) {
        
        switch(index) {
            case 1 -> {
                invoiceBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                invoiceLayout.setVisible(false);
            }
            case 2 -> {
                serviceBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                serviceLayout.setVisible(false);
            }
            case 3 -> {
                PetBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                petLayout.setVisible(false);
            }
            case 4 -> {
                productBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                productLayout.setVisible(false);
            }
            case 5 -> {
                statisticBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                statisticLayout.setVisible(false);
            }
            case 6 -> {
                customerBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                customerLayout.setVisible(false);
            }
            case 7 -> {
                empBtn.setBorder(BorderFactory.createLineBorder(Color.white));
                empLayout.setVisible(false);
            }
        }
        
    }
    
    
    public void addToTable(JTable table, Object[] entry, int col) {
        // Get row and column count
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();
        DefaultTableModel model = (DefaultTableModel) table.getModel(); 
        // Get Current Table Entry
        String curEntry = "";
        for (Object o : entry) {
            String e = o.toString();
            curEntry = curEntry + " " + e;
        }

        // Check against all entries
        for (int i = 0; i < rowCount; i++) {
            String rowEntry = "";
            for (int j = 0; j < colCount; j++)
                rowEntry = rowEntry + " " + table.getValueAt(i, j).toString();
            if (rowEntry.equalsIgnoreCase(curEntry)) {
                System.out.println("there");
            }
        }
        model.insertRow(0, entry);
    }

    public static void clearTable( JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel(); 
        model.setRowCount(0);
        productArrayOrigin = ProductDAO.getInstance().SelectAll();
        productArray = ProductDAO.getInstance().SelectAll();
        productArrayDel = new ArrayList<>();
    } 
    public boolean existsInTable(JTable table, Object[] entry) {

        // Get row and column count
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();

        // Get Current Table Entry
        String curEntry = "";
        for (Object o : entry) {
            String e = o.toString();
            curEntry = curEntry + " " + e;
        }

        // Check against all entries
        for (int i = 0; i < rowCount; i++) {
            String rowEntry = "";
            for (int j = 0; j < colCount; j++)
                rowEntry = rowEntry + " " + table.getValueAt(i, j).toString();
            if (rowEntry.equalsIgnoreCase(curEntry)) {

                System.out.println(table.getValueAt(i, 3));
                return true;
            }
        }
        return false;
    }
    
    public static void disableBtn(JButton b) {
        b.setBackground(Color.gray);
        b.setForeground(Color.white);
        b.setEnabled(false);
    }
    
    public static void updateProductListTable(){
        DefaultTableModel model = (DefaultTableModel) productListTable.getModel();
        model.setRowCount(0);
        ArrayList<Product> list = ProductDAO.getInstance().SelectAll();
        for (Product p : list){
            System.out.println(p);
            model.addRow(new Object[]{p.getProductCode(),p.getProductName(),p.getQuantity(),p.getImportPrice(),p.getProductPrice(),p.getProductNotes(),p.getDateAdded()});
        }
        productListTable.setModel(model);
    }
    
     public static void updateServiceTable(){
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.setRowCount(0);
        ArrayList<Service> list = ServiceDAO.getInstance().SelectAll();
        for (Service p : list){
            model.addRow(new Object[]{p.getServiceCode(), p.getServiceName(), p.getServiceNotes(), p.getServicePrice()});
        }
        serviceTable.setModel(model);
    }
     
    public static void updatePetTable(){
        DefaultTableModel model = (DefaultTableModel) serviceTypeTable.getModel();
        model.setRowCount(0);
        ArrayList<Pet> listArray = PetDAO.getInstance().SelectAll();
        for (Pet p : listArray){
            model.addRow(new Object[]{p.getCode(), p.getName(), p.getType(), p.getDateOfAcc(), p.getPrice(), p.getNotes()});
        }
        serviceTypeTable.setModel(model);
    }
    
    public static void updateEmpTable(){
        DefaultTableModel model = (DefaultTableModel) empTable.getModel();
        model.setRowCount(0);
        ArrayList<Employee> list = EmployeeDAO.getInstance().SelectAll();
        for (Employee p : list){
            System.out.println(p);
            model.addRow(new Object[]{p.getCode(),p.getName(),p.getDateOfBirth(),p.getAddress(),p.getEmail(),p.getPhoneNumber(),p.getDateOfEmployee(),p.getSalary()});
        }
        empTable.setModel(model);
    }
    
    public static void updateCusTable(){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        model.setRowCount(0);
        ArrayList<Customer> list = CustomerDAO.getInstance().SelectAll();
        for (Customer p : list){
            System.out.println(p);
            model.addRow(new Object[]{p.getCode(),p.getPhoneNumber(),p.getName(),p.getAddress(),p.getEmail(), p.getDateOfBirth()});
        }
        customerTable.setModel(model);
    }
    
    public static void updatePetList() {
        DefaultListModel list  = new DefaultListModel() ;   
//        ArrayList<Pet> listArray = PetDAO.getInstance().SelectAll();
//        System.out.println(listArray);
        for (Pet p : petArray){
            list.addElement(p.getType()+" "+p.getName()+" "+p.getNotes());
        }
        petList.setModel(list);
    }
    
    public static void updatePetList2() {
        DefaultListModel list  = new DefaultListModel() ;   
        ArrayList<Pet> listArray = PetDAO.getInstance().SelectAll();
//        System.out.println(listArray);s
        for (Pet p : listArray){
            list.addElement(p.getType()+" "+p.getName()+" "+p.getNotes());
        }
        petList.setModel(list);
    }
    
    public static void updateProductList() {
        DefaultListModel list  = new DefaultListModel() ;   
//        ArrayList<Product> listArray = ProductDAO.getInstance().SelectAll();
//        System.out.println(listArray);
//        productArrayOrigin = ProductDAO.getInstance().SelectAll();
//        productArray = ProductDAO.getInstance().SelectAll();
//        productArrayDel = new ArrayList<>();
        productArray = ProductDAO.SelectAvble();
        for (Product p : productArray){
           
                list.addElement(p.getProductName() + " : " + p.getQuantity());
        }
        productList.setModel(list);
    }
    
    public static void updateProductList2() {
        DefaultListModel list  = new DefaultListModel() ;   
//        ArrayList<Product> listArray = ProductDAO.getInstance().SelectAll();
//        System.out.println(listArray);
//        productArrayOrigin = ProductDAO.getInstance().SelectAll();
//        productArray = ProductDAO.getInstance().SelectAll();
//        productArrayDel = new ArrayList<>();
        
        for (Product p : productArray){
           
                list.addElement(p.getProductName() + " : " + p.getQuantity());
        }
        productList.setModel(list);
    }
    
    public static void updateServiceList() {
        DefaultListModel list  = new DefaultListModel() ;   
        ArrayList<Service> listArray = ServiceDAO.getInstance().SelectAll();
        serviceArray = ServiceDAO.getInstance().SelectAll();
        for (Service p : listArray){
            list.addElement(p.getServiceName());
        }
        serviceList.setModel(list);
    }
    
    public static boolean checkDeleted(int x, int a[]) {
        for (int i=0 ; i<a.length;i++) {
            if (x == a[i]) return true;
        }
        return false;
    }
    
    
    public static void reAddPet(String id) {
        for (Pet p : petArrayDel){
//            System.out.println("Deleted");
            if (p.getCode().equals(id)) {
                System.out.println("Added");
                petArray.add(p);
                System.out.println(petArray);
                System.out.println("Deleted");
                petArrayDel.remove(p);
                System.out.println(petArrayDel);
                updatePetList();
                return;
            }
        }
    }
    
    public static void reAddProduct(String id) {
        for (Product p : productArrayDel){
//            System.out.println("Deleted");
            if (p.getProductCode().equals(id)) {
                p.setQuantity(1);
                productArray.add(p);
                productArrayDel.remove(p);
//                System.out.println(petArrayDel);
                updateProductList();
                return;
            }
        } 
        int i = 0;
        for (Product p : productArray){
            
//            System.out.println("Deleted");
            if (p.getProductCode().equals(id)) {
                p.setQuantity(p.getQuantity() + 1);
                productArray.set(i, p);
                productArrayDel.remove(p);
//                System.out.println(petArrayDel);
                updateProductList();
                return;
            }
            i++;
        }
        
    }
    
    public static void setSumCost()  {
        int sum = 0;
        if (productTable.getRowCount() == 0) {
            sum = 0;
        }
        else {
            for (int i = 0; i < productTable.getRowCount(); i++) {  
                sum += Integer.parseInt(productTable.getValueAt(i, 3).toString()) * Integer.parseInt(productTable.getValueAt(i, 4).toString()) ;
            }

        }
        
        sumPrice.setText("Tổng tiền: " + Integer.toString(sum));
    }
    
    public static int getSumCost()  {
        int sum = 0;
        if (productTable.getRowCount() == 0) {
            sum = 0;
        }
        else {
            for (int i = 0; i < productTable.getRowCount(); i++) {  
                sum += Integer.parseInt(productTable.getValueAt(i, 3).toString()) * Integer.parseInt(productTable.getValueAt(i, 4).toString()) ;
            }

        }
        return sum;
    }
    
    public static void  resetProductList() {
        DefaultListModel list  = new DefaultListModel() ;
        ArrayList<Product> listArray = ProductDAO.getInstance().SelectAll();
//        System.out.println(listArray);
        for (Product p : listArray){
            list.addElement(p.getProductName() + " : " + p.getQuantity());
        }
        productList.setModel(list);
        productArrayOrigin = ProductDAO.getInstance().SelectAll();
        productArray = ProductDAO.getInstance().SelectAll();
        productArrayDel = new ArrayList<>();
    }
    
    public static void resetProductTable() {
        updatePetList();
        updateServiceList();
        resetProductList();
        clearTable(productTable);

    }
    
    public static String createInvoice() {
        String cName = cusName.getText();
        String cPhone = cusPhone.getText();
        
//        Tao id tu dong cho Invoice
        String maxIdInvoice = InvoiceDAO.getInstance().getMaxIdFromDatabase();
        if(maxIdInvoice == null){
            maxIdInvoice = "0";
        }
        int newIdInvoice = Integer.parseInt(maxIdInvoice) + 1;
        String iCode = String.format("%04d", newIdInvoice);
//        Tao id tu dong cho Customer
        String maxIdCus = CustomerDAO.getInstance().getMaxIdFromDatabase();
        if(maxIdCus == null){
            maxIdCus = "0";
        }
        int newIdCus = Integer.parseInt(maxIdCus) + 1;
        String cCode = String.format("%02d", newIdCus);
//        Xu ly logic
        System.out.println(iCode);
        boolean c = CustomerDAO.isExisted(cName, cPhone);
        if (c) {
            Customer n = CustomerDAO.getCustomer(cName, cPhone);
            cCode = n.getCode();
            System.out.println("update" + n.getCode());
            n.setInvoiceCount(n.getInvoiceCount() + 1);
            int createCus = CustomerDAO.getInstance().update(n);
        } else {
            Customer newCus = new Customer();
            newCus.setCode(cCode);
            newCus.setName(cName);
            newCus.setPhoneNumber(cPhone);
            newCus.setInvoiceCount(1);
            System.out.println(newCus.getInvoiceCount());
            int createCus = CustomerDAO.getInstance().insert(newCus);
        }
        Invoice i = new Invoice(iCode,curDay.getText(),getSumCost(),cCode,currentEmp.getCode(),cName,currentEmp.getName(),cPhone);
        
        int createIn = InvoiceDAO.getInstance().insert(i);
        return iCode;
    }
    
    
    public static void updateProduct(String pid) {
        int i =0, realquan = 0;
        for (Product p : productArray){
            if (p.getProductCode().equals(pid)) {
                realquan = productArray.get(i).getQuantity();           
            }
            i++;
        }
        System.out.println("update ne");
        ProductDAO.updateProductByID(pid, realquan);
    }
    
    public static void createProductDeTail(String iCode, String pCode, String name, int price, int quantity) {
        
        String maxIdPD = ProductDetailDAO.getInstance().getMaxIdFromDatabase();
//      
       
        if(maxIdPD == null){
            maxIdPD = "0";
        }
        
        int newIdInvoice = Integer.parseInt(maxIdPD) + 1;
        String pdCode = String.format("%04d", newIdInvoice);
        
        ProductDetail pd = new ProductDetail(pdCode,iCode,pCode,name,price,quantity);
        System.out.println("Tao ne" + pd.getDetailCode());
        int createPD = ProductDetailDAO.getInstance().insert(pd);
        updateProduct(pCode);
    }
    
    public static void createPetDeTail(String iCode, String pCode, String name, int price, int quantity) {
        
        String maxIdPet = PetDetailDAO.getInstance().getMaxIdFromDatabase();
        if(maxIdPet == null){
            maxIdPet= "0";
        }
        
        int newIdInvoice = Integer.parseInt(maxIdPet) + 1;
        String pdCode = String.format("%04d", newIdInvoice);
        
        PetDetail pd = new PetDetail(pdCode,iCode,pCode,name,price,quantity);
        System.out.println("Tao ne" + pd.getDetailId());
        int createPD = PetDetailDAO.getInstance().insert(pd);
        Pet pet = PetDAO.selectByID(pCode);
        System.out.println(pet);
        int deletePet = PetDAO.getInstance().delete(pet);
        
    }
    
    public static void createServiceDeTail(String iCode, String pCode, String name, int price, int quantity) {
        
        String maxIdService = ServiceDetailDAO.getInstance().getMaxIdFromDatabase();
        if(maxIdService == null){
            maxIdService= "0";
        }
        
        int newIdInvoice = Integer.parseInt(maxIdService) + 1;
        String pdCode = String.format("%04d", newIdInvoice);
        ServiceDetail pd = new ServiceDetail(pdCode,iCode,pCode,name,price,quantity);
        System.out.println("Tao ne" + pd.getDetailId());
        int createPD = ServiceDetailDAO.getInstance().insert(pd);
    }
    
    public static void updateProductTable() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
        updateProductList();
        updateCusTable();
        updatePetTable();
        updatePetList();
        updateEmpTable();
        updateServiceTable();
        updateServiceList();
        updateProductListTable();
        setSumCost();   
    }
    
    public static void updateInvoiceListTable() {
        ArrayList<Invoice> invoiceArray = InvoiceDAO.getInstance().SelectAll();
        DefaultTableModel model = (DefaultTableModel) invoiceListTable.getModel();
        model.setRowCount(0);
        for (Invoice p : invoiceArray) {
            model.addRow(new Object[] { p.getInvoiceCode(),p.getEmpName(),p.getCusName(),p.getDateCreate(),p.getTotal() });
        }
        invoiceListTable.setModel(model);
    }
    
    public static void updateCustomerFavListTable() {
        ArrayList<Customer> customerArray = CustomerDAO.SelectAllFav();
        DefaultTableModel model = (DefaultTableModel) cusFav.getModel();
        model.setRowCount(0);
        for (Customer p : customerArray) {
            model.addRow(new Object[] {p.getName(),p.getPhoneNumber(),p.getAddress(),p.getEmail(),p.getInvoiceCount()});
        }
        cusFav.setModel(model);
    }
    
    public static void updatePetListTable() {
        ArrayList<PetDetail> pdArray = PetDetailDAO.getInstance().SelectAll();
        ArrayList<Pet> pArray = PetDAO.getInstance().SelectAll();
        DefaultTableModel model = (DefaultTableModel) petTab.getModel();
        model.setRowCount(0);
        for (PetDetail p : pdArray) {
            model.addRow(new Object[] {p.getPetCode(),p.getPetName(),p.getPrice()});
        }
        for (Pet p : pArray) {
            model.addRow(new Object[] {p.getCode(),p.getName(),""});
        }
        petTab.setModel(model);
    }


    public static void updateProductStatisticListTable() {
        ArrayList<Product> productArray = ProductDAO.getInstance().SelectAll();
        DefaultTableModel model = (DefaultTableModel) productStatistic.getModel();
        model.setRowCount(0);
        for (Product p : productArray) {
            int stock = p.getQuantity();
            int sold = ProductDAO.getSoldCount(p.getProductCode());
            model.addRow(new Object[] {p.getProductCode(),p.getProductName(), stock + sold,stock, sold,ProductDAO.getRevenue(p.getProductCode())});
        }
        productStatistic.setModel(model);
    }
    
    public static void updateServiceStatisticListTable() {
        ArrayList<Service> arr = ServiceDAO.getInstance().SelectAll();
        System.out.println(arr);
        DefaultTableModel model = (DefaultTableModel) serviceS.getModel();
        model.setRowCount(0);
        for (Service p : arr) {
            int quan = ServiceDAO.getCount(p.getServiceCode());
            int re = ServiceDAO.getRevenue(p.getServiceCode());
            model.addRow(new Object[] {p.getServiceCode(),p.getServiceName(),quan,re});
        }
        serviceS.setModel(model);
    }
    
    public static void  updateStatistic() {
        updateServiceStatisticListTable();
        updateProductStatisticListTable();
        updateCustomerFavListTable();
        updateInvoiceListTable();
        updatePetListTable();
    }
    
    public ManagerForm() {
        initComponents();
        this.setIconImage(new ImageIcon("Images/pet-shop.PNG").getImage());
        updateProductListTable();
        updateServiceTable();
        updatePetTable();
        updateEmpTable();
        updatePetList();
        updateProductList();
        updateServiceList();
        updateCusTable();
        updateInvoiceListTable();
        updateCustomerFavListTable();
        updateProductStatisticListTable();
        updateServiceStatisticListTable();
        updatePetListTable();
    } 
    
    
    public ManagerForm(String code) {
        currentAcc = AccountDAO.getAccountFromAccountId(code);
        currentEmp = EmployeeDAO.getEmpFromAccountId(currentAcc.getAccountId());
        
        initComponents();
        role.setText(currentAcc.getAccountRole());
        curName.setText(currentEmp.getName());
        nameEmp.setText(currentEmp.getName());
        curDay.setText((String) formatter.format(date));
        this.setIconImage(new ImageIcon("Images/pet-shop.PNG").getImage());
        updateProductListTable();
        updateServiceTable();
        updatePetTable();
        updateEmpTable();
        updatePetList();
        updateProductList();
        updateServiceList();
        updateCusTable();
        String role = currentAcc.getAccountRole().toString();
        updateInvoiceListTable();
        updateCustomerFavListTable();
        System.out.println(currentAcc.getAccountRole().toString().length());
        System.out.println("Nhân viên");
        System.out.println(currentAcc.getAccountRole().toString().equals("Nhân viên"));
        if (currentAcc.getAccountRole().equals("Nhân viên")) {
            Container parent = empBtn.getParent();
            parent.remove(empBtn);
            parent.validate();
            parent.repaint();

        }
        updateProductStatisticListTable();
        updateServiceStatisticListTable();
        updatePetListTable();
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel24 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        invoiceBtn = new javax.swing.JLabel();
        serviceBtn = new javax.swing.JLabel();
        PetBtn = new javax.swing.JLabel();
        productBtn = new javax.swing.JLabel();
        customerBtn = new javax.swing.JLabel();
        statisticBtn = new javax.swing.JLabel();
        empBtn = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        invoiceLayout = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        curName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        curDay = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cusPhone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cusName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        petList = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        serviceList = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        sumPrice = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        serviceLayout = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        searchService = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        petLayout = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        searchBtnPetMan = new javax.swing.JButton();
        searchPetMan = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        serviceTypeTable = new javax.swing.JTable();
        productLayout = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        productListTable = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        searchBtnProduct = new javax.swing.JButton();
        productSearchMan = new javax.swing.JTextField();
        customerLayout = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        searchCusBtnMan = new javax.swing.JButton();
        customerSearchMan = new javax.swing.JTextField();
        statisticLayout = new javax.swing.JPanel();
        searchSer = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        productStatistic = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        searchProduct = new javax.swing.JTextField();
        searchProBtn = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        serviceS = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        searchStaField = new javax.swing.JTextField();
        searchSerBtn = new javax.swing.JButton();
        petS = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        petTab = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        searchPetBtn = new javax.swing.JButton();
        searchPet = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        invoiceListTable = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        searchInvoice = new javax.swing.JTextField();
        searchInvoiceBtn = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        cusFav = new javax.swing.JTable();
        empLayout = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        empTable = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        searchBtnEmpMan = new javax.swing.JButton();
        empSearchMan = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        nameEmp = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        role = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon("Image/pet-shop.PNG").getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        menu.setBackground(new java.awt.Color(153, 255, 153));
        menu.setLayout(new java.awt.GridLayout(7, 0, 10, 0));

        invoiceBtn.setBackground(new java.awt.Color(51, 0, 51));
        invoiceBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        invoiceBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invoiceBtn.setText("Hóa đơn");
        invoiceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoiceBtnMouseClicked(evt);
            }
        });
        menu.add(invoiceBtn);

        serviceBtn.setBackground(new java.awt.Color(51, 0, 51));
        serviceBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        serviceBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceBtn.setText("Dịch vụ");
        serviceBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        serviceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serviceBtnMouseClicked(evt);
            }
        });
        menu.add(serviceBtn);

        PetBtn.setBackground(new java.awt.Color(51, 0, 51));
        PetBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PetBtn.setText("Thú cưng");
        PetBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        PetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PetBtnMouseClicked(evt);
            }
        });
        menu.add(PetBtn);

        productBtn.setBackground(new java.awt.Color(51, 0, 51));
        productBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        productBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productBtn.setText("Sản phẩm");
        productBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        productBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productBtnMouseClicked(evt);
            }
        });
        menu.add(productBtn);

        customerBtn.setBackground(new java.awt.Color(51, 0, 51));
        customerBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        customerBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customerBtn.setText("Khách hàng");
        customerBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        customerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerBtnMouseClicked(evt);
            }
        });
        menu.add(customerBtn);

        statisticBtn.setBackground(new java.awt.Color(51, 0, 51));
        statisticBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statisticBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statisticBtn.setText("Thống kê");
        statisticBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        statisticBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statisticBtnMouseClicked(evt);
            }
        });
        menu.add(statisticBtn);

        empBtn.setBackground(new java.awt.Color(51, 0, 51));
        empBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        empBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empBtn.setText("Nhân viên");
        empBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        empBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empBtnMouseClicked(evt);
            }
        });
        menu.add(empBtn);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 255), 2, true));
        jPanel4.setLayout(new java.awt.CardLayout());

        invoiceLayout.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(127, 219, 255));

        jPanel6.setBackground(new java.awt.Color(51, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐĂNG KÝ HÓA ĐƠN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nhân viên");

        curName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        curName.setText("Nguyễn Lê Khang");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Ngày tạo hóa đơn");

        curDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        curDay.setText("22-5-2023");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("SĐT khách hàng");

        cusPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cusPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusPhoneActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        cusName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thú cưng");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Sản phẩm");

        productList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productList);

        petList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        petList.setDoubleBuffered(true);
        petList.setInheritsPopupMenu(true);
        petList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                petListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(petList);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Dịch vụ");

        serviceList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serviceListMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(serviceList);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(cusPhone)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(curName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(curDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(curDay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cusName)
                    .addComponent(cusPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255)));
        jPanel8.setPreferredSize(new java.awt.Dimension(800, 617));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        productTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại", "Mã", "Tên", "Số lượng", "Giá thành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setFillsViewportHeight(true);
        productTable.setGridColor(new java.awt.Color(255, 255, 255));
        productTable.setSelectionBackground(new java.awt.Color(51, 255, 255));
        productTable.setShowGrid(true);
        productTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setResizable(false);
            productTable.getColumnModel().getColumn(1).setResizable(false);
            productTable.getColumnModel().getColumn(2).setResizable(false);
            productTable.getColumnModel().getColumn(3).setResizable(false);
            productTable.getColumnModel().getColumn(4).setResizable(false);
        }

        sumPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sumPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sumPrice.setText("Tổng Tiền:  ");

        jButton20.setBackground(new java.awt.Color(153, 255, 153));
        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton20.setForeground(new java.awt.Color(102, 0, 51));
        jButton20.setText("Xóa");
        jButton20.setPreferredSize(new java.awt.Dimension(131, 31));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(153, 255, 153));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton21.setForeground(new java.awt.Color(102, 0, 51));
        jButton21.setText("Tạo hóa đơn");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(153, 255, 153));
        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton19.setForeground(new java.awt.Color(102, 0, 51));
        jButton19.setText("Làm mới");
        jButton19.setMinimumSize(new java.awt.Dimension(131, 31));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(sumPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton21)
                        .addGap(18, 18, 18)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sumPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout invoiceLayoutLayout = new javax.swing.GroupLayout(invoiceLayout);
        invoiceLayout.setLayout(invoiceLayoutLayout);
        invoiceLayoutLayout.setHorizontalGroup(
            invoiceLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        invoiceLayoutLayout.setVerticalGroup(
            invoiceLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invoiceLayoutLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(invoiceLayout, "card3");

        serviceLayout.setBackground(new java.awt.Color(153, 255, 153));

        jPanel10.setBackground(new java.awt.Color(51, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(457, 41));

        searchService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchServiceActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("QUẢN LÝ DỊCH VỤ");

        searchBtn.setBackground(new java.awt.Color(153, 255, 153));
        searchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(102, 0, 51));
        searchBtn.setText("Tìm kiếm");
        searchBtn.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchService, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchService)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.BorderLayout());

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Ghi chú", "Giá bán"
            }
        ));
        serviceTable.setFillsViewportHeight(true);
        jScrollPane5.setViewportView(serviceTable);

        jPanel11.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jButton22.setBackground(new java.awt.Color(153, 255, 153));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton22.setForeground(new java.awt.Color(102, 0, 51));
        jButton22.setText("Xóa");
        jButton22.setPreferredSize(new java.awt.Dimension(131, 31));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(153, 255, 153));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton23.setForeground(new java.awt.Color(102, 0, 51));
        jButton23.setText("Sửa");
        jButton23.setPreferredSize(new java.awt.Dimension(131, 31));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(153, 255, 153));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton24.setForeground(new java.awt.Color(102, 0, 51));
        jButton24.setText("Thêm");
        jButton24.setPreferredSize(new java.awt.Dimension(131, 31));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(526, Short.MAX_VALUE)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout serviceLayoutLayout = new javax.swing.GroupLayout(serviceLayout);
        serviceLayout.setLayout(serviceLayoutLayout);
        serviceLayoutLayout.setHorizontalGroup(
            serviceLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        serviceLayoutLayout.setVerticalGroup(
            serviceLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serviceLayoutLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))
        );

        jPanel4.add(serviceLayout, "card3");

        petLayout.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanel30.setBackground(new java.awt.Color(51, 255, 255));
        jPanel30.setPreferredSize(new java.awt.Dimension(913, 41));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("QUẢN LÝ THÚ CƯNG");

        searchBtnPetMan.setBackground(new java.awt.Color(153, 255, 153));
        searchBtnPetMan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtnPetMan.setText("Tìm kiếm");
        searchBtnPetMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnPetManActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                .addComponent(searchPetMan, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtnPetMan)
                .addGap(3, 3, 3))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBtnPetMan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchPetMan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.add(jPanel30, java.awt.BorderLayout.PAGE_START);

        jPanel12.add(jPanel27, java.awt.BorderLayout.PAGE_START);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jButton25.setBackground(new java.awt.Color(153, 255, 153));
        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton25.setForeground(new java.awt.Color(102, 0, 51));
        jButton25.setText("Xóa");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(153, 255, 153));
        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton26.setForeground(new java.awt.Color(102, 0, 51));
        jButton26.setText("Thêm");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(153, 255, 153));
        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton27.setForeground(new java.awt.Color(102, 0, 51));
        jButton27.setText("Sửa");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(469, Short.MAX_VALUE)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel28, java.awt.BorderLayout.PAGE_END);

        serviceTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên ", "Loại", "Ngày nhập", "Giá", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Long.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        serviceTypeTable.setFillsViewportHeight(true);
        jScrollPane7.setViewportView(serviceTypeTable);

        jPanel12.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        petLayout.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel4.add(petLayout, "card4");

        productLayout.setBackground(new java.awt.Color(153, 255, 153));
        productLayout.setForeground(new java.awt.Color(102, 0, 51));
        productLayout.setLayout(new java.awt.BorderLayout());

        productListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá nhập", "Giá bán", "Ghi chú", "Ngày nhập kho"
            }
        ));
        productListTable.setFillsViewportHeight(true);
        jScrollPane8.setViewportView(productListTable);

        productLayout.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jButton7.setBackground(new java.awt.Color(153, 255, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(102, 0, 51));
        jButton7.setText("Thêm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(153, 255, 153));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton8.setForeground(new java.awt.Color(102, 0, 51));
        jButton8.setText("Sửa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(153, 255, 153));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton9.setForeground(new java.awt.Color(102, 0, 51));
        jButton9.setText("Xóa");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(536, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        productLayout.add(jPanel14, java.awt.BorderLayout.PAGE_END);

        jPanel15.setBackground(new java.awt.Color(51, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(913, 50));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("DANH SÁCH SẢN PHẨM");

        searchBtnProduct.setBackground(new java.awt.Color(153, 255, 153));
        searchBtnProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtnProduct.setText("Tìm kiếm");
        searchBtnProduct.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        searchBtnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(productSearchMan, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(productSearchMan)
                    .addComponent(searchBtnProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        productLayout.add(jPanel15, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(productLayout, "card5");

        customerLayout.setBackground(new java.awt.Color(153, 255, 153));
        customerLayout.setForeground(new java.awt.Color(102, 0, 51));
        customerLayout.setLayout(new java.awt.BorderLayout());

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "SDT", "Tên khách hàng", "Địa chỉ", "Email", "Ngày sinh"
            }
        ));
        customerTable.setFillsViewportHeight(true);
        jScrollPane9.setViewportView(customerTable);

        customerLayout.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jButton10.setBackground(new java.awt.Color(153, 255, 153));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton10.setForeground(new java.awt.Color(102, 0, 51));
        jButton10.setText("Thêm");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(153, 255, 153));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton11.setForeground(new java.awt.Color(102, 0, 51));
        jButton11.setText("Sửa");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(153, 255, 153));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton12.setForeground(new java.awt.Color(102, 0, 51));
        jButton12.setText("Xóa");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(535, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        customerLayout.add(jPanel16, java.awt.BorderLayout.PAGE_END);

        jPanel17.setBackground(new java.awt.Color(51, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("DANH SÁCH KHÁCH HÀNG");

        searchCusBtnMan.setBackground(new java.awt.Color(153, 255, 153));
        searchCusBtnMan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchCusBtnMan.setText("Tìm kiếm\n");
        searchCusBtnMan.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        searchCusBtnMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCusBtnManActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(customerSearchMan, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchCusBtnMan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(customerSearchMan)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(searchCusBtnMan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        customerLayout.add(jPanel17, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(customerLayout, "card6");

        statisticLayout.setBackground(new java.awt.Color(255, 255, 255));
        statisticLayout.setLayout(new java.awt.BorderLayout());

        searchSer.setBackground(new java.awt.Color(153, 255, 153));
        searchSer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel20.setLayout(new java.awt.BorderLayout());

        productStatistic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tổng số lượng", "Số lượng còn lại", "Số lượng đã bán", "Tổng doanh thu"
            }
        ));
        productStatistic.setFillsViewportHeight(true);
        jScrollPane10.setViewportView(productStatistic);

        jPanel20.add(jScrollPane10, java.awt.BorderLayout.CENTER);

        jPanel21.setBackground(new java.awt.Color(51, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(908, 40));

        searchProBtn.setBackground(new java.awt.Color(153, 255, 153));
        searchProBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchProBtn.setText("Tìm kiếm");
        searchProBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(599, Short.MAX_VALUE)
                .addComponent(searchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchProBtn)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchProBtn))
                .addContainerGap())
        );

        jPanel20.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        searchSer.addTab("Sản phẩm", jPanel20);

        jPanel9.setLayout(new java.awt.BorderLayout());

        serviceS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Đã sử dụng", "Tổng doanh thu"
            }
        ));
        serviceS.setFillsViewportHeight(true);
        jScrollPane13.setViewportView(serviceS);

        jPanel9.add(jScrollPane13, java.awt.BorderLayout.CENTER);

        jPanel23.setBackground(new java.awt.Color(51, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(908, 50));

        searchSerBtn.setBackground(new java.awt.Color(153, 255, 153));
        searchSerBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchSerBtn.setText("Tìm kiếm");
        searchSerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(612, Short.MAX_VALUE)
                .addComponent(searchStaField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchSerBtn)
                .addGap(7, 7, 7))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchStaField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSerBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel23, java.awt.BorderLayout.PAGE_START);

        searchSer.addTab("Dịch vụ", jPanel9);

        petS.setLayout(new java.awt.BorderLayout());

        petTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thú cưng", "Tên thú cưng", "Doanh thu"
            }
        ));
        petTab.setFillsViewportHeight(true);
        jScrollPane15.setViewportView(petTab);

        petS.add(jScrollPane15, java.awt.BorderLayout.CENTER);

        jPanel29.setBackground(new java.awt.Color(51, 255, 255));
        jPanel29.setPreferredSize(new java.awt.Dimension(908, 40));

        searchPetBtn.setBackground(new java.awt.Color(153, 255, 153));
        searchPetBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchPetBtn.setText("Tìm kiếm");
        searchPetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 598, Short.MAX_VALUE)
                .addComponent(searchPet, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPetBtn)
                    .addComponent(searchPet, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        petS.add(jPanel29, java.awt.BorderLayout.PAGE_START);

        searchSer.addTab("Thú cưng", petS);

        jPanel18.setLayout(new java.awt.BorderLayout());

        invoiceListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Người tạo", "Tên khách hàng", "Ngày tạo", "Tổng tiền"
            }
        ));
        invoiceListTable.setFillsViewportHeight(true);
        jScrollPane11.setViewportView(invoiceListTable);

        jPanel18.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        jPanel22.setBackground(new java.awt.Color(51, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(908, 45));

        searchInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceActionPerformed(evt);
            }
        });

        searchInvoiceBtn.setBackground(new java.awt.Color(153, 255, 153));
        searchInvoiceBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchInvoiceBtn.setText("Tìm kiếm");
        searchInvoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInvoiceBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(578, Short.MAX_VALUE)
                .addComponent(searchInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchInvoiceBtn)
                .addGap(10, 10, 10))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchInvoiceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76))
        );

        jPanel18.add(jPanel22, java.awt.BorderLayout.PAGE_START);

        searchSer.addTab("Hóa đơn", jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new java.awt.BorderLayout());

        cusFav.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Email", "Tổng hóa đơn"
            }
        ));
        cusFav.setFillsViewportHeight(true);
        jScrollPane12.setViewportView(cusFav);
        if (cusFav.getColumnModel().getColumnCount() > 0) {
            cusFav.getColumnModel().getColumn(1).setResizable(false);
            cusFav.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel19.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        searchSer.addTab("Khách hàng quen thuộc", jPanel19);

        statisticLayout.add(searchSer, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(statisticLayout, "card7");

        empLayout.setBackground(new java.awt.Color(153, 255, 153));
        empLayout.setLayout(new java.awt.BorderLayout());

        empTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Ngày sinh", "Địa chỉ", "Email", "SĐT", "Ngày vào làm", "Lương"
            }
        ));
        empTable.setFillsViewportHeight(true);
        jScrollPane14.setViewportView(empTable);
        if (empTable.getColumnModel().getColumnCount() > 0) {
            empTable.getColumnModel().getColumn(7).setResizable(false);
        }

        empLayout.add(jScrollPane14, java.awt.BorderLayout.CENTER);

        jPanel25.setBackground(new java.awt.Color(51, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("QUẢN LÝ NHÂN VIÊN");

        searchBtnEmpMan.setBackground(new java.awt.Color(153, 255, 153));
        searchBtnEmpMan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtnEmpMan.setText("Tìm kiếm");
        searchBtnEmpMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnEmpManActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(empSearchMan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtnEmpMan, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(empSearchMan)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(searchBtnEmpMan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        empLayout.add(jPanel25, java.awt.BorderLayout.PAGE_START);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jButton13.setBackground(new java.awt.Color(153, 255, 153));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton13.setForeground(new java.awt.Color(102, 0, 51));
        jButton13.setText("Thêm");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(153, 255, 153));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton14.setForeground(new java.awt.Color(102, 0, 51));
        jButton14.setText("Sửa");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(153, 255, 153));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton15.setForeground(new java.awt.Color(102, 0, 51));
        jButton15.setText("Xóa");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(566, Short.MAX_VALUE)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        empLayout.add(jPanel26, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(empLayout, "card8");

        nameEmp.setBackground(new java.awt.Color(51, 255, 204));
        nameEmp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nameEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameEmp.setText("Tên nhân viên");
        nameEmp.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 14, 8, 4, new java.awt.Color(255, 102, 51)));
        nameEmp.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        nameEmp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton28.setBackground(new java.awt.Color(153, 255, 153));
        jButton28.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton28.setForeground(new java.awt.Color(102, 0, 51));
        jButton28.setText("Đăng xuất");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        role.setBackground(new java.awt.Color(51, 255, 204));
        role.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        role.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        role.setText("Vai trò");
        role.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 14, 8, 4, new java.awt.Color(255, 102, 51)));
        role.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        role.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(role, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nameEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void invoiceBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceBtnMouseClicked
        removeClicked(state);
        state = 1;
        invoiceBtn.setBackground(new Color(0x0074D9));
        invoiceBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        invoiceLayout.setVisible(true);
    }//GEN-LAST:event_invoiceBtnMouseClicked

    private void serviceBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceBtnMouseClicked
        // TODO add your handling code here:
        removeClicked(state);
        state = 2;
        serviceBtn.setBackground(new Color(0x0074D9));
        serviceBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        serviceLayout.setVisible(true);
//        layout1.setVisible(false);
    }//GEN-LAST:event_serviceBtnMouseClicked

    private void PetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PetBtnMouseClicked
        // TODO add your handling code here:
        removeClicked(state);
        state = 3;
        PetBtn.setBackground(new Color(0x0074D9));
        PetBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        petLayout.setVisible(true);
    }//GEN-LAST:event_PetBtnMouseClicked

    private void productBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productBtnMouseClicked
        // TODO add your handling code here:
        removeClicked(state);
        state = 4;
        productBtn.setBackground(new Color(0x0074D9));
        productBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        productLayout.setVisible(true);
    }//GEN-LAST:event_productBtnMouseClicked

    private void customerBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerBtnMouseClicked
        // TODO add your handling code here:
        removeClicked(state);
        state = 6;
        customerBtn.setBackground(new Color(0x0074D9));
        customerBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        customerLayout.setVisible(true);
    }//GEN-LAST:event_customerBtnMouseClicked

    private void statisticBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statisticBtnMouseClicked
        // TODO add your handling code here:
        removeClicked(state);
        state = 5;
        statisticBtn.setBackground(new Color(0x0074D9));
        statisticBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        statisticLayout.setVisible(true);
    }//GEN-LAST:event_statisticBtnMouseClicked

    private void empBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empBtnMouseClicked
        // TODO add your handling code here:
        removeClicked(state);
        state = 7;
        empBtn.setBackground(new Color(0x0074D9));
        empBtn.setBorder(BorderFactory.createMatteBorder(6, 14, 8, 2, Color.yellow));
        empLayout.setVisible(true);
    }//GEN-LAST:event_empBtnMouseClicked

    private void cusPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusPhoneActionPerformed

    private void cusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusNameActionPerformed

    private void searchServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchServiceActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_searchServiceActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        RegisterFrame data = new RegisterFrame();
        data.setVisible(true);
        data.pack();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void petListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_petListMouseClicked
        // TODO add your handling code here:
        int index = petList.getSelectedIndex();
        Pet pet = petArray.get(index);
        petArrayDel.add(pet);
        petArray.remove(pet);
        ((DefaultListModel) petList.getModel()).remove(index);
        DefaultTableModel model = (DefaultTableModel) productTable.getModel(); 
        model.insertRow(0,new Object[] { "Thú cưng",pet.getCode(),pet.getName(),1,pet.getPrice() });
        setSumCost();
    }//GEN-LAST:event_petListMouseClicked

    private void productListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productListMouseClicked
        // TODO add your handling code here:
        boolean isExisted = false;
        int index = productList.getSelectedIndex();
        DefaultTableModel model = (DefaultTableModel) productTable.getModel(); 
//        model.insertRow(0,new Object[] { "SP","SP001",value,1,2000000 });
        Product p = productArray.get(index);
        p.setQuantity(p.getQuantity()-1);
        if (p.getQuantity() == 0) {
            productArray.remove(index);
            productArrayDel.add(productArrayOrigin.get(index));
        } else {
            productArray.set(index,p);
        }
        updateProductList2();
        if (productTable.getRowCount() == 0) {
            model.insertRow(0,new Object[] { "Sản phẩm",p.getProductCode(),p.getProductName(),1,p.getProductPrice() });
        }
        else {
            for (int i = 0; i < productTable.getRowCount(); i++) {  
                
                if (productTable.getValueAt(i, 1).equals(p.getProductCode())) {
                    System.out.println(p.getProductCode());
                    int q = Integer.parseInt(productTable.getValueAt(i, 3).toString());
                    productTable.setValueAt(q+1,i, 3);
                    isExisted = true;
                    break;
                } 
            }
            if (!isExisted) model.insertRow(0,new Object[] { "Sản phẩm",p.getProductCode(),p.getProductName(),1,p.getProductPrice() });
        }
        setSumCost();
    }//GEN-LAST:event_productListMouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        productArrayOrigin = ProductDAO.getInstance().SelectAll();
    productArray = ProductDAO.SelectAvble();
    productArrayDel = new ArrayList<>();
    petArray = PetDAO.getInstance().SelectAll();
    petArrayDel = new ArrayList<>();
    serviceArray = ServiceDAO.getInstance().SelectAll();
        updateProductTable();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        String cName = cusName.getText();
        String cPhone = cusPhone.getText();
        if (cName.equals("")) {
            sb.append("Ten khach hang trong!\n");
            valid = false;
        }
        if (cPhone.equals("")) {
            sb.append("SDT khach hang trong!\n");
            valid = false;
        }
        if (sb.length() > 0){
                    SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
                    

                    });
        }
        
        if (valid) {

            String iCode = createInvoice();
            int n= productTable.getRowCount();
            for (int i = 0; i < n; i++) {
                String pCode = productTable.getValueAt(i, 1).toString();
                String pName = productTable.getValueAt(i, 2).toString();
                int quan = (int)productTable.getValueAt(i, 3);
                int price = (int)productTable.getValueAt(i, 4);
                if (productTable.getValueAt(i, 0).equals("Sản phẩm")) {
                    createProductDeTail(iCode, pCode, pName, price, quan);
                }
                if (productTable.getValueAt(i, 0).equals("Thú cưng")){
                    createPetDeTail(iCode, pCode, pName, price, quan);
                }
                if (productTable.getValueAt(i, 0).equals("Dịch vụ")){
                    createServiceDeTail(iCode, pCode, pName, price, quan);
                }
            }
            updateProductTable();
            updateStatistic();
        } else {
            valid = true;
        }
        updateStatistic();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:\
        int index = serviceTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một dịch vụ để sửa");
        } 
        else {
            DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String name = model.getValueAt(index, 1).toString();
            String price = model.getValueAt(index, 3).toString();
            String note = model.getValueAt(index, 2).toString();
            
            InputServiceForm data = new InputServiceForm(code,name,note,price,index);
            data.setVisible(true);
            data.pack();
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        InputServiceForm data = new InputServiceForm();
        data.setVisible(true);
        data.pack();

    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        int index = serviceTable.getSelectedRow();
        if (index != -1)   {
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dịch vụ này ?", "Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes optio
            
        
            DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            if (index != -1) model.removeRow(index);

            Service t = new Service(code);
            int deleteService = ServiceDAO.getInstance().delete(t);
            ManagerForm.updateServiceTable();
            ManagerForm.updateProductTable();
        }
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int index = productListTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một sản phẩm để sửa");
        } 
        else {
            DefaultTableModel model = (DefaultTableModel) productListTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String name = model.getValueAt(index, 1).toString();
            int quantity = Integer.parseInt(model.getValueAt(index, 2).toString());
            int inPrice = Integer.parseInt(model.getValueAt(index, 3).toString());
            int outPrice = Integer.parseInt(model.getValueAt(index, 4).toString());
            String note = model.getValueAt(index, 5).toString();
            String date = model.getValueAt(index, 6).toString();
            InputProductForm data = new InputProductForm(code,name,quantity,inPrice,outPrice,note,date,index);
            data.setVisible(true);
            data.pack();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        int index = productListTable.getSelectedRow();
        if (index > -1) {
                    if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản này ?", "Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            DefaultTableModel model = (DefaultTableModel) productListTable.getModel(); 
//            Lấy ra product ID của hàng đã đc người dùng select
            String productId = (String) model.getValueAt(index, 0);
            Product product = new Product();
            product.setProductCode(productId);
            model.removeRow(index);
            int deleteRow = ProductDAO.getInstance().delete(product);
            ManagerForm.updateProductListTable();
        ManagerForm.updateProductTable();
                    }
        }
//        ManagerForm.updateProductListTable();
//        ManagerForm.updateProductTable();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        InputCustomerForm data = new InputCustomerForm();
        data.setVisible(true);
        data.pack();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int index = customerTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một khách để sửa");
        } 
        else {
            DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String name = model.getValueAt(index, 1).toString();
            String phone = model.getValueAt(index, 2).toString();
            String address = model.getValueAt(index, 3).toString();
            String email = model.getValueAt(index, 4).toString();
            String bd = model.getValueAt(index, 5).toString();
            InputCustomerForm data = new InputCustomerForm(code,name,phone,address,email,bd,index);
            data.setVisible(true);
            data.pack();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int index = customerTable.getSelectedRow();
        if (index != -1) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng này ?", "Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
            String cusId = (String) model.getValueAt(index, 0);
            System.out.println(cusId);
            Customer customer = new Customer();
            customer.setCode(cusId);
            System.out.println(customer);
            int deleteRow = CustomerDAO.getInstance().delete(customer);
            model.removeRow(index);
            updateCustomerFavListTable();
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int index = empTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một nhân viên để sửa");
        } 
        else {
            DefaultTableModel model = (DefaultTableModel) empTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            if (code.equals(currentEmp.getCode())) {
                System.out.println(code);
                System.out.println(currentEmp.getCode());
                JOptionPane.showMessageDialog(null, "Không Thể sửa tài khoản đang đăng nhập");
                return;
            }
            String name = model.getValueAt(index, 1).toString();
            String bd = model.getValueAt(index, 2).toString();
            String add = model.getValueAt(index, 3).toString();
            String email = model.getValueAt(index, 4).toString();
            String phone = model.getValueAt(index, 5).toString();
            String sd = model.getValueAt(index, 6).toString();
//            String role = model.getValueAt(, NORMAL)
            System.out.println(code);
            String accountId = EmployeeDAO.GetAccountIdEmp(code);
            System.out.println("there");
            Account account = AccountDAO.GetAccountIdAcc(accountId);
            String salary =model.getValueAt(index, 7).toString();
            RegisterFrame data = new RegisterFrame(code,name,bd,add,email,phone,sd,salary,account.getAccountRole(),account.getAccountName(),account.getAccountPassword(),account.getAccountPassword(), index);
            data.setVisible(true);
            data.pack();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        int index = empTable.getSelectedRow();
        if (index > -1) {
            DefaultTableModel model = (DefaultTableModel) empTable.getModel(); 
//            Khởi tạo một emp với id = giá trị trong table
            String empId = (String) model.getValueAt(index, 0);
            Employee employee = new Employee();
            employee.setCode(empId);
//            Khởi tạo emp mới với đầy đủ thuộc tính
            Employee newEmp = EmployeeDAO.getInstance().SelectById(employee);
            Account account = AccountDAO.GetAccountIdAcc(newEmp.getAccountId());
            System.out.print(account);
            if (empId.equals(currentEmp.getCode())) {
                JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên đang đăng nhập");
            } else {
                int deleteRow = EmployeeDAO.getInstance().delete(employee);
                int deleteAccount = AccountDAO.getInstance().delete(account);
                model.removeRow(index);
            }
            
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        InputProductForm data = new InputProductForm();
        data.setVisible(true);
        data.pack();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        int index = serviceTypeTable.getSelectedRow();
        if (index > -1) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa thú cưng này ?", "Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) serviceTypeTable.getModel(); 
//            Lấy ra product ID của hàng đã đc người dùng select
            String petId = (String) model.getValueAt(index, 0);
            Pet pet = new Pet();
            pet.setCode(petId);
            model.removeRow(index);
            int deleteRow = PetDAO.getInstance().delete(pet);
            updatePetTable();
        petArray = PetDAO.getInstance().SelectAll();
        updateProductTable();
            }
        }
        
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        InputPetForm1 input = new InputPetForm1();
        input.setVisible(true);
        
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        int index = serviceTypeTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một thú cưng để sửa");
        } 
        else {
            DefaultTableModel model = (DefaultTableModel) serviceTypeTable.getModel();
            String code = model.getValueAt(index, 0).toString();
            String name = model.getValueAt(index, 1).toString();
            String type = model.getValueAt(index, 2).toString();
            String date = model.getValueAt(index, 3).toString();
            int price = Integer.parseInt(model.getValueAt(index, 4).toString()) ;
            String note = model.getValueAt(index, 5).toString();
            InputPetForm1 data = new InputPetForm1(code, name, type, note, date, price, index);
            data.setVisible(true);
            data.pack();
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        int index = productTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        String id = model.getValueAt(index, 1).toString();
        String type = model.getValueAt(index, 0).toString();
        if (type.equals("Thú cưng")) { 
                System.out.println("petttttttttttttttttttt");
                reAddPet(id);
                model.removeRow(index);
                updatePetTable();
             
        }
        if (type.equals("Sản phẩm")) {
            int q = (int) model.getValueAt(index, 3) - 1;
            if (q == 0) {
                   
                    model.removeRow(index);
            } else {
                productTable.setValueAt(q, index, 3);
                    
            }
            reAddProduct(id);
            updateProductList();
        }
        if (type.equals("Dịch vụ")) {
            int q = (int) model.getValueAt(index, 3) - 1;
            if (q == 0) {
                    model.removeRow(index);
            } else {
                productTable.setValueAt(q, index, 3);
                    
            }
        }
        setSumCost();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void serviceListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceListMouseClicked
        // TODO add your handling code here:
        int index = serviceList.getSelectedIndex();
        boolean isExisted = false;
        Service s = serviceArray.get(index);
        DefaultTableModel model = (DefaultTableModel) productTable.getModel(); 
        if (productTable.getRowCount() == 0) {
            model.insertRow(0,new Object[] { "Dịch vụ",s.getServiceCode(),s.getServiceName(),1,s.getServicePrice() });
        }
        else {
            for (int i = 0; i < productTable.getRowCount(); i++) {  
                
                if (productTable.getValueAt(i, 1).equals(s.getServiceCode())) {
//                    System.out.println(p.getProductCode());
                    int q = Integer.parseInt(productTable.getValueAt(i, 3).toString());
                    productTable.setValueAt(q+1,i, 3);
                    isExisted = true;
                    break;
                } 
            }
            if (!isExisted) model.insertRow(0,new Object[] { "Dịch vụ",s.getServiceCode(),s.getServiceName(),1,s.getServicePrice() });
        }
       
        setSumCost();
    }//GEN-LAST:event_serviceListMouseClicked

    private void searchInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInvoiceActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.setRowCount(0);
        
        String search = searchService.getText();
        if (search.equals("")){
            updateServiceStatisticListTable();
        }
        ArrayList<Service> serList = ServiceDAO.findServiceByIdOrName(search);
        if (serList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Service p : serList){
                model.addRow(new Object[]{p.getServiceCode(), p.getServiceName(), p.getServiceNotes(), p.getServicePrice()});
            }
            serviceTable.setModel(model);
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchInvoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInvoiceBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) invoiceListTable.getModel();
        model.setRowCount(0);
        
        String search = searchInvoice.getText();
        if (search.equals("")){
            updateInvoiceListTable();
            return;
        }
        ArrayList<Invoice> invoiceList = InvoiceDAO.findServiceByIdOrName(search);
        if (invoiceList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Invoice p : invoiceList){
                model.addRow(new Object[]{p.getInvoiceCode(), p.getEmpName(), p.getCusName(), p.getDateCreate(), p.getTotal()});
            }
            invoiceListTable.setModel(model);
        }
    }//GEN-LAST:event_searchInvoiceBtnActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        dispose();
        new LoginFrame();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void searchProBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) productStatistic.getModel();
        model.setRowCount(0);
        
        String search = searchProduct.getText();
        if (search.equals("")){
            updateProductStatisticListTable();
            return;
        }
        ArrayList<Product> proList = ProductDAO.findServiceByIdOrName(search);
        if (proList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Product p : proList){
                int stock = p.getQuantity();
                int sold = ProductDAO.getSoldCount(p.getProductCode());
                model.addRow(new Object[] {p.getProductCode(),p.getProductName(), stock + sold,stock, sold,ProductDAO.getRevenue(p.getProductCode())});            
            }
                productStatistic.setModel(model);
        }
    }//GEN-LAST:event_searchProBtnActionPerformed

    private void searchSerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSerBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) serviceS.getModel();
        model.setRowCount(0);
        
        String search = searchStaField.getText();
        if (search.equals("")){
            updateServiceStatisticListTable();
            return;
        }
        ArrayList<Service> serList = ServiceDAO.findServiceByIdOrName(search);
        if (serList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Service p : serList){
                int quan = ServiceDAO.getCount(p.getServiceCode());
                int re = ServiceDAO.getRevenue(p.getServiceCode());
                model.addRow(new Object[] {p.getServiceCode(),p.getServiceName(),quan,re});           
            }
                serviceS.setModel(model);
        }
        
    }//GEN-LAST:event_searchSerBtnActionPerformed

    private void searchPetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPetBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) petTab.getModel();
        model.setRowCount(0);
        
        String search = searchPet.getText();
        if (search.equals("")){
            updatePetListTable();
            return;
        }
        ArrayList<Pet> petList = PetDAO.findServiceByIdOrName(search);
        ArrayList<PetDetail> petDetail = PetDetailDAO.findServiceByIdOrName(search);
        if (petList.isEmpty() && petDetail.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (PetDetail p : petDetail) {
                model.addRow(new Object[] {p.getPetCode(),p.getPetName(),p.getPrice()});
            }
            for (Pet p : petList){
                model.addRow(new Object[] {p.getCode(),p.getName(),""});           
            }
            petTab.setModel(model);
        }
        
    }//GEN-LAST:event_searchPetBtnActionPerformed

    private void searchBtnPetManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnPetManActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) serviceTypeTable.getModel();
        model.setRowCount(0);
        
        String search = searchPetMan.getText();
        if (search.equals("")){
            updatePetTable();
            return;
        }
        ArrayList<Pet> petList = PetDAO.findServiceByIdOrName(search);
        
        if (petList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Pet p : petList){
                model.addRow(new Object[] {p.getCode(),p.getName(),p.getType(), p.getDateOfAcc(), p.getPrice(), p.getNotes()});           
            }
            serviceTypeTable.setModel(model);
        }
        
    }//GEN-LAST:event_searchBtnPetManActionPerformed

    private void searchBtnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnProductActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) productListTable.getModel();
        model.setRowCount(0);
        
        String search = productSearchMan.getText();
        if (search.equals("")){
            updateProductTable();
            return;
        }
        ArrayList<Product> proList = ProductDAO.findServiceByIdOrName(search);
        
        if (proList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Product p : proList){
                model.addRow(new Object[] {p.getProductCode(),p.getProductName(),p.getQuantity(), p.getImportPrice(), p.getProductPrice(), p.getProductNotes(), p.getDateAdded()});           
            }
            productListTable.setModel(model);
        }
    }//GEN-LAST:event_searchBtnProductActionPerformed

    private void searchCusBtnManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCusBtnManActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        model.setRowCount(0);
        
        String search = customerSearchMan.getText();
        if (search.equals("")){
            updateCusTable();
            return;
        }
        ArrayList<Customer> cusList = CustomerDAO.findServiceByIdOrName(search);
        
        if (cusList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Customer p : cusList){
                model.addRow(new Object[] {p.getCode(),p.getPhoneNumber(),p.getName(), p.getAddress(), p.getEmail(), p.getDateOfBirth()});           
            }
            customerTable.setModel(model);
        }
    }//GEN-LAST:event_searchCusBtnManActionPerformed

    private void searchBtnEmpManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnEmpManActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) empTable.getModel();
        model.setRowCount(0);
        
        String search = empSearchMan.getText();
        if (search.equals("")){
            updateEmpTable();
            return;
        }
        ArrayList<Employee> empList = EmployeeDAO.findServiceByIdOrName(search);
        
        if (empList.isEmpty()){
            System.err.println("Khong the tim");
        }
        else {
            for (Employee p : empList){
                model.addRow(new Object[] {p.getCode(), p.getName(), p.getDateOfBirth(), p.getAddress(), p.getEmail(), p.getPhoneNumber(), p.getDateOfEmployee(),p.getSalary()});           
            }
            empTable.setModel(model);
        }
    }//GEN-LAST:event_searchBtnEmpManActionPerformed

    public static void replaceRowToServiceTable(Object[] data,int row) {
        DefaultTableModel modelservice = (DefaultTableModel) serviceTable.getModel(); 
        for (int i= 0;i < 4; i++) {
            modelservice.setValueAt(data[i], row, i);
        }
    }
    
    public static void addRowToServiceTable(Object[] data) {
        DefaultTableModel modelservice = (DefaultTableModel) serviceTable.getModel(); 
        modelservice.addRow(data);
    }
    
     public static void replaceRowToProductListTable(Object[] data,int row) {
        DefaultTableModel modelservice = (DefaultTableModel) productListTable.getModel(); 
        for (int i= 0;i < 7; i++) {
            modelservice.setValueAt(data[i], row, i);
        }
    }
    
    public static void addRowToProductListTable(Object[] data) {
        DefaultTableModel modelservice = (DefaultTableModel) productListTable.getModel(); 
        modelservice.addRow(data);
    }
    
    public static void addRowToPetTable(Object[] data) {
        DefaultTableModel modelservice = (DefaultTableModel) serviceTypeTable.getModel(); 
        modelservice.addRow(data);
    }
    
    public static void replaceRowToCustomerTable(Object[] data,int row) {
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel(); 
        for (int i= 0;i < 5; i++) {
            model.setValueAt(data[i], row, i);
        }
    }
    
    public static void addRowToCustomerTable(Object[] data) {
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel(); 
        model.addRow(data);
    }
    
    public static void replaceRowToEmpTable(Object[] data,int row) {
        DefaultTableModel model = (DefaultTableModel) empTable.getModel(); 
        for (int i= 0;i < model.getColumnCount(); i++) {
            model.setValueAt(data[i], row, i);
        }
    }
    
    public static void addRowToEmpTable(Object[] data) {
        DefaultTableModel model = (DefaultTableModel) empTable.getModel(); 
        model.addRow(data);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PetBtn;
    private static javax.swing.JLabel curDay;
    private static javax.swing.JLabel curName;
    private static javax.swing.JTable cusFav;
    private static javax.swing.JTextField cusName;
    private static javax.swing.JTextField cusPhone;
    private javax.swing.JLabel customerBtn;
    private javax.swing.JPanel customerLayout;
    private javax.swing.JTextField customerSearchMan;
    private static javax.swing.JTable customerTable;
    private javax.swing.JLabel empBtn;
    private javax.swing.JPanel empLayout;
    private javax.swing.JTextField empSearchMan;
    private static javax.swing.JTable empTable;
    private javax.swing.JLabel invoiceBtn;
    private javax.swing.JPanel invoiceLayout;
    private static javax.swing.JTable invoiceListTable;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel nameEmp;
    private javax.swing.JPanel petLayout;
    private static javax.swing.JList<String> petList;
    private javax.swing.JPanel petS;
    private static javax.swing.JTable petTab;
    private javax.swing.JLabel productBtn;
    private javax.swing.JPanel productLayout;
    private static javax.swing.JList<String> productList;
    private static javax.swing.JTable productListTable;
    private javax.swing.JTextField productSearchMan;
    private static javax.swing.JTable productStatistic;
    private static javax.swing.JTable productTable;
    private javax.swing.JLabel role;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton searchBtnEmpMan;
    private javax.swing.JButton searchBtnPetMan;
    private javax.swing.JButton searchBtnProduct;
    private javax.swing.JButton searchCusBtnMan;
    private javax.swing.JTextField searchInvoice;
    private javax.swing.JButton searchInvoiceBtn;
    private javax.swing.JTextField searchPet;
    private javax.swing.JButton searchPetBtn;
    private javax.swing.JTextField searchPetMan;
    private javax.swing.JButton searchProBtn;
    private javax.swing.JTextField searchProduct;
    private javax.swing.JTabbedPane searchSer;
    private javax.swing.JButton searchSerBtn;
    private javax.swing.JTextField searchService;
    private javax.swing.JTextField searchStaField;
    private javax.swing.JLabel serviceBtn;
    private javax.swing.JPanel serviceLayout;
    private static javax.swing.JList<String> serviceList;
    private static javax.swing.JTable serviceS;
    private static javax.swing.JTable serviceTable;
    private static javax.swing.JTable serviceTypeTable;
    private javax.swing.JLabel statisticBtn;
    private javax.swing.JPanel statisticLayout;
    private static javax.swing.JLabel sumPrice;
    // End of variables declaration//GEN-END:variables
}
