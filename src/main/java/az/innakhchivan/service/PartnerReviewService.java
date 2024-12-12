package az.innakhchivan.service;

import az.innakhchivan.dto.request.PartnerReviewRequestDto;
import az.innakhchivan.dto.response.PartnerReviewResponseDto;
import az.innakhchivan.entity.PartnerReview;
import az.innakhchivan.exception.PartnerReviewNotFoundException;
import az.innakhchivan.repository.PartnerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerReviewService {
    private final PartnerReviewRepository partnerReviewRepository;

    public void create(PartnerReviewRequestDto partnerReviewRequest) {
        PartnerReview partnerReview = new PartnerReview();

        partnerReview.setAzPartnerName(partnerReviewRequest.getAzPartnerName());
        partnerReview.setAzComment(partnerReviewRequest.getAzComment());
        partnerReview.setEnPartnerName(partnerReviewRequest.getEnPartnerName());
        partnerReview.setEnComment(partnerReviewRequest.getEnComment());
        partnerReview.setRuPartnerName(partnerReviewRequest.getRuPartnerName());
        partnerReview.setRuComment(partnerReviewRequest.getRuComment());

        partnerReviewRepository.save(partnerReview);
    }


    public void updatePartnerReview(Long id, PartnerReviewRequestDto partnerReviewRequest) {
        PartnerReview partner = partnerReviewRepository.findById(id).orElseThrow(
                () -> new PartnerReviewNotFoundException("Partner Review Not Found with ID: " + id)
        );

        partner.setAzPartnerName(partnerReviewRequest.getAzPartnerName());
        partner.setAzComment(partnerReviewRequest.getAzComment());
        partner.setEnPartnerName(partnerReviewRequest.getEnPartnerName());
        partner.setEnComment(partnerReviewRequest.getEnComment());
        partner.setRuPartnerName(partnerReviewRequest.getRuPartnerName());
        partner.setRuComment(partnerReviewRequest.getRuComment());

        partnerReviewRepository.save(partner);

    }


    public PartnerReview getPartnerReviewById(Long id) {
        PartnerReview partner = partnerReviewRepository.findById(id).orElseThrow(
                () -> new PartnerReviewNotFoundException("Partner Review Not Found with ID: " + id)
        );

        return PartnerReview.builder()
                .id(partner.getId())
                .azPartnerName(partner.getAzPartnerName())
                .azComment(partner.getAzComment())
                .enPartnerName(partner.getEnPartnerName())
                .enComment(partner.getEnComment())
                .ruPartnerName(partner.getRuPartnerName())
                .ruComment(partner.getRuComment())
                .iconUrl(partner.getIconUrl())
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

    public List<PartnerReview> getAll() {
        return partnerReviewRepository.findAll().stream()
                .map(x -> new PartnerReview(
                        x.getId(),
                        x.getAzPartnerName(),
                        x.getAzComment(),
                        x.getEnPartnerName(),
                        x.getEnComment(),
                        x.getRuPartnerName(),
                        x.getRuComment(),
                        x.getIconUrl()
                ))
                .collect(Collectors.toList());

    }


    public void deletePartnerReview(Long id) {
        PartnerReview partner = partnerReviewRepository.findById(id).orElseThrow(
                () -> new PartnerReviewNotFoundException("Partner Review Not Found with ID: " + id)
        );
        partnerReviewRepository.delete(partner);
    }
}
