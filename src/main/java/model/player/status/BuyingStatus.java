package model.player.status;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Recipe;
import model.product.RawMaterial;

public class BuyingStatus extends Status{

    public BuyingStatus(Player player) {
        super(player);
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
        player.addMaterial(material);
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
