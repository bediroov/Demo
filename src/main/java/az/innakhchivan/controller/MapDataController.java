package az.innakhchivan.controller;

import az.innakhchivan.dto.request.MapDataRequestDto;
import az.innakhchivan.dto.response.MapDataResponse;
import az.innakhchivan.dto.response.MapDataResponseDto;
import az.innakhchivan.entity.MapData;
import az.innakhchivan.service.MapDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/map-data")
@RequiredArgsConstructor
@Slf4j
public class MapDataController {
    private final MapDataService mapDataService;

    @PostMapping
    public ResponseEntity<Void> createdMapData(@RequestBody MapDataRequestDto requestDto) {
         mapDataService.createMapData(requestDto);
        log.info("🔐 controler filter chain initializing...");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatedMapData(@PathVariable Long id,
                                                 @RequestBody MapDataRequestDto mapDataRequestDto) {

        mapDataService.updateMapData(id, mapDataRequestDto);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDataResponseDto> getMapDataById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "az") String lang) {
        MapDataResponseDto mapData = mapDataService.getMapDataById(id, lang);
        return new ResponseEntity<>(mapData, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MapDataResponseDto>> getAllMapData(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<MapDataResponseDto> mapDataResponseDto = mapDataService.getAllMapData(lang);
        return new ResponseEntity<>(mapDataResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MapDataResponse>> getAll() {
        List<MapDataResponse> mapDataResponse = mapDataService.getAllMap();
        return new ResponseEntity<>(mapDataResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedMapData(@PathVariable Long id) {
        mapDataService.deleteMapData(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/region/{uniqueKey}")
    public ResponseEntity<List<MapDataResponseDto>> getMapDataByRegionUniqueKey(@PathVariable String uniqueKey,
                                                                                @RequestParam(required = false, defaultValue = "az") String lang) {
        List<MapDataResponseDto>  responseDtoList = mapDataService.getMapDataByRegionUniqueKey(uniqueKey, lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

}
