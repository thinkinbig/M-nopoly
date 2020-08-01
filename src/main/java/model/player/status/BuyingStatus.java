package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Dish;
import model.product.RawMaterial;

public class BuyingStatus extends Status{

    public BuyingStatus(Player player) {
        super(player);
    }

    @Override
    public void prepare(Dish dish) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void harvest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int buy(RawMaterial material) {
        player.addMaterial(material);
        return 0;
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
