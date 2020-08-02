package controller;

import model.ChessBoard;
import model.Model;
import model.player.playfield.Field;
import model.player.playfield.FieldFactory;
import model.product.RawMaterial;
import model.product.Recipe;
import view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultController implements Controller {
    private InitializedRules rules;
    private Model model;
    private View view;
    private static final String CONCAT = ";";
    private static final int MIN = 4;
    private static final int MAX = 25;

    @Override
    public void initialize(String players, String fields) throws InvocationTargetException, IllegalAccessException {
        String[] strings = fields.split(";");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        if (strings.length < MIN || strings.length > MAX)
            throw new IllegalArgumentException("fields exceeds expected length");
        rules = new InitializedRules(strings);
        for (Method m : rules.getClass().getDeclaredMethods()) {
            if (m.canAccess(rules) && ! (boolean) m.invoke(rules))
                throw new IllegalArgumentException("fields parameter not meet the requirements");
        }
        List<Field> playFields = new LinkedList<>();
        int playersNumber = Integer.parseInt(players);
        for (String s : strings) {
            playFields.add(FieldFactory.map(s));
        }
        model = new ChessBoard(playFields, playersNumber);
    }


    @Override
    public String requestRoll(String number) {
        String string1 = model.roll(Integer.parseInt(number)).toString();
        String string2 = Integer.toString(model.gold());
        return string1 + CONCAT + string2;
    }

    @Override
    public String requestHarvest() {
        String string1 = model.harvest().toString();
        String string2 = Integer.toString(model.gold());
        return string1 + CONCAT + string2;
    }

    @Override
    public String requestBuy(String resource) {
        String string1 = Integer.toString(model.buy(RawMaterial.map(resource)));
        String string2 = Integer.toString(model.gold());
        return string1 + CONCAT + string2;
    }

    @Override
    public String requestPrepare(String recipe) {
        String string1 = Integer.toString(model.prepare(Recipe.map(recipe)));
        String string2 = model.judgeWin();
        return (string2 == null) ? string1 : string1 + "\n" + string2;
    }

    @Override
    public String requestAvailable() {
        StringBuilder sb = new StringBuilder();
        for(Recipe recipe : model.available()) {
            sb.append(recipe.toString() + "\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String requestShowMarket() {
        Map<RawMaterial, Integer> map = model.showMarket();
        StringBuilder sb = new StringBuilder();
        for (RawMaterial raw : map.keySet()) {
            sb.append(map.get(raw) + ";" + raw.toString() + "\n");
        }
        return sb.toString().trim();
    }

    @Override
    public String requestShowPlayer(String player) {
        int num = Integer.parseInt(player);
        int gold = model.getPlayer(num).gold();
        StringBuilder sb = new StringBuilder();
        sb.append(gold + ";");
        List<Integer> list = model.showPlayer(num);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1)
                sb.append(list.get(i));
            else
                sb.append(list.get(i) + ";");
        }
        return sb.toString();
    }

    @Override
    public String requestTurn() {
        return model.turn().toString();
    }

    @Override
    public void requestQuit() {
        model.quit();
    }

    @Override
    public String judgeWin() {
        return null;
    }

    @Override
    public void update() {
        this.requestQuit();
    }

    public DefaultController(Model model, View view) {
        this.model = model;
        this.view = view;
    }
}
