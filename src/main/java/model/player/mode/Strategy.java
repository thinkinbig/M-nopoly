package model.player.mode;

import model.player.Player;

public interface Strategy {

    /**
     * winning strategy
     * @param player player
     * @return true when the player is won
     */
    boolean win(Player player);
}
