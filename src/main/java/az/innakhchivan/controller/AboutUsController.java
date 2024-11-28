package az.innakhchivan.controller;

import az.innakhchivan.dto.request.AboutRequestDto;
import az.innakhchivan.dto.response.AboutResponseDto;
import az.innakhchivan.service.AboutUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
public class AboutUsController {

    private final AboutUsService aboutUsService;

    @PostMapping
    public ResponseEntity<AboutResponseDto> createAbout(AboutRequestDto aboutRequestDto) {
        AboutResponseDto created = aboutUsService.createAbout(aboutRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
