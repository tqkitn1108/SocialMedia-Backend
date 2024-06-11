package com.tqkien03.userservice.controller;

import com.tqkien03.userservice.dto.FollowDto;
import com.tqkien03.userservice.dto.FriendDto;
import com.tqkien03.userservice.dto.ProfileDto;
import com.tqkien03.userservice.dto.UserSummary;
import com.tqkien03.userservice.model.User;
import com.tqkien03.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserSummary> getCurrentUser(Authentication authentication) {
        UserSummary userSummary = service.getCurrentUser(authentication.getName());
        return ResponseEntity.ok(userSummary);
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<?> getUserSummary(@PathVariable String userId, Authentication authentication) {
        return ResponseEntity.ok(service.getUserSummary(userId, authentication.getName()));
    }

    @GetMapping("/summary-from-me/{userId}")
    public ResponseEntity<?> getUserSummary(@PathVariable String userId,@RequestParam("from") String myId) {
        return ResponseEntity.ok(service.getUserSummary(userId, myId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserSummary>> searchUserByName(@RequestParam String key, Authentication authentication) {
        List<UserSummary> userSummary = service.searchUser(key, authentication.getName());
        return !userSummary.isEmpty() ?
                ResponseEntity.ok(userSummary) :
                ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserSummary> searchUserById(@PathVariable String id, Authentication authentication) {
        UserSummary userSummary = service.searchUserById(id, authentication.getName());
        return userSummary != null ?
                ResponseEntity.ok(userSummary) :
                ResponseEntity.noContent().build();
    }

//    @GetMapping("/{userId}/followers")
//    public ResponseEntity<List<FollowDto>> getFollowers(
//            @PathVariable String userId, Authentication authentication,
//            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        List<FollowDto> followers = service.getFollowers(userId, authentication.getName(), page, size);
//        return !followers.isEmpty() ? ResponseEntity.ok(followers) : ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/{userId}/followings")
//    public ResponseEntity<List<FollowDto>> getFollowings(
//            @PathVariable String userId, Authentication authentication,
//            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        List<FollowDto> followings = service.getFollowings(userId, authentication.getName(), page, size);
//        return !followings.isEmpty() ? ResponseEntity.ok(followings) : ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/{userId}/friends")
//    public ResponseEntity<List<FriendDto>> getFriends(
//            @PathVariable String userId, Authentication authentication,
//            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        List<FriendDto> friends = service.getFriends(userId, authentication.getName(), page, size);
//        return !friends.isEmpty() ? ResponseEntity.ok(friends) : ResponseEntity.noContent().build();
//    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<FollowDto>> getFollowers(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<FollowDto> followers = service.getFollowers(userId, page, size);
        return !followers.isEmpty() ? ResponseEntity.ok(followers) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity<List<FollowDto>> getFollowings(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<FollowDto> followings = service.getFollowings(userId, page, size);
        return !followings.isEmpty() ? ResponseEntity.ok(followings) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<FriendDto>> getFriends(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<FriendDto> friends = service.getFriends(userId, page, size);
        return !friends.isEmpty() ? ResponseEntity.ok(friends) : ResponseEntity.noContent().build();
    }

    @PostMapping("/add-friend/{id}")
    public ResponseEntity<?> addFriend(@PathVariable String id, Authentication authentication) {
        if (service.addFriend(id, authentication.getName()))
            return ResponseEntity.ok("Send friend request successfully");
        return ResponseEntity.badRequest().body("cannot follow");
    }

    @PostMapping("/accept-friend/{id}")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable String id, Authentication authentication) {
        if (service.acceptFriendRequest(id, authentication.getName()))
            return ResponseEntity.ok("Accept friend request successfully");
        return ResponseEntity.badRequest().body("Failed");
    }

    @PostMapping("/follow/{id}")
    public ResponseEntity<?> follow(@PathVariable String id, Authentication authentication) {
        if (service.follow(id, authentication.getName()))
            return ResponseEntity.ok("Follow successfully");
        return ResponseEntity.badRequest().body("cannot follow");
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDto profileDto, Authentication authentication) {
        service.updateProfile(profileDto, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/avatar")
    public ResponseEntity<?> updateAvatar(@RequestBody Map<String, String> updates, Authentication authentication) {
        service.updateAvatar(updates, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unfollow/{id}")
    public ResponseEntity<?> unfollow(@PathVariable String id, Authentication authentication) {
        if (service.unfollow(id, authentication.getName()))
            return ResponseEntity.ok("Unfollow successfully");
        return ResponseEntity.badRequest().body("cannot unfollow");
    }

    @DeleteMapping("/unfriend/{id}")
    public ResponseEntity<?> unfriend(@PathVariable String id, Authentication authentication) {
        if (service.unfriend(id, authentication.getName()))
            return ResponseEntity.ok("Unfriend successfully");
        return ResponseEntity.badRequest().body("cannot unfriend");
    }

}
