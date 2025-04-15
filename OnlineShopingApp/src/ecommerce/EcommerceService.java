package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class EcommerceService {
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.removeIf(p -> p.getProductId() == productId);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id) return c;
        }
        return null;
    }

    public Product findProductById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) return p;
        }
        return null;
    }

    public void placeOrder(Customer customer, ShoppingCart cart) {
        Order order = new Order(customer);
        for (Product p : cart.getItems().keySet()) {
            int quantity = cart.getItems().get(p);
            if (p.getStockQuantity() >= quantity) {
                p.setStockQuantity(p.getStockQuantity() - quantity);
                order.addProduct(p, quantity);
            }
        }
        orders.add(order);
        customer.getOrders().add(order);
        cart.clearCart();
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
                break;
            }
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
