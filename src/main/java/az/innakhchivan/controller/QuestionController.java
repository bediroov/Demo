package az.innakhchivan.controller;

import az.innakhchivan.dto.request.QuestionRequestDto;
import az.innakhchivan.dto.response.QuestionResponseDto;
import az.innakhchivan.entity.Question;
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
    public ResponseEntity<Void> createdQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
         questionService.createQuestion(questionRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long Id,
                                               @RequestBody QuestionRequestDto questionRequestDto) {
        questionService.updateQuestion(Id, questionRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestion(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<QuestionResponseDto> questionResponseDto = questionService.getQuestionAll(lang);
        return new ResponseEntity<>(questionResponseDto, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAll() {
        List<Question> questionResponse = questionService.getAll();
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deletedQuestion(@PathVariable Long Id) {
        questionService.deletedQuestion(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
