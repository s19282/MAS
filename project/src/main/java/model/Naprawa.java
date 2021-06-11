package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Naprawa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Column(length = 200)
    private String nazwaPodzespolu;
    @Column(length = 1000)
    private String opis;
    private LocalTime czasTrwania;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double koszt;
    @ManyToMany(mappedBy = "naprawy")
    private final List<CzynnoscEksploatacyjna> czynnosciEksploatacyjne = new ArrayList<>();
    @OneToMany(
            mappedBy = "naprawa",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Czesc> czesci = new ArrayList<>();
    @ManyToOne
    private Wizyta wizyta;

    public Naprawa(String nazwaPodzespolu, String opis, LocalTime czasTrwania, Double koszt) {
        this.nazwaPodzespolu = nazwaPodzespolu;
        this.opis = opis;
        this.czasTrwania = czasTrwania;
        status = Status.OCZEKUJACA;
        this.koszt = koszt;
    }

    public Naprawa() {
    }

    public void dodajCzynnoscEksploatacyjna(CzynnoscEksploatacyjna czynnoscEksploatacyjna)
    {
        if(!czynnosciEksploatacyjne.contains(czynnoscEksploatacyjna))
        {
            czynnosciEksploatacyjne.add(czynnoscEksploatacyjna);
            czynnoscEksploatacyjna.dodajNaprawe(this);
        }
    }


    public void usunCzynnoscEksploatacyjna(CzynnoscEksploatacyjna czynnoscEksploatacyjna)
    {
        if(czynnosciEksploatacyjne.contains(czynnoscEksploatacyjna))
        {
            czynnosciEksploatacyjne.remove(czynnoscEksploatacyjna);
            czynnoscEksploatacyjna.usunNaprawe(this);
        }
    }

    public List<CzynnoscEksploatacyjna> getCzynnosciEksploatacyjne()
    {
        return czynnosciEksploatacyjne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalTime getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(LocalTime czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNazwaPodzespolu() {
        return nazwaPodzespolu;
    }

    public void setNazwaPodzespolu(String nazwaPodzespolu) {
        this.nazwaPodzespolu = nazwaPodzespolu;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }
}
