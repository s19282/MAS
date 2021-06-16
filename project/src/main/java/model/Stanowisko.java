package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stanowisko {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private String nazwa;
    private String zakresObowiazkow;
    @OneToMany(
            mappedBy = "stanowisko",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Zatrudnienie> zatrudnienia = new ArrayList<>();

    public Stanowisko(String nazwa, String zakresObowiazkow) {
        this.nazwa = nazwa;
        this.zakresObowiazkow = zakresObowiazkow;
    }

    public Stanowisko() {
    }

    public void dodajZatrudnienie(Zatrudnienie zatrudnienie)
    {
        if(!zatrudnienia.contains(zatrudnienie))
        {
            zatrudnienia.add(zatrudnienie);
            zatrudnienie.dodajStanowisko(this);
        }
    }

    public void usunZatrudnienie(Zatrudnienie zatrudnienie)
    {
        if(zatrudnienia.contains(zatrudnienie))
        {
            zatrudnienia.remove(zatrudnienie);
            zatrudnienie.usunStanowisko();
        }
    }

    public List<Zatrudnienie> getZatrudnienia() {
        return zatrudnienia;
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
