package model.player.status;

import model.player.playfield.Field;
import model.product.RawMaterial;
import model.player.Player;
import model.product.Recipe;

public abstract class Status {
    protected Player player;
    protected Status next;

    public abstract void harvest();
    public abstract void buy(RawMaterial material);
    public abstract void turn();
    public abstract Field roll(int dice);

    protected final void changeStatus() {
        player.setStatus(next);
    }

    public void prepare(Recipe recipe) {
        if (player.canPrepareMeal(recipe)) {
            player.reduceMaterial(RawMaterial.FLOUR, recipe.flour);
            player.reduceMaterial(RawMaterial.EGG, recipe.egg);
            player.reduceMaterial(RawMaterial.MILK, recipe.milk);
            player.setPrepared(recipe);
            player.earnGold(recipe.profit);
        }
    }

    public Status(Player player) {
        this.player = player;
    }
}
