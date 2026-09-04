package controller;

import domain.CustomInput;
import domain.DelimiterSum;
import view.InputView;
import view.OutputView;

public class Controller {
    private DelimiterSum delimiterSum;
    private InputView inputView;
    private OutputView outputView;
    private CustomInput customInput;

    public Controller(DelimiterSum delimiterSum, InputView inputView, OutputView outputView, CustomInput customInput) {
        this.delimiterSum = delimiterSum;
        this.inputView = inputView;
        this.outputView = outputView;
        this.customInput = customInput;
    }

    public void calculator(){
        outputView.printStart();
        String userInput = inputView.getUserNumber();
        customInput.set(userInput);
        customInput.validateInput();
        int result = delimiterSum.sum(customInput);
        outputView.printResult(result);
    }
}
