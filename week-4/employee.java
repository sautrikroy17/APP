import java.util.*;
class Employee{
    int employeeId;
    String employeeName;
    int salary;
Employee(int employeeId, String employeeName, int salary) {
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.salary = salary;
}
void display(){
    System.out.println("Employee Id: " + employeeId);
    System.out.println("Employee Name: " + employeeName);
    System.out.println("Salary: " + salary);
}
void annualSalary() {
    int annualSalary = salary * 12;
    System.out.println("Annual Salary of " + employeeName + " is: " + annualSalary);
    int bonus=0;
    if(salary>30000){
        System.out.println("Eligible for bonus");
        bonus=annualSalary*10/100;
        System.out.println("Bonus of " + employeeName + " is: " + bonus);}
    else{  
        System.out.println("Not eligible for bonus");
    }
    }
public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);    
    Employee[] emp = new Employee[5];
    for(int i=0; i<5; i++) {
    System.out.println("Enter Employee Id: ");
    int employeeId = sc.nextInt();
    System.out.println("Enter Employee Name: ");
    String employeeName = sc.next();
    System.out.println("Enter Employee Salary: ");
    int salary = sc.nextInt();
    emp[i] = new Employee(employeeId, employeeName, salary);
    emp[i].display();
    emp[i].annualSalary();
}
    sc.close();
}
}
