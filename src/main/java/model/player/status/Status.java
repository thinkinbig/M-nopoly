package model.player.status;

import model.player.playfield.Field;
import model.product.RawMaterial;
import model.player.Player;
import model.product.Recipe;

public abstract class Status {

    /**
     * current player that owns the state
     */
    protected final Player player;

    /**
     * next state, it may not be defined
     */
    protected Status next;

    /**
     * harvest implementation according to the state
     */
    public abstract void harvest();

    /**
     * buy implementation according to the state
     * @param material raw material
     */
    public abstract void buy(RawMaterial material);

    /**
     * turn to next state
     */
    public abstract void turn();

    /**
     * roll the dice according to the state
     * @param dice dice number
     * @return
     */
    public abstract Field roll(int dice);

    /**
     * change to the next state
     */
    protected final void changeStatus() {
        player.setStatus(next);
    }

    protected static final String BUY_ERROR = "player cannot buy";
    protected static final String PREPARE_ERROR = "player can not prepare";
    protected static final String HARVEST_ERROR = "the market is full";

    /**
     * prepare the meal according to the recipe
     * @param recipe recipe
     */
    public void prepare(Recipe recipe) {
        if (player.canPrepareMeal(recipe)) {
            player.reduceMaterial(RawMaterial.FLOUR, recipe.flour);
            player.reduceMaterial(RawMaterial.EGG, recipe.egg);
            player.reduceMaterial(RawMaterial.MILK, recipe.milk);
            player.earnGold(recipe.profit);
        } else {
            throw new UnsupportedOperationException(PREPARE_ERROR);
        }
    }

    /**
     * constructor
     * @param player current player
     */
    public Status(Player player) {
        this.player = player;
    }
}
