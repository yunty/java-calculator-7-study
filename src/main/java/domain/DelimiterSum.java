package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterSum { //구분자와 문자열을 받아 합친다.
    public int sum(CustomInput input){
        int sum = 0;
        String[] sep = input.getUserInput().split(input.getDelimiter());
        for(String s : sep){
            sum+=Integer.parseInt(s);
        }
        return sum;
    }
}
