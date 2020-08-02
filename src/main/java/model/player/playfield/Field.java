package model.player.playfield;

import model.product.RawMaterial;
import model.player.Player;

public abstract class Field {
    private static int counter = 0;
    public final int id = counter++;
    protected int players = 0;

    protected Field() {
    }

    public void entry(Player player) {
        if (players + 1 <= 4)
            players++;
        else
            throw new IllegalArgumentException();
    }

    public void exit(Player player) {
        this.players--;
    }

    public abstract RawMaterial getMaterial();


}
