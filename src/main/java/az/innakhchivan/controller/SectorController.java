package az.innakhchivan.controller;

import az.innakhchivan.dto.request.SectorRequestDto;
import az.innakhchivan.dto.response.SectorResponse;
import az.innakhchivan.dto.response.SectorResponseDto;
import az.innakhchivan.entity.Sector;
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
    public ResponseEntity<Void> addSector(@Valid @RequestBody SectorRequestDto sectorRequestDto) {
        sectorService.addSector(sectorRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SectorResponseDto>> getAllSectors(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<SectorResponseDto> newsList = sectorService.getAllSector(lang);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<SectorResponse>> getAll() {
        List<SectorResponse> newsList = sectorService.getAll();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSector(@PathVariable Long id,
                                                          @Valid @RequestBody SectorRequestDto sectorRequestDto) {
      sectorService.updateSector(id, sectorRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
