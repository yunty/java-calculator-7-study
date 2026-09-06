# 문자열 계산기 리팩터링

## 실행 흐름

`Application`에서 객체를 생성하여 `Controller`에 주입한다.

1. `InputView.readInput()`이 원본 문자열을 읽는다.
2. `InputParser.parse(rawInput)`이 구분자를 추출하고 숫자 본문을 분리한다.
3. 파서는 각 토큰의 형식과 int 범위를 검사하여 `List<Integer>`를 반환한다.
4. `Calculator.sum(numbers)`가 숫자를 더한다.
5. `OutputView.printResult(result)`가 결과를 출력한다.

## 클래스별 책임

| 클래스 | 책임 |
| --- | --- |
| Application | 객체 생성과 연결 |
| Controller | 입력 → 파싱 → 계산 → 출력 순서 진행 |
| InputView | 콘솔 입력 |
| InputParser | 입력 문법 해석, 검증, 숫자 변환 |
| Calculator | 합산과 합계 범위 검사 |
| OutputView | 출력 문구와 형식 관리 |

파서와 계산기는 입력마다 달라지는 값을 필드에 보관하지 않는다.
파서의 내부 메서드는 private이며 공개된 parse()를 통해 테스트한다.
Controller의 의존 객체는 생성자로 주입하고 final로 보관한다.

## 기존 코드에서 바뀐 부분

- InputParse → InputParser: 상태를 담은 객체 대신 숫자 목록을 반환한다.
- 사용자가 추가했던 parseNumber()의 검증과 예외 메시지를 실제 변환 과정에 연결했다.
- DelimiterSum → Calculator: 문자열이나 구분자를 알 필요가 없다.
- DelimiterPattern enum은 없애고 파서에서 사용하는 private static final Pattern으로 옮겼다.
- OutputView의 문구는 private static final 상수로 관리한다.
- Java 소스 컴파일과 테스트 실행의 인코딩을 UTF-8로 지정했다.

## 입력 정책

- 빈 원본 문자열의 결과는 0이다. null은 잘못된 입력이다.
- 기본 구분자는 쉼표와 콜론이며 커스텀 구분자와 함께 사용할 수 있다.
- 커스텀 선언은 `//구분자\n숫자본문`이다. 여기서 `\n`은 실제 줄바꿈이 아닌 두 문자이다.
- 기존의 여러 글자 구분자 허용을 유지한다. 구분자에 정규식 기호가 있어도 문자 그대로 처리한다.
- 여러 글자 구분자와 기본 구분자가 겹치면 커스텀 구분자를 먼저 적용한다.
- 0과 앞자리 0을 허용한다. 공백을 자동으로 제거하지 않는다.
- 커스텀 선언 뒤의 빈 본문, 연속 구분자, 앞뒤 구분자, 음수, 숫자 이외의 토큰은 예외다.
- 각 숫자와 합계는 int 범위를 사용하며 범위를 넘으면 IllegalArgumentException이다.
- 과제에서 구분자 길이나 숫자 범위를 다르게 지정한다면 해당 정책과 테스트를 함께 변경한다.

선택적인 커스텀 선언 정규식은 숫자 본문까지 검증하지 않는다.
잘못된 선언이 일반 본문으로 남더라도 이후 숫자 토큰 검사에서 예외가 발생한다.
split(regex, -1)은 뒤의 빈 토큰까지 남겨 마지막 구분자를 발견하게 해 준다.

## 테스트 추가하기

- InputParserTest: 문자열을 넣고 containsExactly()로 숫자 목록을 확인한다.
- CalculatorTest: 숫자 목록을 넣고 isEqualTo()로 합계를 확인한다.
- ApplicationTest: run()으로 콘솔 입력을 전달하고 output()으로 출력 결과를 확인한다.
- 예외는 assertThatThrownBy(...).isInstanceOf(IllegalArgumentException.class)로 확인한다.
- 여러 잘못된 입력을 같은 방식으로 검사하려면 @ValueSource에 입력을 추가한다.

```powershell
.\gradlew.bat test
.\gradlew.bat test --tests "domain.InputParserTest"
.\gradlew.bat test --tests "domain.CalculatorTest"
.\gradlew.bat test --tests "calculator.ApplicationTest"
```

NsTest의 run("")은 빈 입력 스트림이므로 빈 줄 입력은 run("\n")으로 시뮬레이션한다.
테스트 보고서는 build/reports/tests/test/index.html에서 확인할 수 있다.
