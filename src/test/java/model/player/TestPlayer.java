package model.player;

import model.player.mode.Strategy;
import model.player.mode.Strategy_1;
import model.player.playfield.Field;
import model.player.playfield.FieldFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestPlayer {
    private String[] strings = "S;M;C;H;C;M;C;H".split(";");
    private Strategy strategy = new Strategy_1();
    private int test_count = 10;
    private List<Field> fields;
    private Player player;

    @Before
    public final void setUP() {
        fields = new ArrayList<>();
        Arrays.stream(strings).forEach(s -> fields.add(FieldFactory.map(s)));
        player = Player.newPlayer(strategy, fields);
    }

    @After
    public final void tearDown() {
        player = null;
        fields = null;
        strategy = null;
        strings = null;
    }

    @Test
    public final void test_rollDice() {
        Random r = new Random();
        int j = 0;
        while (test_count-- > 0) {
            int i = r.nextInt(6) + 1;
            j = (i + j) % strings.length;
            Assert.assertEquals(strings[j], player.roll(i).toString());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public final void test_rollDiceFailure() {
        player.roll(12);
    }
}
