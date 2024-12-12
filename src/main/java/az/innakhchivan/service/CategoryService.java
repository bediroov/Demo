package az.innakhchivan.service;

import az.innakhchivan.dto.request.CategoryRequestdDto;
import az.innakhchivan.dto.response.CategoryResponse;
import az.innakhchivan.dto.response.CategoryResponseDto;
import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import az.innakhchivan.entity.Category;
import az.innakhchivan.entity.Project;
import az.innakhchivan.entity.Sector;
import az.innakhchivan.exception.CategoryNotFoundException;
import az.innakhchivan.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequestdDto categoryRequest) {
        Category category = new Category();
        category.setAzName(categoryRequest.getAzName());
        category.setEnName(categoryRequest.getEnName());
        category.setRuName(categoryRequest.getRuName());
        categoryRepository.save(category);
    }


    public void updateCategory(Long id,  CategoryRequestdDto categoryRequest) {
        Category category =categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not found with id: " + id)
        );
        category.setAzName(categoryRequest.getAzName());
        category.setEnName(categoryRequest.getEnName());
        category.setRuName(categoryRequest.getRuName());
        categoryRepository.save(category);
    }

    public List<CategoryResponseDto> getAllCategory(String lang) {
        return categoryRepository.findAll().stream()
                .map(x -> new CategoryResponseDto(
                        x.getId(),
                        x.getCategoryName(lang)
                ))
                .collect(Collectors.toList());
    }


    @Transactional
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getAzName(),
                        category.getEnName(),
                        category.getRuName(),
                        category.getEntrepreneurs() != null
                                ? category.getEntrepreneurs().stream().map(BecomingAnEntrepreneurInNakhinvest::getId).toList()
                                : List.of(),
                        category.getProjects() != null
                                ? category.getProjects().stream().map(Project::getId).toList()
                                : List.of(),
                        category.getSector() != null
                                ? category.getSector().stream().map(Sector::getId).toList()
                                : List.of()
                ))
                .collect(Collectors.toList());
    }


    public void deleteCategory(Long id) {
        Category category =categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not found with id: " + id));

                categoryRepository.delete(category);
    }







//    public List<Category> getAll() {
//        return categoryRepository.findAllWithRelations().stream()
//                .map(category -> new Category(
//                        category.getId(),
//                        category.getAzName(),
//                        category.getEnName(),
//                        category.getRuName(),
//                        category.getEntrepreneurs() != null ? category.getEntrepreneurs().stream().toList() : List.of(),
//                        category.getProjects() != null ? category.getProjects().stream().toList() : List.of(),
//                        category.getSector() != null ? category.getSector().stream().toList() : List.of()
//                ))
//                .collect(Collectors.toList());
//
//    }

//    public List<Category> getAll() {
//        return categoryRepository.findAll().stream()
//                .map(category -> {
//                    try {
//                        Hibernate.initialize(category.getEntrepreneurs());
//                        Hibernate.initialize(category.getProjects());
//                        Hibernate.initialize(category.getSector());
//
//                        return new Category(
//                                category.getId(),
//                                category.getAzName(),
//                                category.getEnName(),
//                                category.getRuName(),
//                                category.getEntrepreneurs() != null ? category.getEntrepreneurs().stream().toList() : List.of(),
//                                category.getProjects() != null ? category.getProjects().stream().toList() : List.of(),
//                                category.getSector() != null ? category.getSector().stream().toList() : List.of()
//                        );
//                    } catch (Exception e) {
//                        throw new RuntimeException("Error while fetching category data: " + e.getMessage(), e);
//                    }
//                })
//                .collect(Collectors.toList());
//    }


}
