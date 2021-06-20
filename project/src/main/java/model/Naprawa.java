package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Naprawa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Column(length = 200)
    private String nazwaPodzespolu;
    @Column(length = 1000)
    private String opis;
    private LocalTime czasTrwania;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double koszt;
    @ManyToMany(mappedBy = "naprawy")
    private final List<CzynnoscEksploatacyjna> czynnosciEksploatacyjne = new ArrayList<>();
    @ManyToMany(
            mappedBy = "naprawy",
            fetch = FetchType.EAGER
    )
    private final List<Osoba> osoby = new ArrayList<>();
    @OneToMany(
            mappedBy = "naprawa",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Czesc> czesci = new ArrayList<>();
    @ManyToOne
    private Wizyta wizyta;

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public Naprawa(String nazwaPodzespolu, String opis, LocalTime czasTrwania, Double koszt) {
        this.nazwaPodzespolu = nazwaPodzespolu;
        this.opis = opis;
        this.czasTrwania = czasTrwania;
        status = Status.OCZEKUJACA;
        this.koszt = koszt;
    }

    public Naprawa() {
    }

    public void dodajWizyte(Wizyta wizyta)
    {
        if(this.wizyta != null)
        {
            if(this.wizyta != wizyta)
            {
                this.wizyta.usunNaprawe(this);
                this.wizyta = wizyta;
                wizyta.dodajNaprawe(this);
            }
        }
        else
        {
            this.wizyta = wizyta;
            wizyta.dodajNaprawe(this);
        }
    }

    public void usunWizyte()
    {
        if(wizyta.getNaprawy().contains(this))
            wizyta.usunNaprawe(this);

        if(wizyta !=null)
        {
            wizyta = null;
        }
    }

    public void dodajOsobe(Osoba osoba) throws Exception {
        if(!osoba.getTypyOsob().contains(TypyOsoby.PRACOWNIK))
        {
            throw new Exception("Ta osoba nie jest pracownikiem!");
        }
        if(!osoby.contains(osoba))
        {
            osoby.add(osoba);
            osoba.dodajNaprawe(this);
        }
    }

    public void usunOsobe(Osoba osoba) throws Exception {
        if(!osoba.getTypyOsob().contains(TypyOsoby.PRACOWNIK))
        {
            throw new Exception("Ta osoba nie jest pracownikiem!");
        }
        if(osoby.contains(osoba))
        {
            osoby.remove(osoba);
            osoba.usunNaprawe(this);
        }
    }

    public void dodajCzynnoscEksploatacyjna(CzynnoscEksploatacyjna czynnoscEksploatacyjna)
    {
        if(!czynnosciEksploatacyjne.contains(czynnoscEksploatacyjna))
        {
            czynnosciEksploatacyjne.add(czynnoscEksploatacyjna);
            czynnoscEksploatacyjna.dodajNaprawe(this);
        }
    }


    public void usunCzynnoscEksploatacyjna(CzynnoscEksploatacyjna czynnoscEksploatacyjna)
    {
        if(czynnosciEksploatacyjne.contains(czynnoscEksploatacyjna))
        {
            czynnosciEksploatacyjne.remove(czynnoscEksploatacyjna);
            czynnoscEksploatacyjna.usunNaprawe(this);
        }
    }

    public void dodajCzesc(Czesc czesc)
    {
        if(!czesci.contains(czesc))
        {
            czesci.add(czesc);
            czesc.dodajNaprawe(this);
        }
    }


    public void usunCzesc(Czesc czesc)
    {
        if(czesci.contains(czesc))
        {
            czesci.remove(czesc);
            czesc.usunNaprawe();
        }
    }

    public static void dodajNaprawe(String nazwaPodzespolu, String opis, LocalTime czasTrwania, Double koszt)
    {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try
        {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            Naprawa naprawa = new Naprawa(nazwaPodzespolu, opis, czasTrwania, koszt);

            session.save(naprawa);

            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally {
            if(sessionFactory !=null)
            {
                sessionFactory.close();
            }
        }
    }

    public List<Czesc> getCzesci() {
        return czesci;
    }

    public List<CzynnoscEksploatacyjna> getCzynnosciEksploatacyjne()
    {
        return czynnosciEksploatacyjne;
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
    public String szczegolyNaprawy()
    {
        return "Id: "+getId()+" Nazwa podzespołu: "+getNazwaPodzespolu()+" Opis: "+getOpis()+" Czas trwania: "+getCzasTrwania()+" Stan: "+getStatus();
    }

    @Override
    public String toString() {
        return "Id naprawy: "+getId()+", "+getNazwaPodzespolu();
    }
}
