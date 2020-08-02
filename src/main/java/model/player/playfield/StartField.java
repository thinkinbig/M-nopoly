package model.player.playfield;


import model.product.RawMaterial;
import model.player.Player;

public class StartField extends Field {
    @Override
    public void entry(Player player) {
        super.entry(player);
        player.earnGold(5);
    }

    @Override
    public RawMaterial getMaterial() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "S";
    }

    protected StartField() { }
}
