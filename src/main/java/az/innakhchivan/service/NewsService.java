package az.innakhchivan.service;

import az.innakhchivan.dto.request.NewsRequestDto;
import az.innakhchivan.dto.response.NewsResponseDto;
import az.innakhchivan.entity.News;
import az.innakhchivan.entity.PhotoGallery;
import az.innakhchivan.exception.NewsNotFoundException;
import az.innakhchivan.helper.ImageUtils;
import az.innakhchivan.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    News news = new News();

    public NewsResponseDto addNews(NewsRequestDto newsDto, String lang) throws IOException {

        switch (lang) {
            case "az":
                news.setAzTitle(newsDto.getTitle());
                news.setAzDescription(newsDto.getDescription());
                news.setAzAuthor(newsDto.getAuthor());
                break;
            case "en":
                news.setEnTitle(newsDto.getTitle());
                news.setEnDescription(newsDto.getDescription());
                news.setEnAuthor(newsDto.getAuthor());
                break;
            case "ru":
                news.setRuTitle(newsDto.getTitle());
                news.setRuDescription(newsDto.getDescription());
                news.setRuAuthor(newsDto.getAuthor());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);
        }

//        news.setImageName(imageFile.getOriginalFilename());
//        news.setImageType(imageFile.getContentType());
//        news.setImageData(ImageUtils.compressImage(imageFile.getBytes()));

        newsRepository.save(news);

        return NewsResponseDto.builder()
                .id(news.getId())
                .title(news.getNewsTitle(lang))
                .description(news.getNewsDescription(lang))
                .author(news.getNewsAuthor(lang))
                .created(news.getCreatedAt())
                .build();
    }


    public NewsResponseDto getNewsById(Long id, String lang) {
         news = newsRepository.findById(id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + id)
        );

        String title = news.getNewsTitle(lang);
        String description = news.getNewsDescription(lang);
        String author = news.getNewsAuthor(lang);

//        if (title == null || description == null || author == null) {
//            title = news.getNewsTitle("az");
//            description = news.getNewsDescription("az");
//            author = news.getNewsAuthor("az");
//        }

//        byte[] imageData = null;
//        if (news.getImageData() != null) {
//            try {
//                imageData = ImageUtils.decompressImage(news.getImageData());
//            } catch (DataFormatException | IOException e) {
//                throw new RuntimeException("Failed to decompress image for news ID: " + id, e);
//            }

        return NewsResponseDto.builder()
                .id(news.getId())
                .title(title)
                .description(description)
                .author(author)
                .created(news.getCreatedAt())
                .build();
    }

    public NewsResponseDto updateNews(Long id, NewsRequestDto newsDto, String lang) {
           news = newsRepository.findById(id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + id)
        );

        switch (lang) {
            case "az":
                news.setAzTitle(newsDto.getTitle());
                news.setAzDescription(newsDto.getDescription());
                news.setAzAuthor(newsDto.getAuthor());
                break;
            case "en":
                news.setEnTitle(newsDto.getTitle());
                news.setEnDescription(newsDto.getDescription());
                news.setEnAuthor(newsDto.getAuthor());
                break;
            case "ru":
                news.setRuTitle(newsDto.getTitle());
                news.setRuDescription(newsDto.getDescription());
                news.setRuAuthor(newsDto.getAuthor());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);
        }


        newsRepository.save(news);

        String title = news.getNewsTitle(lang);
        String description = news.getNewsDescription(lang);
        String author = news.getNewsAuthor(lang);

        return NewsResponseDto.builder()
                .id(news.getId())
                .title(title)
                .description(description)
                .author(author)
                .created(news.getCreatedAt())
                .build();
    }

    public List<NewsResponseDto> getAllNews(String lang) {
        return newsRepository.findAll().stream()
                .map(news -> {
                    String title = news.getNewsTitle(lang);
                    String description = news.getNewsDescription(lang);
                    String author = news.getNewsAuthor(lang);

                    return NewsResponseDto.builder()
                            .id(news.getId())
                            .title(title)
                            .description(description)
                            .author(author)
                            .created(news.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }



    public void deleteNews(Long Id) {
        News news = newsRepository.findById(Id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + Id)
        );

        newsRepository.delete(news);
    }

    public void deleteNewsForLang(Long id, String lang) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new NewsNotFoundException("News not found with id: " + id)
        );

        switch (lang) {
            case "az":
                news.setAzTitle(null);
                news.setAzDescription(null);
                news.setAzAuthor(null);
                break;
            case "en":
                news.setEnTitle(null);
                news.setEnDescription(null);
                news.setEnAuthor(null);
                break;
            case "ru":
                news.setRuTitle(null);
                news.setRuDescription(null);
                news.setRuAuthor(null);
                break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + lang);
        }

        if (news.getAzTitle() == null && news.getEnTitle() == null && news.getRuTitle() == null) {
            newsRepository.delete(news);
        } else {
            newsRepository.save(news);
        }
    }

}
