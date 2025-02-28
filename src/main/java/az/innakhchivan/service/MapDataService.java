package az.innakhchivan.service;

import az.innakhchivan.dto.request.MapDataRequestDto;
import az.innakhchivan.dto.response.MapDataResponse;
import az.innakhchivan.dto.response.MapDataResponseDto;
import az.innakhchivan.entity.MapData;
import az.innakhchivan.entity.Region;
import az.innakhchivan.exception.MapDataNotFoundException;
import az.innakhchivan.exception.RegionNotFoundException;
import az.innakhchivan.repository.MapDataRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapDataService {
    private final MapDataRepository mapDataRepository;
    private final RegionService regionService;


    public MapDataResponse createMapData(MapDataRequestDto mapDataRequestDto) {
        MapData mapData = new MapData();

        mapData.setLocation(mapDataRequestDto.getLocation());
        mapData.setArea(mapDataRequestDto.getArea());
        mapData.setPopulation(mapDataRequestDto.getPopulation());
        mapData.setAverageSalary(mapDataRequestDto.getAverageSalary());

        mapData.setAzTitle(mapDataRequestDto.getAzTitle());
        mapData.setAzDescription(mapDataRequestDto.getAzDescription());

        mapData.setEnTitle(mapDataRequestDto.getEnTitle());
        mapData.setEnDescription(mapDataRequestDto.getEnDescription());

        mapData.setRuTitle(mapDataRequestDto.getRuTitle());
        mapData.setRuDescription(mapDataRequestDto.getRuDescription());

        mapData.setIconUrl(mapDataRequestDto.getIconUrl());

        // 🆕 Yeni sahələr əlavə edilir
        mapData.setSettlementCount(mapDataRequestDto.getSettlementCount());
        mapData.setVillageCount(mapDataRequestDto.getVillageCount());
        mapData.setCityAdminArea(mapDataRequestDto.getCityAdminArea());
        mapData.setDistanceFromBaku(mapDataRequestDto.getDistanceFromBaku());
        mapData.setGeneralInfo(mapDataRequestDto.getGeneralInfo());

        // Region əlavə edin
        mapData.setRegion(regionService.getRegionByUniqueKey(mapDataRequestDto.getRegionUniqueKey()));

        mapDataRepository.save(mapData);
        return MapDataResponse.builder()
                .id(mapData.getId())
                .build();

    }

    public String updateMapData(Long id, MapDataRequestDto mapDataRequestDto) {
        MapData mapData = mapDataRepository.findById(id).orElseThrow(
                () -> new MapDataNotFoundException("MapData not found with id: " + id)
        );

        mapData.setLocation(mapDataRequestDto.getLocation());
        mapData.setArea(mapDataRequestDto.getArea());
        mapData.setPopulation(mapDataRequestDto.getPopulation());
        mapData.setAverageSalary(mapDataRequestDto.getAverageSalary());

        mapData.setAzTitle(mapDataRequestDto.getAzTitle());
        mapData.setAzDescription(mapDataRequestDto.getAzDescription());

        mapData.setEnTitle(mapDataRequestDto.getEnTitle());
        mapData.setEnDescription(mapDataRequestDto.getEnDescription());

        mapData.setRuTitle(mapDataRequestDto.getRuTitle());
        mapData.setRuDescription(mapDataRequestDto.getRuDescription());

        mapData.setIconUrl(mapDataRequestDto.getIconUrl());

        // 🆕 Yeni sahələr yenilənir
        mapData.setSettlementCount(mapDataRequestDto.getSettlementCount());
        mapData.setVillageCount(mapDataRequestDto.getVillageCount());
        mapData.setCityAdminArea(mapDataRequestDto.getCityAdminArea());
        mapData.setDistanceFromBaku(mapDataRequestDto.getDistanceFromBaku());
        mapData.setGeneralInfo(mapDataRequestDto.getGeneralInfo());

        mapData.setRegion(regionService.getRegionByUniqueKey(mapDataRequestDto.getRegionUniqueKey()));


        mapDataRepository.save(mapData);

        return "MapData successfully updated";

    }


    public MapDataResponseDto getMapDataById(Long id, String lang) {
        MapData mapData = mapDataRepository.findById(id).orElseThrow(
                () -> new MapDataNotFoundException("MapData not found with id: " + id)
        );

        return MapDataResponseDto.builder()
                .id(mapData.getId())
                .location(mapData.getLocation())
                .area(mapData.getArea())
                .population(mapData.getPopulation())
                .averageSalary(mapData.getAverageSalary())
                .title(mapData.getMapDataTitle(lang))
                .description(mapData.getMapDataDescription(lang))
                .iconUrl(mapData.getIconUrl())
                // 🆕 Yeni sahələr
                .settlementCount(mapData.getSettlementCount())
                .villageCount(mapData.getVillageCount())
                .cityAdminArea(mapData.getCityAdminArea())
                .distanceFromBaku(mapData.getDistanceFromBaku())
                .generalInfo(mapData.getGeneralInfo())
                .build();

    }


    public List<MapDataResponseDto> getAllMapData(String lang) {
        return mapDataRepository.findAll().stream()
                .map(mapData -> new MapDataResponseDto(
                        mapData.getId(),
                        mapData.getLocation(),
                        mapData.getArea(),
                        mapData.getPopulation(),
                        mapData.getAverageSalary(),
                        mapData.getMapDataTitle(lang),
                        mapData.getMapDataDescription(lang),
                        mapData.getIconUrl(),
                        // 🆕 Yeni sahələr
                        mapData.getSettlementCount(),
                        mapData.getVillageCount(),
                        mapData.getCityAdminArea(),
                        mapData.getDistanceFromBaku(),
                        mapData.getGeneralInfo()
                ))
                .collect(Collectors.toList());
    }


    public List<MapDataResponseDto> getMapDataByRegionUniqueKey(String uniqueKey, String lang) {
        Region region = regionService.getRegionByUniqueKey(uniqueKey);

        if (!region.getIsActive()) {
            throw new RegionNotFoundException("Region is not active.");
        }

        return mapDataRepository.findByRegion(region).stream()
                .map(mapData -> new MapDataResponseDto(
                        mapData.getId(),
                        mapData.getLocation(),
                        mapData.getArea(),
                        mapData.getPopulation(),
                        mapData.getAverageSalary(),
                        mapData.getMapDataTitle(lang),
                        mapData.getMapDataDescription(lang),
                        mapData.getIconUrl(),
                        // 🆕 Yeni sahələr
                        mapData.getSettlementCount(),
                        mapData.getVillageCount(),
                        mapData.getCityAdminArea(),
                        mapData.getDistanceFromBaku(),
                        mapData.getGeneralInfo()
                ))
                .collect(Collectors.toList());
    }



    public void deleteMapData(Long id) {
        MapData mapData = mapDataRepository.findById(id).orElseThrow(
                () -> new MapDataNotFoundException("MapData not found with id: " + id)
        );

        mapDataRepository.delete(mapData);
    }

}
