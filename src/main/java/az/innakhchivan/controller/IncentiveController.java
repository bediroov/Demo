package az.innakhchivan.controller;

import az.innakhchivan.dto.request.IncentiveRequestDto;
import az.innakhchivan.dto.response.IncentiveResponseDto;
import az.innakhchivan.service.IncentiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incentive")
@RequiredArgsConstructor
public class IncentiveController {

    private final IncentiveService incentiveService;

    @PostMapping
    public ResponseEntity<IncentiveResponseDto> createdIncentive(@RequestBody IncentiveRequestDto incentiveRequestDto) {
        IncentiveResponseDto created = incentiveService.createIncentive(incentiveRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<String> updateIncentive(@PathVariable Long Id,
                                                                @RequestBody IncentiveRequestDto incentiveRequestDto) {
        String responseDto = incentiveService.updateIncentive(Id, incentiveRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<IncentiveResponseDto>> getAllIncentive(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<IncentiveResponseDto> incentiveResponseDto = incentiveService.getIncentiveAll(lang);
        return new ResponseEntity<>(incentiveResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deletedIncentive(@PathVariable Long Id) {
        incentiveService.deletedIncentive(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
