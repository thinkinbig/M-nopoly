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
     * the price of raw material when get sold
     */
    int RAW_PRICE = 1;

    /**
     * player roll the dice and move to the target field
     * @param dice number of dice
     * @throws IllegalArgumentException the dice number is too big or too small
     * @return target field
     */
    Field roll(int dice) throws IllegalArgumentException;

    /**
     * player can prepare meal according to the recipe
     * the profit will be earned by player
     * @param recipe recipe
     */
    void prepareMeal(Recipe recipe);

    /**
     * check if player can prepare meal
     * @param recipe recipe
     * @return true when the player meets the demand,
     * i.e. not on the start field and has enough money
     */
    boolean canPrepareMeal(Recipe recipe);


    /**
     * the player create raw material according to the field he was on,
     * the raw material will be sold to market and the gold will be
     * earned according to the sold price
     * @return raw material
     */
    RawMaterial harvest();


    /**
     * buy material
     * @param material material
     */
    void buy(RawMaterial material);

    /**
     * check if player can buy material
     * @param material raw material
     * @return true when can buy
     */
    boolean canBuy(RawMaterial material);


    /**
     * add Gold to player's pocket
     * @param number number
     */
    void earnGold(int number);

    /**
     * use gold to buy material
     * @param number number
     */
    void useGold(int number);

    boolean addMaterial(RawMaterial material);

    void reduceMaterial(RawMaterial material, int number) throws IllegalArgumentException ;

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
