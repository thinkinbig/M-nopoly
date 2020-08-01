package model.product;

public enum Recipe {
    YOGURT(0, 0, 3, 8),
    MERINGUE(0, 3, 0, 9),
    BREAD(3, 0, 0, 10),
    BUN(2, 0, 1, 11),
    CREPE(1, 2, 0, 12),
    PUDDING(0, 1, 2, 13),
    CAKE(2, 2, 2, 22);


    public final int flour;
    public final int egg;
    public final int milk;
    public final int profit;

    Recipe(int flour, int egg, int milk, int profit) {
        this.flour = flour;
        this.egg = egg;
        this.milk = milk;
        this.profit = profit;
    }
}
