package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @ManyToOne
    private Stanowisko stanowisko;
    @ManyToOne
    private Osoba osoba;

    public Zatrudnienie(LocalDate dataZatrudnienia, LocalDate dataZwolnienia, Double pensja) {
        this.dataZatrudnienia = dataZatrudnienia;
        this.dataZwolnienia = dataZwolnienia;
        this.pensja = pensja;
    }

    public Zatrudnienie(LocalDate dataZatrudnienia, Double pensja) {
        this.dataZatrudnienia = dataZatrudnienia;
        this.pensja = pensja;
    }

    public void dodajOsobe(Osoba osoba) throws Exception {
        if(!osoba.getTypyOsob().contains(TypyOsoby.PRACOWNIK))
        {
            throw new Exception("Ta osoba nie jest pracownikiem!");
        }
        if(this.osoba != null)
        {
            if(this.osoba != osoba)
            {
                this.osoba.usunZatrudnienie(this);
                this.osoba = osoba;
                osoba.dodajZatrudnienie(this);
            }
        }
        else
        {
            this.osoba = osoba;
            osoba.dodajZatrudnienie(this);
        }
    }

    public void usunOsobe() throws Exception {
        if(osoba.getZatrudnienia().contains(this))
            osoba.usunZatrudnienie(this);

        if(osoba !=null)
        {
            osoba = null;
        }
    }

    public void dodajStanowisko(Stanowisko stanowisko)
    {
        if(this.stanowisko != null)
        {
            if(this.stanowisko != stanowisko)
            {
                this.stanowisko.usunZatrudnienie(this);
                this.stanowisko = stanowisko;
                stanowisko.dodajZatrudnienie(this);
            }
        }
        else
        {
            this.stanowisko = stanowisko;
            stanowisko.dodajZatrudnienie(this);
        }
    }

    public void usunStanowisko()
    {
        if(stanowisko.getZatrudnienia().contains(this))
            stanowisko.usunZatrudnienie(this);

        if(stanowisko !=null)
        {
            stanowisko = null;
        }
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
