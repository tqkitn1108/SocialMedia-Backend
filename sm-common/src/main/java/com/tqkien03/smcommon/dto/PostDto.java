package com.tqkien03.smcommon.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostDto {
    private Integer id;
    private String owner;
    private String content;
    private List<MediaDto> medias;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private int reactsCount;
    private int commentsCount;
    private UserSummary user;
}
