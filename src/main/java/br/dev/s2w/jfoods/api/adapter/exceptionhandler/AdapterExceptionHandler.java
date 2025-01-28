package br.dev.s2w.jfoods.api.adapter.exceptionhandler;

import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdapterExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public static final String GENERIC_USER_MESSAGE = "An unexpected internal system error has occurred. " +
            "Please try again and if the problem persists, contact your system administrator.";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(GENERIC_USER_MESSAGE)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(GENERIC_USER_MESSAGE)
                    .build();
        }

        return super.handleExceptionInternal(e, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = "The request payload is invalid. Check syntax error!";

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e,
                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (e instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) e, headers, status, request);
        }

        return super.handleTypeMismatch(e, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = String.format("The resource '%s', which you tried to access, is non-existent.", e.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more fields are invalid. Fill in correctly and try again!";

        BindingResult bindingResult = e.getBindingResult();

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.BUSINESS_ERROR;
        String detail = e.getMessage();
        HttpHeaders headers = new HttpHeaders();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUse(EntityInUseException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = e.getMessage();
        HttpHeaders headers = new HttpHeaders();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = e.getMessage();
        HttpHeaders headers = new HttpHeaders();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        HttpHeaders headers = new HttpHeaders();
        String detail = GENERIC_USER_MESSAGE;

        e.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException e, HttpHeaders headers,
                                                       HttpStatus status, WebRequest request) {
        String path = joinPath(e.getPath());
        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = String.format("Property '%s' has been assigned the value '%s', " +
                        "which is of an invalid type. Correct and enter a value compatible with type %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException e, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {
        String path = joinPath(e.getPath());
        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = String.format("Property '%s' does not exist. " +
                "Please correct or remove this property and try again.", path);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpHeaders headers,
                                                                    HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.INVALID_PARAMETER;
        String detail = String.format("The URL parameter '%s' was assigned the value '%s', " +
                        "which is of an invalid type. Please correct and enter a value compatible with the type %s.",
                e.getName(), e.getValue(), e.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_USER_MESSAGE)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
    }
}
