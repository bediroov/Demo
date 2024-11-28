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
        AboutUs aboutUs = new AboutUs();
        aboutUs.setTitle(aboutRequestDto.getTitle());
        aboutUs.setDescription(aboutRequestDto.getDescription());
        aboutUsRepository.save(aboutUs);

        return new AboutResponseDto(
                aboutUs.getId(),
                aboutUs.getTitle(),
                aboutUs.getDescription()
        );
    }

    public AboutResponseDto updateAbout(Long id, AboutRequestDto aboutRequestDto) {
        AboutUs aboutUs = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));
        aboutUs.setTitle(aboutRequestDto.getTitle());
        aboutUs.setDescription(aboutRequestDto.getDescription());

        AboutUs about = aboutUsRepository.save(aboutUs);

        return new AboutResponseDto(
                about.getId(),
                about.getTitle(),
                about.getDescription());
    }

    public AboutResponseDto getAboutById(Long id) {
        AboutUs aboutUs = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));
        return new AboutResponseDto(
                aboutUs.getId(),
                aboutUs.getTitle(),
                aboutUs.getDescription()
        );
    }

    public List<AboutResponseDto> getAboutAll() {
        return aboutUsRepository.findAll().stream()
                .map(aboutUs -> new AboutResponseDto(
                        aboutUs.getId(),
                        aboutUs.getTitle(),
                        aboutUs.getDescription()
                ))
                .collect(Collectors.toList());
    }

    public void deletedAbout(Long id) {
        AboutUs aboutUs = aboutUsRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("AboutUs not found Id : " + id));

        aboutUsRepository.delete(aboutUs);
    }
}
