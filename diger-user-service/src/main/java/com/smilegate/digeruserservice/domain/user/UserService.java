package com.smilegate.digeruserservice.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void create(
            String loginId,
            String password,
            String nickname,
            Role role
    ) {
        userRepository.save(new UserVo(
                loginId,
                password,
                nickname,
                role
        ).fromVo());
    }
}
