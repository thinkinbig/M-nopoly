package model.player.playfield;

import model.product.RawMaterial;
import org.junit.Assert;
import org.junit.Test;

public class TestMeadow {

    private Field test = FieldFactory.newMeadow();

    @Test
    public final void test_material() {
        Assert.assertEquals(RawMaterial.MILK, test.getMaterial());
    }

    @Test
    public final void test_toString() {
        Assert.assertEquals("C", test.toString());
    }
}
