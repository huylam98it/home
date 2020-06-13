package lamph11.home.service;

import lamph11.home.entity.Category;
import lamph11.home.repository.CategoryRepository;
import lamph11.home.resource.dto.category.CategoryAdd;
import lamph11.home.resource.dto.category.CategoryDto;
import lamph11.home.resource.dto.category.CategoryUpdate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryDto create(CategoryAdd categoryAdd){
        Category category = Category.builder()
                .name(categoryAdd.getName())
                .status(1).build();

        Category parent = null;
        if(!StringUtils.isEmpty(categoryAdd.getParent())){
            parent = categoryRepository.findById(categoryAdd.getParent()).orElse(null);
        }

        category.setParent(parent);
        return Optional.of(
                categoryRepository.save(category)
        ).map(mapper).get();
    }


    public CategoryDto update(CategoryUpdate categoryUpdate){
        Category category = categoryRepository.findById(categoryUpdate.getId())
                .orElse(null);

        if(StringUtils.isEmpty(category))
            throw new RuntimeException("category not found");

        category = Category.builder()
                .id(categoryUpdate.getId())
                .name(categoryUpdate.getName())
                .status(categoryUpdate.getStatus())
                .build();

        Category parent = null;
        if(!StringUtils.isEmpty(categoryUpdate.getParentId())){
            parent = categoryRepository.findById(categoryUpdate.getParentId()).orElse(null);
        }
        category.setParent(parent);

        return Optional.of(
                categoryRepository.save(category)
        ).map(mapper).get();
    }




    public List<CategoryDto> getAll(){
        return categoryRepository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }








    public static Function<Category, CategoryDto> mapper = category->{
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus())
                .parentId(category.getParent().getId())
                .build();
    };
}
