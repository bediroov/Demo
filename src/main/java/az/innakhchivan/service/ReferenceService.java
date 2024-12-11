package az.innakhchivan.service;

import az.innakhchivan.entity.Reference;
import az.innakhchivan.dto.request.ReferenceRequestDto;
import az.innakhchivan.dto.response.ReferenceResponseDto;
import az.innakhchivan.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferenceService {
    private final ReferenceRepository referenceRepository;

    public ReferenceResponseDto create(ReferenceRequestDto referenceRequestDto) {

        Reference reference = new Reference();
        reference.setName(referenceRequestDto.getName());
        reference.setImageUrl(reference.getImageUrl());
        referenceRepository.save(reference);

        return ReferenceResponseDto.builder()
                .id(reference.getId())
                .name(reference.getName())
                .iconUrl(reference.getImageUrl())
                .build();
    }


    public List<ReferenceResponseDto> getAllReferences() {
        return referenceRepository.findAll().stream()
                .map(x -> new ReferenceResponseDto(
                        x.getId(),
                        x.getName(),
                        x.getImageUrl()
                ))
                .collect(Collectors.toList());
    }
}
