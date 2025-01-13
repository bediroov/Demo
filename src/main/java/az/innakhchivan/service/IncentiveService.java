package az.innakhchivan.service;

import az.innakhchivan.dto.request.IncentiveRequestDto;
import az.innakhchivan.dto.response.IncentiveResponseDto;
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

    public void createIncentive(IncentiveRequestDto incentiveRequestDto) {

        Incentive incentive = new Incentive();

        incentive.setAzTitle(incentiveRequestDto.getAzTitle());
        incentive.setAzDescription(incentiveRequestDto.getAzDescription());
        incentive.setEnTitle(incentiveRequestDto.getEnTitle());
        incentive.setEnDescription(incentiveRequestDto.getEnDescription());
        incentive.setRuTitle(incentiveRequestDto.getRuTitle());
        incentive.setRuDescription(incentiveRequestDto.getRuDescription());

        incentiveRepository.save(incentive);
    }

    public void updateIncentive(Long id, IncentiveRequestDto incentiveRequestDto) {
        Incentive incentive = incentiveRepository.findById(id).orElseThrow(
                () -> new IncentiveNotFoundException("Incentive not found Id : " + id));

        incentive.setAzTitle(incentiveRequestDto.getAzTitle());
        incentive.setAzDescription(incentiveRequestDto.getAzDescription());
        incentive.setEnTitle(incentiveRequestDto.getEnTitle());
        incentive.setEnDescription(incentiveRequestDto.getEnDescription());
        incentive.setRuTitle(incentiveRequestDto.getRuTitle());
        incentive.setRuDescription(incentiveRequestDto.getRuDescription());

        incentiveRepository.save(incentive);
    }

    public List<IncentiveResponseDto> getIncentiveAll(String lang) {
        return incentiveRepository.findAllByOrderByIdAsc().stream()
                .map(incentive -> new IncentiveResponseDto(
                        incentive.getId(),
                        incentive.getIncentiveTitle(lang),
                        incentive.getIncentiveDescription(lang)
                ))
                .collect(Collectors.toList());
    }


    public List<Incentive> getAll() {
        return incentiveRepository.findAllByOrderByIdAsc().stream()
                .map(incentive -> new Incentive(
                        incentive.getId(),
                        incentive.getAzTitle(),
                        incentive.getAzDescription(),
                        incentive.getEnTitle(),
                        incentive.getEnDescription(),
                        incentive.getRuTitle(),
                        incentive.getRuDescription()
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
