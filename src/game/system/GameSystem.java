package game.system;

import edu.kit.informatik.Terminal;
import game.components.GameFields;
import game.config.Config;
import game.player.Player;

import java.util.Arrays;

/**
 * @author memoryrabbit
 * @version 0.0.1
 */
public class GameSystem {

//########################################## Attributes ###########################################//
    private boolean gameStopped = false;

    private boolean hasRolled = false;

    private boolean hasHarvested = false;

    private boolean hasBought = false;

    private boolean gameEnded = false;

    private int rounds;

    private int playerNumber;

    private GameFields[] gameFields;

    private Player[] players;

    private Player currentPlayer;

    /*
     * three ingredients and their lowest price
     * first is flour, second is egg and the third is milk
     */
    private int[] market = {4, 4, 4};
//######################################## End Attributes #######################################//

//########################################### Methods ##########################################//

    /**
     *
     * @param players
     * @param fields
     */
    public GameSystem(Player[] players, String[] fields) {
        if (players == null) return;
        this.gameFields = GamePlace.getInstance(fields).getFieldList();
        this.rounds = 0;
        this.players = players;
        this.playerNumber = players.length;
        currentPlayer = players[0];
    }

    /**
     *
     * @param num
     */

    public void roll(int num) {
        if (gameEnded) {
            return;
        }
        if (hasRolled) {
            Terminal.printError("you have already rolled in this turn");
            return;
        }
        if (num < Config.MIN_ROLL_NUMBER || num > Config.MAX_ROLL_NUMBER) {
            Terminal.printError("wrong roll number");
            return;
        }
        int step = currentPlayer.roll(num);
        int location = currentPlayer.getLocationOfField();
        int newLocation = (step + location) % gameFields.length;
        currentPlayer.setLocationOfField(newLocation);
        if (newLocation == 0) currentPlayer.setGold(Config.GOLD_FOR_START_FIELD);
        Terminal.printLine(String.format("%s;%d", gameFields[newLocation].getName(), currentPlayer.getGold()));
        hasRolled = true;
        hasWon();
    }

    /**
     *
     */

    private void hasWon() {
        if (currentPlayer.getGold() >= 100) {
            gameEnded = true;
            Terminal.printLine(String.format("P%d,wins", (rounds % playerNumber) - 1));
        }
    }

    /**
     *
     * @return
     */

    private boolean startField() {
        if (currentPlayer.getLocationOfField() == 0) {
            Terminal.printError("you are on the start field, so you can't harvest or buy");
            return true;
        }
        return false;
    }

    /**
     *
     * @return return true when can harvest, false when not
     */

    private boolean canHarvest() {
        if (gameEnded) return false;
        if (!hasRolled) {
            Terminal.printError("you must roll first");
            return false;
        }
        if (hasHarvested) {
            Terminal.printError("you have already harvested");
            return false;
        }
        if (hasBought) {
            Terminal.printError("you have bought something, so you can't harvest any more.");
            return false;
        }
        return true;
    }

    /**
     *
     */

    public void harvest() {
        if (!canHarvest() || startField()) return;
        int currentGood = gameFields[currentPlayer.getLocationOfField()].getCurrentGood();
        if (market[currentGood] == Config.LOWEST_PRICE_IN_MARKET) {
            Terminal.printError("the market of this good is already full, so you can't harvest");
            return;
        }
        market[currentGood]--;
        currentPlayer.setGold(Config.GOLD_FOR_SALE);
        Terminal.printLine(String.format("%s;%d", Config.GOODS.getValue(currentGood), currentPlayer.getGold()));
        hasWon();
        hasHarvested = true;
    }

    /**
     *
     * @param index
     * @return
     */

    private boolean canBuy(int index) {
        if (gameEnded) return false;
        if (!hasRolled) {
            Terminal.printError("you must roll first");
            return false;
        }
        if (hasHarvested) {
            Terminal.printError("you have harvested, so you can't buy any more");
            return false;
        }
        if (market[index] > Config.HIGHEST_PRICE_IN_MARKET) {
            Terminal.printError(String.format("no %s on market available", Config.GOODS.getValue(index)));
            return false;
        }
        if (currentPlayer.getGold() < market[index]) {
            Terminal.printError("you don't have enough money");
            return false;
        }
        return true;
    }

    /**
     *
     * @param goodsName
     */

