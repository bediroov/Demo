package az.innakhchivan.service;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
import az.innakhchivan.entity.WhyNakhinvest;
import az.innakhchivan.exception.WhyNakhinvestNotFoundException;
import az.innakhchivan.repository.WhyNakhinvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WhyNakhinvestService {
    private final WhyNakhinvestRepository whyNakhinvestRepository;

    public WhyNakhinvestResponseDto createNakhinvest (WhyNakhinvestRequestDto whyNakhinvestRequestDto) {
        WhyNakhinvest   nakhinvest = WhyNakhinvest.builder()
                .title(whyNakhinvestRequestDto.getTitle())
                .description(whyNakhinvestRequestDto.getDescription())
                .build();
        whyNakhinvestRepository.save(nakhinvest);

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .title(nakhinvest.getTitle())
                .description(nakhinvest.getDescription())
                .build();
    }

    public WhyNakhinvestResponseDto updateNakhinvest (Long id, WhyNakhinvestRequestDto requestDto) {
        WhyNakhinvest nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id)
        );

        nakhinvest.setTitle(requestDto.getTitle());
        nakhinvest.setDescription(requestDto.getDescription());
        whyNakhinvestRepository.save(nakhinvest);

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .title(nakhinvest.getTitle())
                .description(nakhinvest.getDescription())
                .build();
    }

    public WhyNakhinvestResponseDto getNakhinvestById (Long id) {
        WhyNakhinvest nakhinvest = whyNakhinvestRepository.findById(id).orElseThrow(
                () -> new WhyNakhinvestNotFoundException("Nakhinvest not found with id : " + id)
        );

        return WhyNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .title(nakhinvest.getTitle())
                .description(nakhinvest.getDescription())
                .build();
    }
}
