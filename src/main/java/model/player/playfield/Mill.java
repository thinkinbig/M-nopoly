package model.player.playfield;


import model.RawMaterial;
import model.player.Player;

public class Mill extends Field {

    @Override
    public void entry(Player player) {

    }

    @Override
    public RawMaterial getMaterial() {
        return RawMaterial.FLOUR;
    }
}
