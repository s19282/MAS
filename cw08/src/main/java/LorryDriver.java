import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class LorryDriver extends Driver
{
    @Basic
    private int commercialDrivingLicenceNumber;

    public LorryDriver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain, int commercialDrivingLicenceNumber) {
        super(firstName, lastName, drivingLicenceNumber, dateOfObtain);
        this.commercialDrivingLicenceNumber = commercialDrivingLicenceNumber;
    }

    public LorryDriver() {
        super();
    }

    public int getCommercialDrivingLicenceNumber() {
        return commercialDrivingLicenceNumber;
    }

    public void setCommercialDrivingLicenceNumber(int commercialDrivingLicenceNumber) {
        this.commercialDrivingLicenceNumber = commercialDrivingLicenceNumber;
    }

    @Override
    public String toString() {
        return "LorryDriver{" +
                "commercialDrivingLicenceNumber=" + commercialDrivingLicenceNumber +
                '}';
    }
}
