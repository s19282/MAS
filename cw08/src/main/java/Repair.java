import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
@Entity
public class Repair implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Basic
    private String partName;
    @Basic
    private String description;
    @Basic
    private LocalTime duration;
    @Enumerated(EnumType.STRING)
    private RepairState state;
    @Basic
    private Double partsCost;
    @ManyToOne
    private Visit visit;

    public Repair(String partName, String description, LocalTime duration, RepairState state, Double partsCost)
    {
        this.partName = partName;
        this.description = description;
        this.duration = duration;
        this.state = state;
        this.partsCost = partsCost;
    }

    public Repair(LocalTime duration, Double partsCost)
    {
        this.duration = duration;
        this.partsCost = partsCost;
    }

    public Repair() {

    }

    public void setVisit(Visit visit)
    {
        if(this.visit != null)
        {
            if(this.visit != visit)
            {
                this.visit.removeRepair(this);
                this.visit = visit;
                visit.addRepair(this);
            }
        }
        else
        {
            this.visit = visit;
            visit.addRepair(this);
        }
    }

    public void removeVisit()
    {
        if(visit.getRepairs().contains(this))
            visit.removeRepair(this);

        if(visit !=null)
        {
            visit = null;
        }

    }

    public Visit getVisit() {
        return visit;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration.getHour();
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public RepairState getState() {
        return state;
    }

    public void setState(RepairState state) {
        this.state = state;
    }

    public Double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Double partsCost) {
        this.partsCost = partsCost;
    }

    public String showRepair()
    {
        return "Repair{" +
                "partName=" + partName +
                ", description=" + description +
                ", duration=" + duration +
                ", state=" + state +
                ", partsCost=" + partsCost +
                ", visit=" + (visit!=null ? visit.showVisit() : "null") +
                "}";
    }

    @Override
    public String toString() {
        return "Repair{" +
                "partName=" + partName +
                ", description=" + description +
                ", duration=" + duration +
                ", state=" + state +
                ", partsCost=" + partsCost +
                "}";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}