package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParse {
    public static final String CUSTOM_DELIMITER = "(?://(.)\\n)?(.*)";
    public static final String NUMBER_REGEX = "[0-9]+";
    public static final String DELIMITER = "[,:]";
    private static Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);

    public List<Integer> getNumberList(String input) {
        validator(input);
        Matcher matcher = pattern.matcher(input);
        if(isNotMatch(matcher)){
            throw new IllegalArgumentException("올바르지 않은 형식입니다.");
        }
        String delimiter = addCustomDelimiter(matcher.group(1));
        return convertToList(matcher, delimiter);
    }

    private static boolean isNotMatch(Matcher matcher) {
        return !matcher.matches();
    }

    private List<Integer> convertToList(Matcher matcher, String delimiter) {
        List<Integer> list = Arrays.stream(splitNumber(matcher, delimiter))
                .map(this::toIntNumber)
                .toList();
        return list;
    }

    private String[] splitNumber(Matcher matcher, String delimiter) {
        return matcher.group(2).split(delimiter, -1);
    }

    private void validator(String input) {
        isNull(input);
        isEmpty(input);
    }

    private void isNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값이 null일수는 없습니다.");
        }
    }

    private void isEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("빈값을 입력받을수는 없습니다.");
        }
    }

    private int toIntNumber(String input) {
        if (isNotNumber(input)) {
            throw new IllegalArgumentException("잘못된 형식입니다.");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("지원하지 않는 숫자입니다.");
        }
    }

    private static boolean isNotNumber(String input) {
        return !input.matches(NUMBER_REGEX);
    }

    private String addCustomDelimiter(String customDelimiter) {
        if (customDelimiter == null) {
            return DELIMITER;
        }
        return Pattern.quote(customDelimiter) + "|" + DELIMITER;
    }

}
