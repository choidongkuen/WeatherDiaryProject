package zerobase.weather.config;

// 컨트롤러단에서 예외를 잡는 기능과 관련된 어노테이션이 @ExceptionHandler라면,
// 모든 컨트롤러로부터 나오는 예외처리와 관련된 어노테이션은 @ControllerAdvice or @RestControllerAdvice 이다.

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 어떤 http 메소드를 반환할 건지(서버 문제)
    @ExceptionHandler(Exception.class)
    public Exception handlerException(){
        System.out.println("error from GlobalExceptionHandler");
        return new Exception();
    }
}
