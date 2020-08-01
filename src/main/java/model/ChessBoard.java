package model;

import model.player.Player;
import model.player.playfield.Field;
import model.product.Dish;
import model.product.RawMaterial;
import util.Observer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChessBoard implements Observer, Model {
    private List<Field> fields;
    private Set<Observer> observers = new HashSet<>();
    private Player[] players;
    private Player current;

    public ChessBoard(List<Field> fields, int playerNumber) {
        this.fields = fields;
        players = new Player[playerNumber];
        for (int i = 0; i < playerNumber; ++i) {
            players[i] = Player.newPlayer(fields);
        }
        current = players[0];
    }


    @Override
    public void update() {
        int id = current.id();
        id = (id + 1) % players.length;
        for (Player player : players) {
            if (id == player.id()) {
                current = player;
            }
        }
    }


    @Override
    public Field roll(int dice) {
        return current.roll(dice);
    }

    @Override
    public int gold() {
        return current.gold();
    }

    @Override
    public RawMaterial harvest() {
        return current.harvest();
    }

    @Override
    public int buy(RawMaterial material) {
        return current.buy(material);
    }

    @Override
    public int prepare(Dish dish) {
        return 0;
    }

    @Override
    public Iterable<Dish> available() {
        return null;
    }

    @Override
    public Map<RawMaterial, Integer> showMarket() {
        return null;
    }

    @Override
    public Map<RawMaterial, Integer> showPlayer(int number) {
        return null;
    }

    @Override
    public Player turn() {
        return null;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
