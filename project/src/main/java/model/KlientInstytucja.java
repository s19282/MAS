package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class KlientInstytucja extends Klient {
    private String nazwa;
    @Column(length = 9)
    private String regon;
    private static Double maxRabatNaUslugi;
    private Long numerTelefonu;

    public KlientInstytucja(String nazwa, String regon, Long numerTelefonu, String numerKlienta) {
        super(numerKlienta);
        this.nazwa = nazwa;
        this.regon = regon;
        this.numerTelefonu = numerTelefonu;
    }

    public KlientInstytucja() {
    }

    public static void dodajKlienta(String numerKlienta,String nazwa, String regon, Long numerTelefonu)
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

            KlientInstytucja klientInstytucja = new KlientInstytucja(nazwa,regon,numerTelefonu,numerKlienta);

            session.save(klientInstytucja);

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

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public static Double getMaxRabatNaUslugi() {
        return maxRabatNaUslugi;
    }

    public static void setMaxRabatNaUslugi(Double maxRabatNaUslugi) {
        KlientInstytucja.maxRabatNaUslugi = maxRabatNaUslugi;
    }

    public Long getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(Long numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
