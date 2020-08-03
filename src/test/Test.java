package test;

import edu.kit.informatik.Terminal;
import game.config.Config;
import game.system.GamePlace;

public class Test {

    public static void main(String[] args) {
        String[] f = {"S","M","C","H"};
        GamePlace gamePlace = GamePlace.getInstance(f);
        String a = "prepare pudding";
        System.out.println(a.matches("^prepare\\s(yoghurt|meringue|bread|bun|crepe|pudding|cake)$"));

    }
}
