package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomInput { // 구분자와 숫자를 구분한다.
    private String delimiter;
    private String userInput;

    private CustomInput(String rawInput) {
        set(rawInput);
        validateInput();
    }

    public static CustomInput from(String rawInput){
        return new CustomInput(rawInput);
    }
    public String getDelimiter() {
        return delimiter;
    }
    public String getUserInput() {
        return userInput;
    }

    private void set(String rawInput){
        this.delimiter = "[,:]";
        this.userInput = "";
        Pattern pattern = Pattern.compile(DelimiterPattern.CUSTOM_DELIMITER_PATTERN.getRegex());
        Matcher matcher = pattern.matcher(rawInput);
        if(matcher.matches()){
            String customDelimiter = matcher.group(1);
            delimiter += "|"+Pattern.quote(customDelimiter);
            this.userInput = matcher.group(2);
            return;
        }
        this.userInput=rawInput;
    }
    private void validateInput(){
        if(!userInput.matches(DelimiterPattern.DELIMITER_PATTERN.getRegex().formatted(delimiter))){
            throw new IllegalArgumentException();
        }
    }
}
