package model.player.playfield;

import model.product.RawMaterial;
import model.player.Player;

public abstract class Field {
    private static int counter = 0;
    private static String MESSAGE = "players on the field should be less than 5 and more than 0";
    public final int id = counter++;
    protected int players = 0;

    protected Field() {
    }

    /**
     * entry allow fields operations while player enters
     * @param player
     */
    public void entry(Player player) {
        if (players + 1 <= 4)
            players++;
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    /**
     * exit allow fields operations while player exit
     * @param player
     */
    public void exit(Player player) {
        if (players - 1 >= 0)
            this.players--;
        else
            this.players = 0;
    }

    /**
     * method that convert field to string
     * @return string
     */
    public abstract String toString();

    /**
     * method that fetch responding material
     * @return material
     */
    public abstract RawMaterial getMaterial();


}
