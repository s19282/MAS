package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Osoba {
    @Id
    private Long id;
    private String imie;
    private String nazwisko;
    private Long numerTelefonu;

    public Osoba(Long id, String imie, String nazwisko, Long numerTelefonu) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
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

    public Long getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(Long numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
