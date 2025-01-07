package br.dev.s2w.jfoods.api.adapter.exceptionhandler;

import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdapterExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusinessException(BusinessException e) {
        Problem problem = Problem.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerEntityNotFoundException(EntityNotFoundException e) {
        Problem problem = Problem.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handlerHttpMediaTypeNotSupportedException() {
        Problem problem = Problem.builder()
                .timestamp(LocalDateTime.now())
                .message("Unsupported media type")
                .build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problem);
    }

}
