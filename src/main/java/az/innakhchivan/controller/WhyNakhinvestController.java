package az.innakhchivan.controller;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
import az.innakhchivan.service.WhyNakhinvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/why-nakhinvest/")
@RequiredArgsConstructor
public class WhyNakhinvestController {
    private final WhyNakhinvestService whyNakhinvestService;

    @PostMapping
    public ResponseEntity<WhyNakhinvestResponseDto> created(@RequestBody WhyNakhinvestRequestDto requestDto,
                                                            @RequestParam(required = false, defaultValue = "az") String lang) {
        WhyNakhinvestResponseDto responseDto = whyNakhinvestService.createNakhinvest(requestDto, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhyNakhinvestResponseDto> updated(@PathVariable Long id, @RequestBody WhyNakhinvestRequestDto requestDto,
                                                            @RequestParam(required = false, defaultValue = "az") String lang) {
        WhyNakhinvestResponseDto responseDto = whyNakhinvestService.updateNakhinvest(id, requestDto, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WhyNakhinvestResponseDto> getNakhinvestById(@PathVariable Long id,
                                                                      @RequestParam(required = false, defaultValue = "az") String lang) {
        WhyNakhinvestResponseDto responseDto = whyNakhinvestService.getNakhinvestById(id, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedNkhinvest(@PathVariable Long id) {
        whyNakhinvestService.deletedNakhinvest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
