package lamph11.home.resource.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private Long id;
    private String name;
    private Integer status;
    private Long parentId;
}
