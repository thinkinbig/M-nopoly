package model;

import model.product.RawMaterial;
import model.util.Stack;

public class Market {
    private static final Stack[] stacks = {
            new Stack(RawMaterial.EGG),
            new Stack(RawMaterial.MILK),
            new Stack(RawMaterial.FLOUR)
    };

    public static void sell(RawMaterial material) {
        getStack(material).pop();
    }

    public static int getPrice(RawMaterial material) {
        return getStack(material).getPrice();
    }

    public static void addMaterial(RawMaterial material) {
        getStack(material).push(material);
    }

    public static boolean stackFull(RawMaterial material) {
        return getStack(material).isFull();
    }

    public static Stack getStack(RawMaterial material) {
        for (Stack s : stacks) {
            if (s.getType() == material) {
                return s;
            }
        }
        return null;
    }
}
