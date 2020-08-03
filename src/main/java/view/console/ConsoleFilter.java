package view.console;

import controller.RegexController;

import java.lang.reflect.InvocationTargetException;

public interface ConsoleFilter {
    void initialize(RegexController controller) throws InvocationTargetException, IllegalAccessException;

    void receiveInput(RegexController controller);
}
