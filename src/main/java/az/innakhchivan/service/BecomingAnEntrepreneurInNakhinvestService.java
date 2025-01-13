package az.innakhchivan.service;

import az.innakhchivan.dto.request.BecomingAnEntrepreneurInNakhinvestRequestDto;
import az.innakhchivan.dto.response.BecomingAnEntrepreneurInNakhinvestResponseDto;
import az.innakhchivan.dto.response.CategoryResponseDtoForRelation;
import az.innakhchivan.dto.response.EntrepreneurInNakhinvestResponse;
import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import az.innakhchivan.entity.Category;
import az.innakhchivan.exception.BecomingAnEntrepreneurInNakhinvestNotFoundException;
import az.innakhchivan.exception.CategoryNotFoundException;
import az.innakhchivan.repository.BecomingAnEntrepreneurInNakhinvestRepository;
import az.innakhchivan.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BecomingAnEntrepreneurInNakhinvestService {

    private final BecomingAnEntrepreneurInNakhinvestRepository becomingAnEntrepreneurInNakhinvestRepository;
    private final CategoryRepository categoryRepository;

    public void createEntrepreneur(BecomingAnEntrepreneurInNakhinvestRequestDto requestDto) {
        BecomingAnEntrepreneurInNakhinvest nakhinvest = new BecomingAnEntrepreneurInNakhinvest();

        nakhinvest.setAzTitle(requestDto.getAzTitle());
        nakhinvest.setAzDescription(requestDto.getAzDescription());
        nakhinvest.setEnTitle(requestDto.getEnTitle());
        nakhinvest.setEnDescription(requestDto.getEnDescription());
        nakhinvest.setRuTitle(requestDto.getRuTitle());
        nakhinvest.setRuDescription(requestDto.getRuDescription());
        nakhinvest.setIconUrl(requestDto.getIconUrl());

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + requestDto.getCategoryId()));
        nakhinvest.setCategory(category);

        becomingAnEntrepreneurInNakhinvestRepository.save(nakhinvest);
    }

    public void updateEntrepreneur(Long id, BecomingAnEntrepreneurInNakhinvestRequestDto requestDto) {
       BecomingAnEntrepreneurInNakhinvest entrepreneur = becomingAnEntrepreneurInNakhinvestRepository.findById(id).orElseThrow(
                () -> new BecomingAnEntrepreneurInNakhinvestNotFoundException("Becoming An Entrepreneur In Nakhinvest Not Found with Id : " + id));

        entrepreneur.setAzTitle(requestDto.getAzTitle());
        entrepreneur.setAzDescription(requestDto.getAzDescription());
        entrepreneur.setEnTitle(requestDto.getEnTitle());
        entrepreneur.setEnDescription(requestDto.getEnDescription());
        entrepreneur.setRuTitle(requestDto.getRuTitle());
        entrepreneur.setRuDescription(requestDto.getRuDescription());
        entrepreneur.setIconUrl(requestDto.getIconUrl());


        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + requestDto.getCategoryId()));
        entrepreneur.setCategory(category);

        becomingAnEntrepreneurInNakhinvestRepository.save(entrepreneur);
    }

    public List<BecomingAnEntrepreneurInNakhinvestResponseDto> getEntrepreneurAll(String lang) {
        return becomingAnEntrepreneurInNakhinvestRepository.findAllByOrderByIdAsc().stream()
                .map(entrepreneur -> new BecomingAnEntrepreneurInNakhinvestResponseDto(
                        entrepreneur.getId(),
                        entrepreneur.getEntrepreneurTitle(lang),
                        entrepreneur.getEntrepreneurDescription(lang),
                        entrepreneur.getCategory().getCategoryName(lang),
                        entrepreneur.getIconUrl()
                ))
                .collect(Collectors.toList());
    }



    public List<EntrepreneurInNakhinvestResponse> getAll() {
        return becomingAnEntrepreneurInNakhinvestRepository.findAllWithCategory().stream()
                .map(entrepreneur -> new EntrepreneurInNakhinvestResponse(
                        entrepreneur.getId(),
                        entrepreneur.getAzTitle(),
                        entrepreneur.getAzDescription(),
                        entrepreneur.getEnTitle(),
                        entrepreneur.getEnDescription(),
                        entrepreneur.getRuTitle(),
                        entrepreneur.getRuDescription(),
                        entrepreneur.getIconUrl(),
                        new CategoryResponseDtoForRelation(
                                entrepreneur.getCategory().getId()
                        )
                ))
                .collect(Collectors.toList());
    }


    public void deletedEntrepreneur(Long id) {
        BecomingAnEntrepreneurInNakhinvest entrepreneur = becomingAnEntrepreneurInNakhinvestRepository.findById(id).orElseThrow(
                () -> new BecomingAnEntrepreneurInNakhinvestNotFoundException("Becoming An Entrepreneur In Nakhinvest Not Found with Id : " + id)
        );
        becomingAnEntrepreneurInNakhinvestRepository.delete(entrepreneur);
    }

}
