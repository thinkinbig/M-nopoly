package model.player.playfield;

import org.junit.Assert;
import org.junit.Test;

public class TestStartField {
    private Field test = FieldFactory.newStartField();

    @Test
    public final void test_toString() {
        Assert.assertEquals("S", test.toString());
    }

}
