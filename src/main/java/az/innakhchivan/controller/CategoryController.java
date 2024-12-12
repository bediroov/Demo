package az.innakhchivan.controller;

import az.innakhchivan.dto.request.CategoryRequestdDto;
import az.innakhchivan.dto.response.CategoryResponse;
import az.innakhchivan.dto.response.CategoryResponseDto;
import az.innakhchivan.entity.Category;
import az.innakhchivan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequestdDto categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedCategory(@PathVariable Long id, @RequestBody CategoryRequestdDto categoryRequest) {
        categoryService.updateCategory(id, categoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<CategoryResponseDto> responseDtoList = categoryService.getAllCategory(lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> responseDtoList = categoryService.getAll();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }
}
