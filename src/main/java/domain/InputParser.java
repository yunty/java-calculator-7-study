package domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static final String DEFAULT_DELIMITER = "[,:]";
    // group(1): 선택적인 커스텀 구분자, group(2): 숫자 본문
    // \\n은 실제 줄바꿈이 아니라 사용자가 입력한 역슬래시와 n이다.
    private static final Pattern INPUT_PATTERN = Pattern.compile("^(?://(.+?)\\\\n)?(.*)$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");

    public List<Integer> parse(String rawInput) {
        if (rawInput == null) {
            throw new IllegalArgumentException("입력은 null일 수 없습니다.");
        }
        if (rawInput.isEmpty()) {
            return List.of();
        }

        Matcher matcher = INPUT_PATTERN.matcher(rawInput);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }

        String delimiterRegex = createDelimiterRegex(matcher.group(1));
        String numberText = matcher.group(2);
        return Arrays.stream(numberText.split(delimiterRegex, -1))
                .map(this::parseNumber)
                .toList();
    }

    private String createDelimiterRegex(String customDelimiter) {
        if (customDelimiter == null) {
            return DEFAULT_DELIMITER;
        }
        return Pattern.quote(customDelimiter) + "|" + DEFAULT_DELIMITER;
    }

    private int parseNumber(String token) {
        if (!NUMBER_PATTERN.matcher(token).matches()) {
            throw new IllegalArgumentException(
                    "숫자는 비어 있지 않은 숫자 문자열이어야 합니다."
            );
        }
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 숫자가 지원 범위를 초과했습니다.", e);
        }
    }
}
