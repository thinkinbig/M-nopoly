package model;

import model.player.Player;
import model.player.mode.Strategy;
import model.player.mode.Strategy_1;
import model.player.playfield.Field;
import model.product.Recipe;
import model.product.RawMaterial;
import util.Observer;

import java.util.*;

public class ChessBoard implements Observer, Model {
    private List<Field> fields;
    private Set<Observer> observers = new HashSet<>();
    private List<Player> players;
    private Strategy win = new Strategy_1();
    private Player current;
    private boolean isQuited;

    public ChessBoard(List<Field> fields, int playerNumber) {
        this.fields = fields;
        players = new ArrayList<>(playerNumber);
        for (int i = 0; i < playerNumber; ++i) {
            Player player = Player.newPlayer(win, fields);
            player.addObserver(this);
            players.set(i, player);
        }
        current = players.get(0);
    }


    @Override
    public void update() {
        int id = current.id();
        id = (id + 1) % players.size();
        for (Player player : players) {
            if (id == player.id()) {
                current = player;
            }
        }
    }


    @Override
    public Field roll(int dice) {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        return current.roll(dice);
    }

    @Override
    public int gold() {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        return current.gold();
    }

    @Override
    public RawMaterial harvest() {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        RawMaterial raw = current.harvest();
        return raw;
    }

    @Override
    public int buy(RawMaterial material) {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        current.buy(material);
        return current.gold();
    }

    @Override
    public int prepare(Recipe recipe) {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        current.prepareMeal(recipe);
        return current.gold();
    }

    @Override
    public Iterable<Recipe> available() {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        return current.available();
    }

    @Override
    public Map<RawMaterial, Integer> showMarket() {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        return Market.showMarket();
    }

    @Override
    public List<Integer> showPlayer(int number) {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        if (number >= players.size() || number <= 0)
            throw new IllegalArgumentException("there is no such player");
        return getPlayer(number).show();
    }

    @Override
    public Player turn() {
        if (isQuited)
            throw new UnsupportedOperationException("the game is over");
        current.turn();
        return current;
    }

    @Override
    public String judgeWin() {
        if (current.win()) {
            quit();
            return current.toString() + "wins";
        }
        return null;
    }

    @Override
    public void quit() {
        isQuited = true;
    }

    @Override
    public boolean isQuited() {
        return isQuited;
    }

    @Override
    public Player getPlayer(int number) {
        return players.get(number - 1);
    }
}
