package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CzynnoscEksploatacyjna {

    @Id
    private Long id;
    @Column(length = 200)
    private String opis;
    private Double koszt;

    public CzynnoscEksploatacyjna(String opis, Double koszt) {
        this.opis = opis;
        this.koszt = koszt;
    }

    public CzynnoscEksploatacyjna() {
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
