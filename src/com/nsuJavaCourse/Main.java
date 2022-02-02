package com.nsuJavaCourse;

import java.util.Collection;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
//        Collection<Integer> vector = new ArrayList<>();
        Collection<Integer> vector = new DynamicArray<>();
        vector.add(2);
        vector.add(11);
        vector.add(10);
        vector.add(11);
        vector.add(13);

        System.out.println("iterator working:");
        for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println("vector size is " + vector.size());
        vector.remove(11);
        System.out.println("11 to be removed:");
        for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println("11 has been added back twice after that");
        vector.add(11);
        vector.add(11);

        System.out.println("Vector containing 3 is " + vector.contains(3));
        System.out.println("Vector containing 10 is " + vector.contains(10));

        Collection<Integer> vector2 = new DynamicArray<>();
        vector2.add(2);
        vector2.add(10);
        vector2.add(13);

        System.out.println("vector2 is ");
        for (Iterator<Integer> iterator = vector2.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println("vector contains vector2 is " + vector.containsAll(vector2));


        vector.removeAll(vector2);
        System.out.println("vector without elements present in vector2 is ");
        for (Iterator<Integer> iterator = vector.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println("Now let's add them back with the addAll function");
        vector.addAll(vector2);
        for (Iterator<Integer> iterator = vector.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println("clearing the elements with the clear function");
        vector.clear();
        System.out.println("vector size is " + vector.size());




    }
}
