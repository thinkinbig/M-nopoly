package model.player.playfield;

import model.player.Player;

public abstract class Field {
    private static int counter = 0;
    public final int id = counter++;
    protected int players = 0;

    public Field() {
    }

    public void addPlayerNumber() {
        if (players + 1 <= 4)
            players++;
        else
            throw new IllegalArgumentException();
    }

    public abstract void action(Player player);


}
