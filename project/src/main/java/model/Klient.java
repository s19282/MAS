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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Column(unique = true)
    private String numerKlienta;
    private Double rabatNaUslugi;
    private static Double maxRabatNaUslugi;
    @ManyToMany(mappedBy = "klienci")
    private final List<Samochod> samochody = new ArrayList<>();

    public Klient(String numerKlienta) {
        this.numerKlienta = numerKlienta;
    }

    public Klient() {
    }

    public void dodajSamochod(Samochod samochod) throws Exception {
        if(!samochody.contains(samochod))
        {
            samochody.add(samochod);
            samochod.dodajKlienta(this);
        }
    }


    public void usunSamochod(Samochod samochod) throws Exception {
        if(samochody.contains(samochod))
        {
            samochody.remove(samochod);
            samochod.usunKlienta(this);
        }
    }

    public static List<Klient> pokazWszystkichKlientow()
    {
        List<Klient> klienci = new ArrayList<>();
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
            CriteriaQuery<Osoba> criteria = builder.createQuery( Osoba.class );
            Root<Osoba> root = criteria.from( Osoba.class );
            criteria.select( root );
            klienci = session.createQuery( criteria ).getResultList().stream()
                    .filter(osoba -> osoba.getTypyOsob().contains(TypyOsoby.KLIENT_INDYWIDUALNY))
                    .collect(Collectors.toList());

            CriteriaQuery<KlientInstytucja> criteria2 = builder.createQuery( KlientInstytucja.class );
            Root<KlientInstytucja> root2 = criteria.from( KlientInstytucja.class );
            criteria2.select( root2 );
            klienci.addAll(session.createQuery(criteria2).getResultList());

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
        return klienci;
    }

    public List<Samochod> pobierzWszystkieSamochodyKlienta() {
        return samochody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumerKlienta() throws Exception
    {
        return numerKlienta;
    }

    public void setNumerKlienta(String numerKlienta) throws Exception {
        this.numerKlienta = numerKlienta;
    }

    public Double getRabatNaUslugi() {
        return rabatNaUslugi;
    }

    public void setRabatNaUslugi(Double rabatNaUslugi) {
        this.rabatNaUslugi = rabatNaUslugi;
    }

    public static Double getMaxRabatNaUslugi() {
        return maxRabatNaUslugi;
    }

    public static void setMaxRabatNaUslugi(Double maxRabatNaUslugi) {
        Klient.maxRabatNaUslugi = maxRabatNaUslugi;
    }
}
