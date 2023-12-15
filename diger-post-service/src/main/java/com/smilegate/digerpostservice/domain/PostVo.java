package com.smilegate.digerpostservice.domain;

import com.smilegate.digerpostservice.domain.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostVo extends BaseEntity {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
