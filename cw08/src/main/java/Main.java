import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

//==============Class=====================
            session.beginTransaction();
            Vehicle vehicle = new Vehicle("Subaru","WRX",2000, FuelType.PETROL, 2014);
            Vehicle vehicle2 = new Vehicle("Mitsubishi","Lancer evolution",2000, FuelType.PETROL, 2015);

            session.save(vehicle);
            session.save(vehicle2);
//            vehicle.setEngineCapacity(3000);
//            session.update(vehicle);
//            session.delete(vehicle);
            session.getTransaction().commit();


            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Vehicle> criteria = builder.createQuery( Vehicle.class );
            Root<Vehicle> root = criteria.from( Vehicle.class );
            criteria.select( root );
//            criteria.where( builder.equal( root.get( "year" ), 2014 ) );
            List<Vehicle> vehiclesFromDb = session.createQuery( criteria ).getResultList();

            for(Vehicle v : vehiclesFromDb)
            {
                System.out.println(v);
            }
//=================Association==================
            session.beginTransaction();
            Repair r1 = new Repair("def544fk","intake manifold replacement", LocalTime.of(1,0),RepairState.WAITING,1000D);
            Repair r2 = new Repair("def544fk","exhaust replacement", LocalTime.of(1,40),RepairState.IN_PROGRESS,1340D);
            Visit visit = new Visit(LocalDate.now(),LocalDate.now().plusDays(3),3000D);
            visit.addRepair(r1);
            visit.addRepair(r2);

            session.save(r1);
            session.save(r2);
//            r1.setDescription("intake manifold fix");
//            session.update(r1);
//            r2.removeVisit();
//            session.delete(r2);
            session.save(visit);
//            session.remove(visit);
            session.getTransaction().commit();

            builder = session.getCriteriaBuilder();

            CriteriaQuery<Repair> criteria2 = builder.createQuery( Repair.class );
            Root<Repair> root2 = criteria2.from( Repair.class );
            criteria2.select( root2 );
            List<Repair> repairs = session.createQuery( criteria2 ).getResultList();

            for(Repair r : repairs)
            {
                System.out.println(r);
            }

//=================Inheritance==================
            session.beginTransaction();
            Driver driver = new Driver("Alan","Vicknair",1212, LocalDate.now().minusYears(3));
            LorryDriver lorryDriver = new LorryDriver("Tod", "Hone", 35334, LocalDate.now().minusYears(2),54643);
            TaxiDriver taxiDriver = new TaxiDriver("Troy","Thompson",34343,LocalDate.now().minusYears(5),33232,534545);
            session.save(driver);
            session.save(lorryDriver);
            session.save(taxiDriver);
//            driver.setFirstName("nie Alan");
//            session.update(driver);
//            session.remove(driver);

            session.getTransaction().commit();


            session.beginTransaction();

            builder = session.getCriteriaBuilder();

            CriteriaQuery<Driver> criteria3 = builder.createQuery( Driver.class );
            Root<LorryDriver> root3 = criteria3.from( LorryDriver.class );
            criteria3.select( root3 );
            List<Driver> driversFromDb = session.createQuery( criteria3 ).getResultList();

            for(Driver d : driversFromDb)
            {
                System.out.println(d);
            }
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
