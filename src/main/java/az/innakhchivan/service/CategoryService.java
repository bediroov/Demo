package az.innakhchivan.service;

import az.innakhchivan.dto.request.CategoryRequestdDto;
import az.innakhchivan.dto.response.CategoryResponseDto;
import az.innakhchivan.entity.Category;
import az.innakhchivan.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponseDto createCategory(CategoryRequestdDto categoryRequest) {
        Category category = new Category();
        category.setAzName(categoryRequest.getAzName());
        category.setEnName(categoryRequest.getEnName());
        category.setRuName(categoryRequest.getRuName());
        categoryRepository.save(category);

        return CategoryResponseDto.builder()
                .id(category.getId())
                .build();
    }

    public List<CategoryResponseDto> getAllCategory(String lang) {
        return categoryRepository.findAll().stream()
                .map(x -> new CategoryResponseDto(
                        x.getId(),
                        x.getCategoryName(lang)
                ))
                .collect(Collectors.toList());
    }
}
