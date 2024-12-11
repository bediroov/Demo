package az.innakhchivan.controller;

import az.innakhchivan.dto.request.ReferenceRequestDto;
import az.innakhchivan.dto.response.ReferenceResponseDto;
import az.innakhchivan.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reference")
@RequiredArgsConstructor
public class ReferenceController {
    private final ReferenceService referenceService;

    @PostMapping
    public ResponseEntity<ReferenceResponseDto> created(@RequestBody ReferenceRequestDto referenceRequestDto) {
        ReferenceResponseDto responseDto= referenceService.create(referenceRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReferenceResponseDto>> getAllReferences() {
        List<ReferenceResponseDto> responseDto = referenceService.getAllReferences();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
