package model.product;

public enum Recipe {
    YOGURT(0, 0, 3, 8) {
        @Override
        public String toString() {
            return "yogurt";
        }
    },
    MERINGUE(0, 3, 0, 9) {
        @Override
        public String toString() {
            return "meringue";
        }
    },
    BREAD(3, 0, 0, 10) {
        @Override
        public String toString() {
            return "bread";
        }
    },
    BUN(2, 0, 1, 11) {
        @Override
        public String toString() {
            return "bun";
        }
    },
    CREPE(1, 2, 0, 12) {
        @Override
        public String toString() {
            return "crepe";
        }
    },
    PUDDING(0, 1, 2, 13) {
        @Override
        public String toString() {
            return "pudding";
        }
    },
    CAKE(2, 2, 2, 22) {
        @Override
        public String toString() {
            return "cake";
        }
    };


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

    public static final Recipe map(String name) {
        for (Recipe recipe : Recipe.values()) {
            if (recipe.toString().equals(name)) {
                return recipe;
            }
        }
        return null;
    }
}
