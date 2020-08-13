package model.player.playfield;

import model.player.ConcretePlayer;
import model.player.Player;
import org.junit.Test;
import org.mockito.Mockito;

public class TestField {
    private Field test = FieldFactory.newStartField();
    private Player player = Mockito.mock(ConcretePlayer.class);

    @Test(expected = IllegalArgumentException.class)
    public final void test_entry() {
        for (int i = 0; i < 5; i++) {
            test.entry(player);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public final void test_exit() {
        test.exit(player);
    }
}
