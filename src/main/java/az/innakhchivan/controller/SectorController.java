package az.innakhchivan.controller;

import az.innakhchivan.dto.request.SectorRequestDto;
import az.innakhchivan.dto.response.SectorResponseDto;
import az.innakhchivan.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sector")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<SectorResponseDto> addSector(@Valid @RequestBody SectorRequestDto sectorRequestDto,
                                                       @RequestParam(required = false, defaultValue = "az") String lang) {
        SectorResponseDto responseDto = sectorService.addSector(sectorRequestDto, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorResponseDto> getSectorById(@PathVariable Long id,
                                                           @RequestParam(required = false, defaultValue = "az") String lang) {
        SectorResponseDto responseDto = sectorService.getSectorById(id, lang);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<SectorResponseDto>> getAllSectors(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<SectorResponseDto> newsList = sectorService.getAllNews(lang);
        return ResponseEntity.ok(newsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorResponseDto> updateSector(@PathVariable Long id,
                                                          @Valid @RequestBody SectorRequestDto sectorRequestDto,
                                                          @RequestParam(required = false, defaultValue = "az") String lang) {
        SectorResponseDto responseDto = sectorService.updateSector(id, sectorRequestDto, lang);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }
}
