package model.player.mode;

import model.player.Player;

public interface Strategy {

    boolean win(Player player);
}
