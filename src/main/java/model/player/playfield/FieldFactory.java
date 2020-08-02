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

    /**
     * map method maps field name to field
     * @param name name of the field
     * @return field
     */
    public static Field map(String name) {
        Field field = null;
        switch (name) {
            case "H":
                field = new HenHouse();
                break;
            case "C":
                field =  new Meadow();
                break;
            case "M":
                field = new Mill();
                break;
            default:
                break;
        }
        // this line ensure that the name and field has an 1-to-1 association
        assert field.toString().equals(name);
        return field;
    }
}
