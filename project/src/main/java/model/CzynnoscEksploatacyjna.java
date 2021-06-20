package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import java.time.LocalTime;
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

    public static void dodajCzynnosc(String opis, Double koszt)
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

            CzynnoscEksploatacyjna czynnoscEksploatacyjna = new CzynnoscEksploatacyjna(opis, koszt);

            session.save(czynnoscEksploatacyjna);

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
