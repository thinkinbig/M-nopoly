package model.product;

import model.Market;

public enum RawMaterial {
    FLOUR, EGG, MILK;


    public final int getPrice() {
        return Market.getPrice(this);
    }

    public final void add() {
        Market.addMaterial(this);
    }
}
