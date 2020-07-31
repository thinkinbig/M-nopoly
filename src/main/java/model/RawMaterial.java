package model;

public enum RawMaterial {
    EGG {
        @Override
        public int getPrice() {
            return Market.getEggPrice();
        }

        @Override
        public void add() {
            Market.addEgg();
        }
    }, MILK {
        @Override
        public int getPrice() {
            return Market.getMilkPrice();
        }

        @Override
        public void add() {
            Market.addMilk();
        }
    }, FLOUR {
        @Override
        public int getPrice() {
            return Market.getFlourPrice();
        }

        @Override
        public void add() {
            Market.addFlour();
        }
    };


    public abstract int getPrice();

    public abstract void add();
}
