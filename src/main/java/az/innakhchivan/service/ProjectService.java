package az.innakhchivan.service;

import az.innakhchivan.dto.request.ProjectRequestDto;
import az.innakhchivan.dto.response.ProjectResponseDto;
import az.innakhchivan.entity.Project;
import az.innakhchivan.exception.ProjectNotFoundException;
import az.innakhchivan.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    Project project = new Project();

    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto, String lang) {

        switch (lang) {
            case "en":
                project.setEnCategory(projectRequestDto.getCategory());
                project.setEnProjectName(projectRequestDto.getProjectName());
                project.setEnDescription(projectRequestDto.getDescription());
                break;
            case "ru":
                project.setRuCategory(projectRequestDto.getCategory());
                project.setRuProjectName(projectRequestDto.getProjectName());
                project.setRuDescription(projectRequestDto.getDescription());
                break;
            default:
                project.setAzCategory(projectRequestDto.getCategory());
                project.setAzProjectName(projectRequestDto.getProjectName());
                project.setAzDescription(projectRequestDto.getDescription());
                break;
        }
        projectRepository.save(project);

        return ProjectResponseDto.builder()
                .id(project.getId())
                .category(project.getProjectCategory(lang))
                .projectName(project.getProjectName(lang))
                .description(project.getProjectDescription(lang))
                .createdAt(project.getCreatedAt())
                .build();
    }

    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto, String lang) {

        project = projectRepository.findById(id).orElseThrow(
        ()-> new ProjectNotFoundException(String.format("Project with id %s not found", id)));

        switch (lang) {
            case "en":
                project.setEnCategory(projectRequestDto.getCategory());
                project.setEnProjectName(projectRequestDto.getProjectName());
                project.setEnDescription(projectRequestDto.getDescription());
                break;
            case "ru":
                project.setRuCategory(projectRequestDto.getCategory());
                project.setRuProjectName(projectRequestDto.getProjectName());
                project.setRuDescription(projectRequestDto.getDescription());
                break;
            default:
                project.setAzCategory(projectRequestDto.getCategory());
                project.setAzProjectName(projectRequestDto.getProjectName());
                project.setAzDescription(projectRequestDto.getDescription());
                break;
        }
        projectRepository.save(project);

        return ProjectResponseDto.builder()
                .id(project.getId())
                .category(project.getProjectCategory(lang))
                .projectName(project.getProjectName(lang))
                .description(project.getProjectDescription(lang))
                .createdAt(project.getCreatedAt())
                .build();


    }

    public ProjectResponseDto getProjectById(Long id,String lang) {
        project = projectRepository.findById(id).orElseThrow(
                ()-> new ProjectNotFoundException(String.format("Project with id %s not found", id)));

        return ProjectResponseDto.builder()
                .id(project.getId())
                .category(project.getProjectCategory(lang))
                .projectName(project.getProjectName(lang))
                .description(project.getProjectDescription(lang))
                .createdAt(project.getCreatedAt())
                .build();
    }

    public List<ProjectResponseDto> getAllProjects(String lang) {
        return projectRepository.findAll().stream()
                .map(x -> new ProjectResponseDto(
                        x.getId(),
                        x.getProjectCategory(lang),
                        x.getProjectName(lang),
                        x.getProjectDescription(lang),
                        x.getCreatedAt())
                )
                .collect(Collectors.toList());
    }

    public void deleteProject(Long id) {
        project = projectRepository.findById(id).orElseThrow(
                ()-> new ProjectNotFoundException(String.format("Project with id %s not found", id))
        );
        projectRepository.delete(project);
    }
}
