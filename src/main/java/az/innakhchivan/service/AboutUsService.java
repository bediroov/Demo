package az.innakhchivan.service;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.entity.AboutUs;
import az.innakhchivan.exception.AboutUsNotFoundException;
import az.innakhchivan.repository.AboutUsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AboutUsService {
    private final AboutUsRepository aboutUsRepository;

    AboutUs about = new AboutUs();

    public AboutResponseDto createAbout(AboutRequestDto aboutRequestDto, String lang) {

        switch (lang) {
            case "az":
                about.setAzTitle(aboutRequestDto.getTitle());
                about.setAzDescription(aboutRequestDto.getDescription());
                break;
            case "en":
                about.setEnTitle(aboutRequestDto.getTitle());
                about.setEnDescription(aboutRequestDto.getDescription());
                break;
            case "ru":
                about.setRuTitle(aboutRequestDto.getTitle());
                about.setRuDescription(aboutRequestDto.getDescription());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);

        }

        aboutUsRepository.save(about);

        return new AboutResponseDto(
                about.getId(),
                about.getAboutUsTitle(lang),
                about.getAboutUsDescription(lang)
        );
    }

    public AboutResponseDto updateAbout(Long id, AboutRequestDto aboutRequestDto, String lang) {
        about = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));

        switch (lang) {
            case "az":
                about.setAzTitle(aboutRequestDto.getTitle());
                about.setAzDescription(aboutRequestDto.getDescription());
                break;
            case "en":
                about.setEnTitle(aboutRequestDto.getTitle());
                about.setEnDescription(aboutRequestDto.getDescription());
                break;
            case "ru":
                about.setRuTitle(aboutRequestDto.getTitle());
                about.setRuDescription(aboutRequestDto.getDescription());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);

        }

        aboutUsRepository.save(about);

        return new AboutResponseDto(
                about.getId(),
                about.getAboutUsTitle(lang),
                about.getAboutUsDescription(lang)
        );
    }

    public AboutResponseDto getAboutById(Long id, String lang) {
        about = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));


        return new AboutResponseDto(
                about.getId(),
                about.getAboutUsTitle(lang),
                about.getAboutUsDescription(lang)
        );
    }

    public List<AboutResponseDto> getAboutAll(String lang) {
        return aboutUsRepository.findAll().stream()
                .map(aboutUs -> new AboutResponseDto(
                        aboutUs.getId(),
                        aboutUs.getAboutUsTitle(lang),
                        aboutUs.getAboutUsDescription(lang)
                ))
                .collect(Collectors.toList());
    }

    public void deletedAbout(Long id) {
        about = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));

        aboutUsRepository.delete(about);
    }
}
