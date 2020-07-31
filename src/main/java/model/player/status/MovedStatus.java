package model.player.status;


import model.player.Player;

public class MovedStatus extends Status {

    public MovedStatus(Player player) {
        super(player);
    }

    @Override
    public void prepare() {
        player.prepareMeal();
    }

    @Override
    public void harvest() {

    }

    @Override
    public void buy() {

    }

    @Override
    public void turn() {

    }

    @Override
    public void roll(int dice) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Status next() {
        return null;
    }

}
