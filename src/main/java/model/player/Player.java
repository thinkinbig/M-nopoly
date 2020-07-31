package model.player;

import model.product.RawMaterial;
import model.player.playfield.Field;
import model.player.status.Status;
import model.product.Dish;

import java.util.List;

public interface Player {

    Dish sell();

    void move(int dice);

    void prepareMeal(Dish dish);

    boolean canPrepareMeal(Dish dish);

    void setPrepared(Dish dish);

    void harvest();

    void buy();

    boolean canBuy();

    boolean canHarvest(RawMaterial material);

    void earnGold(int number);

    void useGold(int number);

    boolean addMaterial();

    void reduceMaterial(RawMaterial material, int number);

    boolean addRawToMarket();

    void setStatus(Status status);

    static Player newPlayer(List<Field> fields) {
        return new ConcretePlayer(fields);
    }
}
