package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Dish;
import model.product.RawMaterial;

public class InitialStatus extends Status {

    public InitialStatus(Player player) {
        super(player);
        super.next = new MovedStatus(player);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void turn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Field roll(int dice) {
        return player.move(dice);
    }

}
