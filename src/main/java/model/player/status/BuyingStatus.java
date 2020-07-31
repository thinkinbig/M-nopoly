package model.player.status;

import model.player.Player;
import model.product.Dish;

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
    public void buy() {
        player.addMaterial();
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
