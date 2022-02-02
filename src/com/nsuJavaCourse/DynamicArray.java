package com.nsuJavaCourse;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.Iterator;

public class DynamicArray<E> implements Collection<E> {

    private int size;
    private int realSize;
    private Object[] array;

    public DynamicArray() {
        this.size = 0;
        this.realSize = 10;
        this.array = new Object[realSize];
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }



    //I know it's strange that this is the only method implemented through the iterator,
    //it's done purely for educational purposes. Many methods can be done with it without direct access to array
    @Override
    public boolean contains(Object o) {
        for (E e : this) {
            if (e == o) {
                return true;
            }
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return array[currentIndex+1] != null;
            }

            @Override
            public E next() {
                currentIndex++;
                if (currentIndex > size) {
                    throw new NoSuchElementException();
                }
                return (E) array[currentIndex];
            }

            @Override
            public void remove() {
                if (currentIndex == -1) {
                    return;
                }
                removeElement(currentIndex);
                currentIndex--;
                size--;
            }
        };
    }


    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public boolean add(Object o) {
        //check if resize needed
        if (size == (realSize - 1)) {
            Object[] newArray = new Object[realSize*2];
            if (size >= 0) System.arraycopy(this.array, 0, newArray, 0, size);
            realSize*=2;
            array = newArray;
        }

        array[size] = o;
        size++;

        return true;
    }


    //it's strange to do this with an array
    @Override
    public boolean remove(Object o) {
        boolean ArrayChanged = false;
        int amountRemoved = 0;
        for (int i = 0; i<size; i++) {
            if (array[i] == o) {
                removeElement(i);
                ArrayChanged = true;
                amountRemoved++;
            }
        }
        size-=amountRemoved;
        return ArrayChanged;
    }

    @Override
    public boolean addAll(Collection c) {
        if (size+c.size() >= realSize) {
            Object[] newArray = new Object[(size+c.size())*2];
            if (size >= 0) System.arraycopy(this.array, 0, newArray, 0, size);
            this.array = newArray;
        }
        Iterator iterator = c.iterator();
        for (int i = 0; i<c.size();i++) {
            this.add(iterator.next());
        }
        realSize = (size+c.size())*2;
        size+=c.size();
        return true;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.realSize = 10;
        this.array = new Object[realSize];
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean ArrayChanged = false;
        for (int i = 0; i<size(); i++) {
            if (!c.contains(array[i])) {
                this.remove(array[i]);
                ArrayChanged = true;
            }
        }
        return ArrayChanged;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean ArrayChanged = false;
        Iterator iterator = c.iterator();
        for (int i = 0; i<c.size(); i++) {
            this.remove(iterator.next());
            ArrayChanged = true;
        }
        return ArrayChanged;
    }

    @Override
    public boolean containsAll(Collection c) {
        Iterator iterator = c.iterator();
        for (int i = 0; i<c.size(); i++) {
            if (!this.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }


    @Override
    public Object[] toArray(Object[] a) {
        Object[] newArray = new Object[size];
        System.arraycopy(this.array, 0, newArray, 0, size);
        return newArray;
    }

    void removeElement(int position) {
        if (size - 1 - position >= 0) System.arraycopy(array, position + 1, array, position, size - 1 - position);
        array[size-1] = null;
    }
    public void printArray() {
        for (int i = 0; i<size; i++) {
            System.out.print(array[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

}