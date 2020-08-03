package controller;

import model.Model;
import view.View;

import java.lang.reflect.InvocationTargetException;

public interface Controller {
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
    String requestJudgeWin();
    void draw(String message);
    boolean isInit();
    boolean isQuited();

    static Controller byDefault(Model model, View view) {
        return new DefaultController(model, view);
    }

    static Controller byConsole(Model model, View view) { return new RegexController(model, view); }
}
