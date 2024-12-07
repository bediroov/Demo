package az.innakhchivan.controller;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.service.AboutUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
public class AboutUsController {

    private final AboutUsService aboutUsService;

    @PostMapping
    public ResponseEntity<AboutResponseDto> createAbout(@RequestBody AboutRequestDto aboutRequestDto,
                                                        @RequestParam(required = false, defaultValue = "az") String lang) {
        AboutResponseDto created = aboutUsService.createAbout(aboutRequestDto, lang);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/{Id}")
    public ResponseEntity<AboutResponseDto> updateAbout(@PathVariable Long Id,
                                                        @RequestBody AboutRequestDto aboutRequestDto,
                                                        @RequestParam(required = false, defaultValue = "az") String lang) {
        AboutResponseDto responseDto = aboutUsService.updateAbout(Id, aboutRequestDto, lang);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<AboutResponseDto> getAboutById(@PathVariable Long Id,  @RequestParam(required = false, defaultValue = "az") String lang) {
        AboutResponseDto aboutResponseDto = aboutUsService.getAboutById(Id, lang);
        return new ResponseEntity<>(aboutResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AboutResponseDto>> getAll(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<AboutResponseDto> aboutResponseDto = aboutUsService.getAboutAll(lang);
        return new ResponseEntity<>(aboutResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleted(@PathVariable Long Id) {
        aboutUsService.deletedAbout(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
