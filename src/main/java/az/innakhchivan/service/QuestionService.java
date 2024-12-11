package az.innakhchivan.service;

import az.innakhchivan.dto.request.QuestionRequestDto;
import az.innakhchivan.dto.response.QuestionResponseDto;
import az.innakhchivan.entity.Question;
import az.innakhchivan.exception.QuestionNotFoundException;
import az.innakhchivan.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionResponseDto createQuestion(QuestionRequestDto questionRequestDto) {

        Question question = new Question();

        question.setAzTitle(questionRequestDto.getAzTitle());
        question.setAzDescription(questionRequestDto.getAzDescription());
        question.setEnTitle(questionRequestDto.getEnTitle());
        question.setEnDescription(questionRequestDto.getEnDescription());
        question.setRuTitle(questionRequestDto.getRuTitle());
        question.setRuDescription(questionRequestDto.getRuDescription());

        questionRepository.save(question);

        return QuestionResponseDto.builder()
                .id(question.getId())
                .build();
    }

    public String updateQuestion(Long id, QuestionRequestDto questionRequestDto) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));


        question.setAzTitle(questionRequestDto.getAzTitle());

        question.setAzDescription(questionRequestDto.getAzDescription());
        question.setEnTitle(questionRequestDto.getEnTitle());
        question.setEnDescription(questionRequestDto.getEnDescription());
        question.setRuTitle(questionRequestDto.getRuTitle());
        question.setRuDescription(questionRequestDto.getRuDescription());

        questionRepository.save(question);

        return "Question update succesfully ";
    }


    public List<QuestionResponseDto> getQuestionAll(String lang) {
        return questionRepository.findAll().stream()
                .map(question -> new QuestionResponseDto(
                        question.getId(),
                        question.getQuestionTitle(lang),
                        question.getQuestionDescription(lang)
                ))
                .collect(Collectors.toList());
    }


    public void deletedQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));

        questionRepository.delete(question);
    }
}
