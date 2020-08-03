package game.components;

import game.config.Config;

/**
 * @author memoryrabbit
 * @version 0.0.1
 */
public class HenHouse extends GameFields {

    /**
     *
     */
    private final String name = "H";

    /**
     *
     * @return the name of this field
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return index of the good in market
     */
    public int getCurrentGood() {
        return Config.GOODS.getIndex(Config.GOODS.egg);
    }
}
