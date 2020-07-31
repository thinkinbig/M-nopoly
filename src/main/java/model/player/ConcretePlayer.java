package model.player;

import model.material.RawMaterial;
import model.player.status.InitialStatus;
import model.player.status.Status;
import model.product.Product;

public class ConcretePlayer implements Player{
    private Status status;
    private int gold;

    public boolean buy(RawMaterial material) {
        return false;
    }
    public Product sell() {
        return null;
    }
    public void move(int dice) {

    }

    // if can prepareMeal, prepareMeal
    public void prepareMeal() {
        if (canPrepareMeal()) {

        }
    }

    public boolean canPrepareMeal() {
        return false;
    }

    @Override
    public final void setStatus(Status status) {
        this.status = status;
    }

    public ConcretePlayer() {
        this.status = new InitialStatus(this);
        this.gold = 20;
    }
}
