package model.player.status;

import model.player.Player;

public class BuyingStatus extends Status{

    public BuyingStatus(Player player) {
        super(player);
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
        player.buy();
    }

    @Override
    public void turn() {
        super.next = new EndStatus(player);
        changeStatus();
    }

    @Override
    public void roll(int dice) {
        throw new UnsupportedOperationException();
    }
}
