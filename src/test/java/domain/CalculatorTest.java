package domain;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void 숫자를_더한다() {
        assertThat(calculator.sum(List.of(1, 2, 3))).isEqualTo(6);
    }

    @Test
    void 빈_목록의_합은_영이다() {
        assertThat(calculator.sum(List.of())).isZero();
    }

    @Test
    void 영을_더할_수_있다() {
        assertThat(calculator.sum(List.of(0, 1, 0))).isEqualTo(1);
    }

    @Test
    void int_최댓값까지_더할_수_있다() {
        assertThat(calculator.sum(List.of(Integer.MAX_VALUE - 1, 1)))
                .isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void 합계_범위_초과는_예외가_발생한다() {
        assertThatThrownBy(() -> calculator.sum(List.of(Integer.MAX_VALUE, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("합계가 지원 범위를 초과했습니다.");
    }

    @Test
    void 다시_계산해도_이전_합계가_남지_않는다() {
        assertThat(calculator.sum(List.of(1, 2))).isEqualTo(3);
        assertThat(calculator.sum(List.of(4, 5))).isEqualTo(9);
    }
}
