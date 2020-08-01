package model.player.playfield;

public class FieldFactory {

    public static Field newHenHouse() {
        return new HenHouse();
    }

    public static Field newMeadow() {
        return new Meadow();
    }

    public static Field newMill() {
        return new Mill();
    }

    public static Field StartField() {
        return new StartField();
    }
}
