package az.innakhchivan.service;

import az.innakhchivan.dto.response.SearchResponseDto;
import az.innakhchivan.entity.*;
import az.innakhchivan.repository.*;
import az.innakhchivan.utility.GlobalSearchSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GlobalSearchService {

    private final NewsRepository newsRepository;
    private final AboutUsRepository aboutUsRepository;
    private final BecomingAnEntrepreneurInNakhinvestRepository entrepreneurRepository;
    private final HowWeHelpRepository howWeHelpRepository;
    private final IncentiveRepository incentiveRepository;
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;
    private final SectorRepository sectorRepository;
    private final WhoAreWeRepository whoAreWeRepository;
    private final WhyNakhinvestRepository whyNakhinvestRepository;

    public Map<String, Object> search(String keyword, String lang, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<News> newsSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<News> newsPage = newsRepository.findAll(newsSpec, pageable);
        List<SearchResponseDto> newsResults = newsPage.stream().map(news -> new SearchResponseDto(
                news.getId(),
                news.getNewsTitle(lang),
                "news"
        )).collect(Collectors.toList());

        Specification<AboutUs> aboutUsSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<AboutUs> aboutUsPage = aboutUsRepository.findAll(aboutUsSpec, pageable);
        List<SearchResponseDto> aboutUsResults = aboutUsPage.stream().map(aboutUs -> new SearchResponseDto(
                aboutUs.getId(),
                aboutUs.getAboutUsTitle(lang),
                "aboutUs"
        )).collect(Collectors.toList());

        Specification<BecomingAnEntrepreneurInNakhinvest> entrepreneurSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<BecomingAnEntrepreneurInNakhinvest> entrepreneurPage = entrepreneurRepository.findAll(entrepreneurSpec, pageable);
        List<SearchResponseDto> entrepreneurResults = entrepreneurPage.stream()
                .map(entrepreneur -> new SearchResponseDto(
                        entrepreneur.getId(),
                        entrepreneur.getEntrepreneurTitle(lang),
                        "becoming-an-entrepreneur-in-nakchivan"
                ))
                .collect(Collectors.toList());


        Specification<HowWeHelp> howWeHelpSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<HowWeHelp> howWeHelpPage = howWeHelpRepository.findAll(howWeHelpSpec, pageable);
        List<SearchResponseDto> howWeHelpResults = howWeHelpPage.stream()
                .map(howWeHelp -> new SearchResponseDto(
                        howWeHelp.getId(),
                        howWeHelp.getAboutUsTitle(lang),
                        "how-do-we-help"
                ))
                .collect(Collectors.toList());


        Specification<Incentive> incentiveSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<Incentive> incentivePage = incentiveRepository.findAll(incentiveSpec, pageable);
        List<SearchResponseDto> incentiveResults = incentivePage.stream()
                .map(incentive -> new SearchResponseDto(
                        incentive.getId(),
                        incentive.getIncentiveTitle(lang),
                        "incentives"
                ))
                .toList();


        Specification<Project> projectSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<Project> projectPage = projectRepository.findAll(projectSpec, pageable);
        List<SearchResponseDto> projectResults = projectPage.stream()
                .map(project -> new SearchResponseDto(
                        project.getId(),
                        project.getProjectTitle(lang),
                        "projects"
                ))
                .collect(Collectors.toList());


        Specification<Question> questionSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<Question> questionPage = questionRepository.findAll(questionSpec, pageable);
        List<SearchResponseDto> questionResults = questionPage.stream()
                .map(question -> new SearchResponseDto(
                        question.getId(),
                        question.getQuestionTitle(lang),
                        "faq"
                ))
                .collect(Collectors.toList());


        Specification<Sector> sectorSpec = GlobalSearchSpecifications.searchInFieldsSector(keyword, lang);
        Page<Sector> sectorPage = sectorRepository.findAll(sectorSpec, pageable);
        List<SearchResponseDto> sectorResults = sectorPage.stream()
                .map(sector -> new SearchResponseDto(
                        sector.getId(),
                        sector.getSectorDescription(lang),
                        "sectors"
                ))
                .collect(Collectors.toList());


        Specification<WhoAreWe> whoAreWeSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<WhoAreWe> whoAreWePage = whoAreWeRepository.findAll(whoAreWeSpec, pageable);
        List<SearchResponseDto> whoAreWeResults = whoAreWePage.stream()
                .map(whoAreWe -> new SearchResponseDto(
                        whoAreWe.getId(),
                        whoAreWe.getWhoAreWeTitle(lang),
                        "who-are-we"
                ))
                .collect(Collectors.toList());


        Specification<WhyNakhinvest> whyNakhinvestSpec = GlobalSearchSpecifications.searchInFields(keyword, lang);
        Page<WhyNakhinvest> whyNakhinvestPage = whyNakhinvestRepository.findAll(whyNakhinvestSpec, pageable);
        List<SearchResponseDto> whyNakhinvestResults = whyNakhinvestPage.stream()
                .map(nakhinvest -> new SearchResponseDto(
                        nakhinvest.getId(),
                        nakhinvest.getWhyNakhinvestTitle(lang),
                        "why-nakhinvest"
                ))
                .collect(Collectors.toList());


        Map<String, Object> response = new HashMap<>();
        response.put("News", newsResults);
        response.put("AboutUs", aboutUsResults);
        response.put("Entrepreneurs", entrepreneurResults);
        response.put("HowWeHelp", howWeHelpResults);
        response.put("Incentive", incentiveResults);
        response.put("Project", projectResults);
        response.put("Question", questionResults);
        response.put("Sector", sectorResults);
        response.put("WhoAreWe", whoAreWeResults);
        response.put("WhyNakhinvest", whyNakhinvestResults);


        return response;
    }
}
