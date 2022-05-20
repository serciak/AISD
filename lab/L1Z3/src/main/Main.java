package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class predykatZaliczeni implements Predicate<Student> {

    @Override
    public boolean accept(Student arg) {
        return arg.getOcena() >= 3;
    }
}

class predykatNieZaliczeni implements Predicate<Student> {

    @Override
    public boolean accept(Student arg) {
        return arg.getOcena() < 3;
    }
}



public class Main {

    public static Student[] dodajNaPozycji(Student[] s, Student student, int pos) {
        Student[] temp = new Student[s.length+1];

        if (pos >= 0)
            System.arraycopy(s, 0, temp, 0, pos);
        temp[pos] = student;

        if (temp.length - (pos + 1) >= 0)
            System.arraycopy(s, pos, temp, pos + 1, temp.length - (pos + 1));
        s = temp;

        return s;
    }

    public static ArrayIterator<Student> dodajStudenta(ArrayIterator<Student> iterator, Student[] s, Student student) {
        Student temp;

        if(iterator.first().getId()  > student.getId()) {
            dodajNaPozycji(s, student, 0);

            return new ArrayIterator<>(dodajNaPozycji(s, student, 0));
        }
        while(iterator.hasNext()) {
            temp = iterator.next();

            if(temp.getId() > student.getId()) {
                return new ArrayIterator<>(dodajNaPozycji(s, student, iterator.previousIndex()));
            }
            if(temp.getId() == student.getId()) {
                System.out.println("Student o tym numerze id juz istnieje.");
                return new ArrayIterator<>(s);
            }
        }
        iterator.previous();

        return new ArrayIterator<>(dodajNaPozycji(s, student, iterator.nextIndex()));
    }

    public static Student[] usunNaPozycji(Student[] s, int pos) {
        if(s.length > 1) {
            Student[] temp = new Student[s.length - 1];

            if (pos - 1 >= 0) {
                System.arraycopy(s, 0, temp, 0, pos - 1);
            }
            else {
                for(int i = 1; i < s.length; i++) {
                    temp[i-1] = s[i];
                }
                return temp;
            }
            if (temp.length - (pos - 1) >= 0)
                System.arraycopy(s, pos, temp, pos - 1, temp.length - (pos - 1));

            return temp;
        }
        return new Student[0];

    }

    public static ArrayIterator<Student> usunStudenta(ArrayIterator<Student> iterator, Student[] s, int id) {

        if(iterator.first().getId() == id) {
            return new ArrayIterator<>(usunNaPozycji(s, 0));
        }

        while (iterator.hasNext()) {
            Student temp = iterator.next();

            if(temp.getId() == id) {
                iterator.previous();
                return new ArrayIterator<>(usunNaPozycji(s, iterator.nextIndex()));
            }
        }
        System.out.println("Student o danym indeksie nie istnieje.\n");
        return new ArrayIterator<>(s);
    }

    public static ArrayIterator<Student> sortujPoOcenach(ArrayIterator<Student> iterator, Student[] s) {
        boolean hasChanged = true;
        String tempN;
        String tempI;
        int tempID;
        double tempO;
        int temp1;
        int temp2;
        ArrayIterator<Student> tempIt = iterator;

        while(hasChanged) {
            temp2 = 0;

            for(iterator.first(); iterator.hasNext() && temp2 + 1 < s.length; iterator.next()) {
                temp1 = iterator.currentIndex();
                temp2 = iterator.nextIndex();
                hasChanged = false;

                if(s[temp1].getOcena() < s[temp2].getOcena()) {

                    tempN = s[temp1].getNazwisko();
                    tempI = s[temp1].getImie();
                    tempID = s[temp1].getId();
                    tempO = s[temp1].getOcena();

                    s[temp1].setNazwisko(s[temp2].getNazwisko());
                    s[temp1].setImie(s[temp2].getImie());
                    s[temp1].setId(s[temp2].getId());
                    s[temp1].setOcena(s[temp2].getOcena());

                    s[temp2].setNazwisko(tempN);
                    s[temp2].setImie(tempI);
                    s[temp2].setId(tempID);
                    s[temp2].setOcena(tempO);

                    hasChanged = true;
                }
            }

            iterator = tempIt;
        }
        return new ArrayIterator<>(s);
    }

    public static void wyswietlStudentow(ArrayIterator<Student> iterator) {
        iterator.first();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
    }

    public static void wpiszOcene(Student[] s, int id, double ocena) {
        ArrayIterator<Student> iterator = new ArrayIterator<>(s);

        while (iterator.hasNext()) {
            Student temp = iterator.next();

            if(temp.getId() == id) {
                temp.setOcena(ocena);
            }
        }
    }

    public static void wyswietlZaliczonych(ArrayIterator<Student> iterator) {
        iterator.first();
        FilterIterator<Student> fIt = new FilterIterator<>(iterator, new predykatZaliczeni());
        double suma = 0;
        int n = 0;

        while(fIt.hasNext()) {
            suma += fIt.next().getOcena();
            n++;
        }
        System.out.println("Srednia ocen studentow, ktorzy zaliczyli kurs: " + (suma/n) + "\n");
    }

    public static void wyswietlNieZaliczonych(Student[] s) {
        FilterIterator fIt = new FilterIterator<>(new ArrayIterator<>(s), new predykatNieZaliczeni());

        System.out.println("Studenci, ktorzy nie zaliczyli kursu:");
        fIt.forEachRemaining(System.out::println);
        System.out.println();
    }

    public static void main(String[] args) {
        Student s1 = new Student(3, "Jakub", "Seredynski");
        Student s2 = new Student(5, "Maciej", "Nowak");
        Student s3 = new Student(6, "Piotr", "Zielony");
        Student s4 = new Student(10, "Jakub", "Lewy");
        Student s5 = new Student(7, "Hubert", "Ignacy");

        Student[] studenci = new Student[]{s1,s2,s3,s4};
        ArrayIterator<Student> iterator = new ArrayIterator<>(studenci);

        wpiszOcene(studenci, 6, 5.5);
        wpiszOcene(studenci, 3, 4.5);
        wpiszOcene(studenci, 5, 2);
        wpiszOcene(studenci, 10, 5.0);


        wyswietlStudentow(iterator);

        //A
        iterator = dodajStudenta(new ArrayIterator<>(studenci), studenci, s5);
        wyswietlStudentow(iterator);

        //B
        iterator = usunStudenta(new ArrayIterator<>(studenci), studenci, 5);
        wyswietlStudentow(iterator);

        //C
        iterator = sortujPoOcenach(new ArrayIterator<>(studenci), studenci);
        wyswietlStudentow(iterator);
    }
}
