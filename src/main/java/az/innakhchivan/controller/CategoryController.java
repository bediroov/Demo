package az.innakhchivan.controller;
import az.innakhchivan.dto.request.CategoryRequestdDto;
import az.innakhchivan.dto.response.CategoryResponseDto;
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
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestdDto categoryRequest) {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<CategoryResponseDto> responseDtoList = categoryService.getAllCategory(lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }


}
