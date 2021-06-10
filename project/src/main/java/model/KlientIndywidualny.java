package model;

import javax.persistence.Entity;

@Entity
public class KlientIndywidualny extends Klient
{
    private static Double maxRabatNaUslugi;
    private String imie;
    private String nazwisko;
    private Long numerTelefonu;
}
