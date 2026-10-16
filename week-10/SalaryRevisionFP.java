import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryRevisionFP {

    public static class Employee {
        private final String name;
        private final double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return String.format("%s: ₹%.2f", name, salary);
        }
    }

    public static List<Employee> findEligibleEmployees(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getSalary() < 40000.0)
                .collect(Collectors.toList());
    }

    public static List<Employee> reviseSalaries(List<Employee> employees) {
        return employees.stream()
                .map(e -> {
                    if (e.getSalary() < 40000.0) {
                        return new Employee(e.getName(), e.getSalary() * 1.10);
                    }
                    return new Employee(e.getName(), e.getSalary());
                })
                .collect(Collectors.toList());
    }

    public static double calculateTotalExpenditure(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
    }

    public static void main(String[] args) {
        List<Employee> staff = Arrays.asList(
                new Employee("Ravi", 35000.0),
                new Employee("Priya", 42000.0),
                new Employee("Kumar", 28000.0),
                new Employee("Meena", 55000.0),
                new Employee("Suresh", 39000.0),
                new Employee("Ananya", 60000.0)
        );

        System.out.println("Original Employee Salaries:");
        staff.forEach(System.out::println);

        List<Employee> eligible = findEligibleEmployees(staff);
        System.out.println("\nEmployees Eligible for 10% Increment (< ₹40,000) (filter):");
        eligible.forEach(System.out::println);

        List<Employee> revised = reviseSalaries(staff);
        System.out.println("\nSalaries After 10% Increment (map):");
        revised.forEach(System.out::println);

        double totalBefore = calculateTotalExpenditure(staff);
        double totalAfter = calculateTotalExpenditure(revised);
        System.out.printf("\nTotal Expenditure Before Revision (reduce): ₹%.2f\n", totalBefore);
        System.out.printf("Total Expenditure After Revision (reduce): ₹%.2f\n", totalAfter);
        System.out.printf("Net Increase in Expenditure: ₹%.2f\n", (totalAfter - totalBefore));
    }
}
