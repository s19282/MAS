import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

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
            Osoba osobaPracownik = new Osoba("Jan","Kowal",353454434,20D, 44225586433L);
            Osoba osobaKlient = new Osoba("Bogdan","Wiśniewski",353454434,"c1292");
            Osoba osobaPracownikKlientIndywidualny = new Osoba("Imie","Nazwisko",53454434,"c1203",20D, 44225586433L);

            session.save(osobaPracownik);
            session.save(osobaKlient);
            session.save(osobaPracownikKlientIndywidualny);

            Stanowisko stanowisko = new Stanowisko("mechanik", "naprawa pojazdów");
            Zatrudnienie zatrudnienie = new Zatrudnienie(LocalDate.now().minusYears(4),4000D,osobaPracownik,stanowisko);
            session.save(zatrudnienie);

            Naprawa naprawa = new Naprawa("tylny dyferencjał", "wymiana oleju w tylnym dyferencjale", LocalTime.of(1,0),400D);
//            osobaKlient.dodajNaprawe(naprawa);
            naprawa.dodajOsobe(osobaPracownik);
            session.save(naprawa);

            Klient klient = new KlientInstytucja("PJATK","010816248",345654789L);
            Samochod samochod = new Samochod("CBA 23221", "Mitsubishi", "Lancer");
            osobaKlient.dodajSamochod(samochod);
            session.save(klient);
            session.save(samochod);
            session.save(stanowisko);

            Wizyta wizyta = new Wizyta(LocalDateTime.now().plusDays(4),LocalDateTime.now().plusDays(5),5000D);
            wizyta.dodajSamochod(samochod);
            wizyta.dodajNaprawe(naprawa);
            session.save(wizyta);
            CzynnoscEksploatacyjna czynnoscEksploatacyjna = new CzynnoscEksploatacyjna("Wymiana oleju",50D);
            naprawa.dodajCzynnoscEksploatacyjna(czynnoscEksploatacyjna);
            session.save(czynnoscEksploatacyjna);
            Czesc czesc = new Czesc("chłodnica", 100d);
            naprawa.dodajCzesc(czesc);
            session.save(czesc);
            session.getTransaction().commit();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Osoba> criteria = builder.createQuery( Osoba.class );
            Root<Osoba> root = criteria.from( Osoba.class );
            criteria.select( root );
//            criteria.where( builder.equal( root.get( "typOsoby" ), TypyOsoby.PRACOWNIK) );
            List<Osoba> osoby = session.createQuery( criteria ).getResultList();

            for(Osoba o : osoby.stream().filter(o -> o.getTypyOsob().contains(TypyOsoby.PRACOWNIK)).collect(Collectors.toList()))
            {
                System.out.println(o);
            }
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
}
