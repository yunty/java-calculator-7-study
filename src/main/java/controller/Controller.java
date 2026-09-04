package controller;

import domain.DelimiterSum;
import view.InputView;
import view.OutputView;

public class Controller {
    DelimiterSum delimiterSum;
    InputView inputView;
    OutputView outputView;

    public Controller(DelimiterSum delimiterSum, InputView inputView, OutputView outputView) {
        this.delimiterSum = delimiterSum;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    public void calculator(){
        outputView.printStart();
        String userInput = inputView.getUserNumber();
        delimiterSum.set(userInput);
        delimiterSum.validateInput();
        int result = delimiterSum.sum();
        outputView.printResult(result);
    }
}
