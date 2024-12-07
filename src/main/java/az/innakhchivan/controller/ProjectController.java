package az.innakhchivan.controller;

import az.innakhchivan.dto.request.ProjectRequestDto;
import az.innakhchivan.dto.response.ProjectResponseDto;
import az.innakhchivan.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDto> addedProject (@RequestBody ProjectRequestDto projectRequestDto,
                                                            @RequestParam(required = false, defaultValue = "az") String lang) {
        ProjectResponseDto projectResponseDto = projectService.addProject(projectRequestDto, lang);
        return new ResponseEntity<>(projectResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id,
                                                             @RequestParam(required = false, defaultValue = "az") String lang) {
        ProjectResponseDto projectResponseDto = projectService.getProjectById(id, lang);
        return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjectResponseDto> updatedProject(@PathVariable Long id,
                                                             @RequestBody ProjectRequestDto projectRequestDto,
                                                             @RequestParam(required = false, defaultValue = "az") String lang) {
        ProjectResponseDto projectResponseDto = projectService.updateProject(id, projectRequestDto, lang);
        return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<ProjectResponseDto> projectResponseDto = projectService.getAllProjects(lang);
        return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
