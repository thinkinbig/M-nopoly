package model.player;

import model.player.playfield.Field;
import model.player.status.Status;
import model.product.Product;

import java.util.List;

public interface Player {

    Product sell();

    void move(int dice);

    void prepareMeal();

    boolean canPrepareMeal();

    void harvest();

    void buy();

    boolean canBuy();

    boolean canHarvest();

    boolean atStartField();

    void earnGold(int number);

    void useGold(int number);

    void setStatus(Status status);

    static Player newPlayer(List<Field> fields) {
        return new ConcretePlayer(fields);
    }
}
