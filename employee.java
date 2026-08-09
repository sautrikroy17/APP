import java.util.Scanner;
class employee {
    int employeeId;
    String employeeName;
    double salary;
    employee(int employeeId,String employeeName,double salary) {
     this.employeeId=employeeId;
     this.employeeName=employeeName;
     this.salary=salary;
    }
    void display(){
        System.out.println("Employee Id= "+employeeId);
        System.out.println("Employee Name= "+employeeName);
        System.out.println("Salary= "+salary);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter employee ID ");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Employee Name ");
        String name=sc.nextLine();
        System.out.println("Enter Salary ");
        double sal=sc.nextInt();
        employee emp = new employee(id, name, sal);
        emp.display();
        sc.close();
    }
}