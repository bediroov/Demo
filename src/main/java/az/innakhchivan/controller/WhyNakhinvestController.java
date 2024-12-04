package az.innakhchivan.controller;

import az.innakhchivan.dto.request.WhyNakhinvestRequestDto;
import az.innakhchivan.dto.response.WhyNakhinvestResponseDto;
import az.innakhchivan.service.WhyNakhinvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nakhinvest/")
@RequiredArgsConstructor
public class WhyNakhinvestController {
    private final WhyNakhinvestService whyNakhinvestService;

    @PostMapping
    public ResponseEntity<WhyNakhinvestResponseDto> created(@RequestBody WhyNakhinvestRequestDto requestDto) {
        WhyNakhinvestResponseDto responseDto = whyNakhinvestService.createNakhinvest(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhyNakhinvestResponseDto> updated(@PathVariable Long id, @RequestBody WhyNakhinvestRequestDto requestDto) {
        WhyNakhinvestResponseDto responseDto = whyNakhinvestService.updateNakhinvest(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WhyNakhinvestResponseDto> getNakhinvestById(@PathVariable Long id) {
        WhyNakhinvestResponseDto responseDto = whyNakhinvestService.getNakhinvestById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
