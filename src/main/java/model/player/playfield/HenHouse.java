package model.player.playfield;

import model.product.RawMaterial;

public class HenHouse extends Field {

    @Override
    public RawMaterial getMaterial() {
        return RawMaterial.EGG;
    }

    @Override
    public String toString() {
        return "H";
    }

    protected HenHouse() { }


}
