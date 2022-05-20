package main;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main {

    public static void zapiszBin(ArrayList<Pomiar> p) {
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("pomiary.bin"));

            for (Pomiar pomiar : p) {
                oOS.writeObject(pomiar);
            }
            oOS.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Pomiar> odczytajBin() {
        ArrayList<Pomiar> p = new ArrayList<>();
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("pomiary.bin"));

            while(true) {
                p.add((Pomiar) oIS.readObject());
            }

        } catch(EOFException e) {
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static double srednia(int rok, int miesiac) {
        double suma = 0;
        int n = 0;
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("pomiary.bin"));

            while(true) {
                Pomiar p = ((Pomiar) oIS.readObject());
                if(p.getCzas().getRok() == rok && p.getCzas().getMiesiac() == miesiac) {
                    suma += p.getTemperatura();
                    n++;
                }
            }

        } catch(EOFException e) {
            return suma/n;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return suma/n;
    }

    public static void ocieplenie(int rok, int miesiac) {
        double s = srednia(rok, miesiac);
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("pomiary.bin"));
            System.out.println("Dni w ktorych nastapilo ocieplenie (srednia temperatura: " + s + "):");

            while(true) {
                Pomiar p = (Pomiar) oIS.readObject();
                if(p.getTemperatura() > s) {
                    System.out.println(p);
                }
            }

        } catch(EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void wypiszMiesiac(int rok, int miesiac) {
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("pomiary.bin"));
            System.out.println("Pomiary z " + rok + "/" + miesiac + ":");

            while (true) {
                Pomiar p = (Pomiar) oIS.readObject();
                if (p.getCzas().getRok() == rok && p.getCzas().getMiesiac() == miesiac) {
                    System.out.println(p);
                }
            }

        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dopiszPomiar(Pomiar pomiar) {
        ArrayList<Pomiar> pomiary = odczytajBin();
        ListIterator<Pomiar> it = pomiary.listIterator();

        while(it.hasNext()) {
            Pomiar temp = it.next();

            if(!(temp.getCzas().getRok() < pomiar.getCzas().getRok() || temp.getCzas().getMiesiac() < pomiar.getCzas().getMiesiac() || temp.getCzas().getDzien() < pomiar.getCzas().getDzien() || temp.getCzas().getGodzina() < pomiar.getCzas().getGodzina() || temp.getCzas().getMinuta() < pomiar.getCzas().getMinuta())) {
                pomiary.add(it.previousIndex(), pomiar);
                zapiszBin(pomiary);
                return;
            }
        }
        pomiary.add(pomiar);
        zapiszBin(pomiary);
    }


    public static void main(String[] args) {
        Pomiar p1 = new Pomiar(new Czas(2018, 5, 10, 16, 15), 19.4);
        Pomiar p2 = new Pomiar(new Czas(2018, 5, 15, 15, 10), 21.9);
        Pomiar p3 = new Pomiar(new Czas(2018, 5, 20, 13, 0), 14.4);
        Pomiar p4 = new Pomiar(new Czas(2019, 11, 20, 16, 15), 9.4);
        Pomiar p5 = new Pomiar(new Czas(2020, 10, 12, 12, 45), 13.4);
        Pomiar p6 = new Pomiar(new Czas(2018, 5, 15, 12, 10), 9.5);

        ArrayList<Pomiar> pomiary = new ArrayList<>();

        pomiary.add(p1);
        pomiary.add(p2);
        pomiary.add(p3);
        pomiary.add(p4);
        pomiary.add(p5);


        zapiszBin(pomiary);

        wypiszMiesiac(2018, 5);
        ocieplenie(2018, 5);
        dopiszPomiar(p6);

        System.out.println(odczytajBin());
    }

}
