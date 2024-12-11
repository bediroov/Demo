package az.innakhchivan.controller;

import az.innakhchivan.dto.request.RegionRequestDto;
import az.innakhchivan.dto.response.RegionResponseDto;
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
    public ResponseEntity<RegionResponseDto> createRegion(@RequestBody RegionRequestDto regionRequestDto) {
        RegionResponseDto responseDto = regionService.createRegion(regionRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/deactivate/{uniqueKey}")
    public ResponseEntity<String> deactivateRegion(@PathVariable String  uniqueKey) {
        regionService.deactivateRegion(uniqueKey);
        return new ResponseEntity<>("Region deactivated successfully.", HttpStatus.OK);
    }

    @GetMapping("/status/{uniqueKey}")
    public ResponseEntity<Boolean> isRegionActive(@PathVariable String uniqueKey) {
        boolean isActive = regionService.isRegionActive(uniqueKey);
        return new ResponseEntity<>(isActive, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionResponseDto>> getAllRegions(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<RegionResponseDto> responseDtos = regionService.getAllRegions(lang);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

}
