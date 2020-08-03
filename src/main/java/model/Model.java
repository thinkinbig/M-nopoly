package model;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Recipe;
import model.product.RawMaterial;
import util.Subject;

import java.util.List;
import java.util.Map;

public interface Model {

    Field roll(int number);

    int gold();

    RawMaterial harvest();

    int buy(RawMaterial material);

    int prepare(Recipe recipe);

    Iterable<Recipe> available();

    Map<RawMaterial, Integer> showMarket();

    List<Integer> showPlayer(int number);

    Player turn();

    String judgeWin();

    Player getPlayer(int number);

    void quit();

    boolean isQuited();
}
