package az.innakhchivan.controller;

import az.innakhchivan.dto.request.NewsRequestDto;
import az.innakhchivan.dto.response.NewsResponseDto;
import az.innakhchivan.entity.News;
import az.innakhchivan.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<Void> addedNews(@Valid @RequestBody NewsRequestDto newsRequestDto) throws IOException {

        newsService.addNews(newsRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<NewsResponseDto>> getAllNews( @RequestParam(required = false, defaultValue = "az") String lang) {

        List<NewsResponseDto> newsList = newsService.getAllNews(lang);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<News>> getAll() {

        List<News> newsList = newsService.getAll();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatedNews(@PathVariable Long id, @Valid @RequestBody NewsRequestDto newsRequestDto) {
        newsService.updateNews(id, newsRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

