package com.smilegate.digerpostservice.domain.service;

import com.smilegate.digerpostservice.domain.PostVo;
import com.smilegate.digerpostservice.domain.persistence.PostType;

public interface PostService {

    PostVo create(String title, String content, PostType postType);

    PostVo loadById(Long id);

    PostVo update(String title, String content);
}
