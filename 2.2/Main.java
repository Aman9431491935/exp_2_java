import java.io.*;
import java.util.*;

class PartA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter integers separated by space:");
        String[] inputs = sc.nextLine().split(" ");
        for (String s : inputs) numbers.add(Integer.parseInt(s));
        int sum = 0;
        for (Integer num : numbers) sum += num;
        System.out.println("Sum of integers: " + sum);
    }
}

class Student implements Serializable {
    int studentID;
    String name;
    double grade;
    Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }
    public String toString() {
        return "ID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

class PartB {
    public static void main(String[] args) {
        try {
            Student s1 = new Student(1, "Sujal", 8.9);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"));
            oos.writeObject(s1);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"));
            Student s2 = (Student) ois.readObject();
            ois.close();

            System.out.println("Deserialized Student: " + s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Employee {
    int id;
    String name;
    String designation;
    double salary;
    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
    public String toString() {
        return id + " " + name + " " + designation + " " + salary;
    }
}

class PartC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("employees.txt");
        while (true) {
            System.out.println("\n1. Add Employee\n2. Display Employees\n3. Exit\nEnter choice:");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    bw.write(id + "," + name + "," + designation + "," + salary);
                    bw.newLine();
                    System.out.println("Employee added successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 2) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    System.out.println("\nEmployee Records:");
                    while ((line = br.readLine()) != null) System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
