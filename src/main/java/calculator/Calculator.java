package calculator;

import java.util.List;

public class Calculator {
    public long getSum(List<Integer> numbers){
        long sum = 0L;
        for(int number : numbers){
            if(sum > sum+number){
                throw new IllegalArgumentException("지원하는 합계의 범위를 넘어섰습니다.");
            }
            sum += number;
        }
        return sum;
    }

}
