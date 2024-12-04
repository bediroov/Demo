package az.innakhchivan.service;

import az.innakhchivan.dto.request.NewsRequestDto;
import az.innakhchivan.dto.response.NewsResponseDto;
import az.innakhchivan.entity.NewTranslation;
import az.innakhchivan.entity.News;
import az.innakhchivan.exception.NewsNotFoundException;
import az.innakhchivan.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsResponseDto addNews(NewsRequestDto newsDto) {
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setAuthor(newsDto.getAuthor());
        newsRepository.save(news);

        return NewsResponseDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .author(news.getAuthor())
                .created(new Date())
                .build();
    }

    public NewsResponseDto updateNews(Long Id, NewsRequestDto newsDto) {
        News news = newsRepository.findById(Id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + Id)
        );
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setAuthor(newsDto.getAuthor());
        newsRepository.save(news);

        return NewsResponseDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .author(news.getAuthor())
                .created(new Date())
                .build();
    }

    public NewsResponseDto getNewsById(Long Id) {
        News news = newsRepository.findById(Id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + Id)
        );

        NewTranslation translation = news.getNewTranslation();

        if (translation == null || !translation.getLanguageCode().equals(translation.getLanguageCode())) {
            throw new NewsNotFoundException("Translation not found for language: " + translation.getLanguageCode());
        }

        return NewsResponseDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .author(news.getAuthor())
                .created(new Date())
                .build();

    }

    public List<NewsResponseDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(x -> new NewsResponseDto(
                        x.getId(),
                        x.getTitle(),
                        x.getDescription(),
                        x.getAuthor(),
                        x.getCreatedAt()
                ))
                .collect(Collectors.toList());

    }

    public void deleteNews(Long Id) {
        News news = newsRepository.findById(Id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + Id)
        );
        newsRepository.delete(news);
    }
}
