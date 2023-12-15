package com.smilegate.digeruserservice.domain.service;

import com.smilegate.digeruserservice.auth.JwtAgent;
import com.smilegate.digeruserservice.common.PasswordEncryptor;
import com.smilegate.digeruserservice.dto.response.UserResponse;
import com.smilegate.digeruserservice.dto.response.UserTokenResponse;
import com.smilegate.digeruserservice.domain.UserVo;
import com.smilegate.digeruserservice.domain.persistence.Role;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtAgent jwtAgent;
    private final PasswordEncryptor passwordEncryptor;

    @Transactional
    public UserResponse create(
            String loginId,
            String password,
            String nickname
    ) {
        validateDuplicatedLoginId(loginId);
        validateDuplicatedNickname(nickname);


        UserVo userVo = userRepository.save(
                new UserVo(
                        loginId,
                        passwordEncryptor.onlyHash(password),
                        nickname,
                        Role.USER
                ).fromVo()
        ).toDto();

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
        loadByLoginIdAndPassword(loginId, password);
        return new UserTokenResponse(
                jwtAgent.provide(loginId)
        );
    }

    public UserResponse loadUserInfo(Long id) {
        UserVo userVo = loadById(id);
        return new UserResponse(
                userVo.getId(),
                userVo.getLoginId(),
                userVo.getNickname(),
                userVo.getRole().name()
        );
    }

    private UserVo loadById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (incorrectPassword(userEntity)) throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        return userEntity.get().toDto();
    }

    private UserVo loadByLoginIdAndPassword(
            String loginId,
            String password
    ) {
        Optional<UserEntity> userEntity = userRepository.findByLoginId(loginId);
        if (userEntity.isEmpty()) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        } else if (incorrectPassword(password, userEntity.get().toDto())) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 올바르지 않습니다.");
        }
        return userEntity.get().toDto();
    }

    private boolean incorrectPassword(String password, UserVo userVo) {
        return !passwordEncryptor.matches(
                password,
                userVo.getPassword()
        );
    }

    private static boolean incorrectPassword(Optional<UserEntity> userEntity) {
        return userEntity.isEmpty();
    }

    private void validateDuplicatedLoginId(String loginId) {
        if (userRepository.findByLoginId(loginId).isPresent()) {
            throw new IllegalArgumentException("중복된 로그인 아이디입니다.");
        }
    }

    private void validateDuplicatedNickname(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
    }
}
