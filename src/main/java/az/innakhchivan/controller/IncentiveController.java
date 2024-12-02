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

    @PatchMapping("/{Id}")
    public ResponseEntity<IncentiveResponseDto> updateIncentive(@PathVariable Long Id, @RequestBody IncentiveRequestDto incentiveRequestDto) {
        IncentiveResponseDto responseDto = incentiveService.updateIncentive(Id, incentiveRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<IncentiveResponseDto> getIncentiveById(@PathVariable Long Id) {
        IncentiveResponseDto incentiveResponseDto = incentiveService.getIncentiveById(Id);
        return new ResponseEntity<>(incentiveResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IncentiveResponseDto>> getAllIncentive() {
        List<IncentiveResponseDto> incentiveResponseDto = incentiveService.getIncentiveAll();
        return new ResponseEntity<>(incentiveResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deletedIncentive(@PathVariable Long Id) {
        incentiveService.deletedIncentive(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
