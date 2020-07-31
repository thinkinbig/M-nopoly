package model.player;

import model.material.RawMaterial;
import model.player.status.InitialStatus;
import model.player.status.Status;
import model.product.Product;

public interface Player {

    boolean buy(RawMaterial material);
    Product sell();
    void move(int dice);

    void prepareMeal();

    boolean canPrepareMeal();

    void setStatus(Status status);
}
