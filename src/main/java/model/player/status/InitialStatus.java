package model.player.status;

import model.player.Player;

public class InitialStatus extends Status {

    public InitialStatus(Player player) {
        super(player);
        super.next = new MovedStatus(player);
    }

    @Override
    public void prepare() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void harvest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void buy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void turn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void roll(int dice) {
        player.move(dice);
        changeStatus();
    }

}
