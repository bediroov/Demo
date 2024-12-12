package az.innakhchivan.controller;

import az.innakhchivan.dto.request.RegionRequestDto;
import az.innakhchivan.dto.response.RegionResponse;
import az.innakhchivan.dto.response.RegionResponseDto;
import az.innakhchivan.entity.Region;
import az.innakhchivan.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public ResponseEntity<Void> createRegion(@RequestBody RegionRequestDto regionRequestDto) {
        regionService.createRegion(regionRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/deactivate/{uniqueKey}")
    public ResponseEntity<Void> deactivateRegion(@PathVariable String  uniqueKey) {
        regionService.deactivateRegion(uniqueKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status/{uniqueKey}")
    public ResponseEntity<Boolean> isRegionActive(@PathVariable String uniqueKey) {
        boolean isActive = regionService.isRegionActive(uniqueKey);
        return new ResponseEntity<>(isActive, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RegionResponse>> getAllRegions(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<RegionResponse> responseDtos = regionService.getAllRegions(lang);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionResponseDto>> getAll() {
        List<RegionResponseDto> responseDtos = regionService.getAllRegionsWithMapData();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

}
