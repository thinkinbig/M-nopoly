package model.product;

import model.Market;

public enum RawMaterial {
    EGG, MILK, FLOUR;


    public final int getPrice() {
        return Market.getPrice(this);
    }

    public final void add() {
        Market.addMaterial(this);
    }
}
