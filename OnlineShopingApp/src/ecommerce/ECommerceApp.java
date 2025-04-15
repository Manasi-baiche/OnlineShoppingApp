package ecommerce;

import java.util.Map;
import java.util.Scanner;

public class ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EcommerceService service = new EcommerceService();

        while (true) {
            System.out.println("1. Admin Menu\n2. Customer Menu\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                adminMenu(sc, service);
            } else if (choice == 2) {
                customerMenu(sc, service);
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }

    private static void adminMenu(Scanner sc, EcommerceService service) {
        while (true) {
            System.out.println("Admin Menu:\n1. Add Product\n2. Remove Product\n3. View Products\n4. Create Admin\n5. View Admins\n6. Update Order Status\n7. View Orders\n8. Return");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int qty = sc.nextInt();
                    service.addProduct(new Product(id, name, price, qty));
                    System.out.println("Product added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter Product ID to remove: ");
                    int id = sc.nextInt();
                    service.removeProduct(id);
                    System.out.println("Product removed!");
                }
                case 3 -> {
                    for (Product p : service.getProducts()) System.out.println(p);
                }
                case 4 -> {
                    System.out.print("Enter Admin ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    service.addAdmin(new Admin(id, username, email));
                    System.out.println("Admin created!");
                }
                case 5 -> {
                    for (Admin a : service.getAdmins())
                        System.out.println("Admin ID: " + a.getUserId() + ", Username: " + a.getUsername() + ", Email: " + a.getEmail());
                }
                case 6 -> {
                    System.out.print("Enter Order ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String status = sc.nextLine();
                    service.updateOrderStatus(id, status);
                }
                case 7 -> {
                    for (Order o : service.getOrders()) {
                        System.out.println("Order ID: " + o.getOrderId() + ", Customer: " + o.getCustomer().getUsername() + ", Status: " + o.getStatus());
                        for (Map.Entry<Product, Integer> entry : o.getProducts().entrySet()) {
                            System.out.println(" Product: " + entry.getKey().getName() + ", Quantity: " + entry.getValue());
                        }
                    }
                }
                case 8 -> {
                    System.out.println("Exiting Admin...");
                    return;
                }
            }
        }
    }

    private static void customerMenu(Scanner sc, EcommerceService service) {
        while (true) {
            System.out.println("Customer Menu:\n1. Create Customer\n2. View Customers\n3. Place Order\n4. View Orders\n5. View Products\n6. Return");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    service.addCustomer(new Customer(id, username, email, address));
                    System.out.println("Customer created successfully!");
                }
                case 2 -> {
                    for (Customer c : service.getCustomers())
                        System.out.println("User ID: " + c.getUserId() + ", Username: " + c.getUsername() + ", Email: " + c.getEmail() + ", Address: " + c.getAddress());
                }
                case 3 -> {
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    Customer customer = service.findCustomerById(id);
                    if (customer == null) {
                        System.out.println("Customer not found!");
                        break;
                    }
                    while (true) {
                        System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                        int productId = sc.nextInt();
                        if (productId == -1) break;
                        Product product = service.findProductById(productId);
                        if (product != null) {
                            System.out.print("Enter quantity: ");
                            int quantity = sc.nextInt();
                            customer.getShoppingCart().addItem(product, quantity);
                        } else {
                            System.out.println("Invalid Product ID.");
                        }
                    }
                    service.placeOrder(customer, customer.getShoppingCart());
                    System.out.println("Order placed successfully!");
                }
                case 4 -> {
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    Customer customer = service.findCustomerById(id);
                    if (customer != null) {
                        for (Order o : customer.getOrders()) {
                            System.out.println("Order ID: " + o.getOrderId() + ", Status: " + o.getStatus());
                            for (Map.Entry<Product, Integer> entry : o.getProducts().entrySet()) {
                                System.out.println(" Product: " + entry.getKey().getName() + ", Quantity: " + entry.getValue());
                            }
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                }
                case 5 -> {
                    for (Product p : service.getProducts()) System.out.println(p);
                }
                case 6 -> {
                    System.out.println("Exiting Customer Menu...");
                    return;
                }
            }
        }
    }
}
