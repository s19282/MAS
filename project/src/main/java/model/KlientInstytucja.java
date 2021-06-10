package model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class KlientInstytucja extends Klient {
    private String nazwa;
    @Column(length = 9)
    private String regon;
    private static Double maxRabatNaUslugi;
    private Long numerTelefonu;

    public KlientInstytucja(String nazwa, String regon, Long numerTelefonu) {
        this.nazwa = nazwa;
        this.regon = regon;
        this.numerTelefonu = numerTelefonu;
    }

    public KlientInstytucja() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public static Double getMaxRabatNaUslugi() {
        return maxRabatNaUslugi;
    }

    public static void setMaxRabatNaUslugi(Double maxRabatNaUslugi) {
        KlientInstytucja.maxRabatNaUslugi = maxRabatNaUslugi;
    }

    public Long getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(Long numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
