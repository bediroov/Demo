package az.innakhchivan.controller;

import az.innakhchivan.dto.request.SubmitProjectRequestDto;
import az.innakhchivan.dto.response.SubmitProjectResponseDto;
import az.innakhchivan.service.SubmitProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/submit-project")
@RequiredArgsConstructor
public class SubmitProjectController {
    private final SubmitProjectService submitProjectService;

    @PostMapping
    public ResponseEntity<Void> createdSubmitProject(@RequestBody SubmitProjectRequestDto submitProjectRequestDto) {
        submitProjectService.createSubmitProject(submitProjectRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubmitProjectResponseDto>> getAllSubmitProjects() {
        List<SubmitProjectResponseDto> submitProjects = submitProjectService.getAllSubmitProjects();
        return new ResponseEntity<>(submitProjects, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedSubmitProject(@PathVariable Long id) {
        submitProjectService.deleteSubmitProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/accept-status")
    public ResponseEntity<String> submitProjectAcceptStatus(@RequestParam String email, @RequestParam Boolean accepted) {
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Email formatı yanlışdır.");
        }

        try {
            String status = submitProjectService.submitProjectAcceptStatus(email, accepted);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new  ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
