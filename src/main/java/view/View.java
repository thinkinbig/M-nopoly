package view;

import controller.Controller;
import view.console.ConsoleView;

public interface View {
    void initialize(Controller controller);

    void showUp();

    static View createConsoleView() {
        return new ConsoleView();
    }
}
