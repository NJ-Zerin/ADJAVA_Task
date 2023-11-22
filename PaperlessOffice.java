import java.time.LocalDate;
import java.util.Date;


enum EmployeeType {
    STAFF, OFFICER
}

class PaperleesOffice {
    private final int id;
    private final String name;
    private final Date dateOfBirth;
    private final String email;
    private final LocalDate joiningDate;


    public PaperleesOffice(int id, String name, Date dateOfBirth, String email, LocalDate joiningDate){
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }
}