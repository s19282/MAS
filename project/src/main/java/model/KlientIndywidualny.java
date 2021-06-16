package model;

import javax.persistence.Entity;

@Entity
public class KlientIndywidualny extends Klient
{
//    TODO: remove
    private static Double maxRabatNaUslugi = 0.3;
    private String imie;
    private String nazwisko;
    private Long numerTelefonu;
}
