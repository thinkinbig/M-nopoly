package model.player;

import model.RawMaterial;
import model.player.playfield.Field;
import model.player.playfield.StartField;
import model.player.status.InitialStatus;
import model.player.status.Status;
import model.product.Product;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ConcretePlayer implements Player{
    private Status status;
    private Field field;
    private final List<Field> fields;
    private final Map<RawMaterial, Integer> materials = new EnumMap<>(RawMaterial.class);
    private static int counter = 0;
    private final int id = counter++;
    private int gold;

    @Override
    public Product sell() {
        return null;
    }

    @Override
    public void move(int dice) {
        int id = field.id;
        field.exit(this);
        field = fields.get((id + dice) % fields.size());
        field.entry(this);
    }

    @Override
    public void prepareMeal() {
        if (canPrepareMeal()) {

        }
    }

    public boolean canPrepareMeal() {
        return false;
    }

    @Override
    public void harvest() {
        if (!atStartField() && canHarvest()) {

        }
    }

    @Override
    public void buy() {
        if (!atStartField() && canBuy()) {
        }

    }

    @Override
    public boolean canBuy() {
        return false;
    }

    @Override
    public boolean canHarvest() {
        return false;
    }

    @Override
    public boolean atStartField() {
        return field.getClass() == StartField.class;
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
    public final void setStatus(Status status) {
        this.status = status;
    }

    public ConcretePlayer(List<Field> fields) {
        this.fields = fields;
        field = fields.get(0);
        this.materials.put(RawMaterial.EGG, 0);
        this.materials.put(RawMaterial.FLOUR, 0);
        this.materials.put(RawMaterial.MILK, 0);
        this.status = new InitialStatus(this);
        this.gold = 20;
    }
}
