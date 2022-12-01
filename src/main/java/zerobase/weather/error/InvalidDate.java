package zerobase.weather.error;


// 커스텀 예외 클래스
public class InvalidDate extends RuntimeException{

    private static final String MESSAGE = "정의되지 않는 날짜 입니다.";

    public InvalidDate() {
        super(MESSAGE);
    }
}
