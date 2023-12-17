package com.smilegate.digeruserservice.controller.usecase;

import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.controller.dto.response.UserResponse;
import com.smilegate.digeruserservice.controller.dto.response.UserTokenResponse;
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
    private final JwtAgent jwtAgent;

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
    public UserTokenResponse login(
            String loginId,
            String password
    ) {
        UserVo userVo = userService.loadByLoginIdAndPassword(loginId, password);
        return new UserTokenResponse(
                jwtAgent.provide(userVo.getId(), userVo.getLoginId())
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

}
