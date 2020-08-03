package game.components;

import game.config.Config;

/**
 * @author memoryrabbit
 * @version 0.0.1
 */
public class Mill extends GameFields {

    /**
     *
     */
    private final String name = "M";

    /**
     *
     * @return the name of this good
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the index of this good in market
     */
    public int getCurrentGood() {
        return Config.GOODS.getIndex(Config.GOODS.flour);
    }
}
