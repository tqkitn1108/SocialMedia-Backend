package com.tqkien03.feedservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Feed {
    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    @ManyToMany(mappedBy = "feeds")
    private Set<Post> posts;
}
