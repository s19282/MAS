package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Entity
public class Wizyta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private LocalDateTime dataGodzinaRozpoczecia;
    private LocalDateTime przewidywanaDataGodzinaZakonczenia;
    private LocalDateTime dataGodzinaZakonczenia;
    private Double szacowanyKoszt;
    private Double koszt;
    private Status status;
    private static Double kosztRoboczogodziny = 250d;
    @OneToMany(
            mappedBy = "wizyta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Naprawa> naprawy = new ArrayList<>();
    @ManyToOne
    private Samochod samochod;

    public Wizyta(LocalDateTime dataGodzinaRozpoczecia, LocalDateTime przewidywanaDataGodzinaZakonczenia, Double szacowanyKoszt) {
        this.dataGodzinaRozpoczecia = dataGodzinaRozpoczecia;
        this.przewidywanaDataGodzinaZakonczenia = przewidywanaDataGodzinaZakonczenia;
        this.szacowanyKoszt = szacowanyKoszt;
        status = Status.OCZEKUJACA;
    }

    public Wizyta() {
    }

    public void dodajNaprawe(Naprawa naprawa){
        if(!naprawy.contains(naprawa))
        {
            naprawy.add(naprawa);
            naprawa.dodajWizyte(this);
        }
    }

    public static void dodajWizyte(LocalDateTime dataGodzinaRozpoczecia, LocalDateTime przewidywanaDataGodzinaZakonczenia, Double szacowanyKoszt)
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

            Wizyta wizyta = new Wizyta(dataGodzinaRozpoczecia, przewidywanaDataGodzinaZakonczenia, szacowanyKoszt);

            session.save(wizyta);

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

    public void anulujWizyte()
    {
        status = Status.ANULOWANA;
    }

    public static List<Wizyta> pokazListeWizyt()
    {
        List<Wizyta> wizyty = new ArrayList<>();
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

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Wizyta> criteria = builder.createQuery( Wizyta.class );
            Root<Wizyta> root = criteria.from( Wizyta.class );
            criteria.select( root );
            wizyty = session.createQuery( criteria ).getResultList();

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
        return wizyty;
    }

    public static boolean sprawdzDateCzyDostepna(LocalDateTime od, LocalDateTime do_)
    {
        AtomicBoolean dostepna = new AtomicBoolean(true);
        pokazListeWizyt().forEach(wizyta -> {
            if(!((od.isBefore(wizyta.dataGodzinaRozpoczecia) && do_.isBefore(wizyta.przewidywanaDataGodzinaZakonczenia))
                    ||(od.isAfter(wizyta.dataGodzinaRozpoczecia)) && (do_.isAfter(wizyta.przewidywanaDataGodzinaZakonczenia))))
            {
                dostepna.set(false);
            }
        });
        return dostepna.get();
    }


    public void usunNaprawe(Naprawa naprawa){
        if(naprawy.contains(naprawa))
        {
            naprawy.remove(naprawa);
            naprawa.usunWizyte();
        }
    }

    public void dodajSamochod(Samochod samochod)
    {
        if(this.samochod != null)
        {
            if(this.samochod != samochod)
            {
                this.samochod.usunWizyte(this);
                this.samochod = samochod;
                samochod.dodajWizyte(this);
            }
        }
        else
        {
            this.samochod = samochod;
            samochod.dodajWizyte(this);
        }
    }

    public void usunSamochod()
    {
        if(samochod.getWizyty().contains(this))
            samochod.usunWizyte(this);

        if(samochod !=null)
        {
            samochod = null;
        }
    }

    public List<Naprawa> getNaprawy() {
        return naprawy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataGodzinaRozpoczecia() {
        return dataGodzinaRozpoczecia;
    }

    public void setDataGodzinaRozpoczecia(LocalDateTime dataGodzinaRozpoczecia) {
        this.dataGodzinaRozpoczecia = dataGodzinaRozpoczecia;
    }

    public LocalDateTime getPrzewidywanaDataGodzinaZakonczenia() {
        return przewidywanaDataGodzinaZakonczenia;
    }

    public void setPrzewidywanaDataGodzinaZakonczenia(LocalDateTime przewidywanaDataGodzinaZakonczenia) {
        this.przewidywanaDataGodzinaZakonczenia = przewidywanaDataGodzinaZakonczenia;
    }

    public LocalDateTime getDataGodzinaZakonczenia() {
        return dataGodzinaZakonczenia;
    }

    public void setDataGodzinaZakonczenia(LocalDateTime dataGodzinaZakonczenia) {
        this.dataGodzinaZakonczenia = dataGodzinaZakonczenia;
    }

    public Double getSzacowanyKoszt() {
        return szacowanyKoszt;
    }

    public void setSzacowanyKoszt(Double szacowanyKoszt) {
        this.szacowanyKoszt = szacowanyKoszt;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }

    public static Double getKosztRoboczogodziny() {
        return kosztRoboczogodziny;
    }

    public static void setKosztRoboczogodziny(Double kosztRoboczogodziny) {
        Wizyta.kosztRoboczogodziny = kosztRoboczogodziny;
    }
}
