package lamph11.home.common;

import lamph11.home.dto.ApiResponse;
import lombok.Data;

@Data
public class ExceptionBuilder {

    public static ApiResponse<?> generateException(Exception e){
        ApiResponse response = new ApiResponse();
        return response;
    }
}
