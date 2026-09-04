package view;

public class OutputView {
    final String START_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    final String RESULT_MESSAGE = "결과";

    public void printStart(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }
    public void printResult(int result){
        System.out.println("결과 : "+result);
    }
}
