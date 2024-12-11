package az.innakhchivan.service;

import az.innakhchivan.dto.request.BecomingAnEntrepreneurInNakhinvestRequestDto;
import az.innakhchivan.dto.request.IncentiveRequestDto;
import az.innakhchivan.dto.response.BecomingAnEntrepreneurInNakhinvestResponseDto;
import az.innakhchivan.dto.response.IncentiveResponseDto;
import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import az.innakhchivan.entity.Category;
import az.innakhchivan.entity.Incentive;
import az.innakhchivan.exception.BecomingAnEntrepreneurInNakhinvestNotFoundException;
import az.innakhchivan.exception.IncentiveNotFoundException;
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

    public BecomingAnEntrepreneurInNakhinvestResponseDto createEntrepreneur(BecomingAnEntrepreneurInNakhinvestRequestDto requestDto) {
        BecomingAnEntrepreneurInNakhinvest nakhinvest = new BecomingAnEntrepreneurInNakhinvest();

        nakhinvest.setAzTitle(requestDto.getAzTitle());
        nakhinvest.setAzDescription(requestDto.getAzDescription());
        nakhinvest.setEnTitle(requestDto.getEnTitle());
        nakhinvest.setEnDescription(requestDto.getEnDescription());
        nakhinvest.setRuTitle(requestDto.getRuTitle());
        nakhinvest.setRuDescription(requestDto.getRuDescription());
        nakhinvest.setIconUrl(requestDto.getIconUrl());

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + requestDto.getCategoryId()));
        nakhinvest.setCategory(category);

        becomingAnEntrepreneurInNakhinvestRepository.save(nakhinvest);

        return BecomingAnEntrepreneurInNakhinvestResponseDto.builder()
                .id(nakhinvest.getId())
                .build();
    }

    public String updateEntrepreneur(Long id, BecomingAnEntrepreneurInNakhinvestRequestDto requestDto) {
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
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + requestDto.getCategoryId()));
        entrepreneur.setCategory(category);

        becomingAnEntrepreneurInNakhinvestRepository.save(entrepreneur);

        return "Becoming An Entrepreneur Update Success";
    }

    public List<BecomingAnEntrepreneurInNakhinvestResponseDto> getEntrepreneurAll(String lang) {
        return becomingAnEntrepreneurInNakhinvestRepository.findAll().stream()
                .map(entrepreneur -> new BecomingAnEntrepreneurInNakhinvestResponseDto(
                        entrepreneur.getId(),
                        entrepreneur.getEntrepreneurTitle(lang),
                        entrepreneur.getEntrepreneurDescription(lang),
                        entrepreneur.getCategory().getCategoryName(lang),
                        entrepreneur.getIconUrl()
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
