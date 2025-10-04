import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String toString() {
        return name + " - Age: " + age + " - Salary: " + salary;
    }
}

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return name + " - Marks: " + marks;
    }
}

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String toString() {
        return name + " (" + category + ") - Price: " + price;
    }
}

public class LambdaStreamExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Sujal", 23, 45000),
            new Employee("Riya", 27, 55000),
            new Employee("Aman", 21, 40000),
            new Employee("Vivek", 25, 60000)
        );

        System.out.println("Sort by Name:");
        employees.stream().sorted((e1, e2) -> e1.name.compareTo(e2.name)).forEach(System.out::println);

        System.out.println("\nSort by Age:");
        employees.stream().sorted((e1, e2) -> e1.age - e2.age).forEach(System.out::println);

        System.out.println("\nSort by Salary Descending:");
        employees.stream().sorted((e1, e2) -> Double.compare(e2.salary, e1.salary)).forEach(System.out::println);

        List<Student> students = Arrays.asList(
            new Student("Sujal", 85),
            new Student("Riya", 70),
            new Student("Aman", 90),
            new Student("Vivek", 60)
        );

        System.out.println("\nStudents Scoring Above 75% Sorted by Marks:");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks))
                .map(s -> s.name)
                .forEach(System.out::println);

        List<Product> products = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Phone", 50000, "Electronics"),
            new Product("Shirt", 2000, "Clothing"),
            new Product("Pants", 2500, "Clothing"),
            new Product("Book", 500, "Stationery")
        );

        System.out.println("\nProducts Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream().collect(Collectors.groupingBy(p -> p.category));
        grouped.forEach((cat, list) -> {
            System.out.println(cat + ": " + list);
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        Map<String, Optional<Product>> maxPrice = products.stream()
                .collect(Collectors.groupingBy(p -> p.category, Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));
        maxPrice.forEach((cat, prod) -> System.out.println(cat + ": " + prod.get()));

        double avgPrice = products.stream().collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
