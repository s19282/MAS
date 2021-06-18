import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Warsztat samochodowy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        listaNapraw(frame);

        frame.setVisible(true);
    }

    public static void listaNapraw(JFrame frame)
    {
        List<Osoba> pracownicyZBazy = pobierzPracownikowZBazy();
        List<Naprawa> naprawyZBazy = pobierzNaprawyZBazy();
        JLabel naglowekLabel = new JLabel("Lista napraw");
        JLabel pracownicyLabel = new JLabel("Pracownicy: ");
        JComboBox<String> pracownicy = new JComboBox<>(new String[]{"Wszyscy pracownicy"});
        pracownicyZBazy.forEach(p -> pracownicy.addItem(p.toString()));
//        JComboBox<Osoba> pracownicy = new JComboBox<>(pracownicyZBazy.toArray(Osoba[]::new));
        JLabel naprawyLabel = new JLabel("Naprawy: ");
        JComboBox<String> naprawy = new JComboBox<>(new String[]{"Wszystkie naprawy"});
        naprawyZBazy.forEach(n -> naprawy.addItem(n.toString()));
//        JComboBox<Naprawa> naprawy = new JComboBox<>(naprawyZBazy.toArray(Naprawa[]::new));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        naprawyZBazy.forEach(n->listModel.addElement(n.toString()));
        JList<String> lista = new JList<>(listModel);

        MyHelper helper = new MyHelper();
        pracownicy.addActionListener(e -> {
            if(!helper.isRunning())
            {
                helper.setRunning(true);

                naprawy.removeAllItems();
                naprawy.addItem("Wszystkie naprawy");

                if(pracownicy.getSelectedIndex() == 0)
                {
                    pracownicyZBazy.forEach(p -> pracownicy.addItem(p.toString()));
                }
                else
                {
                    naglowekLabel.setText("Naprawy wykonane przez pracownika : "+pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getId());
                    listModel.clear();
                    pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n-> listModel.addElement(n.toString()));
                    pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n-> naprawy.addItem(n.toString()));
                }

                helper.setRunning(false);
            }
        });

        naprawy.addActionListener(e -> {
            if(!helper.isRunning())
            {
                helper.setRunning(true);

                pracownicy.removeAllItems();
                pracownicy.addItem("Wszyscy pracownicy");

                if(naprawy.getSelectedIndex() == 0)
                {
                    naprawyZBazy.forEach(n -> naprawy.addItem(n.toString()));
                }
                else
                {
                    naglowekLabel.setText("Pracownicy wykonujący naprawę numer: "+naprawyZBazy.get(naprawy.getSelectedIndex()-1).getId());
                    listModel.clear();
                    naprawyZBazy.get(naprawy.getSelectedIndex()-1).getOsoby().forEach(p -> listModel.addElement(p.toString()));
                    naprawyZBazy.get(naprawy.getSelectedIndex()-1).getOsoby().forEach(p -> pracownicy.addItem(p.toString()));
                }

                helper.setRunning(false);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(pracownicyLabel);
        panel.add(pracownicy);
        panel.add(naprawyLabel);
        panel.add(naprawy);
        frame.setLayout(new GridLayout(4,1));
//        frame.add(listaNapraw, BorderLayout.NORTH);
        frame.add(naglowekLabel);
        frame.add(panel);
        frame.add(lista);
//        frame.add(lista, BorderLayout.CENTER);
    }

    public static List<Osoba> pobierzPracownikowZBazy()
    {
        List<Osoba> pracownicy = new ArrayList<>();
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
            pracownicy = session.createQuery( criteria )
                            .getResultList()
                            .stream().filter(o -> o.getTypyOsob()
                            .contains(TypyOsoby.PRACOWNIK)).collect(Collectors.toList());

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
        return pracownicy;
    }

    public static List<Naprawa> pobierzNaprawyZBazy()
    {
        List<Naprawa> naprawy = null;
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

            CriteriaQuery<Naprawa> criteria = builder.createQuery( Naprawa.class );
            Root<Naprawa> root = criteria.from( Naprawa.class );
            criteria.select( root );
            naprawy = session.createQuery( criteria ).getResultList();

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
        return naprawy;
    }
}
