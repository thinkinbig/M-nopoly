import controller.Controller;
import controller.RegexController;
import view.View;
import view.console.ConsoleFilter;
import view.console.ConsoleView;

import java.lang.reflect.InvocationTargetException;

public class App {

    private App() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        ConsoleView.startConsoleGame();
    }
}
