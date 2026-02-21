public class Student {

    private String name;
    private int age;
    private double marks;

    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }

    public String getResult() {
        if (marks >= 75) return "Distinction";
        else if (marks >= 40) return "Pass";
        else return "Fail";
    }

    // Display method
    public void display() {
        System.out.println("----------------------------");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
        System.out.println("Result: " + getResult());
    }
}