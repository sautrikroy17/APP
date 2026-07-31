import java.util.Scanner;
class student1{
    String name;
    int age;
    void display(){
        System.out.println("Name of student ="+name);
        System.out.println("Age of student= "+age);
    }
public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    student1 s1=new student1();
    student1 s2=new student1();
    System.out.println("Enter name of student 1 ");
    s1.name=sc.nextLine();
    System.out.println("enter age of student 1");
    s1.age=sc.nextInt();
    sc.nextLine();  //nextLine() sees the enter key and this input is finished so we use one extra nextline to remove it
    System.out.println("Enter name of student 2");
    s2.name=sc.nextLine();
    System.out.println("Enter age of student 2");
    s2.age=sc.nextInt();
    s1.display();
    s2.display();
    sc.close();
}
}

