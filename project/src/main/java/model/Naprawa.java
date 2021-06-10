package model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Naprawa {
    @Id
    private Long id;
    @Column(length = 200)
    private String nazwaPodzespolu;
    @Column(length = 1000)
    private String opis;
    private LocalTime czasTrwania;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double koszt;

    public Naprawa(String nazwaPodzespolu, String opis, LocalTime czasTrwania, Status status, Double koszt) {
        this.nazwaPodzespolu = nazwaPodzespolu;
        this.opis = opis;
        this.czasTrwania = czasTrwania;
        this.status = status;
        this.koszt = koszt;
    }

    public Naprawa() {
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
