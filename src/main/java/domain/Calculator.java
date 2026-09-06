package domain;

import java.util.List;

public class Calculator {
    public int sum(List<Integer> numbers) {
        int result = 0;
        for (int number : numbers) {
            result = add(result, number);
        }
        return result;
    }

    private int add(int result, int number) {
        try {
            return Math.addExact(result, number);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("합계가 지원 범위를 초과했습니다.", e);
        }
    }
}
