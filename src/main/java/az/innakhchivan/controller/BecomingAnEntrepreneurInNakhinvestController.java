package az.innakhchivan.controller;

import az.innakhchivan.dto.request.BecomingAnEntrepreneurInNakhinvestRequestDto;
import az.innakhchivan.dto.response.BecomingAnEntrepreneurInNakhinvestResponse;
import az.innakhchivan.dto.response.BecomingAnEntrepreneurInNakhinvestResponseDto;
import az.innakhchivan.dto.response.EntrepreneurInNakhinvestResponse;
import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import az.innakhchivan.service.BecomingAnEntrepreneurInNakhinvestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/becoming-an-entrepreneur-in-nakhinvest")
@RequiredArgsConstructor
public class BecomingAnEntrepreneurInNakhinvestController {

    private final BecomingAnEntrepreneurInNakhinvestService becomingAnEntrepreneurInNakhinvestService;

    @PostMapping
    public ResponseEntity<Void> createdEntrepreneur(@Valid @RequestBody BecomingAnEntrepreneurInNakhinvestRequestDto requestDto) {
         becomingAnEntrepreneurInNakhinvestService.createEntrepreneur(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntrepreneur(@PathVariable Long id, @Valid @RequestBody BecomingAnEntrepreneurInNakhinvestRequestDto requestDto) {
        becomingAnEntrepreneurInNakhinvestService.updateEntrepreneur(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<BecomingAnEntrepreneurInNakhinvestResponseDto>> getAllEntrepreneur(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<BecomingAnEntrepreneurInNakhinvestResponseDto> newsList = becomingAnEntrepreneurInNakhinvestService.getEntrepreneurAll(lang);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EntrepreneurInNakhinvestResponse>> getAll() {
        List<EntrepreneurInNakhinvestResponse> newsList = becomingAnEntrepreneurInNakhinvestService.getAll();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrepreneur(@PathVariable Long id) {
        becomingAnEntrepreneurInNakhinvestService.deletedEntrepreneur(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
