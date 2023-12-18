package com.smilegate.digeruserservice.controller.usecase;

import com.smilegate.digeruserservice.controller.dto.response.UserResponse;
import com.smilegate.digeruserservice.domain.UserVo;
import com.smilegate.digeruserservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserService userService;

    @Transactional
    public UserResponse create(
            String loginId,
            String password,
            String nickname
    ) {
        userService.validateDuplicatedLoginId(loginId);
        userService.validateDuplicatedNickname(nickname);
        UserVo userVo = userService.create(loginId, password, nickname);

        return new UserResponse(
                userVo.getId(),
                userVo.getLoginId(),
                userVo.getNickname(),
                userVo.getRole().name()
        );
    }

    @Transactional
    public UserResponse login(
            String loginId,
            String password
    ) {
        UserVo userVo = userService.loadByLoginIdAndPassword(loginId, password);
        return new UserResponse(
                userVo.getId(),
                userVo.getLoginId(),
                userVo.getNickname(),
                userVo.getRole().name()
        );
    }

    public UserResponse retrieve(Long id) {
        UserVo userVo = userService.loadById(id);
        return new UserResponse(
                userVo.getId(),
                userVo.getLoginId(),
                userVo.getNickname(),
                userVo.getRole().name()
        );
    }

    public UserResponse retrieveByLoginId(String loginId) {
        UserVo userVo = userService.loadByLoginId(loginId);
        return new UserResponse(
                userVo.getId(),
                userVo.getLoginId(),
                userVo.getNickname(),
                userVo.getRole().name()
        );
    }

}
