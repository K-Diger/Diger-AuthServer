package com.smilegate.digerpostservice.domain.service;

import com.smilegate.digerpostservice.domain.PostVo;

public interface PostService {

    PostVo create(String title, String content);

    PostVo loadById(Long id);

    PostVo update(String title, String content);
}
