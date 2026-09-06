package calculator;

import controller.Controller;
import domain.Calculator;
import domain.InputParser;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(
                new InputParser(), new Calculator(), new InputView(), new OutputView()
        );

        controller.run();
    }
}
