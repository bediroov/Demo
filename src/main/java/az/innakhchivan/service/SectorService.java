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

    Sector sector = new Sector();

    public SectorResponseDto addSector(SectorRequestDto sectorRequestDto, String lang) {

        switch (lang) {
            case "en":
                sector.setEnCategory(sectorRequestDto.getCategory());
                sector.setEnDescription(sectorRequestDto.getDescription());
                break;
            case "ru":
                sector.setRuCategory(sectorRequestDto.getCategory());
                sector.setRuDescription(sectorRequestDto.getDescription());
                break;
            default:
                sector.setAzCategory(sectorRequestDto.getCategory());
                sector.setAzDescription(sectorRequestDto.getDescription());
                break;
        }
        sectorRepository.save(sector);

        return SectorResponseDto.builder()
                .id(sector.getId())
                .category(sector.getSectorCategory(lang))
                .description(sector.getSectorDescription(lang))
                .build();
    }

    public SectorResponseDto updateSector(Long Id, SectorRequestDto sectorRequestDto, String lang) {
        sector = sectorRepository.findById(Id).orElseThrow(
                () -> new SectorNotFoundException("Sector not found with id: " + Id)
        );

        switch (lang) {
            case "en":
                sector.setEnCategory(sectorRequestDto.getCategory());
                sector.setEnDescription(sectorRequestDto.getDescription());
                break;
            case "ru":
                sector.setRuCategory(sectorRequestDto.getCategory());
                sector.setRuDescription(sectorRequestDto.getDescription());
                break;
            default:
                sector.setAzCategory(sectorRequestDto.getCategory());
                sector.setAzDescription(sectorRequestDto.getDescription());
                break;
        }
        sectorRepository.save(sector);

        return SectorResponseDto.builder()
                .id(sector.getId())
                .category(sector.getSectorCategory(lang))
                .description(sector.getSectorDescription(lang))
                .build();
    }

    public SectorResponseDto getSectorById(Long Id, String lang) {
        sector = sectorRepository.findById(Id).orElseThrow(
                () -> new SectorNotFoundException("Sector not found with id: " + Id)
        );

        return SectorResponseDto.builder()
                .id(sector.getId())
                .category(sector.getSectorCategory(lang))
                .description(sector.getSectorDescription(lang))
                .build();

    }

    public List<SectorResponseDto> getAllNews(String lang) {
        return sectorRepository.findAll().stream()
                .map(x -> new SectorResponseDto(
                        x.getId(),
                        x.getSectorCategory(lang),
                        x.getSectorDescription(lang)
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
