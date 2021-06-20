package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Samochod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    private String numerRejestracyjny;
    private String marka;
    private String model;
    @OneToMany(
            mappedBy = "samochod",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Wizyta> wizyty = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "KlientSamochod",
            joinColumns = { @JoinColumn(name = "samochodId") },
            inverseJoinColumns = { @JoinColumn(name = "klientId") }
    )
    private final List<Klient> klienci = new ArrayList<>();


    public Samochod(String numerRejestracyjny, String marka, String model) {
        this.numerRejestracyjny = numerRejestracyjny;
        this.marka = marka;
        this.model = model;
    }

    public Samochod() {
    }

    public static void dodajSamochod(String numerRejestracyjny, String marka, String model)
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

            Samochod samochod = new Samochod(numerRejestracyjny,marka,model);

            session.save(samochod);

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

    public void dodajKlienta(Klient klient) throws Exception {
        if(!klienci.contains(klient))
        {
            klienci.add(klient);
            klient.dodajSamochod(this);
        }
    }


    public void usunKlienta(Klient klient) throws Exception {
        if(klienci.contains(klient))
        {
            klienci.remove(klient);
            klient.usunSamochod(this);
        }
    }

    public void dodajWizyte(Wizyta wizyta)
    {
        if(!wizyty.contains(wizyta))
        {
            wizyty.add(wizyta);
            wizyta.dodajSamochod(this);
        }
    }


    public void usunWizyte(Wizyta wizyta)
    {
        if(wizyty.contains(wizyta))
        {
            wizyty.remove(wizyta);
            wizyta.usunSamochod();
        }
    }

    public List<Wizyta> getWizyty() {
        return wizyty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
