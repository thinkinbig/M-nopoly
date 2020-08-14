package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Recipe;
import model.product.RawMaterial;

public class InitialStatus extends Status {

    public InitialStatus(Player player) {
        super(player);
        super.next = new MovedStatus(player);
    }

    @Override
    public void prepare(Recipe recipe) {
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
        super.next = new MovedStatus(player);
        changeStatus();
        return player.move(dice);
    }

}
