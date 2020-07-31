package model.player.playfield;


import model.player.Player;

public class StartField extends Field {
    @Override
    public void action(Player player) {
        player.earnGold(5);
    }
}
