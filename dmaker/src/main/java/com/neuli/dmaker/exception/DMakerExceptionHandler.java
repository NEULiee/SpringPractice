package com.neuli.dmaker.exception;

import com.neuli.dmaker.dto.DMakerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.neuli.dmaker.exception.DMakerErrorCode.*;

/**
 *  @ControllerAdvice
 *  : @ExceptionalHandler, @ModelAttribute, @InitBinder 가 적용된 메소드들을
 *    AOP 를 적용해 컨트롤러 단에 적용하기 위해 고안된 annotation
 *
 *  @RestControllerAdvice
 *  : @ResponseBody + @ControllerAdvice
 *
 *  @RestControllerAdvice(annotations = RestController.class)
 *  annotations 속성에 컨트롤러 클래스를 인수로 주면 적용되는 범위를 해당 컨트롤러 클래스로 한정할 수 있다.
 *  아무런 값도 주지 않으면 전역적으로 작동
 */

@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {

    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handleException(DMakerException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage());

        return DMakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    // Controller 내부에 진입하기 전에 발생하는 exception
    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,   // PostMapping 에 Post 가 아닌 다른 요청을 했을 때
            MethodArgumentNotValidException.class           // @Valid 에서 문제가 발생했을 때
    })
    public DMakerErrorResponse handleRequest(Exception e, HttpServletRequest request) {

        log.error("url: {}, message: {}",
                request.getRequestURI(), e.getMessage());

        return DMakerErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    // 최후의 모르는 exception
    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}",
                request.getRequestURI(), e.getMessage());

        return DMakerErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
