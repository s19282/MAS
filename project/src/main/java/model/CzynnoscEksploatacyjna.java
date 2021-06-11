package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CzynnoscEksploatacyjna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Column(length = 200)
    private String opis;
    private Double koszt;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Naprawa_CzynnoscEksploatacyjna",
            joinColumns = { @JoinColumn(name = "czynnoscEksploatacyjnaId") },
            inverseJoinColumns = { @JoinColumn(name = "naprawaId") }
    )
    private final List<Naprawa> naprawy = new ArrayList<>();

    public CzynnoscEksploatacyjna(String opis, Double koszt) {
        this.opis = opis;
        this.koszt = koszt;
    }

    public void dodajNaprawe(Naprawa naprawa)
    {
        if(!naprawy.contains(naprawa))
        {
            naprawy.add(naprawa);
            naprawa.dodajCzynnoscEksploatacyjna(this);
        }
    }


    public void usunNaprawe(Naprawa naprawa)
    {
        if(naprawy.contains(naprawa))
        {
            naprawy.remove(naprawa);
            naprawa.usunCzynnoscEksploatacyjna(this);
        }
    }

    public List<Naprawa> getNaprawy()
    {
        return naprawy;
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
