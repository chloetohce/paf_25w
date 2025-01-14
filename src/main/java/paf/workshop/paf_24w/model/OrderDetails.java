package paf.workshop.paf_24w.model;

public class OrderDetails {
    private int id;

    private String product;

    private double unitPrice;

    private double discount;

    private int quantity;

    private int orderId;

    public OrderDetails() {
    }

    public OrderDetails(int id, String product, double unitPrice, double discount, int quantity, int orderId) {
        this.id = id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    
    
}
