package model;

public class Market {
    private static final Stack<RawMaterial> eggs = new Stack<>();
    private static final Stack<RawMaterial> milks = new Stack<>();
    private static final Stack<RawMaterial> flours = new Stack<>();


    public static int getEggPrice() {
        return eggs.pop();
    }

    public static int getMilkPrice() {
        return milks.pop();
    }

    public static int getFlourPrice() {
        return flours.pop();
    }

    public static void addEgg() {
        eggs.push(RawMaterial.EGG);
    }

    public static void addMilk() {
        milks.push(RawMaterial.MILK);
    }

    public static void addFlour() {
        flours.push(RawMaterial.FLOUR);
    }
}
