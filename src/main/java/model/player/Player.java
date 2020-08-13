package model.player;

import model.player.mode.Strategy;
import model.product.RawMaterial;
import model.player.playfield.Field;
import model.player.status.Status;
import model.product.Recipe;
import util.Subject;

import java.util.List;

public interface Player extends Subject {

    /**
     * the biggest dice number
     */
    int DICE_MIN = 1;

    /**
     * the smallest dice number
     */
    int DICE_MAX = 6;

    /**
     * the initial gold that a player possesses
     */
    int PRICE = 25;

    /**
     *  player roll the dice and move to the target field
     * @param dice number of dice
     * @throws IllegalArgumentException the dice number is too big or too small
     * @return target field
     */
    Field roll(int dice) throws IllegalArgumentException;

    /**
     *  player can prepare meal according to the recipe
     *  when the player has enough money
     * @param recipe recipe
     */
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
