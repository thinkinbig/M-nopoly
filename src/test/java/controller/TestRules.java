package controller;


import org.junit.Assert;
import org.junit.Test;


public class TestRules {
    private String test_string1 = "S;M;H;M;H";
    private String test_string2 = "M;H;M;H";
    private String test_string3 = "S;M;C;H;C;M;C;H";
    private String test_string4 = "S;H;M;C;H";
    private String test_string5 = "S;M;C;H;H";
    private String test_string6 = "S;M;C;H;C;H";
    private String test_string7 = "S;H;M;C;H;C";
    private InitializedRules i1 = new InitializedRules(test_string1);
    private InitializedRules i2 = new InitializedRules(test_string2);
    private InitializedRules i3 = new InitializedRules(test_string3);
    private InitializedRules i4 = new InitializedRules(test_string4);
    private InitializedRules i5 = new InitializedRules(test_string5);
    private InitializedRules i6 = new InitializedRules(test_string6);
    private InitializedRules i7 = new InitializedRules(test_string7);

    @Test
    public final void test_rule1_true() {
        Assert.assertTrue(i1.rule1());
    }

    @Test
    public final void test_rule1_false() {
        Assert.assertFalse(i2.rule1());
    }

    @Test
    public final void test_rule2_false() {
        Assert.assertFalse(i2.rule2());
    }

    @Test
    public final void test_rule2_true() {
        Assert.assertTrue(i3.rule2());
    }

    @Test
    public final void test_rule3_false() {
        Assert.assertFalse(i4.rule3());
        Assert.assertFalse(i5.rule3());
    }

    @Test
    public final void test_rule3_true() {
        Assert.assertTrue(i3.rule3());
    }

    @Test
    public final void test_rule4_true() {
        Assert.assertTrue(i3.rule4());
    }

    @Test
    public final void test_rule4_false() {
        Assert.assertFalse(i6.rule4());
        Assert.assertFalse(i7.rule4());
    }

}
