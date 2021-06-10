package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Czesc {
    @Id
    private Long id;
    @Column(length = 100)
    private String nazwaCzesci;
    private Double koszt;

    public Czesc(String nazwaCzesci, Double koszt) {
        this.nazwaCzesci = nazwaCzesci;
        this.koszt = koszt;
    }

    public Czesc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwaCzesci() {
        return nazwaCzesci;
    }

    public void setNazwaCzesci(String nazwaCzesci) {
        this.nazwaCzesci = nazwaCzesci;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }
}
