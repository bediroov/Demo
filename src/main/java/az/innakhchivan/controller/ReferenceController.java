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
    public ResponseEntity<Void> created(@RequestBody ReferenceRequestDto referenceRequestDto) {
         referenceService.create(referenceRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedReference(@PathVariable Long id, @RequestBody ReferenceRequestDto referenceRequestDto) {
       referenceService.updateReference(id, referenceRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferenceResponseDto> getReferenceById(@PathVariable Long id) {
      ReferenceResponseDto referenceResponse = referenceService.getReferenceById(id);
        return new ResponseEntity<>(referenceResponse ,HttpStatus.OK);
    }



    @GetMapping("/all")
    public ResponseEntity<List<ReferenceResponseDto>> getAllReferences() {
        List<ReferenceResponseDto> responseDto = referenceService.getAllReferences();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedReference(@PathVariable Long id) {
        referenceService.deleteReference(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
