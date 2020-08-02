package controller;

import model.Model;
import util.Observer;
import view.View;

import java.lang.reflect.InvocationTargetException;

public interface Controller extends Observer {
    void initialize(String player, String fields) throws InvocationTargetException, IllegalAccessException;
    String requestRoll(String number);
    String requestHarvest();
    String requestBuy(String resource);
    String requestPrepare(String recipe);
    String requestAvailable();
    String requestShowMarket();
    String requestShowPlayer(String player);
    String requestTurn();
    void requestQuit();
    String judgeWin();

    static Controller byDefault(Model model, View view) {
        return new DefaultController(model, view);
    }
}
