package az.innakhchivan.service;

import az.innakhchivan.dto.request.SectorRequestDto;
import az.innakhchivan.dto.response.SectorResponseDto;
import az.innakhchivan.entity.News;
import az.innakhchivan.entity.Sector;
import az.innakhchivan.exception.SectorNotFoundException;
import az.innakhchivan.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;

    public SectorResponseDto addSector(SectorRequestDto sectorRequestDto) {
        Sector sector = new Sector();

        sector.setCategory(sectorRequestDto.getCategory());
        sector.setDescription(sectorRequestDto.getDescription());
        sectorRepository.save(sector);

        return SectorResponseDto.builder()
                .id(sector.getId())
                .category(sector.getCategory())
                .description(sector.getDescription())
                .build();
    }

    public SectorResponseDto updateSector(Long Id, SectorRequestDto sectorRequestDto) {
        Sector sector = sectorRepository.findById(Id).orElseThrow(
                () -> new SectorNotFoundException("Sector not found with id: " + Id)
        );

        sector.setCategory(sectorRequestDto.getCategory());
        sector.setDescription(sectorRequestDto.getDescription());
        sectorRepository.save(sector);

        return SectorResponseDto.builder()
                .id(sector.getId())
                .category(sector.getCategory())
                .description(sector.getDescription())
                .build();
    }

    public SectorResponseDto getSectorById(Long Id) {
        Sector sector = sectorRepository.findById(Id).orElseThrow(
                () -> new SectorNotFoundException("Sector not found with id: " + Id)
        );

        return SectorResponseDto.builder()
                .id(sector.getId())
                .category(sector.getCategory())
                .description(sector.getDescription())
                .build();

    }

    public List<SectorResponseDto> getAllNews() {
        return sectorRepository.findAll().stream()
                .map(x -> new SectorResponseDto(
                        x.getId(),
                        x.getCategory(),
                        x.getDescription()
                ))
                .collect(Collectors.toList());

    }

    public void deleteSector(Long Id) {
        Sector sector = sectorRepository.findById(Id).orElseThrow(
                () -> new SectorNotFoundException("Sector not found with id: " + Id)
        );
        sectorRepository.delete(sector);
    }
}
