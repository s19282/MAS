package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Zatrudnienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private LocalDate dataZatrudnienia;
    private LocalDate dataZwolnienia;
    private Double pensja;

    public Zatrudnienie(LocalDate dataZatrudnienia, LocalDate dataZwolnienia, Double pensja) {
        this.dataZatrudnienia = dataZatrudnienia;
        this.dataZwolnienia = dataZwolnienia;
        this.pensja = pensja;
    }

    public Zatrudnienie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public LocalDate getDataZwolnienia() {
        return dataZwolnienia;
    }

    public void setDataZwolnienia(LocalDate dataZwolnienia) {
        this.dataZwolnienia = dataZwolnienia;
    }

    public Double getPensja() {
        return pensja;
    }

    public void setPensja(Double pensja) {
        this.pensja = pensja;
    }
}
