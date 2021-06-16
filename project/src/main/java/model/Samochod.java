package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Samochod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private String numerRejestracyjny;
    private String marka;
    private String model;
    @OneToMany(
            mappedBy = "samochod",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Wizyta> wizyty = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "KlientSamochod",
            joinColumns = { @JoinColumn(name = "samochodId") },
            inverseJoinColumns = { @JoinColumn(name = "klientId") }
    )
    private final List<Klient> klienci = new ArrayList<>();


    public Samochod(String numerRejestracyjny, String marka, String model) {
        this.numerRejestracyjny = numerRejestracyjny;
        this.marka = marka;
        this.model = model;
    }

    public Samochod() {
    }

    public void dodajKlienta(Klient klient)
    {
        if(!klienci.contains(klient))
        {
            klienci.add(klient);
            klient.dodajSamochod(this);
        }
    }


    public void usunKlienta(Klient klient)
    {
        if(klienci.contains(klient))
        {
            klienci.remove(klient);
            klient.usunSamochod(this);
        }
    }

    public void dodajWizyte(Wizyta wizyta)
    {
        if(!wizyty.contains(wizyta))
        {
            wizyty.add(wizyta);
            wizyta.dodajSamochod(this);
        }
    }


    public void usunWizyte(Wizyta wizyta)
    {
        if(wizyty.contains(wizyta))
        {
            wizyty.remove(wizyta);
            wizyta.usunSamochod();
        }
    }

    public List<Wizyta> getWizyty() {
        return wizyty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
