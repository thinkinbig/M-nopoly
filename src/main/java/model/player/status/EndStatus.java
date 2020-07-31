package model.player.status;

import model.player.Player;

public class EndStatus extends Status {

    public EndStatus(Player player) {
        super(player);
        super.next = null;
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
        throw new UnsupportedOperationException();
    }
}
