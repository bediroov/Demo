package az.innakhchivan.controller;

import az.innakhchivan.dto.request.HowWeHelpRequestDto;
import az.innakhchivan.dto.response.HowWeHelpResponseDto;
import az.innakhchivan.service.HowWeHelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/how-we-help")
@RequiredArgsConstructor
public class HowWeHelpController {

    private final HowWeHelpService howWeHelpService;

    @PostMapping
    public ResponseEntity<HowWeHelpResponseDto> created(@RequestBody HowWeHelpRequestDto howWeHelpRequestDto) {
        HowWeHelpResponseDto created = howWeHelpService.createHowWeHelp(howWeHelpRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<String> updated(@PathVariable Long Id,
                                          @RequestBody HowWeHelpRequestDto howWeHelpRequestDto) {
        String responseDto = howWeHelpService.update(Id, howWeHelpRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HowWeHelpResponseDto>> getAbout(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<HowWeHelpResponseDto> howWeHelpResponseDto = howWeHelpService.getHowWeHelp(lang);
        return new ResponseEntity<>(howWeHelpResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleted(@PathVariable Long Id) {
        howWeHelpService.deletedHowWeHelp(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
