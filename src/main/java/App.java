import controller.Controller;
import controller.RegexController;
import view.View;
import view.console.ConsoleFilter;

import java.lang.reflect.InvocationTargetException;

public class App {

    private App() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        View view = View.createConsoleView();
        ConsoleFilter filter = (ConsoleFilter) view;
        RegexController controller = (RegexController) Controller.byConsole(null, view);
        while (!controller.isInit()) {
            try {
                filter.initialize(controller);
            } catch (InvocationTargetException e) {
                System.err.println(e.getMessage());
            } catch (IllegalAccessException e) {
                System.err.println(e.getMessage());
            }
        }

        while (!controller.isQuited()) {
            filter.receiveInput(controller);
        }
    }
}
