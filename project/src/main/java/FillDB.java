import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.time.LocalTime;

public class FillDB {
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

            Osoba pracownik1 = new Osoba("Jan","Kowal",987597325,25D, 44225586433L);
            Osoba pracownik2 = new Osoba("Cooper","Macdonald",976294344,10D, 54225586433L);
            Osoba pracownik3 = new Osoba("Gray","Austin",955796544,50D, 46225586433L);
            Osoba pracownik4 = new Osoba("Colt","Henry",912534575,30D, 44925586433L);
            Naprawa naprawa1 = new Naprawa("tylny dyferencjał", "wymiana oleju w tylnym dyferencjale", LocalTime.of(1,0),75D);
            Naprawa naprawa2 = new Naprawa("centralny zamek", "naprawa niedziałającego centralnego zamka w drzwiach pasażera", LocalTime.of(1,30),100D);
            Naprawa naprawa3 = new Naprawa("silnik", "wymiana oleju silnikowego", LocalTime.of(0,30),50D);
            Naprawa naprawa4 = new Naprawa("koła", "wymiana kompletu kół na letnie", LocalTime.of(0,15),30D);

            pracownik1.dodajNaprawe(naprawa1);
            pracownik1.dodajNaprawe(naprawa2);
            pracownik2.dodajNaprawe(naprawa2);
            pracownik3.dodajNaprawe(naprawa3);
            pracownik4.dodajNaprawe(naprawa4);

            session.save(pracownik1);
            session.save(pracownik2);
            session.save(pracownik3);
            session.save(pracownik4);

            session.save(naprawa1);
            session.save(naprawa2);
            session.save(naprawa3);
            session.save(naprawa4);


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
}
