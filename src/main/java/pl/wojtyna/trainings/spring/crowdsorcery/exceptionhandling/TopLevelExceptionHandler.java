package pl.wojtyna.trainings.spring.crowdsorcery.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TopLevelExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<GenericErrorResponse> controllerExceptionHandler(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new GenericErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                            exception.getMessage()));
    }

    private record GenericErrorResponse(String status, String reason) {}
}
