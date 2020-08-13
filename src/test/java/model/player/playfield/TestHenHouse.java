package model.player.playfield;

import model.product.RawMaterial;
import org.junit.Assert;
import org.junit.Test;

public class TestHenHouse {
    private Field test = FieldFactory.newHenHouse();

    @Test
    public final void test_material() {
        Assert.assertEquals(RawMaterial.EGG, test.getMaterial());
    }

    @Test
    public final void test_toString() {
        Assert.assertEquals("H", test.toString());
    }
}
