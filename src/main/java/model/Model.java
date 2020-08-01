package model;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Dish;
import model.product.RawMaterial;
import util.Subject;

import java.util.Map;

public interface Model extends Subject {

    Field roll(int number);

    int gold();

    RawMaterial harvest();

    int buy(RawMaterial material);

    int prepare(Dish dish);

    Iterable<Dish> available();

    Map<RawMaterial, Integer> showMarket();

    Map<RawMaterial, Integer> showPlayer(int number);

    Player turn();
}
