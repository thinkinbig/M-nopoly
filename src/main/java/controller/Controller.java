package controller;

import util.Observer;

public interface Controller extends Observer {
    void initialize(String fields);
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
