package main;

import java.util.ArrayList;
import java.util.Iterator;

public class Student {

    private int id;
    private String imie;
    private String nazwisko;
    private ArrayList<Double> oceny = new ArrayList<>();
    private double srednia = 0;

    public Student(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        obliczSrednia();

        return id + "\t" + nazwisko + "\t" + imie + "\t" + srednia;
    }

    public void dodajOcene(double ocena) {
        oceny.add(ocena);
    }

    private void obliczSrednia() {
        Iterator<Double> iterator = oceny.iterator();
        double suma = 0;

        while(iterator.hasNext()) {
            suma += iterator.next();
        }

        srednia = suma/oceny.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public double getSrednia() {
        obliczSrednia();
        return srednia;
    }

    public void setSrednia(double srednia) {
        this.srednia = srednia;
    }
}

