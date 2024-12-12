package az.innakhchivan.controller;

import az.innakhchivan.dto.request.PartnerReviewRequestDto;
import az.innakhchivan.dto.response.PartnerReviewResponseDto;
import az.innakhchivan.entity.PartnerReview;
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
    public ResponseEntity<Void> createPartnerReview(@RequestBody PartnerReviewRequestDto partnerReviewRequestDto) {
        partnerReviewService.create(partnerReviewRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedPartnerReview(@PathVariable Long id , @RequestBody PartnerReviewRequestDto partnerReviewRequestDto) {
         partnerReviewService.updatePartnerReview(id, partnerReviewRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PartnerReviewResponseDto>> getPartnerReviews(@RequestParam(required = false, defaultValue = "az") String lang) {
        List<PartnerReviewResponseDto> responseDtoList = partnerReviewService.getAllPartnerReviews(lang);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PartnerReview>> getAll() {
        List<PartnerReview> responseDtoList = partnerReviewService.getAll();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedPartnerReview(@PathVariable Long id) {
        partnerReviewService.deletePartnerReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PartnerReview> getPartnerReviewById(@PathVariable Long id) {
        PartnerReview responseDtoList = partnerReviewService.getPartnerReviewById(id);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

}
