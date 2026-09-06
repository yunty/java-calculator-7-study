package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {
    private final InputParser parser = new InputParser();

    @Test
    void 기본_구분자로_숫자_목록을_만든다() {
        assertThat(parser.parse("1,2:3")).containsExactly(1, 2, 3);
    }

    @Test
    void 커스텀과_기본_구분자를_혼합한다() {
        assertThat(parser.parse("//;\\n1;2,3:4")).containsExactly(1, 2, 3, 4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", ".", "+", "|", "[", "\\", ";;", ",;"})
    void 커스텀_구분자는_문자_그대로_사용한다(String delimiter) {
        String input = "//" + delimiter + "\\n1" + delimiter + "2";
        assertThat(parser.parse(input)).containsExactly(1, 2);
    }

    @Test
    void 빈_입력은_빈_목록이다() {
        assertThat(parser.parse("")).isEmpty();
    }

    @Test
    void 영과_앞자리_영을_허용한다() {
        assertThat(parser.parse("0,00,01")).containsExactly(0, 0, 1);
    }

    @Test
    void int_최댓값을_허용한다() {
        assertThat(parser.parse("2147483647")).containsExactly(Integer.MAX_VALUE);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,a,3", "-1,2", "+1", "1, 2", "1,,2", ",1", "1,",
            "//;1;2", "//\\n1,2", "//;\\n", "//;\\n1;;2", "//;\\n1;",
            "1.2", "１,2", "1\n2", "//;\\n//@\\n1@2"
    })
    void 잘못된_입력은_예외가_발생한다(String input) {
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void null_입력은_예외가_발생한다() {
        assertThatThrownBy(() -> parser.parse(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2147483648", "999999999999999999999"})
    void 숫자_범위_초과를_설명한다(String input) {
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 숫자가 지원 범위를 초과했습니다.");
    }

    @Test
    void 같은_파서를_재사용해도_이전_구분자가_남지_않는다() {
        assertThat(parser.parse("//;\\n1;2")).containsExactly(1, 2);
        assertThat(parser.parse("3,4")).containsExactly(3, 4);
        assertThatThrownBy(() -> parser.parse("3;4"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
