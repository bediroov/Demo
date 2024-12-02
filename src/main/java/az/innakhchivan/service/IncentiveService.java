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

    public IncentiveResponseDto createIncentive(IncentiveRequestDto incentiveRequestDto) {
        Incentive incentive = new Incentive();
        incentive.setTitle(incentiveRequestDto.getTitle());
        incentive.setDescription(incentiveRequestDto.getDescription());
        incentiveRepository.save(incentive);

        return new IncentiveResponseDto(
                incentive.getId(),
                incentive.getTitle(),
                incentive.getDescription()
        );
    }

    public IncentiveResponseDto updateIncentive(Long id, IncentiveRequestDto incentiveRequestDto) {
        Incentive incentive = incentiveRepository.findById(id).orElseThrow(
                () -> new IncentiveNotFoundException("Incentive not found Id : " + id)
        );
        incentive.setTitle(incentiveRequestDto.getTitle());
        incentive.setDescription(incentiveRequestDto.getDescription());

        Incentive incentive1 = incentiveRepository.save(incentive);

        return new IncentiveResponseDto(
                incentive1.getId(),
                incentive.getTitle(),
                incentive.getDescription());
    }

    public IncentiveResponseDto getIncentiveById(Long id) {
        Incentive incentive = incentiveRepository.findById(id).orElseThrow(
                () -> new IncentiveNotFoundException("Incentive not found Id : " + id)
        );
        return new IncentiveResponseDto(
                incentive.getId(),
                incentive.getTitle(),
                incentive.getDescription()
        );
    }

    public List<IncentiveResponseDto> getIncentiveAll() {
        return incentiveRepository.findAll().stream()
                .map(x -> new IncentiveResponseDto(
                        x.getId(),
                        x.getTitle(),
                        x.getDescription()
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
