package model.player.status;

import model.player.playfield.Field;
import model.product.RawMaterial;
import model.player.Player;
import model.product.Dish;

public abstract class Status {
    protected Player player;
    protected Status next;

    public abstract void harvest();
    public abstract int buy(RawMaterial material);
    public abstract void turn();
    public abstract Field roll(int dice);

    protected final void changeStatus() {
        player.setStatus(next);
    }

    public void prepare(Dish dish) {
        if (player.canPrepareMeal(dish)) {
            player.reduceMaterial(RawMaterial.FLOUR, dish.flour);
            player.reduceMaterial(RawMaterial.EGG, dish.egg);
            player.reduceMaterial(RawMaterial.MILK, dish.milk);
            player.setPrepared(dish);
            player.earnGold(dish.profit);
        }
    }

    public Status(Player player) {
        this.player = player;
    }
}
