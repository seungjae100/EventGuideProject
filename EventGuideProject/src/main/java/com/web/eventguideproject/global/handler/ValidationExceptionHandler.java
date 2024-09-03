package com.web.eventguideproject.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice  // 모든 컨트롤러에서 발생하는 예외를 처리할 수 있도록 설정
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)  // 특정 예외 타입을 처리할 메서드를 지정
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();  // 필드 이름과 오류 메시지를 저장할 맵 생성
        ex.getBindingResult().getAllErrors().forEach((error) -> {  // 모든 오류를 순회하면서
            String fieldName = ((FieldError) error).getField();  // 오류가 발생한 필드 이름 추출
            String errorMessage = error.getDefaultMessage();  // 해당 필드의 오류 메시지 추출
            errors.put(fieldName, errorMessage);  // 필드 이름과 오류 메시지를 맵에 저장
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);  // 오류 맵과 함께 400 상태 코드로 응답 반환
    }
}