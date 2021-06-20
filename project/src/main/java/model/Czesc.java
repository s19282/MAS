package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import java.time.LocalTime;

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

    public static void dodajCzesc(String nazwaCzesci, Double koszt)
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

            Czesc czesc = new Czesc(nazwaCzesci, koszt);

            session.save(czesc);

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
