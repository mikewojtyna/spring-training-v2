package pl.wojtyna.trainings.spring.crowdsorcery.exceptionhandling;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class TopLevelExceptionHandler {

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<GenericErrorResponse> handleOptimisticLockingExceptions(OptimisticLockingFailureException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(new GenericErrorResponse(HttpStatus.CONFLICT.getReasonPhrase(),
                                                            exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getFieldErrors()
                                   .stream()
                                   .map(fieldError -> Map.entry(fieldError.getField(),
                                                                fieldError.getDefaultMessage() == null ? "" : fieldError.getDefaultMessage()))
                                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return ResponseEntity.badRequest()
                             .body(new ValidationErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                                               exception.getMessage(),
                                                               fieldErrors));
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<GenericErrorResponse> handleGenericException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new GenericErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                            exception.getMessage()));
    }

    private record GenericErrorResponse(String status, String reason) {}

    private record ValidationErrorResponse(String status, String reason, Map<String, String> errors) {}
}
