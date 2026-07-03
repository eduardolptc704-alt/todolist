package springboot.estudo.todolist.errors;


import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler
    public ResponseEntity<String> handleHttpMessageNotReadableException (HttpMessageNotReadableException exception) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMostSpecificCause().getMessage());
    }
}
