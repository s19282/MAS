package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Column(unique = true)
    private String numerKlienta;
    private Double rabatNaUslugi;
    private static Double maxRabatNaUslugi;
    @ManyToMany(mappedBy = "klienci")
    private final List<Samochod> samochody = new ArrayList<>();

    public Klient(String numerKlienta) {
        this.numerKlienta = numerKlienta;
    }

    public Klient() {
    }

    public void dodajSamochod(Samochod samochod)
    {
        if(!samochody.contains(samochod))
        {
            samochody.add(samochod);
            samochod.dodajKlienta(this);
        }
    }


    public void usunSamochod(Samochod samochod)
    {
        if(samochody.contains(samochod))
        {
            samochody.remove(samochod);
            samochod.usunKlienta(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumerKlienta() throws Exception
    {
        return numerKlienta;
    }

    public void setNumerKlienta(String numerKlienta) throws Exception {
        this.numerKlienta = numerKlienta;
    }

    public Double getRabatNaUslugi() {
        return rabatNaUslugi;
    }

    public void setRabatNaUslugi(Double rabatNaUslugi) {
        this.rabatNaUslugi = rabatNaUslugi;
    }

    public static Double getMaxRabatNaUslugi() {
        return maxRabatNaUslugi;
    }

    public static void setMaxRabatNaUslugi(Double maxRabatNaUslugi) {
        Klient.maxRabatNaUslugi = maxRabatNaUslugi;
    }
}
