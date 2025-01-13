package az.innakhchivan.service;

import az.innakhchivan.dto.request.NewsRequestDto;
import az.innakhchivan.dto.response.NewsResponseDto;
import az.innakhchivan.entity.News;
import az.innakhchivan.exception.NewsNotFoundException;
import az.innakhchivan.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public void addNews(NewsRequestDto newsDto) throws IOException {

        News news = new News();
        news.setAzTitle(newsDto.getAzTitle());
        news.setAzDescription(newsDto.getAzDescription());
        news.setEnDescription(newsDto.getEnDescription());
        news.setEnTitle(newsDto.getEnTitle());
        news.setRuTitle(newsDto.getRuTitle());
        news.setRuDescription(newsDto.getRuDescription());
        news.setOptional(newsDto.getOptional());
        news.setImageUrl(newsDto.getImageUrl());

        newsRepository.save(news);
    }


    public void updateNews(Long id, NewsRequestDto newsDto) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + id)
        );

        news.setAzTitle(newsDto.getAzTitle());
        news.setAzDescription(newsDto.getAzDescription());
        news.setEnDescription(newsDto.getEnDescription());
        news.setEnTitle(newsDto.getEnTitle());
        news.setRuTitle(newsDto.getRuTitle());
        news.setRuDescription(newsDto.getRuDescription());
        news.setOptional(newsDto.getOptional());
        news.setImageUrl(newsDto.getImageUrl());


        newsRepository.save(news);
    }

    public List<NewsResponseDto> getAllNews(String lang) {
        return newsRepository.findAllByOrderByIdAsc().stream()
                .map(news -> new NewsResponseDto(
                        news.getId(),
                        news.getNewsTitle(lang),
                        news.getNewsDescription(lang),
                        news.getCreatedAt(),
                        news.getImageUrl(),
                        news.getOptional())
                )
                .collect(Collectors.toList());
    }


    public List<News> getAll() {
        return newsRepository.findAllByOrderByIdAsc().stream()
                .map(news -> new News(
                        news.getId(),
                        news.getAzTitle(),
                        news.getAzDescription(),
                        news.getEnTitle(),
                        news.getEnDescription(),
                        news.getRuTitle(),
                        news.getRuDescription(),
                        news.getImageUrl(),
                        news.getOptional(),
                        news.getCreatedAt(),
                        news.getUpdatedAt()
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
