package game.components;

import game.config.Config;

/**
 * @author memoryrabbit
 * @version 0.0.1
 */
public class CowPasture extends GameFields {

    /**
     *
     */
    public final String name = "C";

    /**
     *
     * @return the name of this field
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the index of this good in the market
     */
    public int getCurrentGood() {
        return Config.GOODS.getIndex(Config.GOODS.milk);
    }
}
