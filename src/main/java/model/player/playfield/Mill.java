package model.player.playfield;


import model.product.RawMaterial;

public class Mill extends Field {

    @Override
    public RawMaterial getMaterial() {
        return RawMaterial.FLOUR;
    }

    @Override
    public String toString() {
        return "M";
    }

    protected Mill() {
    }
}
