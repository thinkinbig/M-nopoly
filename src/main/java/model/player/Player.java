package model.player;

import model.product.RawMaterial;
import model.player.playfield.Field;
import model.player.status.Status;
import model.product.Dish;
import util.Subject;

import java.util.List;

public interface Player extends Subject {

    Field roll(int dice);

    void prepareMeal(Dish dish);

    boolean canPrepareMeal(Dish dish);

    void setPrepared(Dish dish);

    RawMaterial harvest();

    void buy(RawMaterial material);

    boolean canBuy(RawMaterial material);

    boolean canHarvest(RawMaterial material);

    void earnGold(int number);

    void useGold(int number);

    boolean addMaterial(RawMaterial material);

    void reduceMaterial(RawMaterial material, int number);

    boolean addRawToMarket();

    void setStatus(Status status);

    int id();

    int gold();

    Field move(int dice);

    static Player newPlayer(List<Field> fields) {
        return new ConcretePlayer(fields);
    }

}
