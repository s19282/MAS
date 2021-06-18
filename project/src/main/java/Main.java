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
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Warsztat samochodowy");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800,600);
            frame.setResizable(false);

            listaNaprawWersja2(frame);

            frame.setVisible(true);
        });
    }

    public static void listaNaprawWersja2(JFrame frame)
    {
        List<Osoba> pracownicyZBazy = pobierzPracownikowZBazy();
        Set<Naprawa> unikalneNaprawy = new HashSet<>();
        JLabel naglowekLabel = new JLabel("Lista napraw");

        JLabel pracownicyLabel = new JLabel("Pracownicy: ");
        JComboBox<String> pracownicy = new JComboBox<>(new String[]{"Wszyscy pracownicy"});
        pracownicyZBazy.forEach(p -> pracownicy.addItem(p.toString()));

        JLabel naprawyLabel = new JLabel("Naprawy: ");
        JComboBox<String> naprawy = new JComboBox<>(new String[]{"Wszystkie naprawy"});
        pracownicyZBazy.forEach(pracownik -> {
            try {
                unikalneNaprawy.addAll(pracownik.getNaprawy());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        unikalneNaprawy.forEach(n -> naprawy.addItem(n.toString()));

//        JComboBox<Naprawa> naprawy = new JComboBox<>(naprawyZBazy.toArray(Naprawa[]::new));
        MyCellRenderer cellRenderer = new MyCellRenderer(600);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        unikalneNaprawy.forEach(n->listModel.addElement(n.toString()));
        JList<String> lista = new JList<>(listModel);
        lista.setCellRenderer(cellRenderer);

        MyHelper helper = new MyHelper();
        pracownicy.addActionListener(e -> {
            if(!helper.isRunning()) // Zabezpieczenie przeciwko zapętleniu
            {
                helper.setRunning(true);

                if(pracownicy.getSelectedIndex() != 0) // Wybrany jakiś pracownik
                {
                    if(naprawy.getSelectedIndex() != 0) // Wybrana jakaś naprawa
                    {
                        naprawaSzczegoly(pracownicyZBazy, naglowekLabel, pracownicy, naprawy, listModel);

                        Object selected = naprawy.getSelectedItem();
                        naprawy.removeAllItems();
                        naprawy.addItem("Wszystkie naprawy");
                        try {
                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n -> naprawy.addItem(n.toString()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        naprawy.setSelectedItem(selected);
                    }
                    else // Wybrane wszystkie naprawy
                    {
                        naglowekLabel.setText("Lista napraw pracownika: "+pracownicy.getSelectedIndex());
                        listModel.clear();
                        try {
                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n-> listModel.addElement(n.toString()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }


                        naprawy.removeAllItems();
                        naprawy.addItem("Wszystkie naprawy");
                        try {
                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach( n ->naprawy.addItem(n.toString()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
                else // Wybrani wszyscy pracownicy
                {
                    if(naprawy.getSelectedIndex() !=0 ) // Wybrana jakaś naprawa
                    {
                        naglowekLabel.setText("Lista pracowników naprawy nr: "+naprawaZeStringa(unikalneNaprawy,naprawy).getId());
                        listModel.clear();
                        naprawaZeStringa(unikalneNaprawy,naprawy).getOsoby().forEach(p -> listModel.addElement(p.toString()));


                        Object selected = naprawy.getSelectedItem();
                        naprawy.removeAllItems();
                        naprawy.addItem("Wszystkie naprawy");
                        unikalneNaprawy.forEach(n->naprawy.addItem(n.toString()));
                        naprawy.setSelectedItem(selected);
                    }
                    else //Wybrane wszystkie naprawy
                    {
                        naglowekLabel.setText("Lista napraw");
                        listModel.clear();
                        unikalneNaprawy.forEach(n->listModel.addElement(n.toString()));

                        naprawy.removeAllItems();
                        naprawy.addItem("Wszystkie naprawy");
                        unikalneNaprawy.forEach(n->naprawy.addItem(n.toString()));
                    }
                }

                helper.setRunning(false);
            }
        });

        naprawy.addActionListener(e -> {
            if(!helper.isRunning())// Zabezpieczenie przeciwko zapętleniu
            {
                helper.setRunning(true);

                if(naprawy.getSelectedIndex() != 0) // Wybrana jakaś naprawa
                {
                    if(pracownicy.getSelectedIndex() !=0 ) // Wybrany jakiś pracownik
                    {
                        naprawaSzczegoly(pracownicyZBazy, naglowekLabel, pracownicy, naprawy, listModel);


                        Object selected = pracownicy.getSelectedItem();
                        pracownicy.removeAllItems();
                        pracownicy.addItem("Wszyscy pracownicy");
                        naprawaZeStringa(unikalneNaprawy,naprawy).getOsoby().forEach(p->pracownicy.addItem(p.toString()));
                        pracownicy.setSelectedItem(selected);
                    }
                    else //Wybrani wszyscy pracownicy
                    {
                        naglowekLabel.setText("Lista pracowników naprawy nr: "+naprawaZeStringa(unikalneNaprawy,naprawy).getId());
                        listModel.clear();
                        naprawaZeStringa(unikalneNaprawy,naprawy).getOsoby().forEach(p -> listModel.addElement(p.toString()));


                        pracownicy.removeAllItems();
                        pracownicy.addItem("Wszyscy pracownicy");
                        naprawaZeStringa(unikalneNaprawy, naprawy).getOsoby().forEach(o -> pracownicy.addItem(o.toString()));
                    }
                }
                else // Wybrane wszystkie naprawy
                {
                    if(pracownicy.getSelectedIndex() !=0 ) // Wybrany jakiś pracownik
                    {
                        naglowekLabel.setText("Lista napraw pracownika: "+pracownicy.getSelectedIndex());
                        listModel.clear();
                        try {
                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n-> listModel.addElement(n.toString()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }


                        Object selected = pracownicy.getSelectedItem();
                        pracownicy.removeAllItems();
                        pracownicy.addItem("Wszyscy pracownicy");
                        pracownicyZBazy.forEach(p->pracownicy.addItem(p.toString()));
                        pracownicy.setSelectedItem(selected);
                    }
                    else //Wybrani wszyscy pracownicy
                    {
                        naglowekLabel.setText("Lista napraw");
                        listModel.clear();
                        unikalneNaprawy.forEach(n->listModel.addElement(n.toString()));


                        pracownicy.removeAllItems();
                        pracownicy.addItem("Wszyscy pracownicy");
                        pracownicyZBazy.forEach(p->pracownicy.addItem(p.toString()));
                    }
                }

                helper.setRunning(false);
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());


        pracownicyLabel.setBackground(Color.RED);
        pracownicyLabel.setOpaque(true);


        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pracownicyLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(pracownicy, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(naprawyLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(naprawy, c);
        frame.setLayout(new GridBagLayout());

        c = new GridBagConstraints();

        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(naglowekLabel,c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(panel,c);

        c.gridx = 0;
        c.gridy = 2;
        frame.add(lista,c);

        naglowekLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        naglowekLabel.setOpaque(true);
        naglowekLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.setBackground(Color.decode("#8ff486"));
        frame.setBackground(Color.decode("#8ff486"));
        frame.setVisible(true);
        naglowekLabel.setBackground(Color.decode("#8ff486"));
        lista.setBackground(Color.decode("#8ff486"));
    }

    private static Naprawa naprawaZeStringa(Set<Naprawa> unikalneNaprawy, JComboBox<String> naprawy) {
        Pattern p = Pattern.compile("Id naprawy: (\\d+),.*");
        Matcher m = p.matcher(naprawy.getSelectedItem().toString());
        if (m.matches()) {
            return unikalneNaprawy.stream().filter(n -> n.getId() == Long.parseLong(m.group(1))).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    private static void naprawaSzczegoly(List<Osoba> pracownicyZBazy, JLabel naglowekLabel, JComboBox<String> pracownicy, JComboBox<String> naprawy, DefaultListModel<String> listModel) {
        naglowekLabel.setText("Szczegóły naprawy");
        listModel.clear();
        try {
            Osoba pracownik = pracownicyZBazy.get(pracownicy.getSelectedIndex()-1);
            try{
                listModel.addElement(pracownik.szczegolyPracownika()+"\n"+pracownik
                        .getNaprawy().get(naprawy.getSelectedIndex()-1).szczegolyNaprawy());
            }
            catch (IndexOutOfBoundsException ignored){}

        } catch (Exception exception) {
            exception.printStackTrace();
        }
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

    public static void listaNapraw(JFrame frame)
    {
//        List<Osoba> pracownicyZBazy = pobierzPracownikowZBazy();
//        List<Naprawa> naprawyZBazy = pobierzNaprawyZBazy();
//        JLabel naglowekLabel = new JLabel("Lista napraw");
//        JLabel pracownicyLabel = new JLabel("Pracownicy: ");
////        JLabel szczegolyNaprawy = new JLabel();
//        JComboBox<String> pracownicy = new JComboBox<>(new String[]{"Wszyscy pracownicy"});
//        pracownicyZBazy.forEach(p -> pracownicy.addItem(p.toString()));
////        JComboBox<Osoba> pracownicy = new JComboBox<>(pracownicyZBazy.toArray(Osoba[]::new));
//        JLabel naprawyLabel = new JLabel("Naprawy: ");
//        JComboBox<String> naprawy = new JComboBox<>(new String[]{"Wszystkie naprawy"});
//        naprawyZBazy.forEach(n -> naprawy.addItem(n.toString()));
////        JComboBox<Naprawa> naprawy = new JComboBox<>(naprawyZBazy.toArray(Naprawa[]::new));
//        MyCellRenderer cellRenderer = new MyCellRenderer(600);
//        DefaultListModel<String> listModel = new DefaultListModel<>();
//        naprawyZBazy.forEach(n->listModel.addElement(n.toString()));
//        JList<String> lista = new JList<>(listModel);
//        lista.setCellRenderer(cellRenderer);
//
//        MyHelper helper = new MyHelper();
//        pracownicy.addActionListener(e -> {
//            if(!helper.isRunning()) // Zabezpieczenie przeciwko zapętleniu
//            {
//                helper.setRunning(true);
//
////                naprawy.removeAllItems();
////                naprawy.addItem("Wszystkie naprawy");
//
//                if(pracownicy.getSelectedIndex() == 0)
//                {
//                    pracownicy.removeAllItems();
//                    pracownicy.addItem("Wszyscy pracownicy");
////                    pracownicyZBazy.forEach(p -> pracownicy.addItem(p.toString()));
//
//                    listModel.clear();
//                    if(naprawy.getSelectedIndex() == 0)
//                    {
//                        pracownicyZBazy.forEach(p -> pracownicy.addItem(p.toString()));
//                        naprawyZBazy.forEach(n->listModel.addElement(n.toString()));
//                    }
//                    else
//                    {
//                        pracownicyZBazy.forEach(p -> {
//                            try {
//                                if(p.getNaprawy().contains(naprawyZBazy.get(naprawy.getSelectedIndex()-1)))
//                                {
//                                    pracownicy.addItem(p.toString());
//                                }
//                            } catch (Exception exception) {
//                                exception.printStackTrace();
//                            }
//                        });
//                        naprawyZBazy.get(naprawy.getSelectedIndex()-1).getOsoby().forEach(o -> {
//                            try {
//                                listModel.addElement(o.toString());
//                            } catch (Exception exception) {
//                                exception.printStackTrace();
//                            }
//                        });
//                    }
//                }
//                else
//                {
//                    if(naprawy.getSelectedIndex() != 0)
//                    {
//                        naprawaSzczegoly(pracownicyZBazy, naglowekLabel, pracownicy, naprawy, listModel);
//                    }
//                    else
//                    {
//                        naprawy.removeAllItems();
//                        naprawy.addItem("Wszystkie naprawy");
//
//                        naglowekLabel.setText("Naprawy wykonane przez pracownika : "+pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getId());
//                        listModel.clear();
//                        try {
//                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n-> listModel.addElement(n.toString()));
//                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy().forEach(n-> naprawy.addItem(n.toString()));
//                        } catch (Exception exception) {
//                            exception.printStackTrace();
//                        }
//                    }
//                }
//
//                helper.setRunning(false);
//            }
//        });
//
//        naprawy.addActionListener(e -> {
//            if(!helper.isRunning())
//            {
//                helper.setRunning(true);
//
////                pracownicy.removeAllItems();
////                pracownicy.addItem("Wszyscy pracownicy");
//
//                if(naprawy.getSelectedIndex() == 0)
//                {
//                    naprawy.removeAllItems();
//                    naprawy.addItem("Wszystkie naprawy");
//
//                    listModel.clear();
//                    if(pracownicy.getSelectedIndex() == 0)
//                    {
//                        naprawyZBazy.forEach(n -> naprawy.addItem(n.toString()));
//                        pracownicyZBazy.forEach(p->listModel.addElement(p.toString()));
//                    }
//                    else
//                    {
//                        try {
//                            naprawyZBazy.forEach(n -> {
//                                try {
//                                    if(n.getOsoby().contains(pracownicyZBazy.get(pracownicy.getSelectedIndex()-1)))
//                                    {
//                                        naprawy.addItem(n.toString());
//                                    }
//                                } catch (Exception exception) {
//                                    exception.printStackTrace();
//                                }
//                            });
//                            pracownicyZBazy.get(pracownicy.getSelectedIndex()-1).getNaprawy()
//                                    .forEach(o -> listModel.addElement(o.toString()));
//                        } catch (Exception exception) {
//                            exception.printStackTrace();
//                        }
//                    }
//                }
//                else
//                {
//                    if(pracownicy.getSelectedIndex() != 0)
//                    {
//                        naprawaSzczegoly(pracownicyZBazy, naglowekLabel, pracownicy, naprawy, listModel);
//                    }
//                    else {
//                        pracownicy.removeAllItems();
//                        pracownicy.addItem("Wszyscy pracownicy");
//
//                        naglowekLabel.setText("Pracownicy wykonujący naprawę numer: " + naprawyZBazy.get(naprawy.getSelectedIndex() - 1).getId());
//                        listModel.clear();
//                        naprawyZBazy.get(naprawy.getSelectedIndex() - 1).getOsoby().forEach(p -> listModel.addElement(p.toString()));
//                        naprawyZBazy.get(naprawy.getSelectedIndex() - 1).getOsoby().forEach(p -> pracownicy.addItem(p.toString()));
//                    }
//                }
//
//                helper.setRunning(false);
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        panel.add(pracownicyLabel);
//        panel.add(pracownicy);
//        panel.add(naprawyLabel);
//        panel.add(naprawy);
//        frame.setLayout(new GridLayout(4,1));
////        frame.add(listaNapraw, BorderLayout.NORTH);
//        frame.add(naglowekLabel);
//        frame.add(panel);
//        frame.add(lista);
////        frame.add(lista, BorderLayout.CENTER);
    }
}
