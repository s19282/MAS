import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Visit implements Serializable
{
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private LocalDate endDate;
    private Double estimatedCost;
    private List<Repair> repairs = new ArrayList<>();
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
}