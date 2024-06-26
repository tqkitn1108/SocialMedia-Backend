package com.tqkien03.feedservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSummary {
    private String id;
    private String fullName;
    private String avatarUrl;
    private boolean isFollowing;
    private boolean isFollower;
    private boolean isFriend;
    private Integer followersCount;
    private Integer friendsCount;
}
