package az.innakhchivan.service;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.request.WhoAreWeRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.dto.response.WhoAreWeResponseDto;
import az.innakhchivan.entity.AboutUs;
import az.innakhchivan.entity.WhoAreWe;
import az.innakhchivan.exception.AboutUsNotFoundException;
import az.innakhchivan.exception.WhoAreWeUsNotFoundException;
import az.innakhchivan.repository.WhoAreWeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhoAreWeService {
    private final WhoAreWeRepository whoAreWeRepository;

    public WhoAreWeResponseDto createWhoAreWe(WhoAreWeRequestDto whoAreWeRequestDto) {

        WhoAreWe whoAreWe = new WhoAreWe();

        whoAreWe.setAzTitle(whoAreWeRequestDto.getAzTitle());
        whoAreWe.setAzDescription(whoAreWeRequestDto.getAzDescription());
        whoAreWe.setEnTitle(whoAreWeRequestDto.getEnTitle());
        whoAreWe.setEnDescription(whoAreWeRequestDto.getEnDescription());
        whoAreWe.setRuTitle(whoAreWeRequestDto.getRuTitle());
        whoAreWe.setRuDescription(whoAreWeRequestDto.getRuDescription());

        whoAreWeRepository.save(whoAreWe);

        return WhoAreWeResponseDto.builder()
                .id(whoAreWe.getId())
                .build();
    }

    public String updateWhoAreWe(Long id, WhoAreWeRequestDto whoAreWeRequestDto) {
        WhoAreWe whoAreWe = whoAreWeRepository.findById(id).orElseThrow(
                () -> new AboutUsNotFoundException("WhoAreWe not found Id : " + id));

        whoAreWe.setAzTitle(whoAreWeRequestDto.getAzTitle());
        whoAreWe.setAzDescription(whoAreWeRequestDto.getAzDescription());
        whoAreWe.setEnTitle(whoAreWeRequestDto.getEnTitle());
        whoAreWe.setEnDescription(whoAreWeRequestDto.getEnDescription());
        whoAreWe.setRuTitle(whoAreWeRequestDto.getRuTitle());
        whoAreWe.setRuDescription(whoAreWeRequestDto.getRuDescription());

        whoAreWeRepository.save(whoAreWe);

        return "About us updated successfully";
    }

    public List<WhoAreWeResponseDto> getAllWhoAreWe(String lang) {
        return whoAreWeRepository.findAll().stream()
                .map(whoAreWe -> new WhoAreWeResponseDto(
                        whoAreWe.getId(),
                        whoAreWe.getWhoAreWeTitle(lang),
                        whoAreWe.getWhoAreWeDescription(lang)
                ))
                .collect(Collectors.toList());
    }



    public void deletedWhoAreWe(Long id) {
        WhoAreWe whoAreWe = whoAreWeRepository.findById(id).orElseThrow(
                () -> new WhoAreWeUsNotFoundException("WhoAreWe not found Id : " + id));

        whoAreWeRepository.delete(whoAreWe);
    }
}
