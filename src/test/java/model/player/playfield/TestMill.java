package model.player.playfield;

import model.product.RawMaterial;
import org.junit.Assert;
import org.junit.Test;

public class TestMill {
    private Field test = FieldFactory.newMill();

    @Test
    public final void test_getMaterial() {
        Assert.assertEquals(RawMaterial.FLOUR, test.getMaterial());
    }

    @Test
    public final void test_toString() {
        Assert.assertEquals("M", test.toString());
    }
}
