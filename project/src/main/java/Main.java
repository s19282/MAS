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

public class Main
{
//    TODO: test every single association
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
            Osoba osobaPracownik = new Osoba("Imie","Nazwisko",353454434,20D, 44225586433L);
            Osoba osobaKlient = new Osoba("Imie","Nazwisko",353454434,"c1242");
            Osoba osobaPracownikKlientIndywidualny = new Osoba("Imie","Nazwisko",53454434,"c1243",20D, 44225586433L);

            session.save(osobaPracownik);
            session.save(osobaKlient);
            session.save(osobaPracownikKlientIndywidualny);

            Zatrudnienie zatrudnienie = new Zatrudnienie(LocalDate.now().minusYears(4),4000D);
            osobaPracownikKlientIndywidualny.dodajZatrudnienie(zatrudnienie);
//            osobaKlient.dodajZatrudnienie(zatrudnienie);
            session.save(zatrudnienie);

            Naprawa naprawa = new Naprawa("tylny dyferencjał", "wymiana oleju w tylnym dyferencjale", LocalTime.of(1,0),400D);
//            osobaKlient.dodajNaprawe(naprawa);
            naprawa.dodajOsobe(osobaPracownik);
            session.save(naprawa);

            Stanowisko stanowisko = new Stanowisko("mechanik", "naprawa pojazdów");
            stanowisko.dodajZatrudnienie(zatrudnienie);
            session.save(stanowisko);

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
