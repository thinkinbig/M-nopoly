package model.util;

import model.product.RawMaterial;

public class Stack {
    private static final int CAPACITY = 5;

    private RawMaterial raw;

    @SuppressWarnings("unchecked")
    private RawMaterial[] items = new RawMaterial[CAPACITY];

    private int counter = CAPACITY - 1;
    private int size = 0;

    public void push() {
        if (isFull())
            throw new ArrayIndexOutOfBoundsException();
        else {
            items[counter] = raw;
            counter--;
            size++;
        }
    }

    public void pop() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        else {
            items[counter] = null;
            counter++;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public Stack(RawMaterial raw) {
        this.raw = raw;
        this.push();
        this.push();
    }

    public RawMaterial getType() {
        return raw;
    }

    public int getPrice() {
        return counter + 1;
    }

    public boolean isFull() { return size >= CAPACITY; }

    public boolean isEmpty() {
        return size <= 0;
    }
}
