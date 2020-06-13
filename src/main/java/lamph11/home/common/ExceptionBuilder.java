package lamph11.home.common;

import lamph11.home.dto.ApiResponse;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class ExceptionBuilder {


    public static ApiResponse<?> generateException(Throwable throwable) {
        throwable.printStackTrace();
        Throwable t = throwable.getCause();
        if (Objects.nonNull(t)) {
            return generateException(t);
        }

        ApiResponse response = new ApiResponse();
        response.setStatusCode(400);

        if (throwable instanceof MethodArgumentNotValidException) {
            Map<String, String> payload = new HashMap<>();
            ((MethodArgumentNotValidException) throwable).getBindingResult().getFieldErrors().forEach(
                    fieldError -> {
                        payload.put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
            );
            response.setBody(payload);
        } else if (throwable instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = ((MissingServletRequestParameterException) throwable);
            response.setMessage(e.getMessage());
            Map<String, String> payload = new HashMap<>();
            payload.put(e.getParameterName(), "not found with type " + e.getParameterType());
            response.setBody(payload);
        } else {
            response.setMessage("Unknow Exception");
        }

        return response;
    }
}
