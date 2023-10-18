import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PaperlessOffice {
    @interface EmployeeType {
        String value();
    }

    static class Employee {
        private final int id;
        private final String name;
        private final LocalDate dateOfBirth;
        private final String email;
        private final LocalDate joiningDate;

        public Employee(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
            this.id = id;
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.email = email;
            this.joiningDate = joiningDate;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
        public LocalDate getDateOfBirth(){
            return dateOfBirth;
        }
        public String getEmail(){
            return email;
        }
        public LocalDate getJoiningDate() {
            return joiningDate;
        }
    }

    @EmployeeType("Officer")
    static class Officer extends Employee {
        public Officer(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
            super(id, name, dateOfBirth, email, joiningDate);
        }
    }

    @EmployeeType("Staff")
    static class Staff extends Employee {
        public Staff(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
            super(id, name, dateOfBirth, email, joiningDate);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine();

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Joining Date (YYYY-MM-DD): ");
            LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter Employee Type (Officer/Staff): ");
            String employeeType = scanner.nextLine();

            if (employeeType.equals("Officer")) {
                employees.add(new Officer(id, name, dateOfBirth, email, joiningDate));
            } else if (employeeType.equals("Staff")) {
                employees.add(new Staff(id, name, dateOfBirth, email, joiningDate));
            }
        }

        for (Employee employee : employees) {
            int id = employee.getId();
            String name = employee.getName();
            LocalDate joiningDate = employee.getJoiningDate();
            int vacationLeave = calculateLeave(employee, "Vacation");
            int sickLeave = calculateLeave(employee, "Sick");

            System.out.println("Employee ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Joining Date: " + joiningDate);
            System.out.println("Vacation Leave: " + vacationLeave + " days");
            System.out.println("Sick Leave: " + sickLeave + " days");
            System.out.println();
        }

        scanner.close();
    }

    private static int calculateLeave(Employee employee, String leaveType) {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfYear = currentDate.plusYears(1).withDayOfYear(1);
        int totalLeaveDays = (int) ((endOfYear.toEpochDay() - employee.getJoiningDate().toEpochDay()) + 1);
        int totalDaysInYear = endOfYear.isLeapYear() ? 366 : 365;

        double leaveFraction = (leaveType.equals("Vacation")) ? 0.5 : 0.5;
        int leave = (int) Math.round(leaveFraction * totalLeaveDays / totalDaysInYear);
        return leave;
    }

}
