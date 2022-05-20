package main;


import java.util.Iterator;

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

    public static void wyswietlZaliczonych(FilterIterator<Student> iterator) {
        double suma = 0;
        int n = 0;

        while(iterator.hasNext()) {
            suma += iterator.next().getOcena();
            n++;
        }
        System.out.println("Srednia ocen studentow, ktorzy zaliczyli kurs: " + (suma/n) + "\n");
    }

    public static void wyswietlNieZaliczonych(FilterIterator<Student> iterator) {

        System.out.println("Studenci, ktorzy nie zaliczyli kursu:");
        iterator.forEachRemaining(System.out::println);
        System.out.println();
    }

    public static void main(String[] args) {

        Student s1 = new Student(3, "Jakub", "Seredynski");
        Student s2 = new Student(5, "Maciej", "Nowak");
        Student s3 = new Student(6, "Piotr", "Zielony");
        Student s4 = new Student(10, "Jakub", "Lewy");

        Student[] studenci = new Student[]{s1,s2,s3,s4};

        ArrayIterator<Student> iterator = new ArrayIterator<>(studenci);

        wpiszOcene(studenci, 6, 5.5);
        wpiszOcene(studenci, 3, 4.5);
        wpiszOcene(studenci, 5, 2);
        wpiszOcene(studenci, 10, 2.5);

        wyswietlStudentow(iterator);
        wyswietlZaliczonych(new FilterIterator<>(new ArrayIterator<>(studenci), new predykatZaliczeni()));
        wyswietlNieZaliczonych(new FilterIterator<>(new ArrayIterator<>(studenci), new predykatNieZaliczeni()));
    }
}
