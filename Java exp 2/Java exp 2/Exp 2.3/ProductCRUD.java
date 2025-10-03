import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Product");
            System.out.println("2. Read Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> createProduct(sc);
                case 2 -> readProducts();
                case 3 -> updateProduct(sc);
                case 4 -> deleteProduct(sc);
                case 5 -> {
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void createProduct(Scanner sc) {
        System.out.print("Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Product Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("INSERT INTO Product(ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)")) {

            con.setAutoCommit(false);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();
            con.commit();
            System.out.println("Product created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readProducts() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {

            System.out.println("ID\tName\tPrice\tQuantity");
            while (rs.next()) {
                System.out.println(rs.getInt("ProductID") + "\t" + rs.getString("ProductName") + "\t" + rs.getDouble("Price") + "\t" + rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct(Scanner sc) {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Product Name: ");
        String name = sc.nextLine();
        System.out.print("New Price: ");
        double price = sc.nextDouble();
        System.out.print("New Quantity: ");
        int qty = sc.nextInt();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?")) {

            con.setAutoCommit(false);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) con.commit();
            else {
                System.out.println("No product found with that ID.");
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteProduct(Scanner sc) {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {

            con.setAutoCommit(false);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) con.commit();
            else {
                System.out.println("No product found with that ID.");
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
