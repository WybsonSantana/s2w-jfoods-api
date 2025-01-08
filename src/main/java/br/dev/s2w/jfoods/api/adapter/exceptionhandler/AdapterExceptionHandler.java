package br.dev.s2w.jfoods.api.adapter.exceptionhandler;

import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdapterExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .message(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .message((String) body)
                    .build();
        }

        return super.handleExceptionInternal(e, body, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e, WebRequest request) {
        var body = e.getMessage();
        var headers = new HttpHeaders();
        var status = HttpStatus.BAD_REQUEST;

        return handleExceptionInternal(e, body, headers, status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException e, WebRequest request) {
        var body = e.getMessage();
        var headers = new HttpHeaders();
        var status = HttpStatus.CONFLICT;

        return handleExceptionInternal(e, body, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        var body = e.getMessage();
        var headers = new HttpHeaders();
        var status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(e, body, headers, status, request);
    }

}
