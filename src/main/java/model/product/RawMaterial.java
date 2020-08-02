package model.product;

import model.Market;

public enum RawMaterial {
    FLOUR {
        @Override
        public String toString() {
            return "flour";
        }
    }, EGG {
        @Override
        public String toString() {
            return "egg";
        }
    }, MILK {
        @Override
        public String toString() {
            return "milk";
        }
    };


    public final int getPrice() {
        return Market.getPrice(this);
    }

    public final void add() {
        Market.addMaterial(this);
    }

    public static final RawMaterial map(String resource) {
        for (RawMaterial material : RawMaterial.values()) {
            if (material.toString().equals(resource)) {
                return material;
            }
        }
        return null;
    }
}
