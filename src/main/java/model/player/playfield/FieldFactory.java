package model.player.playfield;


public class FieldFactory {

    /**
     * create a new hen house
     * @return hen house
     */
    public static Field newHenHouse() {
        return new HenHouse();
    }

    /**
     * create a new meadow
     * @return meadow
     */
    public static Field newMeadow() {
        return new Meadow();
    }

    /**
     * create a new mill
     * @return mill
     */
    public static Field newMill() {
        return new Mill();
    }

    /**
     * create anew start field
     * @return start field
     */
    public static Field newStartField() {
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
            case "S":
                field = new StartField();
            default:
                break;
        }
        // this line ensure that the name and field has an 1-to-1 association
        if (field != null)
            assert field.toString().equals(name);
        return field;
    }
}
