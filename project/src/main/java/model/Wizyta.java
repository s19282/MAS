package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizyta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private LocalDateTime dataGodzinaRozpoczecia;
    private LocalDateTime przewidywanaDataGodzinaZakonczenia;
    private LocalDateTime dataGodzinaZakonczenia;
    private Double szacowanyKoszt;
    private Double koszt;
    private Status status;
    private static Double kosztRoboczogodziny = 250d;
    @OneToMany(
            mappedBy = "wizyta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Naprawa> naprawy = new ArrayList<>();
    @ManyToOne
    private Samochod samochod;

    public Wizyta(LocalDateTime dataGodzinaRozpoczecia, LocalDateTime przewidywanaDataGodzinaZakonczenia, LocalDateTime dataGodzinaZakonczenia, Double szacowanyKoszt, Double koszt) {
        this.dataGodzinaRozpoczecia = dataGodzinaRozpoczecia;
        this.przewidywanaDataGodzinaZakonczenia = przewidywanaDataGodzinaZakonczenia;
        this.dataGodzinaZakonczenia = dataGodzinaZakonczenia;
        this.szacowanyKoszt = szacowanyKoszt;
        this.koszt = koszt;
        status = Status.OCZEKUJACA;
    }

    public Wizyta() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataGodzinaRozpoczecia() {
        return dataGodzinaRozpoczecia;
    }

    public void setDataGodzinaRozpoczecia(LocalDateTime dataGodzinaRozpoczecia) {
        this.dataGodzinaRozpoczecia = dataGodzinaRozpoczecia;
    }

    public LocalDateTime getPrzewidywanaDataGodzinaZakonczenia() {
        return przewidywanaDataGodzinaZakonczenia;
    }

    public void setPrzewidywanaDataGodzinaZakonczenia(LocalDateTime przewidywanaDataGodzinaZakonczenia) {
        this.przewidywanaDataGodzinaZakonczenia = przewidywanaDataGodzinaZakonczenia;
    }

    public LocalDateTime getDataGodzinaZakonczenia() {
        return dataGodzinaZakonczenia;
    }

    public void setDataGodzinaZakonczenia(LocalDateTime dataGodzinaZakonczenia) {
        this.dataGodzinaZakonczenia = dataGodzinaZakonczenia;
    }

    public Double getSzacowanyKoszt() {
        return szacowanyKoszt;
    }

    public void setSzacowanyKoszt(Double szacowanyKoszt) {
        this.szacowanyKoszt = szacowanyKoszt;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }

    public static Double getKosztRoboczogodziny() {
        return kosztRoboczogodziny;
    }

    public static void setKosztRoboczogodziny(Double kosztRoboczogodziny) {
        Wizyta.kosztRoboczogodziny = kosztRoboczogodziny;
    }
}