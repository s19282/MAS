package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Samochod {
    @Id
    private Long id;
    private String numerRejestracyjny;
    private String marka;
    private String model;

    public Samochod(String numerRejestracyjny, String marka, String model) {
        this.numerRejestracyjny = numerRejestracyjny;
        this.marka = marka;
        this.model = model;
    }

    public Samochod() {
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
