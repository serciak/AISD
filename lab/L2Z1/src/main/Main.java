package main;

import java.io.*;
import java.util.Iterator;

public class Main {

    public static void removeWithKey(OneWayLinkedList<Student> s, double grade) {
        Iterator<Student> it = s.iterator();
        Student temp;

        while(it.hasNext()) {
            temp = it.next();
            if(temp.getGrade() < grade) {
                s.remove(temp);
            }
        }
    }

    public static void serialize(OneWayLinkedList<Student> s) {
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("students.ser"));

            oOS.writeObject(s);
            oOS.close();
            System.out.println("Zapisano.");

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static OneWayLinkedList<Student> deserialize() {
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("students.ser"));

            OneWayLinkedList<Student> s = (OneWayLinkedList<Student>) oIS.readObject();
            oIS.close();
            System.out.println("Odczytano.");

            return s;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        OneWayLinkedList<Student> students = new OneWayLinkedList<>();

        double grade = 3;

        students.add(new Student(1, "Jakub", "Seredynski", 3.0));
        students.add(new Student(3, "Jakub", "Lewy", 5.5));
        students.add(new Student(10, "Jakub", "Jelen", 2.0));

        System.out.println("Lista studentow:");
        System.out.println(students);

        serialize(students);
        System.out.println("Lista studentow po usunieciu(ponizej oceny: " + grade + "):");
        removeWithKey(students, 3);
        System.out.println(students);

        students = deserialize();
        System.out.println("Lista studentow po odczytaniu:");
        System.out.println(students);
    }
}
