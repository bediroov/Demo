package az.innakhchivan.controller;

import az.innakhchivan.dto.request.ProjectRequestDto;
import az.innakhchivan.dto.response.ProjectResponse;
import az.innakhchivan.dto.response.ProjectResponseDto;
import az.innakhchivan.entity.Project;
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
    public ResponseEntity<Void> addProject(@RequestBody ProjectRequestDto projectRequestDto) {
         projectService.addProject(projectRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable Long id,
                                              @RequestBody ProjectRequestDto projectRequestDto) {
        projectService.updateProject(id, projectRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<ProjectResponseDto> projects = projectService.getAllProjects(lang);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponse>> getAll() {
        List<ProjectResponse> projects = projectService.getAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
