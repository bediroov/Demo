package az.innakhchivan.controller;

import az.innakhchivan.dto.request.QuestionRequestDto;
import az.innakhchivan.dto.response.QuestionResponseDto;
import az.innakhchivan.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

private final QuestionService questionService;
    @PostMapping
    public ResponseEntity<QuestionResponseDto> createdQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        QuestionResponseDto created = questionService.createQuestion(questionRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(@PathVariable Long Id, @RequestBody QuestionRequestDto questionRequestDto) {
        QuestionResponseDto responseDto = questionService.updateQuestion(Id, questionRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable Long Id) {
        QuestionResponseDto questionResponseDto = questionService.getQuestionById(Id);
        return new ResponseEntity<>(questionResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestion() {
        List<QuestionResponseDto> questionResponseDto = questionService.getQuestionAll();
        return new ResponseEntity<>(questionResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deletedQuestion(@PathVariable Long Id) {
        questionService.deletedQuestion(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
