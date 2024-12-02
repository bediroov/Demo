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
        question.setTitle(questionRequestDto.getTitle());
        question.setDescription(questionRequestDto.getDescription());
        questionRepository.save(question);

        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getDescription()
                );
    }

    public QuestionResponseDto updateQuestion(Long id, QuestionRequestDto questionRequestDto) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));
        question.setTitle(questionRequestDto.getTitle());
        question.setDescription(questionRequestDto.getDescription());

        Question question1 = questionRepository.save(question);

        return new QuestionResponseDto(
                question1.getId(),
                question1.getTitle(),
                question1.getDescription());
    }

    public QuestionResponseDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));
        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getDescription()
        );
    }

    public List<QuestionResponseDto> getQuestionAll() {
        return questionRepository.findAll().stream()
                .map(question -> new QuestionResponseDto(
                        question.getId(),
                        question.getTitle(),
                        question.getDescription()
                ))
                .collect(Collectors.toList());
    }

    public void deletedQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));

        questionRepository.delete(question);
    }
}
