import java.util.Scanner;
class Student {
    String name;
    int roll;
    void display() {  //method defined inside a class
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + roll);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student s = new Student();
        System.out.print("Enter name: ");
        s.name = sc.nextLine();
        System.out.print("Enter roll number: ");
        s.roll = sc.nextInt();
        s.display();
        sc.close();
    }
}