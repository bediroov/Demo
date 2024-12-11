package az.innakhchivan.service;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.dto.response.SectorResponseDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
import az.innakhchivan.entity.Category;
import az.innakhchivan.entity.WhyNakhinvest;
import az.innakhchivan.exception.WhyNakhinvestNotFoundException;
import az.innakhchivan.repository.CategoryRepository;
import az.innakhchivan.repository.WhyNakhinvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhyNakhinvestService {
    private final WhyNakhinvestRepository whyNakhinvestRepository;
    private final CategoryRepository categoryRepository;


    public WhyNakhinvestResponseDto createNakhinvest(WhyNakhinvestRequestDto whyNakhinvestRequestDto) {
        WhyNakhinvest nakhinvest = new WhyNakhinvest();

        nakhinvest.setAzTitle(whyNakhinvestRequestDto.getAzTitle());
        nakhinvest.setAzDescription(whyNakhinvestRequestDto.getAzDescription());
        nakhinvest.setEnTitle(whyNakhinvestRequestDto.getEnTitle());
        nakhinvest.setEnDescription(whyNakhinvestRequestDto.getEnDescription());
        nakhinvest.setRuTitle(whyNakhinvestRequestDto.getRuTitle());
        nakhinvest.setRuDescription(whyNakhinvestRequestDto.getRuDescription());
        nakhinvest.setImageUrl(whyNakhinvestRequestDto.getImageUr());

        whyNakhinvestRepository.save(nakhinvest);

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .build();
    }

    public String updateNakhinvest(Long id, WhyNakhinvestRequestDto whyNakhinvestRequestDto) {
        WhyNakhinvest nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id)
        );

        nakhinvest.setAzTitle(whyNakhinvestRequestDto.getAzTitle());
        nakhinvest.setAzDescription(whyNakhinvestRequestDto.getAzDescription());
        nakhinvest.setEnTitle(whyNakhinvestRequestDto.getEnTitle());
        nakhinvest.setEnDescription(whyNakhinvestRequestDto.getEnDescription());
        nakhinvest.setRuTitle(whyNakhinvestRequestDto.getRuTitle());
        nakhinvest.setRuDescription(whyNakhinvestRequestDto.getRuDescription());
        nakhinvest.setImageUrl(whyNakhinvestRequestDto.getImageUr());

        whyNakhinvestRepository.save(nakhinvest);

        return "WhyNakhinvest updated successfully";
    }


    public List<WhyNakhinvestResponseDto> getAllNakhinvest(String lang) {
        return whyNakhinvestRepository.findAll().stream()
                .map(nakhinvest -> new WhyNakhinvestResponseDto(
                        nakhinvest.getId(),
                        nakhinvest.getWhyNakhinvestTitle(lang),
                        nakhinvest.getWhyNakhinvestDescription(lang),
                        nakhinvest.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

    public void deletedNakhinvest(Long id) {
        WhyNakhinvest nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id));

        whyNakhinvestRepository.delete(nakhinvest);
    }


}
