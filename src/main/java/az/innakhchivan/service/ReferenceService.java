package az.innakhchivan.service;

import az.innakhchivan.entity.Reference;
import az.innakhchivan.dto.request.ReferenceRequestDto;
import az.innakhchivan.dto.response.ReferenceResponseDto;
import az.innakhchivan.exception.ReferenceNotFoundException;
import az.innakhchivan.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferenceService {
    private final ReferenceRepository referenceRepository;

    public void create(ReferenceRequestDto referenceRequestDto) {

        Reference reference = new Reference();
        reference.setName(referenceRequestDto.getName());
        reference.setImageUrl(referenceRequestDto.getIconUrl());
        referenceRepository.save(reference);
    }


    public void updateReference( Long id ,ReferenceRequestDto referenceRequestDto) {
        Reference reference = referenceRepository.findById(id).orElseThrow(
                ()-> new ReferenceNotFoundException("Reference not found with id: " + id)
        );

        reference.setName(referenceRequestDto.getName());
        reference.setImageUrl(referenceRequestDto.getIconUrl());
        referenceRepository.save(reference);
    }

    public ReferenceResponseDto getReferenceById(Long id) {
        Reference reference = referenceRepository.findById(id).orElseThrow(
                ()-> new ReferenceNotFoundException("Reference not found with id: " + id)
        );
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



    public void deleteReference(Long id) {
        Reference reference = referenceRepository.findById(id).orElseThrow(
                ()-> new ReferenceNotFoundException("Reference not found with id: " + id)
        );
        referenceRepository.delete(reference);
    }
}
