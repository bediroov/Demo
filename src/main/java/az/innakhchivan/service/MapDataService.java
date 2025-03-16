package az.innakhchivan.service;

import az.innakhchivan.dto.request.MapDataRequestDto;
import az.innakhchivan.dto.response.MapDataResponse;
import az.innakhchivan.dto.response.MapDataResponseDto;
import az.innakhchivan.dto.response.RegionResponseDtoForRelation;
import az.innakhchivan.entity.MapData;
import az.innakhchivan.entity.Region;
import az.innakhchivan.exception.MapDataNotFoundException;
import az.innakhchivan.exception.RegionNotFoundException;
import az.innakhchivan.repository.MapDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapDataService {
    private final MapDataRepository mapDataRepository;
    private final RegionService regionService;


    public void createMapData(MapDataRequestDto mapDataRequestDto) {
        log.info("Create Request Data: {}", mapDataRequestDto); // ✅ Gələn JSON-u yoxla

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
        mapData.setAzGeneralInfo(mapDataRequestDto.getAzGeneralInfo());
        mapData.setEnGeneralInfo(mapDataRequestDto.getEnGeneralInfo());
        mapData.setRuGeneralInfo(mapDataRequestDto.getRuGeneralInfo());
        mapData.setRegion(regionService.getRegionByUniqueKey(mapDataRequestDto.getRegionUniqueKey()));

        mapDataRepository.save(mapData);
    }

    public void updateMapData(Long id, MapDataRequestDto mapDataRequestDto) {
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
        mapData.setEnGeneralInfo(mapDataRequestDto.getAzGeneralInfo());
        mapData.setAzGeneralInfo(mapDataRequestDto.getAzGeneralInfo());
        mapData.setRuGeneralInfo(mapDataRequestDto.getRuGeneralInfo());
        mapData.setRegion(regionService.getRegionByUniqueKey(mapDataRequestDto.getRegionUniqueKey()));


        mapDataRepository.save(mapData);

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
                .generalInfo(mapData.getMapDatageneralInfo(lang)) // ✅ Yeni methoddan istifadə etdik
                .build();

    }


    public List<MapDataResponseDto> getAllMapData(String lang) {
        return mapDataRepository.findAllByOrderByIdAsc().stream()
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
                        mapData.getMapDatageneralInfo(lang) // ✅ GeneralInfo dilə uyğun qaytarırıq
                ))
                .collect(Collectors.toList());
    }


    public Object getAllMap(String lang) {
        List<MapData> mapDataList = mapDataRepository.findAllWithRegion();

        if (lang == null) {
            return mapDataList.stream()
                    .map(mapData -> new MapDataResponse(
                            mapData.getId(),
                            mapData.getAzTitle(),
                            mapData.getAzDescription(),
                            mapData.getEnTitle(),
                            mapData.getEnDescription(),
                            mapData.getRuTitle(),
                            mapData.getRuDescription(),
                            mapData.getLocation(),
                            mapData.getArea(),
                            mapData.getAverageSalary(),
                            mapData.getPopulation(),
                            mapData.getIconUrl(),
                            new RegionResponseDtoForRelation(
                                    mapData.getRegion().getId(),
                                    mapData.getRegion().getUniqueKey()
                            ),
                            // 🆕 Yeni sahələr əlavə olunur
                            mapData.getSettlementCount(),
                            mapData.getVillageCount(),
                            mapData.getCityAdminArea(),
                            mapData.getDistanceFromBaku(),
                            mapData.getAzGeneralInfo(),
                            mapData.getEnGeneralInfo(),
                            mapData.getRuGeneralInfo()

                    ))
                    .collect(Collectors.toList());
        }

        HashMap<String, Object> responseMapData = new HashMap<>();

        if ("en".equals(lang)) {
             mapDataList.stream().map((mapData) -> {
                         responseMapData.put("id", mapData.getId());
                         responseMapData.put("title", mapData.getEnTitle());
                         responseMapData.put("description", mapData.getEnDescription());
                         responseMapData.put("location", mapData.getLocation());
                         responseMapData.put("area", mapData.getArea());
                         responseMapData.put("averageSalary", mapData.getAverageSalary());
                         responseMapData.put("population", mapData.getPopulation());
                         responseMapData.put("iconUrl", mapData.getIconUrl());
                         responseMapData.put("region", new RegionResponseDtoForRelation(
                                 mapData.getRegion().getId(),
                                 mapData.getRegion().getUniqueKey()
                         ));
                         responseMapData.put("settlementCount", mapData.getSettlementCount());
                         responseMapData.put("villageCount", mapData.getVillageCount());
                         responseMapData.put("cityAdminArea", mapData.getCityAdminArea());
                         responseMapData.put("distanceFromBaku", mapData.getDistanceFromBaku());
                         responseMapData.put("generalInfo", mapData.getEnGeneralInfo());
                        return responseMapData;
             }

                    ).collect(Collectors.toList());

        }

        if ("az".equals(lang)) {
            mapDataList.stream().map((mapData) -> {
                        responseMapData.put("id", mapData.getId());
                        responseMapData.put("title", mapData.getAzTitle());
                        responseMapData.put("description", mapData.getAzDescription());
                        responseMapData.put("location", mapData.getLocation());
                        responseMapData.put("area", mapData.getArea());
                        responseMapData.put("averageSalary", mapData.getAverageSalary());
                        responseMapData.put("population", mapData.getPopulation());
                        responseMapData.put("iconUrl", mapData.getIconUrl());
                        responseMapData.put("region", new RegionResponseDtoForRelation(
                                mapData.getRegion().getId(),
                                mapData.getRegion().getUniqueKey()
                        ));
                        responseMapData.put("settlementCount", mapData.getSettlementCount());
                        responseMapData.put("villageCount", mapData.getVillageCount());
                        responseMapData.put("cityAdminArea", mapData.getCityAdminArea());
                        responseMapData.put("distanceFromBaku", mapData.getDistanceFromBaku());
                        responseMapData.put("generalInfo", mapData.getAzGeneralInfo());
                        return responseMapData;
                    }

            ).collect(Collectors.toList());

        }

        if ("ru".equals(lang)) {
            mapDataList.stream().map((mapData) -> {
                        responseMapData.put("id", mapData.getId());
                        responseMapData.put("title", mapData.getRuTitle());
                        responseMapData.put("description", mapData.getRuDescription());
                        responseMapData.put("location", mapData.getLocation());
                        responseMapData.put("area", mapData.getArea());
                        responseMapData.put("averageSalary", mapData.getAverageSalary());
                        responseMapData.put("population", mapData.getPopulation());
                        responseMapData.put("iconUrl", mapData.getIconUrl());
                        responseMapData.put("region", new RegionResponseDtoForRelation(
                                mapData.getRegion().getId(),
                                mapData.getRegion().getUniqueKey()
                        ));
                        responseMapData.put("settlementCount", mapData.getSettlementCount());
                        responseMapData.put("villageCount", mapData.getVillageCount());
                        responseMapData.put("cityAdminArea", mapData.getCityAdminArea());
                        responseMapData.put("distanceFromBaku", mapData.getDistanceFromBaku());
                        responseMapData.put("generalInfo", mapData.getRuGeneralInfo());
                        return responseMapData;
                    }

            ).collect(Collectors.toList());

        }


        responseMapData.put("message","Incorrect Language");
        return responseMapData;

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
                        mapData.getMapDatageneralInfo(lang)
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
