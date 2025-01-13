package az.innakhchivan.service;

import az.innakhchivan.dto.request.VideoGalleryRequestDto;
import az.innakhchivan.dto.response.VideoGalleryResponseDto;
import az.innakhchivan.entity.VideoGallery;
import az.innakhchivan.exception.VideoGalleryNotFoundException;
import az.innakhchivan.repository.VideoGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoGalleryService {
    private final VideoGalleryRepository videoGalleryRepository;


    public void addVideoUrl(VideoGalleryRequestDto videoGalleryRequestDto) {
        VideoGallery videoGallery = new VideoGallery();

        videoGallery.setVideoUrl(videoGalleryRequestDto.getVideoUrl());

        videoGalleryRepository.save(videoGallery);
    }

    public void updateVideoUrl(Long id, VideoGalleryRequestDto videoGalleryRequestDto) {
        VideoGallery videoGallery = videoGalleryRepository.findVideoGalleryById(id).orElseThrow(
                () -> new VideoGalleryNotFoundException("Video Gallery not found with id: " + id)
        );
        videoGallery.setVideoUrl(videoGalleryRequestDto.getVideoUrl());
        videoGalleryRepository.save(videoGallery);
    }

    public VideoGalleryResponseDto getVideoUrlById(Long id) {
        VideoGallery videoGallery = videoGalleryRepository.findVideoGalleryById(id).orElseThrow(
                () -> new VideoGalleryNotFoundException("Video Gallery not found with id: " + id)
        );

        return new VideoGalleryResponseDto(
                videoGallery.getId(),
                videoGallery.getVideoUrl()
        );
    }


    public List<VideoGalleryResponseDto> getAllVideos() {
        return videoGalleryRepository.findAllByOrderByIdAsc().stream()
                .map(video -> new VideoGalleryResponseDto(
                        video.getId(),
                        video.getVideoUrl()
                ))
                .collect(Collectors.toList());
    }


    public Void deleteVideoById(Long id) {
        Optional<VideoGallery> videoGallery = videoGalleryRepository.findVideoGalleryById(id);
        if (videoGallery.isPresent()) {
            videoGalleryRepository.delete(videoGallery.get());
            return null;
        }
        throw new VideoGalleryNotFoundException("VideoGallery not found with ID : " + id);
    }
}
