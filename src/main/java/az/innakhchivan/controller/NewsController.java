package az.innakhchivan.controller;

import az.innakhchivan.dto.request.NewsRequestDto;
import az.innakhchivan.dto.response.NewsResponseDto;
import az.innakhchivan.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsResponseDto> addedNews(@Valid @RequestBody NewsRequestDto newsRequestDto,
                                                     @RequestParam(required = false, defaultValue = "az") String lang) throws IOException {

        NewsResponseDto responseDto = newsService.addNews(newsRequestDto, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long id,
                                                       @RequestParam(defaultValue = "az") String lang) {
        NewsResponseDto responseDto = newsService.getNewsById(id, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<NewsResponseDto>> getAllNews( @RequestParam(defaultValue = "az") String lang) {

        List<NewsResponseDto> newsList = newsService.getAllNews(lang);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDto> updatedNews(@PathVariable Long id, @Valid @RequestBody NewsRequestDto newsRequestDto,
                                                       @RequestParam(defaultValue = "az") String lang ) {
        NewsResponseDto responseDto = newsService.updateNews(id, newsRequestDto, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/lang/{id}")
    public ResponseEntity<Void> deletedNewsForLanguage(@PathVariable Long id, @RequestParam String lang) {
        newsService.deleteNewsForLang(id, lang);
        return ResponseEntity.noContent().build();
    }
}

