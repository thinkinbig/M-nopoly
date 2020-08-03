package main;


import edu.kit.informatik.Terminal;
import game.config.Config;
import game.player.Player;
import game.system.GameSystem;

import java.util.Vector;
import java.util.regex.Pattern;

/**
 * easy implementation for the game container
 * @author memoryrabbit
 * @version 0.0.1
 */
public final class Main {

    /**
     * entry of the programme
     * @param args args is an array of String.
     *             In order that the programme can run normally,
     *             args should has length two,
     *             whereas the first component should be a Integer,
     *             which represents the player number. @see Config.java.
     *             The second one is a row of Fields,
     *             which should start with "S" and follows with ";X". X is M or C or H.
     *             i.e. S;C;H;M
     *             except for this, there also another 3 rules.
     *             1. C and H and M must appear min. 1 time.
     *             2. two same fields can't be neighboured. i.e. S;M;M is forbidden.
     *             3. the nearest two same fields shouldn't be more than 4(not concluded) steps from another.
     *                with rule 2&3 we shall ignore field S.
     *                i.e. S;M;C;H;C;H forbidden, cause distance(M)=5>4. (think it as a cycle)
     * @see Config for pre-defined static varialbles.
     */
    public static void main(String[] args) {
        if (args == null) {
            Terminal.printError("arguments needed");
            return;
        }
        if (!checkFormat(args) || !checkContent(args)) return;
        Player[] players = new Player[Integer.parseInt(args[0])];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
        GameSystem system = new GameSystem(players, args[1].split(";"));
        while (!system.getGameStopped()) {
            String order = Terminal.readLine();
            if (order.matches("^roll\\s\\d$")) {
                system.roll(Integer.parseInt(order.split(" ")[1]));
            } else if (order.matches("^harvest$")) {
                system.harvest();
            } else if (order.matches("^buy\\s(egg|milk|flour)$")) {
                switch (order.split(" ")[1]) {
                    case "flour":
                        system.buy(Config.GOODS.flour);
                        break;
                    case "egg":
                        system.buy(Config.GOODS.egg);
                        break;
                    case "milk":
                        system.buy(Config.GOODS.milk);
                    default:
                }
            } else if (order.matches("^prepare\\s(yoghurt|meringue|bread|bun|crepe|pudding|cake)$")) {
                switch (order.split(" ")[1]) {
                    case "yoghurt":
                        system.prepare(Config.RECEIPT.Yoghurt);
                        break;
                    case "meringue":
                        system.prepare(Config.RECEIPT.Meringue);
                        break;
                    case "bread":
                        system.prepare(Config.RECEIPT.Bread);
                        break;
                    case "bun":
                        system.prepare(Config.RECEIPT.Bun);
                        break;
                    case "crepe":
                        system.prepare(Config.RECEIPT.Crepe);
                        break;
                    case "pudding":
                        system.prepare(Config.RECEIPT.Pudding);
                        break;
                    case "cake":
                        system.prepare(Config.RECEIPT.Cake);
                        break;
                    default: return;
                }
            } else if (order.matches("^can-prepare?$")) {
                system.canPrepare();
            } else if (order.matches("^show-market$")) {
                system.showMarket();
            } else if (order.matches("^show-player\\sP[1234]$")) {
                system.showPlayer(Integer.parseInt(order.split("P")[1]));
            } else if (order.matches("^turn$")) {
                system.turn();
            } else if (order.matches("^quit$")) {
                system.quit();
            }else {
                Terminal.printError("order not defined, wrong input");
            }
        }
    }

