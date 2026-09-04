package domain;

public enum DelimiterPattern {
    CUSTOM_DELIMITER_PATTERN("//(.+)\\\\n(.*)"),
    DELIMITER_PATTERN("^\\d+((?:%s)\\d+)*$");
    String regex;

    DelimiterPattern(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
