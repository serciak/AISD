package main;

import java.io.*;
import java.util.Random;

public class Main {

    public static double[][] stworzMacierz() {
        Random generator = new Random();
        double[][] macierz = new double[Math.abs(generator.nextInt()%10)+3][Math.abs(generator.nextInt()%10)+3];

        for(int i = 0; i<macierz.length; i++) {
            for(int j = 0; j<macierz[0].length; j++) {
                macierz[i][j] = Math.ceil(generator.nextDouble()*10000)/100;
            }
        }
        return macierz;
    }

    public static void wypiszMacierz(double[][] m) {
        for(int i = 0; i<m.length; i++) {
            System.out.print("\n\n|\t");
            for(int j = 0; j<m[i].length; j++) {
                System.out.print(m[i][j]);
                System.out.print("\t|\t");
            }
        }
        System.out.println("\n\n");
    }

    public static void maxElement(double[][] m) {
        double max = m[0][0];
        int index1 = 0;
        int index2 = 0;

        for(int i = 0; i<m.length; i++) {
            for(int j = 0; j<m[0].length; j++) {
                if(max<m[i][j]) {
                    max = m[i][j];
                    index1 = i;
                    index2 = j;
                }
            }
        }

        System.out.println("Maksymalna wartość w macierzy: " + max + ", znajduje sie w wierzu: " + index1 + " i kolumnie: " + index2);
    }

    public static void zapiszBin(double[][] m) {
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("macierz.bin"));

            oOS.writeInt(m.length);
            oOS.writeInt(m[0].length);

            for(int i = 0; i < m.length; i++) {
                for(int j = 0; j < m[i].length; j++) {
                    oOS.writeDouble(m[i][j]);
                }
            }
            oOS.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static double[][] odczytajBin() {
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("macierz.bin"));

            int wiersze = oIS.readInt();
            int kolumny = oIS.readInt();

            double[][] nowa = new double[wiersze][kolumny];

            for(int i = 0; i < nowa.length; i++) {
                for(int j = 0; j < nowa[i].length; j++) {
                    nowa[i][j] = oIS.readDouble();
                }
            }
            return nowa;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        zapiszBin(stworzMacierz());
        double[][] m = odczytajBin();
        wypiszMacierz(m);
        maxElement(m);
    }
}
