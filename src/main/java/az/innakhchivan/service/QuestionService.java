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

    public void createQuestion(QuestionRequestDto questionRequestDto) {

        Question question = new Question();

        question.setAzTitle(questionRequestDto.getAzTitle());
        question.setAzDescription(questionRequestDto.getAzDescription());
        question.setEnTitle(questionRequestDto.getEnTitle());
        question.setEnDescription(questionRequestDto.getEnDescription());
        question.setRuTitle(questionRequestDto.getRuTitle());
        question.setRuDescription(questionRequestDto.getRuDescription());

        questionRepository.save(question);
    }

    public void updateQuestion(Long id, QuestionRequestDto questionRequestDto) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));


        question.setAzTitle(questionRequestDto.getAzTitle());

        question.setAzDescription(questionRequestDto.getAzDescription());
        question.setEnTitle(questionRequestDto.getEnTitle());
        question.setEnDescription(questionRequestDto.getEnDescription());
        question.setRuTitle(questionRequestDto.getRuTitle());
        question.setRuDescription(questionRequestDto.getRuDescription());

        questionRepository.save(question);
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

    public List<Question> getAll() {
        return questionRepository.findAll().stream()
                .map(question -> new Question(
                        question.getId(),
                        question.getAzTitle(),
                        question.getAzDescription(),
                        question.getEnTitle(),
                        question.getEnDescription(),
                        question.getRuTitle(),
                        question.getRuDescription()
                ))
                .collect(Collectors.toList());
    }


    public void deletedQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));

        questionRepository.delete(question);
    }
}
