package ecommerce;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int counter = 1;
    private int orderId;
    private Customer customer;
    private Map<Product, Integer> products;
    private String status;

    public Order(Customer customer) {
        this.orderId = counter++;
        this.customer = customer;
        this.products = new HashMap<>();
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<Product, Integer> getProducts() { return products; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }
}
