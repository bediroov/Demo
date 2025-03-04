package az.innakhchivan.controller;

import az.innakhchivan.dto.request.MapDataRequestDto;
import az.innakhchivan.dto.response.MapDataResponse;
import az.innakhchivan.dto.response.MapDataResponseDto;
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

@Slf4j // 📌 Lombok log əlavə edir

public class MapDataController {
    private final MapDataService mapDataService;

    @PostMapping
    public ResponseEntity<MapDataResponse> createdMapData(@RequestBody MapDataRequestDto requestDto) {
        MapDataResponse responseDto = mapDataService.createMapData(requestDto);
        log.info("🔐 controler filter chain initializing...");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedMapData(@PathVariable Long id,
                                                 @RequestBody MapDataRequestDto mapDataRequestDto) {

        String responseDto = mapDataService.updateMapData(id, mapDataRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MapDataResponseDto> getMapDataById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "az") String lang) {
        MapDataResponseDto mapData = mapDataService.getMapDataById(id, lang);
        return new ResponseEntity<>(mapData, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MapDataResponseDto>> getAllMapData(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<MapDataResponseDto> mapDataResponseDto = mapDataService.getAllMapData(lang);
        return new ResponseEntity<>(mapDataResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedMapData(@PathVariable Long id) {
        mapDataService.deleteMapData(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/region/{uniqueKey}")
    public ResponseEntity<List<MapDataResponseDto>> getMapDataByRegionUniqueKey(@PathVariable String uniqueKey,
                                                                                @RequestParam(required = false, defaultValue = "az") String lang) {
        List<MapDataResponseDto> responseDtoList = mapDataService.getMapDataByRegionUniqueKey(uniqueKey, lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

}
