import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Visit")
public class Visit implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Basic
    private LocalDate startDate;
    @Basic
    private LocalDate expectedEndDate;
    @Basic
    private LocalDate endDate;
    @Basic
    private Double estimatedCost;
    @OneToMany(
            mappedBy = "visit",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Repair> repairs = new ArrayList<>();
    @Basic
    private static Double manHourCost = 200D;

    public Visit(LocalDate startDate, LocalDate expectedEndDate, LocalDate endDate, Double estimatedCost) {
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = endDate;
        this.estimatedCost = estimatedCost;
    }

    public Visit(LocalDate startDate, LocalDate expectedEndDate, Double estimatedCost) {
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.estimatedCost = estimatedCost;
    }

    public Visit() {

    }

    public void addRepair(Repair repair)
    {
        if(!repairs.contains(repair))
        {
            repairs.add(repair);
            repair.setVisit(this);
        }
    }


    public void removeRepair(Repair repair)
    {
        if(repairs.contains(repair))
        {
            repairs.remove(repair);
            repair.removeVisit();
        }
    }

    public List<Repair> getRepairs()
    {
        return repairs;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public Double getCost()
    {
        double cost = 0D;
        for (Repair r : repairs)
        {
            cost += r.getDuration() * manHourCost + r.getPartsCost();
        }
        return cost;
    }

    public static Double getManHourCost() {
        return manHourCost;
    }

    public static void setManHourCost(Double manHourCost) {
        Visit.manHourCost = manHourCost;
    }

    public String showVisit()
    {
        return "Visit{" +
                "startDate=" + startDate +
                ", expectedEndDate=" + expectedEndDate +
                ", endDate=" + (getEndDate() != null ? getEndDate() : "End date not set") +
                ", estimatedCost=" + estimatedCost +
                "}";
    }
    @Override
    public String toString()
    {
        return "Visit{" +
                "startDate=" + startDate +
                ", expectedEndDate=" + expectedEndDate +
                ", endDate=" + (getEndDate() != null ? getEndDate() : "End date not set") +
                ", estimatedCost=" + estimatedCost +
                ", repairs=" + repairs +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}