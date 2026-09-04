package controller;

import domain.CustomInput;
import domain.DelimiterSum;
import view.InputView;
import view.OutputView;

public class Controller {
    private DelimiterSum delimiterSum;
    private InputView inputView;
    private OutputView outputView;

    public Controller(DelimiterSum delimiterSum, InputView inputView, OutputView outputView) {
        this.delimiterSum = delimiterSum;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void calculator(){
        outputView.printStart();
        String userInput = inputView.getUserNumber();
        CustomInput customInput = CustomInput.from(userInput);
        int result = delimiterSum.sum(customInput);
        outputView.printResult(result);
    }
}
