package com.smilegate.digerpostservice.domain.post;

import com.smilegate.digerpostservice.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostEntity extends BaseEntity {
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(value = EnumType.STRING)
    private PostType postType;
}
