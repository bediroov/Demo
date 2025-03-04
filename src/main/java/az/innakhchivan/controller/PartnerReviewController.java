package az.innakhchivan.controller;

import az.innakhchivan.dto.request.PartnerReviewRequestDto;
import az.innakhchivan.dto.response.PartnerReviewResponseDto;
import az.innakhchivan.service.PartnerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/partner-review")
@RequiredArgsConstructor
public class PartnerReviewController {
    private final PartnerReviewService partnerReviewService;

    @PostMapping
    public ResponseEntity<PartnerReviewResponseDto> createPartnerReview(@RequestBody PartnerReviewRequestDto partnerReviewRequestDto) {
        PartnerReviewResponseDto partnerReviewResponseDto = partnerReviewService.create(partnerReviewRequestDto);
        return new ResponseEntity<>(partnerReviewResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PartnerReviewResponseDto>> getPartnerReviews(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<PartnerReviewResponseDto> responseDtoList = partnerReviewService.getAllPartnerReviews(lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }
}
