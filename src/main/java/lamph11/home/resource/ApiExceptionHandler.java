package lamph11.home.resource;

import lamph11.home.common.ExceptionBuilder;
import lamph11.home.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ApiResponse<?> handlerException(Throwable t){
        return ExceptionBuilder.generateException(t);
    }
}
