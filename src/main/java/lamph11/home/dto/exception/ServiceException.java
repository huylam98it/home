package lamph11.home.dto.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceException {

    private String message;
    private Object payload;
}
