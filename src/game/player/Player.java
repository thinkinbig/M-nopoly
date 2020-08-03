package game.player;

import game.config.Config;

public class Player {

    private int gold;

    private int locationOfField;

    private int[] ingredient = {0, 0, 0};

    private boolean[] cookingList;

    public Player() {
        this.gold = Config.START_UP_GOLD;
        cookingList = new boolean[Config.RECEIPT_AMOUNT];
    }

    public int roll(int num) {
        return num;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int change) {
        this.gold = gold + change;
    }

    public int getLocationOfField() {
        return this.locationOfField;
    }

    public void setLocationOfField(int newLocation) {
        this.locationOfField = newLocation;
    }

    public int[] getIngredient() {
        return ingredient;
    }

    public void setIngredient(int index, int change) {
        this.ingredient[index] += change;
    }

    public boolean[] getCookingList() {
        return cookingList;
    }

    public void setCookingList(int index, boolean cooked) {
        cookingList[index] = cooked;
    }
}
