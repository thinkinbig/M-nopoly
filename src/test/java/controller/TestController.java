package controller;

import model.ChessBoard;
import model.Model;
import org.junit.Test;
import org.mockito.Mockito;
import view.View;
import view.console.ConsoleView;


public class TestController {
    private Model model = Mockito.mock(ChessBoard.class);
    private View view = Mockito.mock(ConsoleView.class);
    private Controller controller = Controller.byDefault(model, view);
    private final String fields = "S;M;C;H;C;M;C;H";


}
