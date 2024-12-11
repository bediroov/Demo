package az.innakhchivan.service;

import az.innakhchivan.dto.request.IncentiveRequestDto;
import az.innakhchivan.dto.response.IncentiveResponseDto;
import az.innakhchivan.dto.response.QuestionResponseDto;
import az.innakhchivan.entity.Incentive;
import az.innakhchivan.exception.IncentiveNotFoundException;
import az.innakhchivan.repository.IncentiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncentiveService {
    private final IncentiveRepository incentiveRepository;

    public IncentiveResponseDto createIncentive(IncentiveRequestDto incentiveRequestDto) {

        Incentive incentive = new Incentive();

        incentive.setAzTitle(incentiveRequestDto.getAzTitle());
        incentive.setAzDescription(incentiveRequestDto.getAzDescription());
        incentive.setEnTitle(incentiveRequestDto.getEnTitle());
        incentive.setEnDescription(incentiveRequestDto.getEnDescription());
        incentive.setRuTitle(incentiveRequestDto.getRuTitle());
        incentive.setRuDescription(incentiveRequestDto.getRuDescription());

        incentiveRepository.save(incentive);

        return IncentiveResponseDto.builder()
                .id(incentive.getId())
                .build();
    }

    public String updateIncentive(Long id, IncentiveRequestDto incentiveRequestDto) {
       Incentive incentive = incentiveRepository.findById(id).orElseThrow(
                () -> new IncentiveNotFoundException("Incentive not found Id : " + id));

        incentive.setAzTitle(incentiveRequestDto.getAzTitle());
        incentive.setAzDescription(incentiveRequestDto.getAzDescription());
        incentive.setEnTitle(incentiveRequestDto.getEnTitle());
        incentive.setEnDescription(incentiveRequestDto.getEnDescription());
        incentive.setRuTitle(incentiveRequestDto.getRuTitle());
        incentive.setRuDescription(incentiveRequestDto.getRuDescription());

        incentiveRepository.save(incentive);

        return "Incentive updated successfully";
    }

    public List<IncentiveResponseDto> getIncentiveAll(String lang) {
        return incentiveRepository.findAll().stream()
                .map(incentive -> new IncentiveResponseDto(
                        incentive.getId(),
                        incentive.getIncentiveTitle(lang),
                        incentive.getIncentiveDescription(lang)
                ))
                .collect(Collectors.toList());
    }

    public void deletedIncentive(Long id) {
        Incentive incentive = incentiveRepository.findById(id).orElseThrow(
                () -> new IncentiveNotFoundException("Incentive not found Id : " + id)
        );
        incentiveRepository.delete(incentive);
    }
}
