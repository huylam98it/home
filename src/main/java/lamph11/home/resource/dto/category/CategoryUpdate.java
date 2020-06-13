package lamph11.home.resource.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdate {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Integer status;
    private Long parentId;
}
