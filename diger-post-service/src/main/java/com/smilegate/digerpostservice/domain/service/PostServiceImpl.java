package com.smilegate.digerpostservice.domain.service;

import com.smilegate.digerpostservice.domain.PostVo;
import com.smilegate.digerpostservice.domain.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostVo create(String title, String content) {
        return null;
    }

    @Override
    public PostVo loadById(Long id) {
        return null;
    }

    @Override
    public PostVo update(String title, String content) {
        return null;
    }
}
