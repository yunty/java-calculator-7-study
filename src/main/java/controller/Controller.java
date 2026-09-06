package controller;

import domain.InputParser;
import domain.Calculator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputParser inputParser;
    private final Calculator calculator;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputParser inputParser, Calculator calculator,
                      InputView inputView, OutputView outputView) {
        this.inputParser = inputParser;
        this.calculator = calculator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStart();
        String rawInput = inputView.readInput();
        List<Integer> numbers = inputParser.parse(rawInput);
        int result = calculator.sum(numbers);
        outputView.printResult(result);
    }
}
