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

    Question question = new Question();

    public QuestionResponseDto createQuestion(QuestionRequestDto questionRequestDto, String lang) {

        switch (lang) {
            case "az":
                question.setAzTitle(questionRequestDto.getTitle());
                question.setAzDescription(questionRequestDto.getDescription());
                break;
            case "en":
                question.setEnTitle(questionRequestDto.getTitle());
                question.setEnDescription(questionRequestDto.getDescription());
                break;
            case "ru":
                question.setRuTitle(questionRequestDto.getTitle());
                question.setRuDescription(questionRequestDto.getDescription());
                break;
            default:
                throw new IllegalArgumentException("Unsupported language " + lang);
        }

        questionRepository.save(question);

        return QuestionResponseDto.builder()
                .id(question.getId())
                .title(question.getQuestionTitle(lang))
                .description(question.getQuestionDescription(lang))
                .build();
    }

    public QuestionResponseDto updateQuestion(Long id, QuestionRequestDto questionRequestDto, String lang) {
        question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));


        switch (lang) {
            case "en":
                question.setEnTitle(questionRequestDto.getTitle());
                question.setEnDescription(questionRequestDto.getDescription());
                break;
            case "ru":
                question.setRuTitle(questionRequestDto.getTitle());
                question.setRuDescription(questionRequestDto.getDescription());
                break;
            default:
                question.setAzTitle(questionRequestDto.getTitle());
                question.setAzDescription(questionRequestDto.getDescription());
                break;
        }

        questionRepository.save(question);

        return QuestionResponseDto.builder()
                .id(question.getId())
                .title(question.getQuestionTitle(lang))
                .description(question.getQuestionDescription(lang))
                .build();
    }

    public QuestionResponseDto getQuestionById(Long id, String lang) {
        question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));


        return QuestionResponseDto.builder()
                .id(question.getId())
                .title(question.getQuestionTitle(lang))
                .description(question.getQuestionDescription(lang))
                .build();
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
        question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question not found Id : " + id));

        questionRepository.delete(question);
    }
}
