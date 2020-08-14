package model;

import model.product.RawMaterial;
import model.util.Stack;

import java.util.*;

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
        getStack(material).push();
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

    public static Iterable<String> showMarket() {
        Map<RawMaterial, Integer> map = new EnumMap<>(RawMaterial.class);
        for (Stack s : stacks) {
            map.put(s.getType(), s.getPrice());
        }

        List<Map.Entry<RawMaterial, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        List<String> result = new ArrayList<>();
        for (Map.Entry<RawMaterial, Integer> entry : list) {
            result.add(entry.getKey() + ";" + entry.getValue());
        }
        return result;
    }
}
