package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterSum {
    private final String CUSTOM_DELIMITER_PATTERN = "//(.+)\\\\n(.*)";
    private String delimiter = "[,:]";
    private String userInput = "";
    private int sum = 0;

    public void validateInput(){
        String delimiterPattern = "^\\d+(" + delimiter + "\\d+)*$";
        Pattern pattern = Pattern.compile(delimiterPattern);
        Matcher matcher = pattern.matcher(userInput);
        System.out.println(delimiter + "user:" + userInput);
        if(!matcher.matches()){
            throw new IllegalArgumentException();
        }
    }

    public void set(String userInput){
        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_PATTERN);
        Matcher matcher = pattern.matcher(userInput);

        if(matcher.matches()){
            String customDelimiter = matcher.group(1);
            delimiter += "|"+Pattern.quote(customDelimiter);
            System.out.println("커스텀");
            this.userInput = matcher.group(2);
            return;
        }
        this.userInput=userInput;
    }

    public int sum(){
        String[] sep = userInput.split(delimiter);
        for(String s : sep){
            sum+=Integer.parseInt(s);
        }
        return sum;
    }
}
