package model.player.status;

import model.player.Player;

public class HarvestedStatus extends Status {

    public HarvestedStatus(Player player) {
        super(player);
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
        super.next = new EndStatus(player);
        changeStatus();
    }

    @Override
    public void roll(int dice) {
        throw new UnsupportedOperationException();
    }

}
