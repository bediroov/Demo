package az.innakhchivan.service;

import az.innakhchivan.dto.request.ProjectRequestDto;
import az.innakhchivan.dto.response.*;
import az.innakhchivan.entity.BaseEntity;
import az.innakhchivan.entity.Category;
import az.innakhchivan.entity.Project;
import az.innakhchivan.exception.CategoryNotFoundException;
import az.innakhchivan.exception.ProjectNotFoundException;
import az.innakhchivan.repository.CategoryRepository;
import az.innakhchivan.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;


    public void addProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();

        project.setAzTitle(projectRequestDto.getAzTitle());
        project.setAzDescription(projectRequestDto.getAzDescription());
        project.setEnTitle(projectRequestDto.getEnTitle());
        project.setEnDescription(projectRequestDto.getEnDescription());
        project.setRuTitle(projectRequestDto.getRuTitle());
        project.setRuDescription(projectRequestDto.getRuDescription());
        project.setImageUrl(projectRequestDto.getImageUrl());

        Category category = categoryRepository.findById(projectRequestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + projectRequestDto.getCategoryId()));
        project.setCategory(category);

        projectRepository.save(project);
    }

    public void updateProject(Long id, ProjectRequestDto projectRequestDto) {

       Project project = projectRepository.findById(id).orElseThrow(
        ()-> new ProjectNotFoundException(String.format("Project with id %s not found", id)));

        project.setAzTitle(projectRequestDto.getAzTitle());
        project.setAzDescription(projectRequestDto.getAzDescription());
        project.setEnTitle(projectRequestDto.getEnTitle());
        project.setEnDescription(projectRequestDto.getEnDescription());
        project.setRuTitle(projectRequestDto.getRuTitle());
        project.setRuDescription(projectRequestDto.getRuDescription());
        project.setImageUrl(projectRequestDto.getImageUrl());

        Category category = categoryRepository.findById(projectRequestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + projectRequestDto.getCategoryId()));
        project.setCategory(category);

        projectRepository.save(project);

    }


    public List<ProjectResponseDto> getAllProjects(String lang) {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectResponseDto(
                        project.getId(),
                        project.getProjectTitle(lang),
                        project.getProjectDescription(lang),
                        project.getImageUrl(),
                        project.getCategory().getCategoryName(lang),
                        project.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<ProjectResponse> getAll() {
        return projectRepository.findAllWithCategory().stream()
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getAzTitle(),
                        project.getAzDescription(),
                        project.getEnTitle(),
                        project.getEnDescription(),
                        project.getRuTitle(),
                        project.getRuDescription(),
                        project.getImageUrl(),
                        new CategoryResponseDtoForRelation(
                                project.getCategory().getId()
                        ),
                        project.getCreatedAt(),
                        project.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }






    public void deleteProject(Long id) {
       Project project = projectRepository.findById(id).orElseThrow(
                ()-> new ProjectNotFoundException(String.format("Project with id %s not found", id))
        );
        projectRepository.delete(project);
    }
}
