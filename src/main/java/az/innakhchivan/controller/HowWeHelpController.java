package az.innakhchivan.controller;

import az.innakhchivan.dto.request.HowWeHelpRequestDto;
import az.innakhchivan.dto.response.HowWeHelpResponseDto;
import az.innakhchivan.entity.HowWeHelp;
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
    public ResponseEntity<Void> created(@RequestBody HowWeHelpRequestDto howWeHelpRequestDto) {
         howWeHelpService.createHowWeHelp(howWeHelpRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<Void> updated(@PathVariable Long Id,
                                          @RequestBody HowWeHelpRequestDto howWeHelpRequestDto) {
       howWeHelpService.update(Id, howWeHelpRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HowWeHelpResponseDto>> getHowWeHelp(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<HowWeHelpResponseDto> howWeHelpResponseDto = howWeHelpService.getHowWeHelp(lang);
        return new ResponseEntity<>(howWeHelpResponseDto, HttpStatus.OK);
    }



    @GetMapping("/all")
    public ResponseEntity<List<HowWeHelp>> getAll() {
        List<HowWeHelp> howWeHelp = howWeHelpService.getAll();
        return new ResponseEntity<>(howWeHelp, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleted(@PathVariable Long Id) {
        howWeHelpService.deletedHowWeHelp(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
