package view;

import controller.Controller;
import view.console.ConsoleView;

public interface View {
    String ROLL = "roll";
    String HARVEST = "harvest";
    String BUY = "buy";
    String PREPARE = "prepare";
    String CAN_PREPARE = "can-prepare?";
    String SHOW_MARKET = "show-market";
    String SHOW_PLAYER = "show-player";
    String TURN = "turn";
    String QUIT = "quit";

    void showUp(String message);

    default void showRoll(Controller controller, String number) {
        controller.draw(controller.requestRoll(number));
    }

    default void showHarvest(Controller controller) {
        controller.draw(controller.requestHarvest());
    }

    default void showBuy(Controller controller, String resource) {
        controller.draw(controller.requestBuy(resource));
    }

    default void showPrepare(Controller controller, String recipe) {
        controller.draw(controller.requestPrepare(recipe));
    }

    default void showCanPrepare(Controller controller) {
        controller.draw(controller.requestAvailable());
    }

    default void showMarket(Controller controller) {
        controller.draw(controller.requestShowMarket());
    }

    default void showPlayer(Controller controller, String player) {
        controller.draw(controller.requestShowPlayer(player));
    }

    default void showTurn(Controller controller) {
        controller.draw(controller.requestTurn());
    }

    static View createConsoleView() {
        return new ConsoleView();
    }
}
