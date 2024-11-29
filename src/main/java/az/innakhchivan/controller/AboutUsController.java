package az.innakhchivan.controller;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.service.AboutUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
public class AboutUsController {

    private final AboutUsService aboutUsService;

    @PostMapping
    public ResponseEntity<AboutResponseDto> createAbout(@RequestBody AboutRequestDto aboutRequestDto) {
        AboutResponseDto created = aboutUsService.createAbout(aboutRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<AboutResponseDto> updateAbout(@PathVariable Long Id, @RequestBody AboutRequestDto aboutRequestDto) {
        AboutResponseDto responseDto = aboutUsService.updateAbout(Id, aboutRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<AboutResponseDto> getAboutById(@PathVariable Long Id) {
        AboutResponseDto aboutResponseDto = aboutUsService.getAboutById(Id);
        return new ResponseEntity<>(aboutResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleted(@PathVariable Long Id) {
        aboutUsService.deletedAbout(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
