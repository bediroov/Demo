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

    public NewsResponseDto addNews(NewsRequestDto newsDto) throws IOException {

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

        return NewsResponseDto.builder()
                .id(news.getId())
                .build();
    }


    public String updateNews(Long id, NewsRequestDto newsDto) {
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


        return "News updated successfully ";
    }

    public List<NewsResponseDto> getAllNews(String lang) {
        return newsRepository.findAll().stream()
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

    public void deleteNews(Long Id) {
        News news = newsRepository.findById(Id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + Id)
        );

        newsRepository.delete(news);
    }

}
