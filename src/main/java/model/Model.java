package model;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Recipe;
import model.product.RawMaterial;

import java.util.Collection;
import java.util.List;

public interface Model {

    Field roll(int number);

    int gold();

    RawMaterial harvest();

    int buy(RawMaterial material);

    int prepare(Recipe recipe);

    Iterable<Recipe> available();

    Iterable<String> showMarket();

    List<Integer> showPlayer(int number);

    Player turn();

    String judgeWin();

    Player getPlayer(int number);

    void quit();

    boolean isQuited();
}
