import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i <= 3; i++) {
            System.out.println("\nEnter details for Employee " + i + ":");

            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            Date dateOfBirth = null;
            while (dateOfBirth == null) {
                System.out.print("Enter Date of Birth (dd-MM-yyyy): ");
                String dobStr = scanner.nextLine();

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    dateFormat.setLenient(false);
                    dateOfBirth = dateFormat.parse(dobStr);
                    if (!dobStr.equals(dateFormat.format(dateOfBirth))) {
                        throw new ParseException("Invalid date format", 0);
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
                }
            }

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Joining Date (dd/MM/yyyy): ");
            String joiningDateStr = scanner.nextLine();
            LocalDate joiningDate = LocalDate.parse(joiningDateStr, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            EmployeeType employeeType = getEmployeeType();
            PaperleesOffice paper = new PaperleesOffice(id, name, dateOfBirth, email, joiningDate);
            calculateAndDisplayLeaveDetails(paper, employeeType);
        }

        scanner.close();
    }

    private static void calculateAndDisplayLeaveDetails(PaperleesOffice employee, EmployeeType employeeType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        int joiningYear = employee.getJoiningDate().getYear();
        int totalDaysInYear = Year.of(joiningYear).length();

        LocalDate endDateOfYear = LocalDate.of(joiningYear, 12, 31);
        long totalDays = employee.getJoiningDate().datesUntil(endDateOfYear.plusDays(1)).count();

        int vacationLeave;
        int sickLeave;

        if (employeeType == EmployeeType.STAFF) {
            vacationLeave = (totalDays * 10 < totalDaysInYear / 2.0) ?
                    (int) Math.floor((totalDays * 10.0) / totalDaysInYear) :
                    (int) Math.ceil((totalDays * 10.0) / totalDaysInYear);

            sickLeave = (totalDays * 7 < totalDaysInYear / 2.0) ?
                    (int) Math.floor((totalDays * 7.0) / totalDaysInYear) :
                    (int) Math.ceil((totalDays * 7.0) / totalDaysInYear);
        } else if (employeeType == EmployeeType.OFFICER) {
            vacationLeave = (totalDays * 15 < totalDaysInYear / 2.0) ?
                    (int) Math.floor((totalDays * 15.0) / totalDaysInYear) :
                    (int) Math.ceil((totalDays * 15.0) / totalDaysInYear);

            sickLeave = (totalDays * 10 < totalDaysInYear / 2.0) ?
                    (int) Math.floor((totalDays * 10.0) / totalDaysInYear) :
                    (int) Math.ceil((totalDays * 10.0) / totalDaysInYear);
        } else {
            System.out.println("Invalid employee type");
            return;
        }

        System.out.println("\nDetails for Employee ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Date of Birth: " + dateFormat.format(employee.getDateOfBirth()));
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Joining Date: " + employee.getJoiningDate());
        System.out.println("Employee Type: " + employeeType);
        System.out.println("Vacation Leave: " + vacationLeave);
        System.out.println("Sick Leave: " + sickLeave);
        System.out.println("------------------------");
    }

    private static EmployeeType getEmployeeType() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee Type (S for Staff, O for Officer): ");
        String typeStr = scanner.nextLine().toLowerCase();

        if ("s".equals(typeStr)) {
            return EmployeeType.STAFF;
        } else if ("o".equals(typeStr)) {
            return EmployeeType.OFFICER;
        } else {
            System.out.println("Invalid employee type. Defaulting to STAFF.");
            return EmployeeType.STAFF;
        }
    }
}


