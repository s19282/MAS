import model.Wizyta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class Main
{
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

//==============Class=====================
            session.beginTransaction();
            Wizyta wizyta = new Wizyta(LocalDateTime.now(), LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(1),500d,450d);
            Wizyta wizyta2 = new Wizyta(LocalDateTime.now(), LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(1),550d,450d);


            session.save(wizyta);
            session.save(wizyta2);

            session.getTransaction().commit();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Wizyta> criteria = builder.createQuery( Wizyta.class );
            Root<Wizyta> root = criteria.from( Wizyta.class );
            criteria.select( root );
//            criteria.where( builder.equal( root.get( "year" ), 2014 ) );
            List<Wizyta> wizyty = session.createQuery( criteria ).getResultList();

            for(Wizyta w : wizyty)
            {
                System.out.println(w);
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
