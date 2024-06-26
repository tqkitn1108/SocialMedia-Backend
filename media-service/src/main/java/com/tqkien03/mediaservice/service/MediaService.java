package com.tqkien03.mediaservice.service;

import com.tqkien03.mediaservice.exception.ResourceNotFoundException;
import com.tqkien03.mediaservice.model.Media;
import com.tqkien03.mediaservice.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    public Media findMediaById(Integer id) {
        return mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media not found"));
    }

    public List<Media> findMediasByListId(List<Integer> mediaIds) {
        return mediaRepository.findAllById(mediaIds);
    }

    public List<Media> findMediasByOwnerId(String ownerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mediaRepository.findByOwnerId(ownerId, pageable);
    }
}
