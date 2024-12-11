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


    public AboutResponseDto createAbout(AboutRequestDto aboutRequestDto) {

        AboutUs about = new AboutUs();

        about.setAzTitle(aboutRequestDto.getAzTitle());
        about.setAzDescription(aboutRequestDto.getAzDescription());
        about.setEnTitle(aboutRequestDto.getEnTitle());
        about.setEnDescription(aboutRequestDto.getEnDescription());
        about.setRuTitle(aboutRequestDto.getRuTitle());
        about.setRuDescription(aboutRequestDto.getRuDescription());
        about.setImageUrl(aboutRequestDto.getImageUrl());

        aboutUsRepository.save(about);

        return AboutResponseDto.builder()
                .id(about.getId())
                .build();
    }

    public String updateAbout(Long id, AboutRequestDto aboutRequestDto) {
        AboutUs about = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));

        about.setAzTitle(aboutRequestDto.getAzTitle());
        about.setAzDescription(aboutRequestDto.getAzDescription());
        about.setEnTitle(aboutRequestDto.getEnTitle());
        about.setEnDescription(aboutRequestDto.getEnDescription());
        about.setRuTitle(aboutRequestDto.getRuTitle());
        about.setRuDescription(aboutRequestDto.getRuDescription());
        about.setImageUrl(aboutRequestDto.getImageUrl());

        aboutUsRepository.save(about);

        return "About us updated successfully";
    }

    public List<AboutResponseDto> getAbout(String lang) {
        return aboutUsRepository.findAll().stream()
                .map(aboutUs -> new AboutResponseDto(
                        aboutUs.getId(),
                        aboutUs.getAboutUsTitle(lang),
                        aboutUs.getAboutUsDescription(lang),
                        aboutUs.getImageUrl()
                ))
                .collect(Collectors.toList());
    }



    public void deletedAbout(Long id) {
       AboutUs about = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));

        aboutUsRepository.delete(about);
    }
}
