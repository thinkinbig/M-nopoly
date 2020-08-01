package control;

import util.Observer;

public interface Controller extends Observer {
    String requestRoll(String number);
    String requestHarvest();
    String requestBuy(String resource);
    String requestPrepare(String recipe);
    String requestAvailable();
    String requestShowMarket();
    String requestShowPlayer(String player);
    String requestTurn();
    String requestQuit();
}
