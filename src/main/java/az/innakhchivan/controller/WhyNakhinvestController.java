package az.innakhchivan.controller;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.SectorResponseDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponse;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
import az.innakhchivan.entity.WhyNakhinvest;
import az.innakhchivan.service.WhyNakhinvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/why-nakhinvest")
@RequiredArgsConstructor
public class WhyNakhinvestController {
    private final WhyNakhinvestService whyNakhinvestService;

    @PostMapping
    public ResponseEntity<Void> created(@RequestBody WhyNakhinvestRequestDto requestDto) {
          whyNakhinvestService.createNakhinvest(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updated(@PathVariable Long id, @RequestBody WhyNakhinvestRequestDto requestDto) {
      whyNakhinvestService.updateNakhinvest(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WhyNakhinvestResponse> getWhyNakhinvestById(@PathVariable Long id) {
        WhyNakhinvestResponse newsList = whyNakhinvestService.getWhyNakhinvestById(id);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WhyNakhinvestResponseDto>> getAllWhyNakhinvest(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<WhyNakhinvestResponseDto> newsList = whyNakhinvestService.getAllNakhinvest(lang);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedNkhinvest(@PathVariable Long id) {
        whyNakhinvestService.deletedNakhinvest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<WhyNakhinvestResponse>> getAll() {
        List<WhyNakhinvestResponse> responseList = whyNakhinvestService.getAll();
        return new ResponseEntity<>(responseList,HttpStatus.OK);
    }

}
