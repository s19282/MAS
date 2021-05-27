import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main
{
    public static void main(String[] args)
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

            Vehicle vehicle = new Vehicle("Subaru","WRX",2000, FuelType.PETROL, 2014);
            Vehicle vehicle2 = new Vehicle("Mitsubishi","Lancer evolution",2000, FuelType.PETROL, 2015);


            session.save(vehicle);
            session.save(vehicle2);
//            vehicle.setEngineCapacity(3000);
//            session.update(vehicle);
//            session.delete(vehicle);
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
