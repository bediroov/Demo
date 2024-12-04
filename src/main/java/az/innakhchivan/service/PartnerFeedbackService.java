package az.innakhchivan.service;

import az.innakhchivan.dto.request.PartnerFeedbackRequestDto;
import az.innakhchivan.dto.response.PartnerFeedbackResponseDto;
import az.innakhchivan.entity.PartnerFeedback;
import az.innakhchivan.repository.PartnerFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerFeedbackService {
    private final PartnerFeedbackRepository partnerFeedbackRepository;

    public PartnerFeedbackResponseDto create (PartnerFeedbackRequestDto partnerFeedbackRequestDto) {
        PartnerFeedback partnerFeedback = new PartnerFeedback();
        partnerFeedback.setPartnerFullName(partnerFeedbackRequestDto.getPartnerFullName());
        partnerFeedback.setComment(partnerFeedbackRequestDto.getComment());
        partnerFeedbackRepository.save(partnerFeedback);

        return new PartnerFeedbackResponseDto(
                partnerFeedback.getId(),
                partnerFeedback.getPartnerFullName(),
                partnerFeedback.getComment()

        );
    }
}
