package paf.workshop.paf_24w.model;

import java.time.LocalDate;

public class Order {
    private int orderId;

    private LocalDate orderDate;

    private String customerName;
    
    private String shipAddress;

    private String notes;

    private double tax;

    public Order(int id, LocalDate date, String customerName, String shipAddress, String text, double tax) {
        this.orderId = id;
        this.orderDate = date;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = text;
        this.tax = tax;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate date) {
        this.orderDate = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String text) {
        this.notes = text;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    
    
}