    /**
     * this method checks whether the format is correct.
     * for detail rules please turn to comments for main method.
     * @param args is a String array that is actually the run arguments, param for main method
     * @return if all rules aren't broken, return true, else false
     */
    private static boolean checkFormat(String[] args) {
        /*
         * match parameter, if there aren't 2 params
         * the game can't be started
         */
        if (args.length != 2) {
            Terminal.printError("wrong parameter number, which should be 2");
            return false;
        }
        /*
         * match parameter 1, which represents the player number
         * it should be an Integer among 2 and 4(both concluded)
         */
        int playerNumber;
        try {
            playerNumber = Integer.parseInt(args[0]);
        } catch (IllegalArgumentException e) {
            Terminal.printError("first parameter must be an Integer");
            return false;
        }
        if (playerNumber < Config.MIN_PLAYER_NUMBER || playerNumber > Config.MAX_PLAYER_NUMBER) {
            Terminal.printError("illegal player number");
            return false;
        }
        /*
         * match parameter 2
         * more exactly, just the regex
         */
        if (!Pattern.matches("^S(;[CHM]){3,24}$", args[1])) {
            Terminal.printError("wrong field initialization");
            return false;
        }
        return true;
    }

    /**
     * this method checks whether the content of fields for game initialization correct is.
     * checking rules in detail please turn to comments for main method
     * @param args is a String array that is actually the run arguments, param for main method
     * @return if all rules aren't broken, return true, else false
     */
    private static boolean checkContent(String[] args) {
        //get all fields
        String[] fields = args[1].split(";");
        // next get index for each field and length of all fields except S
        int lengthFields = fields.length - 1;
        Vector<Integer> cowPasture = new Vector<>(); //case C
        Vector<Integer> mill = new Vector<>(); // case M
        Vector<Integer> henHouse = new Vector<>(); // case H
        for (int i = 1; i < lengthFields + 1; i++) {
            switch (fields[i]) {
                case "C":
                    cowPasture.add(i);
                    break;
                case "M":
                    mill.add(i);
                    break;
                case "H":
                    henHouse.add(i);
                    break;
                default: //never used
            }
        }
        //check whether C and H and M are contained(rule 1)
        if (cowPasture.isEmpty() || mill.isEmpty() || henHouse.isEmpty()) {
            Terminal.printError("not all three fields are contained");
            return false;
        }
        //check whether two same fields neighboured(rule 2) and distance <=4 is(rule 3)
        //determine, whether last = second(here includes S)
        if (fields[1].equals(fields[lengthFields - 1])) {
            Terminal.printError("two same fields neighboured");
            return false;
        }
        boolean cToken = true;
        boolean mToken = true;
        boolean hToken = true;
        int counter = 0;
        if (lengthFields > 4 && (cowPasture.size() == 1 || henHouse.size() == 1 || mill.size() == 1)) {
            Terminal.printError("min. one field has distance>4 with itself(rule 3 broken)");
        }
        if (cowPasture.size() == 1 && henHouse.size() == 1 && mill.size() == 1 && lengthFields == 3) return true;
        while (cToken || hToken || mToken) {
            if ((cToken && cowPasture.get(counter) + 1 == cowPasture.get(counter + 1))
                    || (hToken && henHouse.get(counter) + 1 == henHouse.get(counter + 1))
                    || (mToken && mill.get(counter) + 1 == mill.get(counter + 1)))
            {
                Terminal.printError("two same fields neighboured");
                return false;
            }
            if ((cToken && cowPasture.get(counter + 1) - cowPasture.get(counter) > 4)
                    || (hToken && henHouse.get(counter + 1) - henHouse.get(counter) > 4)
                    || (mToken && mill.get(counter + 1) - mill.get(counter) > 4))
            {
                Terminal.printError("two same fields with distance>4");
                return false;
            }
            if (counter + 2 == cowPasture.size()) cToken = false;
            if (counter + 2 == henHouse.size()) hToken = false;
            if (counter + 2 == mill.size()) mToken = false;
        }
        if (lengthFields - cowPasture.lastElement() + cowPasture.firstElement() > 4
                || lengthFields - henHouse.lastElement() + henHouse.firstElement() > 4
                || lengthFields - mill.lastElement() + mill.firstElement() > 4)
        {
            Terminal.printError("two same fields with distance>4, check for cycles");
            return false;
        }
        return true;
    }
}
