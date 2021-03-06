package model.player;

import model.player.mode.Strategy;
import model.product.RawMaterial;
import model.player.playfield.Field;
import model.player.status.Status;
import model.product.Recipe;
import util.Subject;

import java.util.List;

public interface Player extends Subject {

    Field roll(int dice);

    void prepareMeal(Recipe recipe);

    boolean canPrepareMeal(Recipe recipe);

    void setPrepared(Recipe recipe);

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

    void turn();

    Iterable<Recipe> available();

    Field move(int dice);

    List<Integer> show();

    String toString();

    boolean win();

    void setStrategy(Strategy strategy);

    static Player newPlayer(Strategy strategy, List<Field> fields) {
        return new ConcretePlayer(strategy, fields);
    }

}
