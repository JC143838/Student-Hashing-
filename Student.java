import java.util.Scanner;

class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class HashTable {
    private Student[] table;
    private int size;

    public HashTable(int capacity) {
        table = new Student[capacity];
        size = 0;
    }

    // Hash function
    private int hash(int id) {
        return id % table.length;
    }

    // Add student
    public void add(Student student) {
        int index = hash(student.id);
        if (table[index] == null) {
            table[index] = student;
            size++;
        } else {
            System.out.println("Collision! Student not added.");
        }
    }

    // Remove student
    public void remove(int id) {
        int index = hash(id);
        if (table[index] != null && table[index].id == id) {
            table[index] = null;
            size--;
        }
    }

    // Search student
    public Student search(int id) {
        int index = hash(id);
        if (table[index] != null && table[index].id == id) {
            return table[index];
        }
        return null;
    }

    // Display students
    public void display() {
        for (Student student : table) {
            if (student != null) {
                System.out.println("ID: " + student.id + ", Name: " + student.name);
            }
        }
    }
}

public class StudentDatabase {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    hashTable.add(new Student(id, name));
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    id = scanner.nextInt();
                    hashTable.remove(id);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    id = scanner.nextInt();
                    Student student = hashTable.search(id);
                    if (student != null) {
                        System.out.println("Student found: " + student.name);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    hashTable.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}