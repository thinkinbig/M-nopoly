package model.util;

import model.product.RawMaterial;

public class Stack {
    private static final int CAPACITY = 5;

    private RawMaterial raw;

    @SuppressWarnings("unchecked")
    private RawMaterial[] items = new RawMaterial[CAPACITY];

    private int counter = CAPACITY - 1;

    public void push(RawMaterial raw) {
        if (isFull())
            throw new ArrayIndexOutOfBoundsException();
        else {
            items[counter] = raw;
            counter--;
        }
    }

    public void pop() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        else {
            items[counter] = null;
            counter++;
        }
    }

    public Stack(RawMaterial raw) {
        this.raw = raw;
    }

    public RawMaterial getType() {
        return raw;
    }

    public int getPrice() {
        return counter + 1;
    }

    public boolean isFull() {
       return counter == 0 && items[0] != null;
    }

    public boolean isEmpty() {
        return counter == CAPACITY - 1 && items[counter] == null;
    }
}
