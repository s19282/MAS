import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Driver
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private int drivingLicenceNumber;
    @Basic
    private LocalDate dateOfObtain;

    public Driver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.drivingLicenceNumber = drivingLicenceNumber;
        this.dateOfObtain = dateOfObtain;
    }


    public Driver() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(int drivingLicenceNumber) {
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public LocalDate getDateOfObtain() {
        return dateOfObtain;
    }

    public void setDateOfObtain(LocalDate dateOfObtain) {
        this.dateOfObtain = dateOfObtain;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", drivingLicenceNumber=" + drivingLicenceNumber +
                ", dateOfObtain=" + dateOfObtain +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}