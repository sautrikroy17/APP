import java.util.Scanner;
class student{
    String name;
    int roll;
    String dept;
void display(){
    System.out.println("Name= "+name);
    System.out.println("Roll number "+roll);
    System.out.println("department "+dept);
}
public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    student stud=new student();
    System.out.println("Enter name");
    stud.name=sc.nextLine();
    System.out.println("Enter roll number");
    stud.roll=sc.nextInt();
    sc.nextLine();
    System.out.println("Enter department");
    stud.dept=sc.nextLine();
    stud.display();
    sc.close();
}}