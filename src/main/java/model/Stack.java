package model;

public class Stack<T> {
    private static final int CAPACITY = 5;

    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Object[CAPACITY];

    private int counter = CAPACITY - 1;

    public void push(T item) {
        if (isFull())
            throw new ArrayIndexOutOfBoundsException();
        else {
            items[counter] = item;
            counter--;
        }
    }

    public int pop() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        else {
            int i = counter;
            items[counter] = null;
            counter++;
            return i + 1;
        }
    }

    private boolean isFull() {
       return counter == 0 && items[0] != null;
    }

    private boolean isEmpty() {
        return counter == CAPACITY - 1 && items[counter] == null;
    }
}
