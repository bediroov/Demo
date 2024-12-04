package az.innakhchivan.controller;

import az.innakhchivan.dto.request.PartnerFeedbackRequestDto;
import az.innakhchivan.dto.response.PartnerFeedbackResponseDto;
import az.innakhchivan.service.PartnerFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/partner-feedback")
@RequiredArgsConstructor
public class PartnerFeedbackController {

    private final PartnerFeedbackService partnerFeedbackService;

    @PostMapping
    public ResponseEntity<PartnerFeedbackResponseDto> created (PartnerFeedbackRequestDto partnerFeedbackRequestDto) {
        PartnerFeedbackResponseDto responseDto = partnerFeedbackService.create(partnerFeedbackRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
