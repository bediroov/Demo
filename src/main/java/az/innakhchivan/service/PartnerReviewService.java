package az.innakhchivan.service;

import az.innakhchivan.dto.request.PartnerReviewRequestDto;
import az.innakhchivan.dto.response.PartnerReviewResponseDto;
import az.innakhchivan.entity.PartnerReview;
import az.innakhchivan.repository.PartnerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerReviewService {
    private final PartnerReviewRepository partnerReviewRepository;

    public PartnerReviewResponseDto create(PartnerReviewRequestDto partnerReviewRequest) {
        PartnerReview partnerReview = new PartnerReview();

        partnerReview.setAzPartnerName(partnerReviewRequest.getAzPartnerName());
        partnerReview.setAzComment(partnerReviewRequest.getAzComment());
        partnerReview.setEnPartnerName(partnerReviewRequest.getEnPartnerName());
        partnerReview.setEnComment(partnerReviewRequest.getEnComment());
        partnerReview.setRuPartnerName(partnerReviewRequest.getRuPartnerName());
        partnerReview.setRuComment(partnerReviewRequest.getRuComment());

        partnerReviewRepository.save(partnerReview);

        return PartnerReviewResponseDto.builder()
                .id(partnerReview.getId())
                .build();
    }

    public List<PartnerReviewResponseDto> getAllPartnerReviews(String lang) {
        return partnerReviewRepository.findAll().stream()
                .map(x -> new PartnerReviewResponseDto(
                        x.getId(),
                        x.getPartnerFullName(lang),
                        x.getPartnerComment(lang),
                        x.getIconUrl()
                ))
                .collect(Collectors.toList());

    }
}
