package domain;

public class DelimiterSum { //구분자와 문자열을 받아 합친다.
    public int sum(InputParse input){
        int sum = 0;
        String[] sep = input.getUserInput().split(input.getDelimiter());
        for(String s : sep){
            sum+=Integer.parseInt(s);
        }
        return sum;
    }
}
