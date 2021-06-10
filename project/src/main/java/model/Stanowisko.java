package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stanowisko {
    @Id
    private Long id;
    private String nazwa;
    private String zakresObowiazkow;

    public Stanowisko(String nazwa, String zakresObowiazkow) {
        this.nazwa = nazwa;
        this.zakresObowiazkow = zakresObowiazkow;
    }

    public Stanowisko() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getZakresObowiazkow() {
        return zakresObowiazkow;
    }

    public void setZakresObowiazkow(String zakresObowiazkow) {
        this.zakresObowiazkow = zakresObowiazkow;
    }
}
