package com.smilegate.digerpostservice.domain.service;

import com.smilegate.digerpostservice.common.exception.ExceptionType;
import com.smilegate.digerpostservice.common.exception.PostServerException;
import com.smilegate.digerpostservice.domain.PostVo;
import com.smilegate.digerpostservice.domain.persistence.PostEntity;
import com.smilegate.digerpostservice.domain.persistence.PostRepository;
import com.smilegate.digerpostservice.domain.persistence.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostVo create(String title, String content, PostType postType) {
        PostEntity postEntity = new PostVo(
                title,
                content,
                postType
        ).fromVo();
        postRepository.save(postEntity);
        return postEntity.toVo();
    }

    @Override
    public PostVo loadById(Long id) {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if (postEntity.isEmpty()) throw new PostServerException(ExceptionType.E404);
        return postEntity.get().toVo();
    }

    @Override
    public PostVo update(String title, String content) {
        return null;
    }
}
