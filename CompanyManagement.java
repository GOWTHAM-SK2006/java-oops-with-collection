import java.util.*;


interface Workable {
    void doWork();
}


abstract class Employee implements Workable {

    private int empId;
    private String name;
    protected double salary;

    public Employee(int empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public abstract void calculateSalary();

    public void display() {
        System.out.println(empId + " | " + name + " | Salary : " + salary);
    }
}


class Developer extends Employee {

    public Developer(int empId, String name, double salary) {
        super(empId, name, salary);
    }

    @Override
    public void calculateSalary() {
        salary += 5000;
    }

    @Override
    public void doWork() {
        System.out.println(getName() + " is writing code");
    }
}

class Manager extends Employee {

    public Manager(int empId, String name, double salary) {
        super(empId, name, salary);
    }

    @Override
    public void calculateSalary() {
        salary += 10000;
    }

    @Override
    public void doWork() {
        System.out.println(getName() + " is managing team");
    }
}

class Project {

    private int projectId;
    private String projectName;

    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }
}


class Company {

    private List<Employee> employees = new ArrayList<>();
    private Map<Integer, Project> projectMap = new HashMap<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void assignProject(int empId, Project project) {
        projectMap.put(empId, project);
    }

    public void showAllEmployees() {
        System.out.println("\n===== EMPLOYEE DETAILS =====");
        for (Employee e : employees) {
            e.calculateSalary();   
            e.display();
            e.doWork();

            Project p = projectMap.get(e.getEmpId());
            if (p != null) {
                System.out.println("Project : " + p.getProjectName());
            }
            System.out.println("-------------------------");
        }
    }
}


public class CompanyManagement{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Company company = new Company();

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("\nEmployee " + (i + 1));
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            sc.nextLine(); 
            
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Enter Role (1-Developer, 2-Manager): ");
            int role = sc.nextInt();

            Employee emp;
            if (role == 1) {
                emp = new Developer(id, name, salary);
            } else {
                emp = new Manager(id, name, salary);
            }

            company.addEmployee(emp);

            System.out.print("Enter Project ID: ");
            int pid = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter Project Name: ");
            String pname = sc.nextLine();

            Project project = new Project(pid, pname);
            company.assignProject(id, project);
        }

        company.showAllEmployees();
        sc.close();
    }
}
