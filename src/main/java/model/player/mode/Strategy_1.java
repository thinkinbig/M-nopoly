package model.player.mode;


import model.player.Player;

public class Strategy_1 implements Strategy {
    private static final int STANDARD = 100;

    @Override
    public boolean win(Player player) {
        return player.gold() >= STANDARD;
    }
}
