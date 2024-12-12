package az.innakhchivan.service;

import az.innakhchivan.dto.request.SectorRequestDto;
import az.innakhchivan.dto.response.CategoryResponseDtoForRelation;
import az.innakhchivan.dto.response.SectorResponse;
import az.innakhchivan.dto.response.SectorResponseDto;
import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import az.innakhchivan.entity.Category;
import az.innakhchivan.entity.News;
import az.innakhchivan.entity.Sector;
import az.innakhchivan.exception.CategoryNotFoundException;
import az.innakhchivan.exception.SectorNotFoundException;
import az.innakhchivan.repository.CategoryRepository;
import az.innakhchivan.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;
    private final CategoryRepository categoryRepository;


    public void addSector(SectorRequestDto sectorRequestDto) {
        Sector sector = new Sector();

        sector.setAzDescription(sectorRequestDto.getAzDescription());
        sector.setEnDescription(sectorRequestDto.getEnDescription());
        sector.setRuDescription(sectorRequestDto.getRuDescription());
        sector.setImageUrl(sectorRequestDto.getImageUrl());
        sector.setIconUrl(sectorRequestDto.getIconUrl());

        Category category = categoryRepository.findById(sectorRequestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + sectorRequestDto.getCategoryId()));
        sector.setCategory(category);

        sectorRepository.save(sector);
    }

    public void updateSector(Long Id, SectorRequestDto sectorRequestDto) {
        Sector sector = sectorRepository.findById(Id).orElseThrow(
                () -> new SectorNotFoundException("Sector not found with id: " + Id)
        );

        sector.setAzDescription(sectorRequestDto.getAzDescription());
        sector.setEnDescription(sectorRequestDto.getEnDescription());
        sector.setRuDescription(sectorRequestDto.getRuDescription());
        sector.setImageUrl(sectorRequestDto.getImageUrl());
        sector.setIconUrl(sectorRequestDto.getIconUrl());

        Category category = categoryRepository.findById(sectorRequestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + sectorRequestDto.getCategoryId()));
        sector.setCategory(category);

        sectorRepository.save(sector);
    }


    public List<SectorResponseDto> getAllSector(String lang) {

        return sectorRepository.findAll().stream()
                .map(sector -> new SectorResponseDto(
                        sector.getId(),
                        sector.getCategory().getCategoryName(lang),
                        sector.getSectorDescription(lang),
                        sector.getImageUrl(),
                        sector.getIconUrl()
                ))
                .collect(Collectors.toList());
    }

    public List<SectorResponse> getAll() {
        return sectorRepository.findAllWithCategory().stream()
                .map(sector -> new SectorResponse(
                        sector.getId(),
                        sector.getAzDescription(),
                        sector.getEnDescription(),
                        sector.getRuDescription(),
                        sector.getImageUrl(),
                        sector.getIconUrl(),
                        sector.getCategory() != null ?
                                new CategoryResponseDtoForRelation(sector.getCategory().getId()) : null
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
