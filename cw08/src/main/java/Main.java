import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
            session.beginTransaction();

//==============Class=====================
            Vehicle vehicle = new Vehicle("Subaru","WRX",2000, FuelType.PETROL, 2014);
            Vehicle vehicle2 = new Vehicle("Mitsubishi","Lancer evolution",2000, FuelType.PETROL, 2015);

            session.save(vehicle);
            session.save(vehicle2);
//            vehicle.setEngineCapacity(3000);
//            session.update(vehicle);
//            session.delete(vehicle);

//=================Association==================
            Repair r1 = new Repair("def544fk","intake manifold replacement", LocalTime.of(1,0),RepairState.WAITING,1000D);
            Repair r2 = new Repair("def544fk","exhaust replacement", LocalTime.of(1,40),RepairState.IN_PROGRESS,1340D);
            Visit visit = new Visit(LocalDate.now(),LocalDate.now().plusDays(3),3000D);
            visit.addRepair(r1);
            visit.addRepair(r2);

            session.save(r1);
            session.save(r2);
            session.save(visit);

//=================Inheritance==================
            Driver driver = new Driver("Alan","Vicknair",1212, LocalDate.now().minusYears(3));
            Driver lorryDriver = new Driver("Tod", "Hone", 35334, LocalDate.now().minusYears(2),54643);
            Driver taxiDriver = new Driver("Troy","Thompson",34343,LocalDate.now().minusYears(5),33232,534545);
            Driver taxiAndLorryDriver = new Driver("Carl","Tidwell",434561,LocalDate.now().minusMonths(4),4523423,34234,65656);
            session.save(driver);
            session.save(lorryDriver);
            session.save(taxiDriver);
            session.save(taxiAndLorryDriver);

            session.getTransaction().commit();
            List<Vehicle> vehiclesFromDB = session.createQuery("from Vehicle").list();
            for(Vehicle vehicle_ : vehiclesFromDB)
            {
                System.out.println(vehicle_);
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
