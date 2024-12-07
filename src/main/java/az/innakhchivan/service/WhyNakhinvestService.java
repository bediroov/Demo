package az.innakhchivan.service;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.QuestionResponseDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
import az.innakhchivan.entity.WhyNakhinvest;
import az.innakhchivan.exception.AboutUsNotFoundException;
import az.innakhchivan.exception.WhyNakhinvestNotFoundException;
import az.innakhchivan.repository.WhyNakhinvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WhyNakhinvestService {
    private final WhyNakhinvestRepository whyNakhinvestRepository;

    WhyNakhinvest nakhinvest = new WhyNakhinvest();

    public WhyNakhinvestResponseDto createNakhinvest (WhyNakhinvestRequestDto whyNakhinvestRequestDto, String lang) {
        switch (lang) {
            case "az":
                nakhinvest.setAzTitle(whyNakhinvestRequestDto.getTitle());
                nakhinvest.setAzDescription(whyNakhinvestRequestDto.getDescription());
                break;
            case "en":
                nakhinvest.setEnTitle(whyNakhinvestRequestDto.getTitle());
                nakhinvest.setEnDescription(whyNakhinvestRequestDto.getDescription());
                break;
            case "ru":
                nakhinvest.setRuTitle(whyNakhinvestRequestDto.getTitle());
                nakhinvest.setRuDescription(whyNakhinvestRequestDto.getDescription());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);
        }

        whyNakhinvestRepository.save(nakhinvest);

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .title(nakhinvest.getWhyNakhinvestTitle(lang))
                .description(nakhinvest.getWhyNakhinvestDescription(lang))
                .build();
    }

    public WhyNakhinvestResponseDto updateNakhinvest (Long id, WhyNakhinvestRequestDto whyNakhinvestRequestDto, String lang) {
         nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id)
        );

        switch (lang) {
            case "az":
                nakhinvest.setAzTitle(whyNakhinvestRequestDto.getTitle());
                nakhinvest.setAzDescription(whyNakhinvestRequestDto.getDescription());
                break;
            case "en":
                nakhinvest.setEnTitle(whyNakhinvestRequestDto.getTitle());
                nakhinvest.setEnDescription(whyNakhinvestRequestDto.getDescription());
                break;
            case "ru":
                nakhinvest.setRuTitle(whyNakhinvestRequestDto.getTitle());
                nakhinvest.setRuDescription(whyNakhinvestRequestDto.getDescription());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);
        }

        whyNakhinvestRepository.save(nakhinvest);

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .title(nakhinvest.getWhyNakhinvestTitle(lang))
                .description(nakhinvest.getWhyNakhinvestDescription(lang))
                .build();
    }

    public WhyNakhinvestResponseDto getNakhinvestById (Long id, String lang) {
         nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id)
        );

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .title(nakhinvest.getWhyNakhinvestTitle(lang))
                .description(nakhinvest.getWhyNakhinvestDescription(lang))
                .build();
    }


    public void deletedNakhinvest(Long id) {
        nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id));

        whyNakhinvestRepository.delete(nakhinvest);
    }


}
