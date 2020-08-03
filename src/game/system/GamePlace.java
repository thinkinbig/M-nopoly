package game.system;

import game.components.*;

/**
 * single object
 * @author memoryrabbit
 * @version 0.0.1
 */
public final class GamePlace {

    /**
     *
     */
    private static GameFields[] fieldList;

    /**
     *
     */
    private static GamePlace gamePlace = new GamePlace();

    /**
     *
     */
    private GamePlace() { }

    /**
     *
     * @return field list
     */
    public GameFields[] getFieldList() {
        return gamePlace.fieldList;
    }

    /**
     *
     * @param fields
     * @return game place
     */
    public static GamePlace getInstance(String[] fields) {
        if (gamePlace.fieldList != null) { return gamePlace; }
        gamePlace.fieldList = new GameFields[fields.length];
        for (int i = 0; i < fields.length; i++) {
            switch (fields[i]) {
                case "S":
                    gamePlace.fieldList[i] = new StartField();
                    break;
                case "C":
                    gamePlace.fieldList[i] = new CowPasture();
                    break;
                case "H":
                    gamePlace.fieldList[i] = new HenHouse();
                    break;
                case "M":
                    gamePlace.fieldList[i] = new Mill();
                    break;
                default:
            }
        }
        return gamePlace;
    }
}
