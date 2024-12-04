package az.innakhchivan.controller;

import az.innakhchivan.dto.request.ProjectRequestDto;
import az.innakhchivan.dto.response.ProjectResponseDto;
import az.innakhchivan.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDto> addedProject (@RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto projectResponseDto = projectService.addProject(projectRequestDto);
        return new ResponseEntity<>(projectResponseDto, HttpStatus.CREATED);
    }
}
