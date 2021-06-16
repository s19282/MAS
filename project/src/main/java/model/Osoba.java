package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

enum TypyOsoby {PRACOWNIK, KLIENT_INDYWIDUALNY}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Osoba extends Klient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private String imie;
    private String nazwisko;
    private int numerTelefonu;
    private Double stawkaGodzinowa;
    private Long PESEL;
    @OneToMany(
            mappedBy = "osoba",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Zatrudnienie> zatrudnienia = new ArrayList<>();
    @ElementCollection
    private Set<TypyOsoby> typyOsob = null;

//    Konstruktor pracownika
    public Osoba(String imie, String nazwisko, int numerTelefonu, Double stawkaGodzinowa, Long PESEL) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        this.stawkaGodzinowa = stawkaGodzinowa;
        this.PESEL = PESEL;
        typyOsob = EnumSet.of(TypyOsoby.PRACOWNIK);
    }
    //    Konstruktor klienta indywidualnego
    public Osoba(String imie, String nazwisko, int numerTelefonu, String numerKlienta) throws Exception {
        super(numerKlienta);
        Klient.setMaxRabatNaUslugi(0.2);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        typyOsob = EnumSet.of(TypyOsoby.KLIENT_INDYWIDUALNY);
    }
    //    Konstruktor klienta indywidualnego będącego pracownikiem
    public Osoba(String imie, String nazwisko, int numerTelefonu, String numerKlienta, Double stawkaGodzinowa, Long PESEL) throws Exception {
        super(numerKlienta);
        Klient.setMaxRabatNaUslugi(0.2);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        this.stawkaGodzinowa = stawkaGodzinowa;
        this.PESEL = PESEL;
        typyOsob = EnumSet.of(TypyOsoby.PRACOWNIK,TypyOsoby.KLIENT_INDYWIDUALNY);
    }

    public Double getStawkaGodzinowa() throws Exception {
        if(this.typyOsob.contains(TypyOsoby.PRACOWNIK))
        {
            return stawkaGodzinowa;
        }
        throw new Exception("Ta osoba nie jest pracownikiem!");
    }

    public void setStawkaGodzinowa(Double stawkaGodzinowa) throws Exception {
        if(typyOsob.contains(TypyOsoby.PRACOWNIK))
        {
            this.stawkaGodzinowa = stawkaGodzinowa;
        }
        else
        {
            throw new Exception("Ta osoba nie jest pracownikiem!");
        }
    }

    public Long getPESEL() throws Exception {
        if(this.typyOsob.contains(TypyOsoby.PRACOWNIK))
        {
            return PESEL;
        }
        throw new Exception("Ta osoba nie jest pracownikiem!");
    }

    public void setPESEL(Long PESEL) throws Exception {
        if(typyOsob.contains(TypyOsoby.PRACOWNIK))
        {
            this.PESEL = PESEL;
        }
        else
        {
            throw new Exception("Ta osoba nie jest pracownikiem!");
        }
    }

//    public Double getMaxRabatNaUslugi() throws Exception {
//        if(this.typyOsob.contains(TypyOsoby.KLIENT_INDYWIDUALNY))
//        {
//            return Klient.getMaxRabatNaUslugi();
//        }
//        throw new Exception("Ta osoba nie jest klientem!");
//    }
//
//    public void setMaxRabatNaUslugi(Double maxRabatNaUslugi) throws Exception {
//        if(typyOsob.contains(TypyOsoby.PRACOWNIK))
//        {
//            Osoba.maxRabatNaUslugi = maxRabatNaUslugi;
//        }
//        else
//        {
//            throw new Exception("Ta osoba nie jest pracownikiem!");
//        }
//    }

    public List<Zatrudnienie> getZatrudnienia() {
        return zatrudnienia;
    }

    public Set<TypyOsoby> getTypyOsob() {
        return typyOsob;
    }

    public void setTypyOsob(Set<TypyOsoby> typyOsob) {
        this.typyOsob = typyOsob;
    }

    public Osoba() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(int numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
    public Osoba wyswietlPosortowanychWgStazu()
    {
//        TODO: implement
        return null;
    }
    public long obliczStazPracy()
    {
        long lataPracy = 0;
        for (Zatrudnienie z: zatrudnienia) {
            if(z.getDataZwolnienia() != null)
                lataPracy += z.getDataZwolnienia().toEpochDay()-z.getDataZatrudnienia().toEpochDay();
            else
                lataPracy += LocalDate.now().toEpochDay()-z.getDataZatrudnienia().toEpochDay();
        }
        return lataPracy/= 365;
    }
    public Double obliczPremie()
    {
        return obliczStazPracy()*500D;
    }

    @Override
    public String getNumerKlienta() throws Exception {
        if(this.typyOsob.contains(TypyOsoby.KLIENT_INDYWIDUALNY))
        {
            return super.getNumerKlienta();
        }
        throw new Exception("Ta osoba nie jest pracownikiem!");
    }

    @Override
    public void setNumerKlienta(String numerKlienta) throws Exception {
        if(typyOsob.contains(TypyOsoby.KLIENT_INDYWIDUALNY))
        {
            super.setNumerKlienta(numerKlienta);
        }
        else
        {
            throw new Exception("Ta osoba nie jest pracownikiem!");
        }
    }

    @Override
    public String toString() {
        if(typyOsob.contains(TypyOsoby.PRACOWNIK))
            return "Pracownik{" +
                    "id=" + id +
                    ", imie='" + imie + '\'' +
                    ", nazwisko='" + nazwisko + '\'' +
                    ", numerTelefonu=" + numerTelefonu +
                    ", stawkaGodzinowa=" + stawkaGodzinowa +
                    ", PESEL=" + PESEL +
                    '}';
        else if(typyOsob.contains(TypyOsoby.KLIENT_INDYWIDUALNY)) {
            try {
                return "KlientIndywidualny{" +
                        "id=" + id +
                        ", imie='" + imie + '\'' +
                        ", nazwisko='" + nazwisko + '\'' +
                        ", numerTelefonu=" + numerTelefonu +
                        ", numerKlienta=" + super.getNumerKlienta() +
                        '}';
            } catch (Exception ignored) { }
        }
        else {
            try {
                return "PracownikKlientIndywidualny{" +
                        "id=" + id +
                        ", imie='" + imie + '\'' +
                        ", nazwisko='" + nazwisko + '\'' +
                        ", numerTelefonu=" + numerTelefonu +
                        ", stawkaGodzinowa=" + stawkaGodzinowa +
                        ", PESEL=" + PESEL +
                        ", numerKlienta=" + super.getNumerKlienta() +
                        '}';
            } catch (Exception ignored) { }
        }
        return null;
    }
}
