package az.innakhchivan.service;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponse;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
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


    public void createNakhinvest(WhyNakhinvestRequestDto whyNakhinvestRequestDto) {
        WhyNakhinvest nakhinvest = new WhyNakhinvest();

        nakhinvest.setAzTitle(whyNakhinvestRequestDto.getAzTitle());
        nakhinvest.setAzDescription(whyNakhinvestRequestDto.getAzDescription());
        nakhinvest.setEnTitle(whyNakhinvestRequestDto.getEnTitle());
        nakhinvest.setEnDescription(whyNakhinvestRequestDto.getEnDescription());
        nakhinvest.setRuTitle(whyNakhinvestRequestDto.getRuTitle());
        nakhinvest.setRuDescription(whyNakhinvestRequestDto.getRuDescription());
        nakhinvest.setImageUrl(whyNakhinvestRequestDto.getImageUr());

        whyNakhinvestRepository.save(nakhinvest);
    }

    public void updateNakhinvest(Long id, WhyNakhinvestRequestDto whyNakhinvestRequestDto) {
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
    }


    public WhyNakhinvestResponse getWhyNakhinvestById(Long id) {
        WhyNakhinvest nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id)
        );

        return WhyNakhinvestResponse.builder()
                .id(nakhinvest.getId())
                .azTitle(nakhinvest.getAzTitle())
                .azDescription(nakhinvest.getAzDescription())
                .enTitle(nakhinvest.getEnTitle())
                .enDescription(nakhinvest.getEnDescription())
                .ruTitle(nakhinvest.getRuTitle())
                .ruDescription(nakhinvest.getRuDescription())
                .imageUrl(nakhinvest.getImageUrl())
                .build();
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

    public List<WhyNakhinvestResponse> getAll() {
        return whyNakhinvestRepository.findAll().stream()
                .map(x -> new WhyNakhinvestResponse(
                        x.getId(),
                        x.getAzTitle(),
                        x.getAzDescription(),
                        x.getEnTitle(),
                        x.getEnDescription(),
                        x.getRuTitle(),
                        x.getRuDescription(),
                        x.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

}
