package az.innakhchivan.service;

import az.innakhchivan.dto.request.SubmitProjectRequestDto;
import az.innakhchivan.dto.response.SubmitProjectResponseDto;
import az.innakhchivan.entity.SubmitProject;
import az.innakhchivan.exception.SubmitProjectNotFoundException;
import az.innakhchivan.repository.SubmitProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmitProjectService {
    private final SubmitProjectRepository submitProjectRepository;
    private final EmailService emailService;

    public void createSubmitProject(SubmitProjectRequestDto submitProjectRequestDto) {

        SubmitProject submitProject = new SubmitProject();
        submitProject.setName(submitProjectRequestDto.getName());
        submitProject.setSurname(submitProjectRequestDto.getSurname());
        submitProject.setPhone(submitProjectRequestDto.getPhone());
        submitProject.setEmail(submitProjectRequestDto.getEmail());
        submitProject.setFileUrl(submitProjectRequestDto.getFileUrl());

        submitProjectRepository.save(submitProject);
    }

    public List<SubmitProjectResponseDto> getAllSubmitProjects() {
        return submitProjectRepository.findAllByOrderByIdAsc().stream()
                .map(x -> new SubmitProjectResponseDto(
                        x.getId(),
                        x.getName(),
                        x.getSurname(),
                        x.getPhone(),
                        x.getEmail(),
                        x.getMessage(),
                        x.getFileUrl(),
                        x.getSubmitDate()
                ))
                .collect(Collectors.toList());
    }


    public void deleteSubmitProject(Long id) {
        SubmitProject submitProject = submitProjectRepository.findById(id).orElseThrow(
                () -> new SubmitProjectNotFoundException("Submit project not found with  id: " + id)
        );
        submitProjectRepository.delete(submitProject);
    }


    public String submitProjectAcceptStatus(String email, Boolean accepted) {
        if (email == null) {
            throw new IllegalArgumentException("Email dəyəri boş ola bilməz.");
        }
        String subject = "Project Status Notification";
        String text;

        if (accepted) {
            text = "Təbrik edirik! Sizin layihəniz qəbul edildi.!";
        } else {
            text = "Təəssüf ki, Sizin layihəniz qəbul edilmədi.!";
        }

        return emailService.sendEmail(email, subject, text);

    }

}
