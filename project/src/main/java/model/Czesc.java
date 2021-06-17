package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Czesc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Column(length = 100)
    private String nazwaCzesci;
    private Double koszt;
    @ManyToOne
    private Naprawa naprawa;

    public Czesc(String nazwaCzesci, Double koszt) {
        this.nazwaCzesci = nazwaCzesci;
        this.koszt = koszt;
    }

    public Czesc() {
    }

    public void dodajNaprawe(Naprawa naprawa)
    {
        if(this.naprawa != null)
        {
            if(this.naprawa != naprawa)
            {
                this.naprawa.usunCzesc(this);
                this.naprawa = naprawa;
                naprawa.dodajCzesc(this);
            }
        }
        else
        {
            this.naprawa = naprawa;
            naprawa.dodajCzesc(this);
        }
    }

    public void usunNaprawe()
    {
        if(naprawa.getCzesci().contains(this))
            naprawa.usunCzesc(this);

        if(naprawa !=null)
        {
            naprawa = null;
        }
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
