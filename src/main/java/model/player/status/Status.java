package model.player.status;

import model.player.Player;

public abstract class Status {
    protected Player player;
    public abstract void prepare();
    public abstract void harvest();
    public abstract void buy();
    public abstract void turn();
    public abstract void roll(int dice);
    public abstract Status next();

    protected final void changeStatus() {
        player.setStatus(next());
    }

    public Status(Player player) {
        this.player = player;
    }
}
