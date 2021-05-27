import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class TaxiDriver  extends Driver
{
    @Basic
    private int taxiLicenceNumber;
    @Basic
    private int taximeterNumber;

    public TaxiDriver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain, int taxiLicenceNumber, int taximeterNumber) {
        super(firstName, lastName, drivingLicenceNumber, dateOfObtain);
        this.taxiLicenceNumber = taxiLicenceNumber;
        this.taximeterNumber = taximeterNumber;
    }

    public TaxiDriver() {
        super();
    }

    public int getTaxiLicenceNumber() {
        return taxiLicenceNumber;
    }

    public void setTaxiLicenceNumber(int taxiLicenceNumber) {
        this.taxiLicenceNumber = taxiLicenceNumber;
    }

    public int getTaximeterNumber() {
        return taximeterNumber;
    }

    public void setTaximeterNumber(int taximeterNumber) {
        this.taximeterNumber = taximeterNumber;
    }

    @Override
    public String toString() {
        return "TaxiDriver{" +
                "taxiLicenceNumber=" + taxiLicenceNumber +
                ", taximeterNumber=" + taximeterNumber +
                '}';
    }
}
