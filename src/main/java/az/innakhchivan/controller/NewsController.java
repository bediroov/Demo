package az.innakhchivan.controller;

import az.innakhchivan.dto.request.NewsRequestDto;
import az.innakhchivan.dto.response.NewsResponseDto;
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
    public ResponseEntity<NewsResponseDto> addedNews(@Valid @RequestBody NewsRequestDto newsRequestDto) throws IOException {

        NewsResponseDto responseDto = newsService.addNews(newsRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<NewsResponseDto>> getAllNews( @RequestParam(required = false, defaultValue = "az") String lang) {

        List<NewsResponseDto> newsList = newsService.getAllNews(lang);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedNews(@PathVariable Long id, @Valid @RequestBody NewsRequestDto newsRequestDto) {
        String responseDto = newsService.updateNews(id, newsRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

