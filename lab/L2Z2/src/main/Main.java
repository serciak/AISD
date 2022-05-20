package main;

import java.util.Iterator;

public class Main {

    public static OneWayLinkedList<Student> innerMerge(OneWayLinkedList<Student> ll, OneWayLinkedList<Student> ls) {
        OneWayLinkedList<Student> result = new OneWayLinkedList<>();
        Iterator<Student> it1 = ll.iterator();
        Iterator<Student> it2 = ls.iterator();
        Student temp1 = it1.next();
        Student temp2 = it2.next();

        while (!ls.isEmpty() && !ll.isEmpty()) {

            if(temp1.getIndex() > temp2.getIndex()) {
                result.add(temp2);
                ls.remove(temp2);
                if(it2.hasNext())
                    temp2 = it2.next();
            }
            if(temp1.getIndex() < temp2.getIndex()) {
                result.add(temp1);
                ll.remove(temp1);
                if(it1.hasNext())
                    temp1 = it1.next();
            }
            if(temp1.getIndex() == temp2.getIndex()) {
                result.add(temp1);
                ls.remove(temp2);
                ll.remove(temp1);
                if(it1.hasNext() && it2.hasNext()) {
                    temp1 = it1.next();
                    temp2 = it2.next();
                }
            }
        }

        while(!ll.isEmpty()) {
            result.add(temp1);
            ll.remove(temp1);
            if(it1.hasNext())
                temp1 = it1.next();
        }

        while(!ls.isEmpty()) {
            result.add(temp2);
            ls.remove(temp2);
            if(it2.hasNext())
                temp2 = it2.next();
        }

        return result;
    }

    public static OneWayLinkedList<Student> merge(OneWayLinkedList<Student> l1, OneWayLinkedList<Student> l2) {
        int size1 = l1.size();
        int size2 = l2.size();

        if(size1 == 0) return l2;
        if(size2 == 0) return l1;

        if(l1.size() >= l2.size()) {
            return innerMerge(l1, l2);
        } else {
            return innerMerge(l2, l1);
        }
    }

    public static void main(String[] args) {

        OneWayLinkedList<Student> list1 = new OneWayLinkedList<>();
        OneWayLinkedList<Student> list2 = new OneWayLinkedList<>();

        Student s1 = new Student(1, "Jakub", "Seredynski", 4.0);
        Student s2 = new Student(2, "Piotr", "Krawiec", 4.0);
        Student s3 = new Student(3, "Jakub", "Prawy", 4.0);
        Student s4 = new Student(4, "Hubert", "Mily", 3.0);
        Student s5 = new Student(5, "Grzegorz", "Pewny", 4.0);
        Student s6 = new Student(6, "Jakub", "Lubek", 5.0);
        Student s7 = new Student(7, "Maciej", "Lewy", 5.0);
        Student s8 = new Student(8, "Jakub", "Lewy", 5.0);

        list1.add(s1);
        list1.add(s2);
        list1.add(s3);
        list1.add(s4);
        list2.add(s5);
        list1.add(s6);
        list1.add(s7);
        list2.add(s8);

        System.out.println("Lista 1:" + list1);
        System.out.println("Lista 2:" + list2);

        System.out.println("Listy połączone:" + merge(list1, list2));

    }
}
