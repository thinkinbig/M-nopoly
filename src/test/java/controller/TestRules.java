package controller;


import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;


public class TestRules {
    private String test_string1 = "S;M;H;M;H";
    private String test_string2 = "M;H;M;H";
    private String test_string3 = "S;M;C;H;C;M;C;H";
    private String test_string4 = "S;H;M;C;H";
    private String test_string5 = "S;M;C;H;H";
    private String test_string6 = "S;M;C;H;C;H";
    private String test_string7 = "S;H;M;C;H;C";
    private String test_string8 = "S;M;H;M;S;H";
    private String test_string9 = "S;M;C;H";
    private InitializedRules i1 = new InitializedRules(test_string1);
    private InitializedRules i2 = new InitializedRules(test_string2);
    private InitializedRules i3 = new InitializedRules(test_string3);
    private InitializedRules i4 = new InitializedRules(test_string4);
    private InitializedRules i5 = new InitializedRules(test_string5);
    private InitializedRules i6 = new InitializedRules(test_string6);
    private InitializedRules i7 = new InitializedRules(test_string7);
    private InitializedRules i8 = new InitializedRules(test_string8);
    private InitializedRules i9 = new InitializedRules(test_string9);

    @Test
    public final void test_rule1_true() {
        Assert.assertTrue(i1.rule1());
    }

    @Test
    public final void test_rule1_false() {
        Assert.assertFalse(i2.rule1());
        Assert.assertFalse(i8.rule1());
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
    public final void test_rule3_false() throws ExecutionException, InterruptedException {
        Assert.assertFalse(i4.rule3());
        Assert.assertFalse(i5.rule3());
    }

    @Test
    public final void test_rule3_true() throws ExecutionException, InterruptedException {
        Assert.assertTrue(i3.rule3());
    }

    @Test
    public final void test_rule4_true() throws ExecutionException, InterruptedException {
        Assert.assertTrue(i3.rule4());
    }

    @Test
    public final void test_rule4_false() throws ExecutionException, InterruptedException {
        Assert.assertFalse(i6.rule4());
        Assert.assertFalse(i7.rule4());
    }

    @Test
    public final void test_all_true() throws ExecutionException, InterruptedException {
        Assert.assertTrue(i3.rule1() & i3.rule2() & i3.rule3() & i4.rule4());
        Assert.assertTrue(i9.rule1() & i9.rule2() & i9.rule3() & i9.rule4());
    }

    @Test
    public final void test_all_false() throws ExecutionException, InterruptedException {
        Assert.assertFalse(i8.rule1() & i8.rule2() & i8.rule3() & i8.rule4());
    }

}
