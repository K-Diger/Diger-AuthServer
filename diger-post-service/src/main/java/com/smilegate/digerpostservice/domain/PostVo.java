package com.smilegate.digerpostservice.domain;

import com.smilegate.digerpostservice.domain.persistence.PostEntity;
import com.smilegate.digerpostservice.domain.persistence.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostVo {
    private Long id;
    private String title;
    private String content;
    private PostType postType;

    public PostVo(String title, String content, PostType postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
    }

    public PostEntity fromVo() {
        return new PostEntity(
                id,
                title,
                content,
                postType
        );
    }
}


