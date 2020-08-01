package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.RawMaterial;

public class HarvestedStatus extends Status {

    public HarvestedStatus(Player player) {
        super(player);
    }

    @Override
    public void harvest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int buy(RawMaterial material) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void turn() {
        super.next = new EndStatus(player);
        changeStatus();
    }

    @Override
    public Field roll(int dice) {
        throw new UnsupportedOperationException();
    }

}
