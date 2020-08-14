package model.player.status;

import model.Market;
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
        } else {
            throw new UnsupportedOperationException(HARVEST_ERROR);
        }
    }

    @Override
    public void buy(RawMaterial material) {
        if (player.addMaterial(material)) {
            super.next = new BuyingStatus(player);
            changeStatus();
        } else {
            throw new UnsupportedOperationException(BUY_ERROR);
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
