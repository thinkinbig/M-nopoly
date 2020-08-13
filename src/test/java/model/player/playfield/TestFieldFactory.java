package model.player.playfield;

import org.junit.Assert;
import org.junit.Test;

public class TestFieldFactory {

    @Test
    public final void test_map() {
        Assert.assertTrue(FieldFactory.map("S") instanceof StartField);
        Assert.assertTrue(FieldFactory.map("M") instanceof Mill);
        Assert.assertTrue(FieldFactory.map("C") instanceof Meadow);
        Assert.assertTrue(FieldFactory.map("H") instanceof HenHouse);
        Assert.assertTrue(FieldFactory.map("") == null);
    }
}
