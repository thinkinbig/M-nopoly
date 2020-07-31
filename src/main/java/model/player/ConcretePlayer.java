package model.player;

import model.Market;
import model.product.RawMaterial;
import model.player.playfield.Field;
import model.player.playfield.StartField;
import model.player.status.InitialStatus;
import model.player.status.Status;
import model.product.Dish;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ConcretePlayer implements Player{
    private Status status;
    private Field field;
    private final List<Field> fields;
    private final Map<Dish, Boolean> dishes = new EnumMap<>(Dish.class);
    private final Map<RawMaterial, Integer> materials = new EnumMap<>(RawMaterial.class);
    private static int counter = 0;
    private static final int PRICE = 25;
    private final int id = counter++;
    private int gold;

    @Override
    public void move(int dice) {
        int id = field.id;
        field.exit(this);
        field = fields.get((id + dice) % fields.size());
        field.entry(this);
    }

    @Override
    public void prepareMeal(Dish dish) {
        status.prepare(dish);
    }

    @Override
    public boolean canPrepareMeal(Dish dish) {
        int flour = dish.flour;
        int egg = dish.egg;
        int milk = dish.milk;
        return flour <= materials.get(RawMaterial.FLOUR)
                && egg <= materials.get(RawMaterial.EGG)
                && milk <= materials.get(RawMaterial.MILK);
    }

    @Override
    public void harvest() {
        status.harvest();
    }

    @Override
    public void buy() {
        status.buy();
    }

    @Override
    public boolean canBuy() {
        return gold >= field.getMaterial().getPrice();
    }

    @Override
    public boolean canHarvest(RawMaterial material) {
        return Market.stackFull(material);
    }

    @Override
    public void earnGold(int number) {
        this.gold += number;
    }

    @Override
    public void useGold(int number) {
        if (gold - number >= 0)
            this.gold -= number;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public boolean addMaterial() {
        if (field.getClass() == StartField.class || !canBuy())
            return false;
        else {
            RawMaterial material = field.getMaterial();
            int price = material.getPrice();
            int number = materials.get(material);
            useGold(price);
            materials.put(material, number + 1);
            return true;
        }
    }

    @Override
    public void reduceMaterial(RawMaterial material, int number) {
        int num = materials.get(material);
        if (num >= number)
            materials.put(material, number - num);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public boolean addRawToMarket() {
        if (field.getClass() == StartField.class && !canHarvest(field.getMaterial()))
            return false;
        else {
            Market.addMaterial(field.getMaterial());
            return true;
        }
    }

    @Override
    public final void setStatus(Status status) {
        this.status = status;
    }

    public ConcretePlayer(List<Field> fields) {
        this.fields = fields;
        field = fields.get(0);
        for (RawMaterial material : RawMaterial.values()) {
            this.materials.put(material, 0);
        }

        for (Dish dish : Dish.values()) {
            this.dishes.put(dish, false);
        }

        this.status = new InitialStatus(this);
        this.gold = 20;
    }

    @Override
    public void setPrepared(Dish dish) {
        boolean flag = true;
        for (Dish d : dishes.keySet()) {
            if (d == dish) {
                dishes.put(d, true);
            }
            flag = dishes.get(d) && flag;
        }

        if (flag) {
            earnGold(PRICE);
        }
    }
}