    public void buy(Config.GOODS goodsName) {
        if (goodsName == null) return;
        int index = Config.GOODS.getIndex(goodsName);
        if (!canBuy(index) || startField()) return;
        currentPlayer.setGold(-market[index]);
        currentPlayer.setIngredient(index, 1);
        Terminal.printLine(String.format("%d;%d", market[index]++, currentPlayer.getGold()));
        hasBought = true;
    }

    /**
     *
     */

    public void prepare(Config.RECEIPT receipt) {
        if (gameEnded) {
            Terminal.printError("game is already ended");
            return;
        }
        if (!hasRolled) {
            Terminal.printError("you must roll first");
            return;
        }
        boolean canCook = true;
        for (int j = 0; j < Config.INGREDIENTS_AMOUNT; j++) {
            if (currentPlayer.getIngredient()[j] < Config.RECEIPT.getReceipt(receipt)[j]) {
                canCook = false;
                break;
            }
        }
        if (canCook) {
            for (int i = 0; i < Config.INGREDIENTS_AMOUNT; i++) {
                currentPlayer.setIngredient(i,-Config.RECEIPT.getReceipt(receipt)[i]);
            }
            currentPlayer.setGold(Config.RECEIPT.getReceipt(receipt)[Config.INGREDIENTS_AMOUNT]);
            hasWon();
            currentPlayer.setCookingList(Config.RECEIPT.getIndex(receipt),true);
            boolean[] cookList = currentPlayer.getCookingList();
            boolean bonus = true;
            for (int j = 0; j < cookList.length; j++) {
                if (cookList[j]) {
                    bonus = false;
                    break;
                }
            }
            if (bonus) {
                currentPlayer.setGold(Config.BONUS_GOLD);
                if (!gameEnded) hasWon();
            }
            Terminal.printLine(currentPlayer.getGold());
        }
    }

    /**
     *
     */

    public void canPrepare() {
        if (gameEnded) {
            Terminal.printError("game has ended");
            return;
        }
        for (Config.RECEIPT receipt: Config.RECEIPT.values()) {
            boolean token = false;
            for (int j = 0; j < Config.INGREDIENTS_AMOUNT; j++) {
                if (currentPlayer.getIngredient()[j] < Config.RECEIPT.getReceipt(receipt)[j]) {
                    break;
                }
                if (j == Config.INGREDIENTS_AMOUNT - 1) token = true;
            }
            if (token) {
                Terminal.printLine(receipt.toString().toLowerCase());
            }
        }
    }

    /**
     *
     */

    public void showMarket() {
        String[] marketString = new String[Config.INGREDIENTS_AMOUNT];
        for (int i = 0; i < Config.INGREDIENTS_AMOUNT; i++) {
            marketString[i] = Integer.toString(Config.HIGHEST_PRICE_IN_MARKET + 1 - market[i]);
            marketString[i] = marketString[i].concat(";").concat(Config.GOODS.getValue(i).toString());
        }
        Arrays.sort(marketString);
        for (int i = 0; i < Config.INGREDIENTS_AMOUNT; i++) {
            Terminal.printLine(marketString[i]);
        }
    }

    /**
     * index represents the index of players, which start with 1
     */

    public void showPlayer(int index) {
        index -= 1;
        if (index > players.length - 1) {
            Terminal.printError("this player doesn't exist");
            return;
        }
        int gold = players[index].getGold();
        int[] ingredients = players[index].getIngredient();
        Terminal.printLine(String.format("%d;%d;%d;%d",gold,ingredients[0],ingredients[1],ingredients[2]));
    }

    /**
     *
     */

    public void turn() {
        if (gameEnded) {
            Terminal.printError("game has ended");
            return;
        }
        if (!hasRolled) {
            Terminal.printError("you can't end your round before rolling");
            return;
        }
        players[rounds % playerNumber] = currentPlayer;
        rounds++;
        currentPlayer = players[rounds % playerNumber];
        hasRolled = false;
        hasBought = false;
        hasHarvested = false;
        Terminal.printLine(String.format("P%d", (rounds % playerNumber) + 1));
    }

    /**
     *
     */

    public void quit() {
        gameStopped = true;
    }

    /**
     *
     * @return return whether game has stopped via quit
     */

    public boolean getGameStopped() {
        return this.gameStopped;
    }
}
