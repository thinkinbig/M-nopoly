package game.config;

/**
 * this is the game.config file of the programme.
 * In order to change the pre-defined constants as rules(i.e player number)
 * just change the field variables in this class.
 * @author memoryrabbit
 * @version 0.0.1
 */

public final class Config {

    /**
     * @see main.Main
     */
    public static final int MIN_PLAYER_NUMBER = 2;

    /**
     * @see main.Main
     */
    public static final int MAX_PLAYER_NUMBER = 4;

    public static final int START_UP_GOLD = 20;

    public static final int MIN_ROLL_NUMBER = 1;

    public static final int MAX_ROLL_NUMBER = 6;

    public static final int GOLD_FOR_START_FIELD = 5;

    public static final int INGREDIENTS_AMOUNT = 3;

    public static final int GOLD_FOR_SALE = 1;

    public enum GOODS {
        flour, egg, milk;

        public static Config.GOODS getValue(int index) {
            switch (index) {
                case 0:
                    return GOODS.flour;
                case 1:
                    return GOODS.egg;
                case 2:
                    return GOODS.milk;
                default: // never used
                    return null;
            }
        }

        public static int getIndex(Config.GOODS goods) {
            if (goods == null) return -1;
            switch (goods) {
                case flour:
                    return 0;
                case egg:
                    return 1;
                case milk:
                    return 2;
                default: //never used
                    return -1;
            }
        }
    }

    public static final int LOWEST_PRICE_IN_MARKET = 1;

    public static final int HIGHEST_PRICE_IN_MARKET = 5;

    public static final int RECEIPT_AMOUNT = 7;

    public static final int BONUS_GOLD = 25;

    public enum RECEIPT {
        Yoghurt, Meringue, Bread, Bun, Crepe, Pudding, Cake;

        public static int[] getReceipt(RECEIPT receipt) {
            if (receipt == null) return null;
            switch (receipt) {
                case Yoghurt:
                    return YOGHURT;
                case Meringue:
                    return MERINGUE;
                case Bread:
                    return BREAD;
                case Bun:
                    return BUN;
                case Crepe:
                    return CREPE;
                case Pudding:
                    return PUDDING;
                case Cake:
                    return CAKE;
                default:
                    return null;
            }
        }

        public static int getIndex(RECEIPT receipt) {
            switch (receipt) {
                case Yoghurt:
                    return 0;
                case Meringue:
                    return 1;
                case Bread:
                    return 2;
                case Bun:
                    return 3;
                case Crepe:
                    return 4;
                case Pudding:
                    return 5;
                case Cake:
                    return 6;
                default:
                    return -1;
            }
        }
    }

    private static final int[] YOGHURT = {0, 0, 3, 8};

    private static final int[] MERINGUE = {0, 3, 0, 9};

    private static final int[] BREAD = {3, 0, 0, 10};

    private static final int[] BUN = {2, 0, 1, 11};

    private static final int[] CREPE = {1, 2, 0, 12};

    private static final int[] PUDDING = {0, 1, 2, 13};

    private static final int[] CAKE = {2, 2, 2, 22};

}
