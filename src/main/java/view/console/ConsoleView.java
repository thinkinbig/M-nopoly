package view.console;

import controller.RegexController;
import view.View;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class ConsoleView implements View, ConsoleFilter {

    @Override
    public void initialize(RegexController controller)
            throws InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (controller.isInit())
            throw new IllegalArgumentException("the controller is already initialized");
        controller.filterInitialize(input);
        scanner.close();
    }

    @Override
    public void receiveInput(RegexController controller) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String string = null;
        switch (input.split(" ", 2)[0]) {
            case ROLL:
                string = controller.filterRoll(input);
                break;
            case HARVEST:
                string = controller.filterHarvest(input);
                break;
            case BUY:
                string = controller.filterBuy(input);
                break;
            case PREPARE:
                string = controller.filterPrepare(input);
                break;
            case CAN_PREPARE:
                string = controller.filterCanPrepare(input);
                break;
            case SHOW_MARKET:
                string = controller.filterShowMarket(input);
                break;
            case SHOW_PLAYER:
                string = controller.filterShowPlayer(input);
                break;
            case TURN:
                string = controller.filterTurn(input);
                break;
            case QUIT:
                controller.filterQuit(input);
                break;
            default:
                break;
        }
        controller.draw(string);
        scanner.close();
    }

    @Override
    public void showUp(String message) {
        System.out.println(message);
    }
}
