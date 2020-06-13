package lamph11.home.resource.dto.category;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryAdd {

    @NotBlank
    private String name;
    @Range(min = 1)
    private Long parent;
}
