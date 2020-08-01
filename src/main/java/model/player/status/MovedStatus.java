package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.RawMaterial;

public class MovedStatus extends Status {

    public MovedStatus(Player player) {
        super(player);
    }


    @Override
    public void harvest() {
        if (player.addRawToMarket()) {
            super.next = new HarvestedStatus(player);
            changeStatus();
        }
    }

    @Override
    public void buy(RawMaterial material) {
        if (player.addMaterial(material)) {
            super.next = new BuyingStatus(player);
            changeStatus();
        }
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
