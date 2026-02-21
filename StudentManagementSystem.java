import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagementSystem {

    static ArrayList<Student> students;
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        students = FileUtil.loadStudents(FILE_NAME);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Calculate Average Marks");
            System.out.println("6. Sort by Marks");
            System.out.println("7. View Distinction Students");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: removeStudent(); break;
                case 5: calculateAverage(); break;
                case 6: sortByMarks(); break;
                case 7: viewDistinction(); break;
                case 8: 
                    FileUtil.saveStudents(FILE_NAME, students);
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        int age;
        while (true) {
            System.out.print("Enter Age: ");
            age = Integer.parseInt(sc.nextLine());
            if (age > 0 && age <= 100) break;
            System.out.println("Invalid age! Try again.");
        }

        double marks;
        while (true) {
            System.out.print("Enter Marks: ");
            marks = Double.parseDouble(sc.nextLine());
            if (marks >= 0 && marks <= 100) break;
            System.out.println("Invalid marks! Try again.");
        }

        students.add(new Student(name, age, marks));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) s.display();
    }

    static void searchStudent() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
        boolean found = false;

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.display();
                found = true;
            }
        }

        if (!found) System.out.println("Student not found.");
    }

    static void removeStudent() {
        System.out.print("Enter name to remove: ");
        String name = sc.nextLine();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                students.remove(s);
                System.out.println("Student removed successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    static void calculateAverage() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double total = 0;
        for (Student s : students) total += s.getMarks();

        System.out.println("Average Marks: " + (total / students.size()));
    }

    static void sortByMarks() {
        Collections.sort(students, Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("Sorted by marks (high to low):");
        viewStudents();
    }

    static void viewDistinction() {
        System.out.println("=== Distinction Students ===");
        boolean found = false;
        for (Student s : students) {
            if (s.getMarks() >= 75) {
                s.display();
                found = true;
            }
        }
        if (!found) System.out.println("No distinction students found.");
    }
}