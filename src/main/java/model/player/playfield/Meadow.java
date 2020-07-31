package model.player.playfield;

import model.product.RawMaterial;

public class Meadow extends Field {


    @Override
    public RawMaterial getMaterial() {
        return RawMaterial.MILK;
    }

}
