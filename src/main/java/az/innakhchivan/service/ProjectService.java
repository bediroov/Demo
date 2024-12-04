package az.innakhchivan.service;

import az.innakhchivan.dto.request.ProjectRequestDto;
import az.innakhchivan.dto.response.ProjectResponseDto;
import az.innakhchivan.entity.Project;
import az.innakhchivan.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setCategory(projectRequestDto.getCategory());
        project.setProjectName(projectRequestDto.getProjectName());
        project.setDescription(projectRequestDto.getDescription());
        projectRepository.save(project);

        return ProjectResponseDto.builder()
                .id(project.getId())
                .category(project.getCategory())
                .projectName(project.getProjectName())
                .description(project.getDescription())
                .build();
    }
}
