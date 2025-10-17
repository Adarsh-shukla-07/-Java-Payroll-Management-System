import java.util.ArrayList;
import java.util.Scanner;

// Abstract class Employee
abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

// Full-time employee subclass
class FulltimeEmployee extends Employee {
    private double monthlySalary;

    public FulltimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// Part-time employee subclass
class ParttimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public ParttimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

// Payroll System class
class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees in the system.");
        } else {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}

// Main class with User Input
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payroll = new PayrollSystem();

        while (true) {
            System.out.println("\nPayroll System Menu:");
            System.out.println("1. Add Full-time Employee");
            System.out.println("2. Add Part-time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1: // Add Full-time Employee
                    System.out.print("Enter Full-time Employee Name: ");
                    String ftName = scanner.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int ftId = scanner.nextInt();
                    System.out.print("Enter Monthly Salary: ");
                    double salary = scanner.nextDouble();

                    payroll.addEmployee(new FulltimeEmployee(ftName, ftId, salary));
                    System.out.println("Full-time Employee added successfully!");
                    break;

                case 2: // Add Part-time Employee
                    System.out.print("Enter Part-time Employee Name: ");
                    String ptName = scanner.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int ptId = scanner.nextInt();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = scanner.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    double hourlyRate = scanner.nextDouble();

                    payroll.addEmployee(new ParttimeEmployee(ptName, ptId, hoursWorked, hourlyRate));
                    System.out.println("Part-time Employee added successfully!");
                    break;

                case 3: // Remove Employee
                    System.out.print("Enter Employee ID to Remove: ");
                    int removeId = scanner.nextInt();
                    payroll.removeEmployee(removeId);
                    break;

                case 4: // Display Employees
                    System.out.println("\nEmployee List:");
                    payroll.displayEmployees();
                    break;

                case 5: // Exit Program
                    System.out.println("Exiting Payroll System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
