package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Dish;
import model.product.RawMaterial;

public class EndStatus extends Status {

    public EndStatus(Player player) {
        super(player);
        player.notifyObservers();
        super.next = new InitialStatus(player);
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
    public void buy(RawMaterial material) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void turn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Field roll(int dice) {
        throw new UnsupportedOperationException();
    }
}
