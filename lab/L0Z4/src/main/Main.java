package main;

import java.io.*;

public class Main {

    public static void serializuj(CiagPomiarow ciagPomiarow) {
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("pomiary.ser"));

            oOS.writeObject(ciagPomiarow);
            oOS.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CiagPomiarow deserializuj() {
        CiagPomiarow ciagPomiarow;
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("pomiary.ser"));

            ciagPomiarow = (CiagPomiarow) oIS.readObject();
            oIS.close();
            return ciagPomiarow;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Pomiar p1 = new Pomiar(new Czas(2018, 5, 10, 16, 15), 19.4);
        Pomiar p2 = new Pomiar(new Czas(2018, 5, 15, 15, 10), 21.9);
        Pomiar p3 = new Pomiar(new Czas(2018, 5, 20, 13, 0), 14.4);
        Pomiar p4 = new Pomiar(new Czas(2019, 11, 20, 16, 15), 9.4);
        Pomiar p5 = new Pomiar(new Czas(2020, 10, 12, 12, 45), 13.4);

        Pomiar[] pomiary = new Pomiar[]{p1, p2, p3, p4, p5};

        CiagPomiarow ciagPomiarow = new CiagPomiarow(pomiary);

        serializuj(ciagPomiarow);
        ciagPomiarow = deserializuj();

        System.out.println(ciagPomiarow);
    }

}
