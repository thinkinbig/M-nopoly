package model.player;

import model.Market;
import model.player.mode.Strategy;
import model.player.mode.Strategy_1;
import model.player.playfield.Field;
import model.player.playfield.FieldFactory;
import model.player.status.InitialStatus;
import model.product.RawMaterial;
import model.product.Recipe;
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
            player.setStatus(new InitialStatus(player));
            Assert.assertEquals(strings[j], player.roll(i).toString());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public final void test_rollDiceFailure() {
        player.roll(12);
    }

    @Test
    public final void test_harvest() {
        Field f = player.roll(1);
        int price = player.gold();
        int expected = Market.getStack(f.getMaterial()).size();
        RawMaterial raw = player.harvest();
        int actual = Market.getStack(raw).size();
        Assert.assertEquals(f.getMaterial(), raw);
        Assert.assertEquals(price + 1, player.gold());
        Assert.assertEquals(expected + 1, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void test_harvest_failure() {
        Field field = player.roll(1);
        RawMaterial raw = field.getMaterial();
        while (!Market.getStack(raw).isFull()) {
            Market.getStack(raw).push();
        }
        player.harvest();
    }

    @Test
    public final void test_prepareMeal() {
        player.roll(1);
        for (int i = 0; i < 3; i++) {
            Market.getStack(RawMaterial.MILK).push();
            Assert.assertTrue(player.addMaterial(RawMaterial.MILK));
        }
        Assert.assertEquals(true, player.canPrepareMeal(Recipe.YOGURT));
        int before = player.gold();
        player.prepareMeal(Recipe.YOGURT);
        Assert.assertEquals(before + Recipe.YOGURT.profit, player.gold());
    }

    @Test
    public final void test_buy() {
        player.roll(1);
        int gold = player.gold();
        int price = RawMaterial.EGG.getPrice();
        Assert.assertTrue(player.canBuy(RawMaterial.EGG));
        player.buy(RawMaterial.EGG);
        Assert.assertEquals(gold - price, player.gold());
        gold = player.gold();
        price = RawMaterial.MILK.getPrice();
        player.buy(RawMaterial.MILK);
        Assert.assertEquals(gold - price, player.gold());
        gold = player.gold();
        price = RawMaterial.FLOUR.getPrice();
        player.buy(RawMaterial.FLOUR);
        Assert.assertEquals(gold - price, player.gold());
        gold = player.gold();
        price = RawMaterial.MILK.getPrice();
        player.buy(RawMaterial.MILK);
        Assert.assertEquals(gold - price, player.gold());
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void test_buy_failure() {
        player.roll(1);
        for (int i = 0; i < 3; i++) {
            player.buy(RawMaterial.EGG);
        }
    }

    @Test
    public final void test_earn_gold() {
        int number = 12;
        int gold = player.gold();
        player.earnGold(12);
        Assert.assertEquals(number + gold, player.gold());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void test_use_gold() {
        int number = 19;
        int gold = player.gold();
        player.useGold(number);
        Assert.assertEquals(gold - number, player.gold());
        number = 10;
        player.useGold(number);
    }

    @Test
    public final void test_reduce_materials() {
        player.roll(1);
        Assert.assertTrue(player.addMaterial(RawMaterial.MILK));
        player.reduceMaterial(RawMaterial.MILK, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void test_reduce_materials_failure() {
        player.roll(1);
        player.reduceMaterial(RawMaterial.MILK, 2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void test_turn() {
        player.roll(1);
        player.turn();
        player.roll(1);
    }

}
