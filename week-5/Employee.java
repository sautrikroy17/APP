import java.util.Scanner;

public class Employee {
    protected String name;
    protected String employeeId;
    protected double basicSalary;

    public Employee(String name, String employeeId, double basicSalary) {
        this.name = name;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
    }

    public double calculateSalary() {
        return basicSalary;
    }

    public void displaySalary() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Calculated Total Salary: " + calculateSalary());
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Professor Name: ");
        String profName = scanner.nextLine();
        System.out.print("Enter Professor Employee ID: ");
        String profId = scanner.nextLine();
        System.out.print("Enter Professor Basic Salary: ");
        double profBasic = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Lab Assistant Name: ");
        String labName = scanner.nextLine();
        System.out.print("Enter Lab Assistant Employee ID: ");
        String labId = scanner.nextLine();
        System.out.print("Enter Lab Assistant Basic Salary: ");
        double labBasic = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Administrative Staff Name: ");
        String adminName = scanner.nextLine();
        System.out.print("Enter Administrative Staff Employee ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Administrative Staff Basic Salary: ");
        double adminBasic = scanner.nextDouble();

        Employee[] employees = {
            new Professor(profName, profId, profBasic),
            new LabAssistant(labName, labId, labBasic),
            new AdministrativeStaff(adminName, adminId, adminBasic)
        };

        System.out.println("\n--- University Employees Salary Summary ---");
        for (Employee emp : employees) {
            emp.displaySalary();
        }

        scanner.close();
    }
}

class Professor extends Employee {
    public Professor(String name, String employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    public double calculateSalary() {
        double da = 0.40 * basicSalary;
        double hra = 0.20 * basicSalary;
        double researchAllowance = 10000.0;
        return basicSalary + da + hra + researchAllowance;
    }
}

class LabAssistant extends Employee {
    public LabAssistant(String name, String employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    public double calculateSalary() {
        double da = 0.20 * basicSalary;
        double labAllowance = 3000.0;
        return basicSalary + da + labAllowance;
    }
}

class AdministrativeStaff extends Employee {
    public AdministrativeStaff(String name, String employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    public double calculateSalary() {
        double da = 0.30 * basicSalary;
        double adminAllowance = 5000.0;
        return basicSalary + da + adminAllowance;
    }
}
