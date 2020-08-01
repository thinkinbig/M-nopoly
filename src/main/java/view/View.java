package view;

import control.Controller;
import view.console.ConsoleView;

public interface View {
    void initialize(Controller controller);

    void showUp();

    static View createConsoleView() {
        return new ConsoleView();
    }
}
