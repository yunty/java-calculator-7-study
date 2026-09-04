package calculator;

import controller.Controller;
import domain.DelimiterSum;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new DelimiterSum(), new InputView(), new OutputView());

        controller.calculator();
    }
}
