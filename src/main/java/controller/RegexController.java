package controller;


import model.Model;
import model.product.RawMaterial;
import model.product.Recipe;
import view.View;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexController extends DefaultController implements RegexFilter {

    private static final String NOT_VALID = "the input doesn't match required pattern";

    public RegexController(Model model, View view) {
        super(model, view);
    }

    @Override
    public void filterInitialize(String input) throws InvocationTargetException, IllegalAccessException {
        final String regex = "^java BackersWalz ([0-9]+) (.)* $";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            initialize(matcher.group(1), matcher.group(2));
            return;
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterRoll(String input) {
        final String regex = "^roll [1-6]$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestRoll(matcher.group(1));
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterHarvest(String input) {
        final String regex = "^harvest$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestHarvest();
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterBuy(String input) {
        StringBuilder sb = new StringBuilder();
        RawMaterial[] materials = RawMaterial.values();
        for (int i = 0; i < materials.length; i++) {
            if (i == materials.length - 1)
                sb.append(materials[i].toString());
            else
                sb.append(materials[i].toString() + "|");
        }
        final String regex = "^buy (" + sb.toString() + ")$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestBuy(matcher.group(1));
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterPrepare(String input) {
        StringBuilder sb = new StringBuilder();
        Recipe[] recipes = Recipe.values();
        for (int i = 0; i < recipes.length; i++) {
            if (i == recipes.length - 1)
                sb.append(recipes[i]);
            else
                sb.append(recipes[i] + "|");
        }
        final String regex = "^prepare (" + sb.toString() + ")$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestPrepare(matcher.group(1));
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterCanPrepare(String input) {
        final String regex = "^can-prepare\\?$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestAvailable();
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterShowMarket(String input) {
        final String regex = "^show-market$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestShowMarket();
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterShowPlayer(String input) {
        final String regex = "^show-player P([0-9]+)+$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestShowPlayer(matcher.group(1));
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public String filterTurn(String input) {
        final String regex = "^turn$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return requestTurn();
        }

        throw new IllegalArgumentException(NOT_VALID);
    }

    @Override
    public void filterQuit(String input) {
        final String regex = "quit";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            requestQuit();
            return;
        }

        throw new IllegalArgumentException(NOT_VALID);
    }
}
