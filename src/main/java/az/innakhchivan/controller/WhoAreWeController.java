package az.innakhchivan.controller;

import az.innakhchivan.dto.request.WhoAreWeRequestDto;
import az.innakhchivan.dto.response.WhoAreWeResponseDto;
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
    public ResponseEntity<WhoAreWeResponseDto> createWhoAreWe(@RequestBody WhoAreWeRequestDto whoAreWeRequestDto) {
        WhoAreWeResponseDto whoAreWeResponseDto = whoAreWeService.createWhoAreWe(whoAreWeRequestDto);
        return new ResponseEntity<>(whoAreWeResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedWhoAreWe(@PathVariable Long id, @RequestBody WhoAreWeRequestDto whoAreWeRequestDto) {
        String responseDto = whoAreWeService.updateWhoAreWe(id, whoAreWeRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<WhoAreWeResponseDto>> getAllWhoAreWe(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<WhoAreWeResponseDto> responseDtoList = whoAreWeService.getAllWhoAreWe(lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWhoAreWe(@RequestParam Long id) {
        whoAreWeService.deletedWhoAreWe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
