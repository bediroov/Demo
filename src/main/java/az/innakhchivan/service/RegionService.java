package az.innakhchivan.service;

import az.innakhchivan.dto.request.RegionRequestDto;
import az.innakhchivan.dto.response.RegionResponseDto;
import az.innakhchivan.entity.Region;
import az.innakhchivan.exception.RegionNotFoundException;
import az.innakhchivan.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionResponseDto createRegion(RegionRequestDto regionRequestDto) {
        Region region = new Region();

        region.setUniqueKey(regionRequestDto.getUniqueKey());
        region.setAzName(regionRequestDto.getAzName());
        region.setEnName(regionRequestDto.getEnName());
        region.setRuName(regionRequestDto.getRuName());
        region.setIsActive(true);

        regionRepository.save(region);

        return RegionResponseDto.builder()
                .id(region.getId())
                .uniqueKey(region.getUniqueKey())
                .isActive(region.getIsActive())
                .build();
    }

    public Region getRegionByUniqueKey(String uniqueKey) {
        return regionRepository.findByUniqueKey(uniqueKey)
                .orElseThrow(() -> new RegionNotFoundException("Region not found with unique key: " + uniqueKey));
    }

    public void deactivateRegion(String uniqueKey) {
        Region region = regionRepository.findByUniqueKey(uniqueKey)
                .orElseThrow(() -> new RegionNotFoundException("Region not found with id: " + uniqueKey));
        region.setIsActive(false);
        regionRepository.save(region);
    }

    public boolean isRegionActive(String uniqueKey) {
        Region region = regionRepository.findByUniqueKey(uniqueKey)
                .orElseThrow(() -> new RegionNotFoundException("Region not found with unique key: " + uniqueKey));
        return region.getIsActive();
    }

    public List<RegionResponseDto> getAllRegions(String lang) {
        return regionRepository.findAll().stream()
                .map(x -> new RegionResponseDto(
                        x.getId(),
                        x.getUniqueKey(),
                        x.getRegionName(lang),
                        x.getIsActive()
                ))
                .collect(Collectors.toList());
    }

}
