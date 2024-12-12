package az.innakhchivan.service;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.request.HowWeHelpRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.dto.response.HowWeHelpResponseDto;
import az.innakhchivan.entity.AboutUs;
import az.innakhchivan.entity.HowWeHelp;
import az.innakhchivan.exception.AboutUsNotFoundException;
import az.innakhchivan.exception.HowWeHelpNotFoundException;
import az.innakhchivan.repository.AboutUsRepository;
import az.innakhchivan.repository.HowWeHelpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HowWeHelpService {

    private final HowWeHelpRepository howWeHelpRepository;


    public void createHowWeHelp(HowWeHelpRequestDto howWeHelpRequestDto) {

        HowWeHelp howWeHelp = new HowWeHelp();

        howWeHelp.setAzTitle(howWeHelpRequestDto.getAzTitle());
        howWeHelp.setAzDescription(howWeHelpRequestDto.getAzDescription());
        howWeHelp.setEnTitle(howWeHelpRequestDto.getEnTitle());
        howWeHelp.setEnDescription(howWeHelpRequestDto.getEnDescription());
        howWeHelp.setRuTitle(howWeHelpRequestDto.getRuTitle());
        howWeHelp.setRuDescription(howWeHelpRequestDto.getRuDescription());

        howWeHelpRepository.save(howWeHelp);
    }

    public void update(Long id, HowWeHelpRequestDto howWeHelpRequestDto) {
        HowWeHelp howWeHelp = howWeHelpRepository.findById(id).orElseThrow(
                () -> new HowWeHelpNotFoundException("How we help  not found Id : " + id));

        howWeHelp.setAzTitle(howWeHelpRequestDto.getAzTitle());
        howWeHelp.setAzDescription(howWeHelpRequestDto.getAzDescription());
        howWeHelp.setEnTitle(howWeHelpRequestDto.getEnTitle());
        howWeHelp.setEnDescription(howWeHelpRequestDto.getEnDescription());
        howWeHelp.setRuTitle(howWeHelpRequestDto.getRuTitle());
        howWeHelp.setRuDescription(howWeHelpRequestDto.getRuDescription());

        howWeHelpRepository.save(howWeHelp);
    }

    public List<HowWeHelpResponseDto> getHowWeHelp(String lang) {
        return howWeHelpRepository.findAll().stream()
                .map(howWeHelp -> new HowWeHelpResponseDto(
                        howWeHelp.getId(),
                        howWeHelp.getAboutUsTitle(lang),
                        howWeHelp.getAboutUsDescription(lang)
                ))
                .collect(Collectors.toList());
    }


    public List<HowWeHelp> getAll() {
        return howWeHelpRepository.findAll().stream()
                .map(howWeHelp -> new HowWeHelp(
                        howWeHelp.getId(),
                        howWeHelp.getAzTitle(),
                        howWeHelp.getAzDescription(),
                        howWeHelp.getEnTitle(),
                        howWeHelp.getEnDescription(),
                        howWeHelp.getRuTitle(),
                        howWeHelp.getRuDescription()
                ))
                .collect(Collectors.toList());
    }



    public void deletedHowWeHelp(Long id) {
        HowWeHelp howWeHelp = howWeHelpRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("How We Help not found Id : " + id));

        howWeHelpRepository.delete(howWeHelp);
    }
}
