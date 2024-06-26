package com.tqkien03.reactservice.client;

import com.tqkien03.reactservice.dto.UserSummary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "user-service", url = "${application.config.user-url}")
public interface UserFeignClient {
    @GetMapping("/summary-from-me/{userId}")
    Optional<UserSummary> getUserSummary(@PathVariable String userId, @RequestParam("from") String myId);
}
