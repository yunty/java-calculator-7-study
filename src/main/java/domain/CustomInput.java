package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomInput { // 구분자와 숫자를 구분한다.
    private String delimiter = "[,:]";
    private String userInput = "";
    private Pattern pattern;
    private Matcher matcher;

    public void set(String userInput){
        patternMatcher(DelimiterPattern.CUSTOM_DELIMITER_PATTERN.getRegex(),userInput);
        if(matcher.matches()){
            String customDelimiter = matcher.group(1);
            delimiter += "|"+Pattern.quote(customDelimiter);
            this.userInput = matcher.group(2);
            return;
        }
        this.userInput=userInput;
    }
    public void validateInput(){
        patternMatcher(DelimiterPattern.DELIMITER_PATTERN.getRegex().formatted(delimiter),userInput);
        if(!matcher.matches()){
            throw new IllegalArgumentException();
        }
    }

    private void patternMatcher(String regex, String userInput){
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(userInput);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getUserInput() {
        return userInput;
    }
}
