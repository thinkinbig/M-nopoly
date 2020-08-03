package controller;

import java.lang.reflect.InvocationTargetException;

public interface RegexFilter {
    void filterInitialize(String input) throws InvocationTargetException, IllegalAccessException;
    String filterRoll(String input);
    String filterHarvest(String input);
    String filterBuy(String input);
    String filterPrepare(String input);
    String filterCanPrepare(String input);
    String filterShowMarket(String input);
    String filterShowPlayer(String input);
    String filterTurn(String input);
    void filterQuit(String input);
}
