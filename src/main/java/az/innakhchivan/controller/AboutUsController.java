package az.innakhchivan.controller;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.entity.AboutUs;
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
    public ResponseEntity<Void> createAbout(@RequestBody AboutRequestDto aboutRequestDto) {
         aboutUsService.createAbout(aboutRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Void> updateAbout(@PathVariable Long Id,
                                                        @RequestBody AboutRequestDto aboutRequestDto) {
        aboutUsService.updateAbout(Id, aboutRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AboutResponseDto>> getAbout(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<AboutResponseDto> aboutResponseDto = aboutUsService.getAbout(lang);
        return new ResponseEntity<>(aboutResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleted(@PathVariable Long Id) {
        aboutUsService.deletedAbout(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AboutUs>> getAllAbout() {
        List<AboutUs> aboutResponseDto = aboutUsService.getAllAbout();
        return new ResponseEntity<>(aboutResponseDto, HttpStatus.OK);
    }

}
