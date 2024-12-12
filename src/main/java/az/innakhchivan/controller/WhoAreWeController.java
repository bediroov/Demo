package az.innakhchivan.controller;

import az.innakhchivan.dto.request.WhoAreWeRequestDto;
import az.innakhchivan.dto.response.WhoAreWeResponse;
import az.innakhchivan.dto.response.WhoAreWeResponseDto;
import az.innakhchivan.entity.WhoAreWe;
import az.innakhchivan.service.WhoAreWeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/who-are-we")
@RequiredArgsConstructor
public class WhoAreWeController {
    private final WhoAreWeService whoAreWeService;

    @PostMapping
    public ResponseEntity<Void> createWhoAreWe(@RequestBody WhoAreWeRequestDto whoAreWeRequestDto) {
         whoAreWeService.createWhoAreWe(whoAreWeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedWhoAreWe(@PathVariable Long id, @RequestBody WhoAreWeRequestDto whoAreWeRequestDto) {
       whoAreWeService.updateWhoAreWe(id, whoAreWeRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WhoAreWeResponse> getWhoAreWeById(@PathVariable Long id) {
        WhoAreWeResponse responseDtoList = whoAreWeService.getWhoAreWeById(id);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<WhoAreWeResponseDto>> getAllWhoAreWe(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<WhoAreWeResponseDto> responseDtoList = whoAreWeService.getAllWhoAreWe(lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WhoAreWeResponse>> getAll() {
        List<WhoAreWeResponse> responseDtoList = whoAreWeService.getAll();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedWhoAreWe(@PathVariable Long id) {
        whoAreWeService.deletedWhoAreWe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
